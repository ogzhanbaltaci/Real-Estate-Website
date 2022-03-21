package codezilla.estate.business.abstracts;


import codezilla.estate.core.utilities.results.DataResult;
import codezilla.estate.entities.concretes.User;
import codezilla.estate.core.utilities.results.Result;

public interface UserService {

    Result add(User user);
    Result deleteAccountById(int id);
    DataResult<User> findByEmail(String email);
    void updateNickNameById( int id, String nickName);
}
