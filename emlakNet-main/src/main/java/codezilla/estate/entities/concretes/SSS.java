package codezilla.estate.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sss")
public class SSS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sss_id")
    private int  sss_id;

    @Column(name= "soru")
    @NotBlank
    @NotNull
    private String soru;

    @Column(name= "cevap")
    @NotBlank
    @NotNull
    private String cevap;


}
