package tr.edu.gtu.cse222.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 * Uygulamanin ana sinifi - uygulamadaki tek main metodu buradadir.
 */
public class MainHotelManagementSystemClass {

    /**
     *
     */
    private static Scanner userInputScanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("***************************************");
        System.out.println("***************************************\n");
        System.out.println("HOTEL MANAGEMENT SYSTEM");
        System.out.println("CSE 222 - 2018 Spring - Homework 1\n");
        System.out.println("***************************************");
        System.out.println("***************************************\n");

        // Tum uygulamada kullanilacak hotel nesnesi.
        Hotel hotel = HotelFactory.createHotelWithTenRoom();
        // ANA MENU
        mainloop: while(true) {

            System.out.println("Kullanici Tipi: ");


            System.out.println("***************************************");
            System.out.println("Select User Type");
            System.out.println("1-) Guest");
            System.out.println("2-) Receptionist");
            System.out.println("3-) Exit");
            String userType = userInputScanner.next();
            switch (userType) {
                case "1":
                    guestOperation(hotel);
                    break;
                case "2":
                    receptionistOperation(hotel);
                    break;
                case "3":
                    userInputScanner.close();
                    break mainloop;
                default:
                    System.out.println("Invalid user type!");
                    break;
            }

        }

         // isim bitti scanneri kapat
    }


    /**
     * Konuk Operasyonlarinin Yonetildigi Metod
     *
     * @param name Konuk adi
     * @param hotel uygulamanin tamaminda kullanilan hotel nesnesinin referansi
     */
    private static void guestOperation(Hotel hotel) {

        System.out.println("Adinizi giriniz: ");
        String name = userInputScanner.next();
        Guest currentGuest = new Guest( hotel, name);

        // GUEST MENU
        label:
        while (true) {

            System.out.println("***************************************");
            System.out.println("GUEST OPERATIONS ----------------------");
            System.out.println("1-) Book a room");
            System.out.println("2-) Cancel reservation");
            System.out.println("0-) Geri don");
            System.out.println("Ne yapmak istiyorsunuz? ");
            System.out.println("***************************************");

            // Kullanici yukaridaki menudeki secenekleri basindaki Noya gore seciyor. 1,2 ya da 0 seklinde.
            int selection = userInputScanner.nextInt();

            switch (selection) {
                case 1:
                    currentGuest.bookARoom();
                    break;
                case 2:
                    currentGuest.cancelReservation();
                    break;
                case 0:
                    // Menu loopdan cik yani bir onceki menuye geri don
                    break label;
                default:
                    // Hatali - tanimli olmayan kullanici
                    System.out.println("Hatali giris. 0,1,2 seciniz.");
                    break;
            }

        }

    }

    /**
     * Recepsiyonist Operasyonlarin Yapildigi Metod
     *
     * @param hotel uygulamanin tamaminda kullanilan hotel nesnesinin referansi
     */
    private static void receptionistOperation(Hotel hotel) {

        Receptionist currentReceptionist = new Receptionist(hotel);

        label:
        while (true) {

            System.out.println("***************************************");
            System.out.println("RECEPTIONIST OPERATIONS ----------------------");
            System.out.println("1-) Book a room");
            System.out.println("2-) Checkin");
            System.out.println("3-) Checkout");
            System.out.println("4-) Save to csv");
            System.out.println("5-) Load from csv");
            System.out.println("0-) Back to main menu");
            System.out.println("Ne yapmak istiyorsunuz? ");
            System.out.println("***************************************");

            // Kullanici yukaridaki menudeki secenekleri basindaki Noya gore seciyor. 1,2,3 ya da 0 seklinde.
            int selection = userInputScanner.nextInt();

            switch (selection) {
                case 1:
                    System.out.println("Reseptionist Reservation");
                    System.out.println("Input guest name: ");
                    Scanner scanner = new Scanner(System.in);
                    String guestName = scanner.next();
                    Guest guest = new Guest(hotel, guestName);
                    currentReceptionist.bookARoom(guest);
                    break;
                case 2:
                    gotoCheckInMenu(currentReceptionist);
                    break;
                case 3:
                    gotoCheckoutMenu(currentReceptionist);
                    break;
                case 4:
                    currentReceptionist.getHotel().save();
                    break;
                case 5:
                    gotoLoad(currentReceptionist);
                    break;
                case 0:
                    break label;
                default:
                    // Hatali - tanimli olmayan kullanici
                    System.out.println("Hatali giris. 0,1,2,3 seciniz.");
                    break;
            }

        }

    }

    private static void gotoLoad(Receptionist currentReceptionist) {
        currentReceptionist.getHotel().load();
    }


    private static void gotoCheckInMenu(Receptionist currentReceptionist) {
        System.out.println("***************************************");
        System.out.println("Check-in Reservation");

        Scanner scanner = new Scanner(System.in);

        // Rezervasyon yapmis ama henuz checkin yapmamis guest listesini bul
        List<Room> openReservationRooms = currentReceptionist.getHotel().findOpenReservations();

        // Checkin yapilmamis rezervasyon yoksa uyari ver ve cik
        if (openReservationRooms.isEmpty()) {
            System.out.println("Acik rezervasyon yok.");
            return;
        }

        // Acik rezervasyonlar listelenir ve kullanici listeden checkin yapilacak konuk secim yapar
        while(true) {

            // Acik rezervasyonlari listele
            System.out.println("ACIK REZERVASYONLAR");
            for (int i=0;i<openReservationRooms.size(); i++) {
                Room room = openReservationRooms.get(i);
                int selectionNo = i + 1;
                System.out.println( selectionNo + "-) Room: " + room.getRoomNo() + " Guest: " + room.getGuest().getName());
            }

            System.out.println("0-) Geri don");

            System.out.println("Checkin yapmak istediginiz kaydi seciniz ");
            int selection = scanner.nextInt();

            // Geri don secildi mi kontrolu
            if(selection == 0) {
                return; // check in menusunden cikiyoruz, geri donuyoruz
            }

            // Listedekilerden biri secildi mi, secim gecerli mi kontrolu
            if (selection>0 && selection<=openReservationRooms.size()) {
                // Kullanici 1 sectiyse 0 daki elemani getir
                Room room = openReservationRooms.get(selection-1);
                currentReceptionist.checkin(room);

                System.out.println("Room " + room.getRoomNo() + " checkin completed.");
                return;
            } else {
                System.out.println("Gecersiz secim");
            }

        }
    }

    private static void gotoCheckoutMenu(Receptionist currentReceptionist)
    {
        System.out.println("***************************************");
        System.out.println("Check-out Reservation");

        Scanner scanner = new Scanner(System.in);

        // Rezervasyon yapmis ama henuz checkin yapmamis guest listesini bul
        List<Room> checkInMadeRooms = currentReceptionist.getHotel().findCheckInMadeRooms();
        if (checkInMadeRooms.isEmpty()) {
            System.out.println("Checkin yapilmis hic oda yok.");
            return;
        }

        while(true) {

            System.out.println("CHECKIN YAPILMIS ODALAR");
            for (int i=0;i<checkInMadeRooms.size(); i++) {
                Room room = checkInMadeRooms.get(i);
                int selectionNo = i + 1;
                System.out.println( selectionNo + "-) Room: " + room.getRoomNo() + " Guest: " + room.getGuest().getName());
            }
            System.out.println("0-) Geri don");
            System.out.println("Checkout yapmak istediginiz kaydi seciniz ");
            int selection = scanner.nextInt();

            if(selection == 0) {
                return;
            }

            if (selection>0 && selection<=checkInMadeRooms.size()) {
                // Kullanici 1 sectiyse 0 daki elemani getir
                Room room = checkInMadeRooms.get(selection-1);
                currentReceptionist.checkout(room);

                System.out.println("Room " + room.getRoomNo() + " checkout completed.");
                return;
            } else {
                System.out.println("Gecersiz secim");
            }
        }
    }

}
