package codezilla.estate.dataAccess.abstracts;

import codezilla.estate.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = ?1") //?1 anlamı ilk parametre demek yani gönderdiğimiz email.
    User findByEmail(String email);


    int deleteById( int id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.nickName =:nickName where u.id=:id")
    void updateNickNameById(@Param("id") int id, @Param("nickName") String nickName);
}
