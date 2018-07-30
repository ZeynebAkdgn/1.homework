package tr.edu.gtu.cse222.hw1;

/**
 * Guest objesinin yapabilecegi islemleri tanimlayan interface/ara yuz
 */
public interface IGuest {


    Reservation getReservation();
    String getName();
    /**
     * Rezervasyonu iptal etme operasyonu
     *
     * @return rezervasyon iptali basarili mi degil mi sonucu doner
     */
    boolean cancelReservation();

    /**
     * Guest oda ayirtma metodu.
     * @return ayrilan oda Room nesnesi donulur. Oda ayirma basarisizsa null donulur.
     */
    Room bookARoom();
}
