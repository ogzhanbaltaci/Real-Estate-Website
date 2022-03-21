package codezilla.estate.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@Entity

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ilan")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ilan_id")
    private int ilan_id;

    @Column(name = "ilan_baslik")
    private String ilan_baslik;

    @Column(name = "ilan_fiyat")
    private int ilan_fiyat;

    @CreatedDate
    @Column(name = "ilan_tarih")
    private Date ilan_tarih = new Date();

    @Column(name = "ilan_aciklama")
    private String ilan_aciklama;

    @Column(name = "degerlendirme_ort")
    private double degerlendirme_ort;

    @Column(name = "oda_sayisi")
    private int oda_sayisi;

    @Column(name = "bina_yasi")
    private int bina_yasi;

    @Column(name = "esya_durumu")
    private int esya_durumu;

    @Column(name = "kat")
    private int kat;

    @Column(name = "satilik")
    private int satilik;

    @Column(name = "tam_adres")
    private String tam_adres;

    @Column(name = "sehir")
    private String sehir;

    @Column(name = "ilce")
    private String ilce;

    @Lob
    @Column(name = "ilan_resim", columnDefinition = "MEDIUMBLOB")
    private String ilan_resim;

    public Product(int ilan_id) {
        this.ilan_id = ilan_id;
    }


}
