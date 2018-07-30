package tr.edu.gtu.cse222.hw1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeynep.basibuyuk on 3/4/2018.
 * Hotel nesnesi olusturan factory
 */
public class HotelFactory {
    public static Hotel createHotelWithTenRoom()
    {
        // Buradaki hotel nesnesi onemli, cunku tum uygulamada bu hotel nesnesi kullaniyor.
        return new Hotel(createRooms());
    }

    /**
     * Otel nesnesi icin oda listesi olusturur
     *
     * @return 10 adet oda iceren liste
     */
    private static List<Room> createRooms( ) {
        List <Room> roomsList = new ArrayList<>();

        roomsList.add(new Room("101",1));
        roomsList.add(new Room("102",2));
        roomsList.add(new Room("103",1));
        roomsList.add(new Room("104",1));
        roomsList.add(new Room("105",1));
        roomsList.add(new Room("106",1));
        roomsList.add(new Room("107",1));
        roomsList.add(new Room("108",1));
        roomsList.add(new Room("109",2));
        roomsList.add(new Room("110",2));

        return roomsList;
    }
}
