package codezilla.estate.dataAccess.abstracts;

import codezilla.estate.entities.concretes.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    public User findByResetPasswordToken(String token);
}

