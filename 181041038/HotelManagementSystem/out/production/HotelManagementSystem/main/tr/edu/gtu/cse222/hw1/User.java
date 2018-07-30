package tr.edu.gtu.cse222.hw1;

/**
 * Guest ve Receptionist siniflarina ait ortak bilgilerin tutuldugu soyut User sinifi.
 * Abstract User sinifi - newlenemez
 */
public abstract class User {

    /**
     * Uygulamanin tamaminda kullanilan hotel nesnesinin referansi.
     * alt siniflardan erisilsin diye protected yapildi.
     */
    protected Hotel hotel;

    /**
     * Super constructor. Alt siniflardan super(hotel) seklinde cagrilir.
     *
     * @param hotel uygulamanin tamaminda kullanilan hotel nesnesinin referansi
     */
    public User(Hotel hotel) {
        this.hotel = hotel;
    }
}