package tr.edu.gtu.cse222.hw1;

import org.junit.Test;

import static org.junit.Assert.*;


public class RoomTest {

    @Test
    public void testConstructor() throws Exception {
        Room room  = new Room("101",1);
        String expectedRoomNo="101";
        assertEquals(expectedRoomNo, room.getRoomNo());
        int expectedBedCount=1;
        assertEquals(expectedBedCount, room.getBedCount());
    }

    @Test
    public void testIsEmpty() throws Exception {
        Hotel hotel = HotelFactory.createHotelWithTenRoom();
        Room room = hotel.findAnEmptyRoom();
        //baslangicta odanin bos oldugunu kontrol et
        assertTrue(room.isEmpty());

        String guestName="junit";
        Guest guest = new Guest(hotel, guestName);
        room.setGuest(guest);
        //Odanin artik bos olmadigini kontrol et
        assertFalse(room.isEmpty());
    }

    @Test
    public void testCancelRoomReservation() throws Exception {
        Hotel hotel = HotelFactory.createHotelWithTenRoom();
        Room room = hotel.findAnEmptyRoom();
        //baslangicta odanin bos oldugunu kontrol et
        assertTrue(room.isEmpty());

        String guestName="junit";
        Guest guest = new Guest(hotel, guestName);
        room.setGuest(guest);
        assertNotNull(room.getGuest());
        //Odanin bosaltildigini kontrol et
        room.cancelRoomReservation();
        assertNull(room.getGuest());
    }

}