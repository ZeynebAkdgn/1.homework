package tr.edu.gtu.cse222.hw1;

/**
 * Konuk rezervasyon bilgilerinin tutuldugu sinif
 */
public class Reservation {

    /**
     * Reservation un yapildigi odayi tutar.
     */
    private Room room;
    
    /**
     * Check-in yapilip yapilmadigini belirtir
     */
    private boolean checkInMade;

    /**
     *
     * @param room
     */
    public Reservation(Room room) {
        this.room = room;
        this.checkInMade = false;
    }

    /**
     * Reservasyonu iptal eder
     */
    public void cancel() {
        if(room.isEmpty())
        {
            throw new UnsupportedOperationException("Oda reserve edilmemis.");
        }
        room.cancelRoomReservation();
        System.out.println(String.format("Reservation for room '%s' cancelled.",room.getRoomNo()));
        room = null;
    }


    /**
     * Oda reservasyonu icin checkin islemi yapar
     */
    public void checkIn(){
        if(room.isEmpty())
        {
            throw new UnsupportedOperationException("Oda reserve edilmemis.");
        }
        if(checkInMade)
        {
            throw new UnsupportedOperationException("zaten check in yapilmis.");
        }
        this.checkInMade = true;
        System.out.println(String.format("Oda %s icin checkin yapildi.",room.getRoomNo()));
    }

    /**
     * Reservasyonun yapildigi oda bilgisini doner
     *
     * @return reservasyonun yapildigi oda
     */
    public Room getRoom() {
        return room;
    }

    /**
     *
     * @return checkin yapilmis mi yapilmamis mi bilgisi
     */
    public boolean isCheckInMade() {
        return checkInMade;
    }

}
