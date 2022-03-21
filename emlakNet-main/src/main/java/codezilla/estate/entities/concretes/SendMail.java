package codezilla.estate.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "destek")
public class SendMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="destek_id")
    private int destek_id;

    @Column(name="kullanici_email")
    private String email;

    @Column(name="konu_basligi")
    private String konuBasligi;

    @Column(name="icerik")
    private String icerik;
}
