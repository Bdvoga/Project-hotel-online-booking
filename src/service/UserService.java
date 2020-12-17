package service;

import model.User;
import repository.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public User registerUser(User user) throws Exception {
        //все ли поля заполнены
        if (user != null && user.getUserName() != null && user.getPassword() != null &&
                user.getCountry() != null && user.getUserType() != null) {
            return userRepository.registerUser(user);
        } else {
            throw new Exception("Ошибка! Не все поля заполнены!");
        }
    }

        public void login(String userName, String password) throws Exception {
        userRepository.login(userName, password);
    }
}
