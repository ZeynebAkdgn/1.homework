package tr.edu.gtu.cse222.hw1;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class ReservationTest {

    Hotel hotel = HotelFactory.createHotelWithTenRoom();
    @Test(expected = UnsupportedOperationException.class)
    public void testTryCancelUnreservedRoom() throws Exception {
        Room room = new Room("101",1);
        Reservation reservation = new Reservation(room);
        reservation.cancel();
    }

    @Test
    public void testCancel() throws Exception {
        IGuest guest = new Guest(hotel,"junit");
        Room room = guest.bookARoom();
        assertTrue(room.getGuest() == guest);
        Reservation reservation = guest.getReservation();
        reservation.cancel();
        assertTrue(room.isEmpty());
    }

    @Test
    public void testCkeckIn() throws Exception {
        IGuest guest = new Guest(hotel,"junit");
        Room room = guest.bookARoom();
        assertTrue(room.getGuest() == guest);
        Reservation reservation = guest.getReservation();
        assertFalse(reservation.isCheckInMade());
        reservation.checkIn();
        assertTrue(reservation.isCheckInMade());
    }

    @Test
    public void testConstructor() throws Exception {
        Room room = new Room("101",1);
        Reservation reservation = new Reservation(room);
        assertEquals(room, reservation.getRoom());
    }
}