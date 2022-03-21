package codezilla.estate.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profilephoto")
public class ProfilePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pp_id")
    private int pp_id;


    @Lob
    @Column(name = "pp", columnDefinition = "MEDIUMBLOB")
    private String pp;

}
