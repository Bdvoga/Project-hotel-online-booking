package demo;

import controller.RoomController;
import controller.UserController;
import model.Filter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoRoom {
    public static void main(String[] args) throws Exception {
        RoomController roomController = new RoomController();
        UserController userController = new UserController();

        userController.login("Adriano", "password");

        //Admin - addRoom +
//        Hotel hotel1 = new Hotel(101L,"Mir","Ukraine","Kyiv","Street2");
//        Room room = new Room(0L, 3,33.00,true,false,new Date(),hotel1);
//        Room room1 = null;
//        System.out.println(roomController.addRoom(room1));

        //Admin - deleteRoom +
        roomController.deleteRoom(6080L);

        //List findRooms(Filter filter) +
        Date dateFrom = new SimpleDateFormat("dd-MM-yyyy").parse("30-11-2020");
        Filter filter = new Filter(2,50.0,false,
                false,dateFrom,"Belarus","Grodno");

        System.out.println(roomController.findRooms(filter));

        userController.logout();
    }

}
