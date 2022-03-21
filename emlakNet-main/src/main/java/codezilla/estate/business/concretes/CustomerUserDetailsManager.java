package codezilla.estate.business.concretes;

import codezilla.estate.dataAccess.abstracts.UserDao;
import codezilla.estate.entities.concretes.CustomUserDetails;
import codezilla.estate.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerUserDetailsManager implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    //User login için
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Böyle bir kullanıcı bulunamadı.");
        }
        return new CustomUserDetails(user);
    }
}
