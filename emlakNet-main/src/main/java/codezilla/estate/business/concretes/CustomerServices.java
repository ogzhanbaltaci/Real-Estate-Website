package codezilla.estate.business.concretes;

import codezilla.estate.core.exceptions.CustomerNotFoundException;
import codezilla.estate.dataAccess.abstracts.CustomerRepository;
import codezilla.estate.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServices {
    @Autowired
    private CustomerRepository customerRepo;


    public void updateResetPasswordToken(String token, String email) throws CustomerNotFoundException {
        User user = customerRepo.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            customerRepo.save(user);
        } else {
            throw new CustomerNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return customerRepo.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        customerRepo.save(user);
    }
}
