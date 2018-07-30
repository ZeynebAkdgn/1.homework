package tr.edu.gtu.cse222.hw1;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class GuestTest {

    Hotel hotel = HotelFactory.createHotelWithTenRoom();
    @Test
    public void testConstructor() throws Exception {
        String guestName = "junit";
        IGuest guest = new Guest(hotel, guestName);
        assertEquals(guestName, guest.getName());
        assertNull(guest.getReservation());
        Room room = guest.bookARoom();
    }

    @Test
    public void testBookARoom() throws Exception {
        String guestName = "junit";
        IGuest guest = new Guest(hotel, guestName);
        Room room = guest.bookARoom();
        Reservation reservation = guest.getReservation();
        assertNotNull(reservation);
        assertEquals(reservation.getRoom(), room);
        assertFalse(room.isEmpty());
    }

    @Test
    public void testCancelReservation() throws Exception {
        String guestName = "junit";
        IGuest guest = new Guest(hotel, guestName);
        Room room = guest.bookARoom();
        assertEquals(room.getGuest(), guest);
        assertFalse(room.isEmpty());

        guest.cancelReservation();
        assertTrue(room.isEmpty());
        assertNull(guest.getReservation());
    }


}