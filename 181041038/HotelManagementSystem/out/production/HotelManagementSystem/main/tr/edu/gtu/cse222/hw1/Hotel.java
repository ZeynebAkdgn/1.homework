package tr.edu.gtu.cse222.hw1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Uygulamanin tamaminda kullanilan hotel sinifi.
 * Hotelde tanimli odalari icerir.
 * Ayrica bir cok yardimci metod buradadir.
 */
public class Hotel {

    /**
     * Hotelde tanimli odalar
     */
    private List<Room> rooms;


    public Iterable<Room> getRooms(){return rooms;}

    /**
     * Hotel nesnesi olusturulurken cagrilan constructor
     *
     * @param rooms otelde tanimli odalar
     */
    public Hotel(List<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * Otel kapasite bilgisini verir
     *
     * @return oteldeki tanimli oda sayisi
     */
    public int totalCapacity(){
        return rooms.size();
    }

    /**
     * Henuz ayrilmamis/rezerve edilmemis ilk odayi geri doner
     *
     * @return rezerve edilmemis bir oda verir
     */
    public Room findAnEmptyRoom(){

        // Hotelde tanimli odalari tek tek kontrol et ve ilk bos odayi don
        for (int i=0; i<rooms.size();i++) {
            Room room = rooms.get(i);
            if (room.isEmpty()) {
                return room;
            }
        }
        return null; // bos oda yok :(
    }

    /**
     * Bos oda sayisini hesaplar
     *
     * @return bos oda sayisini verir
     */
    public int getAvailableNumberOfRooms(){
         int availableNumberOfRooms=0;
        for (int i=0; i<rooms.size();i++) {
            Room room = rooms.get(i);
            if (room.isEmpty()) {
                availableNumberOfRooms++;
            }
        }
        return availableNumberOfRooms;
    }

    /**
     * Rezervasyon yapmis ama henuz checkin yapilmamis oda listesini bul
     *
     * @return Rezervasyon yapmis ama henuz checkin yapilmamis oda listesi
     */
    public List<Room> findOpenReservations() {

        List<Room> openReservations = new ArrayList<>();

        for (int i=0; i<rooms.size();i++) {
            Room room = rooms.get(i);
            if (!room.isEmpty()) {

                Reservation reservation = room.getGuest().getReservation();
                if (!reservation.isCheckInMade()) {
                    openReservations.add(room); // Bu reservasyon olusmus ama henuz checkın yapılmamıs bır odadır
                }
            }
        }

        return openReservations;
    }

    /**
     * Checkin yapilmis oda listesini bul
     *
     * @return Checkin yapilmis oda listesi
     */
    public List<Room> findCheckInMadeRooms() {

        List<Room> checkInMadeRooms = new ArrayList<>();

        for (int i=0; i<rooms.size();i++) {
            Room room = rooms.get(i);
            if (!room.isEmpty()) {

                Reservation reservation = room.getGuest().getReservation();
                if (reservation.isCheckInMade()) {
                    checkInMadeRooms.add(room); // Bu reservasyon olusmus ama henuz checkın yapılmamıs bır odadır
                }
            }
        }

        return checkInMadeRooms;
    }


    static final String cvsSplitBy = ",";
    public void save()  {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("reservations.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (Room room : rooms)
        {
            if(room.isEmpty()) continue;
            sb.append(room.getRoomNo()); sb.append(cvsSplitBy);
            sb.append(room.getBedCount()); sb.append(cvsSplitBy);
            sb.append(room.getGuest().getName()); sb.append(cvsSplitBy);
            sb.append(room.getGuest().getReservation().isCheckInMade()); sb.append(cvsSplitBy);
            sb.append(System.lineSeparator());
        }

        pw.write(sb.toString());
        pw.close();
        System.out.println("Saving done!");
    }

    public void load()  {
        String csvFile = "reservations.csv";
        BufferedReader br = null;
        String line = "";
        try {

            br = new BufferedReader(new FileReader(new File(csvFile)));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] lineArray = line.split(cvsSplitBy);
                String guestName = lineArray[2];
                Guest guest = new Guest(this,guestName);
                guest.buildReservationFromCsv(lineArray);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Room findRoom(String roomNumber)
    {
        for(Room room : rooms)
        {
            if(room.getRoomNo().equals(roomNumber)) return room;
        }
        return null;
    }
}

