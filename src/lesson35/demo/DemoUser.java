package lesson35.demo;

import lesson35.controller.UserController;
import lesson35.model.User;
import lesson35.model.UserType;

public class DemoUser {

    //DemoUser demoUser = new DemoUser();

    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();

        userController.login("Adriano", "password");
        //System.out.println(Session.getUser());

        //register
        User newUser = new User(0L, "Mike3", "123", "Ukraine", UserType.USER);
        userController.registerUser(newUser);

        userController.logout();
    }
}