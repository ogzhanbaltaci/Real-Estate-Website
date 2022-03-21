package codezilla.estate.entities.concretes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data

@Entity
@Table(name = "kullanici")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kullanici_id")
    private int id;

    @Column(name= "kullanici_adi")
    @NotBlank
    @NotNull
    private String nickName;

    @Email
    @Column(name = "kullanici_email")
    @NotBlank
    @NotNull
    private String email;

    @Column(name= "kullanici_sifre")
    @NotBlank
    @NotNull
    private String password;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

}
