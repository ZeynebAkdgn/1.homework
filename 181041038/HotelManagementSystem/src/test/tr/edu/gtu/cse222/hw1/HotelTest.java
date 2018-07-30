package tr.edu.gtu.cse222.hw1;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class HotelTest {


    @Test
    public void testTotalCapacity() throws Exception {
        Hotel hotel = HotelFactory.createHotelWithTenRoom();
        assertEquals(10, hotel.totalCapacity());
    }

    @Test
    public void testTotalCapacityDecreases() throws Exception {
        Hotel hotel = HotelFactory.createHotelWithTenRoom();
        assertEquals(10, hotel.totalCapacity());
        assertEquals(10, hotel.getAvailableNumberOfRooms());

        IGuest guest = new Guest(hotel,"junit");
        guest.bookARoom();
        assertEquals(9, hotel.getAvailableNumberOfRooms());
    }

    @Test
    public void testTotalCapacityIncreases() throws Exception {
        Hotel hotel = HotelFactory.createHotelWithTenRoom();
        assertEquals(10, hotel.totalCapacity());
        assertEquals(10, hotel.getAvailableNumberOfRooms());

        IGuest guest = new Guest(hotel,"junit");
        guest.bookARoom();
        assertEquals(9, hotel.getAvailableNumberOfRooms());

        guest.cancelReservation();
        assertEquals(10, hotel.getAvailableNumberOfRooms());
    }

    @Test
    public void testFindAnEmptyRoom() throws Exception {
        Hotel hotel = HotelFactory.createHotelWithTenRoom();
        Room room = hotel.findAnEmptyRoom();
        assertTrue(room.isEmpty());
    }

    @Test
    public void testFindOpenReservationsEmpty() throws Exception {
        Hotel hotel = HotelFactory.createHotelWithTenRoom();
        List<Room> roomsWithOpenReservations = hotel.findOpenReservations();
        assertEquals(0,roomsWithOpenReservations.size());
    }

    @Test
    public void testFindOpenReservationsIncreases() throws Exception {
        Hotel hotel = HotelFactory.createHotelWithTenRoom();
        assertEquals(0,hotel.findOpenReservations().size());
        IGuest guest = new Guest(hotel,"junit");
        Room reservedRoom = guest.bookARoom();
        assertEquals(1,hotel.findOpenReservations().size());
        assertEquals(reservedRoom, hotel.findOpenReservations().get(0));
    }

    @Test
    public void testFindOpenReservationsIgnoresCheckedInRooms() throws Exception {
        Hotel hotel = HotelFactory.createHotelWithTenRoom();
        assertEquals(0,hotel.findOpenReservations().size());
        IGuest guest = new Guest(hotel,"junit");
        Room reservedRoom = guest.bookARoom();
        assertEquals(1,hotel.findOpenReservations().size());
        Reservation reservation = guest.getReservation();
        reservation.checkIn();
        assertEquals(0,hotel.findOpenReservations().size());
        assertEquals(9, hotel.getAvailableNumberOfRooms());
    }

    @Test
    public void testFindCheckInMadeRooms() throws Exception {
        Hotel hotel = HotelFactory.createHotelWithTenRoom();
        assertEquals(0,hotel.findCheckInMadeRooms().size());
        IGuest guest = new Guest(hotel,"junit");
        Room reservedRoom = guest.bookARoom();
        assertEquals(1,hotel.findOpenReservations().size());
        assertEquals(0,hotel.findCheckInMadeRooms().size());
        Reservation reservation = guest.getReservation();
        reservation.checkIn();
        assertEquals(1,hotel.findCheckInMadeRooms().size());
    }
}