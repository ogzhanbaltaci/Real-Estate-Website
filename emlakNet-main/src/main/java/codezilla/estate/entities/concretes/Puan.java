package codezilla.estate.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data

@Entity
@Table(name = "degerlendirme")
@AllArgsConstructor
@NoArgsConstructor
public class Puan {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "degerlendirme_id")
        private int degerlendirme_id;

        @Column(name = "puan")
        private int puan;

        @Column(name="ilan_id")
        private int ilanid;

//        public Puan(int ilanid)
//        {
//                this.ilanid = ilanid;
//        }

}
