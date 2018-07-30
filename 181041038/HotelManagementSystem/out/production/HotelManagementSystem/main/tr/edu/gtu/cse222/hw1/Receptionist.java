package tr.edu.gtu.cse222.hw1;

import java.util.List;
import java.util.Scanner;

/**
 * Receptionist sinifi - User sinifindan turer ve IReceptionist interface'ini uygular
 *
 */
public class Receptionist extends User implements IReceptionist {

    private Hotel hotel;

    /**
     * Bir Receptionist nesnesi olusturan constructor
     *
     * @param hotel uygulamanin tamaminda kullanilan hotel nesnesinin referansi
     */
    public Receptionist(Hotel hotel){
        super(hotel); // ust sinif Userin constructor'ina parametre geciyoruz
        this.hotel = hotel;
    }

    public Hotel getHotel(){return hotel;}

    @Override
    public Room bookARoom(IGuest guest) {
        Room room = guest.bookARoom();
        return room;
    }

    @Override
    public boolean checkin(Room room){
        room.getGuest().getReservation().checkIn();
        return true;
    }


    @Override
    public boolean checkout(Room room){
        room.getGuest().cancelReservation();
        return true;
    }
}
