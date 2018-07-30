package tr.edu.gtu.cse222.hw1;

/**
 * Oteldeki oda bilgilerinin tutuldugu sinif
 */
public class Room {

    /**
     * Oda numarasi
     */
    private String roomNo;

    /**
     * Odadaki yatak sayisi
     */
    private int bedCount;

    /**
     * Odayi rezerve eden konuk bilgisi
     */
    private IGuest guest;

    /**
     * Oda tanimlanirken cagrilan constructor metodu
     *
     * @param theRoomNo oda numarasi
     * @param theBedCount odadaki yatak sayisi
     */
    public Room(String theRoomNo, int theBedCount) {
        roomNo = theRoomNo;
        bedCount = theBedCount;
        guest = null;
    }

    /**
     * Oda Bos mu kontrolu yapan metot
     *
     * @return odanin bos olup olmadigi bilgisi
     */
    public boolean isEmpty() {
        if (guest == null) {
            return true;
        } else {
            return false;
        }
        // return guest == null;
    }

    /**
     * Oda rezervasyonu iptal edilirken cagrilan metot
     */
    public void cancelRoomReservation() {
        guest = null;
    }

    /**
     *
     * @return oda numarasi
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * Odayla iliskili konuk bilgisini veren metot
     *
     * @return odadaki konuk ile ilgili Guest nesnesi
     */
    public IGuest getGuest() {
        return guest;
    }

    /**
     * Odaya konuk iliskilendirmek icin cagrilan metot
     *
     * @param guest odayla iliskilendirilecek konuk
     */
    public void setGuest(IGuest guest) {
        if(getGuest() != null) {
            throw new UnsupportedOperationException("Oda zaten rezerve edilmis");
        }
        this.guest = guest;
    }

    public int getBedCount() {
        return bedCount;
    }

}
