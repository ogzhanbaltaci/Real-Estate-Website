package codezilla.estate.business.concretes;

import codezilla.estate.business.abstracts.UserService;
import codezilla.estate.core.utilities.results.*;
import codezilla.estate.entities.concretes.User;
import codezilla.estate.dataAccess.abstracts.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


@Service
public class UserManager implements UserService {

    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult();

    }

    @Override

    public Result deleteAccountById(int id) {
        try {
            userDao.deleteById(id);
            return new SuccessResult();
        } catch (Exception ex) {
            return new ErrorResult();
        }
    }

    @Override
    public DataResult<User> findByEmail(String email) {
        try {
            User result =userDao.findByEmail(email);
            return new SuccessDataResult<>(result);
        } catch (Exception ex) {
            return new ErrorDataResult<>();
        }
    }

    public void updateNickNameById( int id, String nickName){
       userDao.updateNickNameById(id, nickName);

    }


}
