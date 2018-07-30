package tr.edu.gtu.cse222.hw1;

import org.junit.Test;

import static org.junit.Assert.*;


public class ReceptionistTest {
    Hotel hotel = HotelFactory.createHotelWithTenRoom();


    @Test
    public void testBookARoom() throws Exception {
        IGuest guest = new Guest(hotel, "junit");
        IReceptionist receptionist = new Receptionist(hotel);
        Room room = receptionist.bookARoom(guest);
        assertFalse(room.isEmpty());
        assertEquals(guest, room.getGuest());
    }

    @Test
    public void testCheckin() throws Exception {

        IReceptionist receptionist = new Receptionist(hotel);
        IGuest guest = new Guest(hotel, "junit");
        Room bookedRoom = receptionist.bookARoom(guest);
        assertFalse(guest.getReservation().isCheckInMade());
        receptionist.checkin(bookedRoom);
        assertTrue(guest.getReservation().isCheckInMade());
    }

    @Test
    public void testCheckout() throws Exception {
        IReceptionist receptionist = new Receptionist(hotel);
        IGuest guest = new Guest(hotel, "junit");
        Room room = receptionist.bookARoom(guest);
        assertFalse(guest.getReservation().isCheckInMade());
        receptionist.checkin(room);
        receptionist.checkout(room);
        assertTrue(room.isEmpty());
    }
}