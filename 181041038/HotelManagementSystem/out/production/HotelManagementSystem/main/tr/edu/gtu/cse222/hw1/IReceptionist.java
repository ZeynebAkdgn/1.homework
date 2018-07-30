package tr.edu.gtu.cse222.hw1;

/**
 * Receptionist objesinin yapabilecegi islemleri tanimlayan interface/ara yuz
 */
interface IReceptionist  {

    /**
     * Receptionist checkin operasyonu yapar.
     * Checkin icin Rezervasyon yapmis ama henuz checkin yapmamis guest listesi bulunur ve ekranda listelenir.
     * Bu listeden secim yapilarak checkin oeprasyonu tamamlanir.
     *
     * @return checkin basarili mi bilgisi
     */
    public boolean checkin(Room room);

    /**
     * Receptionist checkout operasyonu
     *
     * @return checkout basarili mi bilgisi
     */
    public boolean checkout(Room room);

    Room bookARoom(IGuest guest);
}


