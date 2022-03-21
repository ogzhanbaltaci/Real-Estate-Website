package codezilla.estate;

import codezilla.estate.entities.concretes.User;
import codezilla.estate.dataAccess.abstracts.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)

public class UserRepositoryTests {

    @Autowired
    private UserDao userDao;


    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setNickName("salli");
        user.setEmail("sallican@gmail.com");
        user.setPassword("12345");

        userDao.save(user);
    }
}
