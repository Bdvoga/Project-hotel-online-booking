package lesson35.demo;

import lesson35.controller.HotelController;
import lesson35.controller.UserController;
import lesson35.model.Hotel;

public class DemoHotel {

    public static void main(String[] args) throws Exception {
        HotelController hotelController = new HotelController();
        UserController userController = new UserController();

        userController.login("Adriano", "password");
        //userController.logout();

        //findHotelByName
        System.out.println(hotelController.findHotelByName("Zapad"));
        System.out.println(hotelController.findHotelByName("Sokol"));

        //findHotelByCity
        System.out.println(hotelController.findHotelByCity("Kyiv"));
        System.out.println(hotelController.findHotelByCity("Lviv"));

        //Admin - addHotel
        Hotel hotel = new Hotel(0L, "Pelican", "Israel", "Haifa", "Street One");
        System.out.println(hotelController.addHotel(hotel));

        //Admin - deleteHotel
        hotelController.deleteHotel(111);

        userController.logout();

    }
}
