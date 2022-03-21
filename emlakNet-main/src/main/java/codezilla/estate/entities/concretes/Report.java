package codezilla.estate.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bildir")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="report_id")
    private int  report_id;

    @Column(name="ilan_id")
    private int productid;

    @Column(name="bildir_email")
    private String bildir_email;

    @Column(name="bildir_icerik")
    private String bildir_icerik;


}