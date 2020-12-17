package lesson35.demo;

import lesson35.controller.OrderController;
import lesson35.controller.UserController;

public class DemoOrder {
    public static void main(String[] args) throws Exception {
        OrderController orderController = new OrderController();
        UserController userController = new UserController();

        userController.login("Adriano", "password");

        //orderController.bookRoom(1001L, 100, "20-12-2020", "22-12-2020");
        //orderController.bookRoom(1001L, 100, "20-12-2021", "22-12-2021");

        orderController.cancelReservation(1001L, 106L);

        userController.logout();
    }
}
