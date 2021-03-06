package controller;

import model.Session;
import model.User;
import service.UserService;

public class UserController {
//    registerUser(User user) - регистрация пользователя в системе +-
//    void login(String userName, String password) - логин пользователя +
//    void logout() +

    private UserService userService = new UserService();
    //private UserRepository userRepository = new UserRepository();

    public User registerUser(User user) throws Exception {
        return userService.registerUser(user);
    }

    public void login(String userName, String password) throws Exception {
        userService.login(userName, password);
    }

    public void logout() {
        Session.setUser(null);
    }


}
