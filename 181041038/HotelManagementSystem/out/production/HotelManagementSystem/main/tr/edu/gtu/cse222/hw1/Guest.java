package tr.edu.gtu.cse222.hw1;

/**
 * Konuk bilgilerinin tutuldugu sinif - User sinifindan turer ve IGuest interface'ini uygular
 */
public class Guest extends User implements IGuest {

    /**
     * Konuk adi
     */
    private String name;

    /**
     * Konugun ilgili rezervasyonu - bookARoom icinde olusturulur ve buraya atanir
     */
    private Reservation reservation;

    /**
     *
     * @param hotel
     * @param name
     */
    public Guest(Hotel hotel, String name) {
        super(hotel);
        this.name = name;
        this.reservation = null; // henuz reservasyon yapilmadi anlaminda
    }

    @Override
    public Room bookARoom(){
        // Zaten rezervasyonu varsa, bir daha rezervasyona izin verme
        if(reservation != null) {
            System.out.println("Zaten rezervasyonunuz var!");
            return null;
        }

        Room room = hotel.findAnEmptyRoom();
        if(room == null) {
            System.out.println("Hic bos oda yok!");
            return null;
        }

        reservation = new Reservation(room);
        room.setGuest(this); // icinde bulundugum nesne : this
        System.out.println(String.format("Room %s is booked for guest %s.", room.getRoomNo(), this.getName()));

        return room;
    }

    @Override
    public boolean cancelReservation(){

        if (reservation == null) {
            System.out.println("Reservasyonunuz yok. Once rezervasyon yapiniz.");
            return false; // cancel basarısız
        } else {
            reservation.cancel();
            reservation = null;
            return true; // cancel basarılı
        }
    }

    /**
     *
     * @return konuk adi
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return konugun ilgili rezervasyonu
     */
    public Reservation getReservation() {
        return reservation;
    }

    public void buildReservationFromCsv(String[] lineArray)
    {
        String roomNumber = lineArray[0];
        boolean isCheckedIn = lineArray[3].equalsIgnoreCase("true");
        Room room = hotel.findRoom(roomNumber);
        room.setGuest(this);
        reservation = new Reservation(room);
        if(isCheckedIn) reservation.checkIn();
    }

}

