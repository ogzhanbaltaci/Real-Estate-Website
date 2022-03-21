package codezilla.estate.entities.concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sosyal")
@AllArgsConstructor
@NoArgsConstructor
public class Sosyal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sosyal_id")
    private int sosyal_id;


    @Column(name= "sosyal_name")
    private String sosyal_name;
}
