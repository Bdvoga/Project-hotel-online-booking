package service;

import model.Room;
import model.User;
import repository.GeneralRepository;
import repository.OrderRepository;
import repository.RoomRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;

public class OrderService extends GeneralRepository {
    OrderRepository orderRepository = new OrderRepository();
    GeneralService generalService = new GeneralService();
    RoomRepository roomRepository = new RoomRepository();
    UserRepository userRepository = new UserRepository();

    public void bookRoom(long roomId, long userId, String dateFrom, String dateTo) throws Exception {
        generalService.verification();

        //Есть ли такая комната
        ArrayList<Room> rooms = roomRepository.getRooms();
        if (findById(rooms,roomId) == null) {
            throw new Exception("Такой комнаты не существует");
        }

        //Сбводна ли с даты dateFrom
        Date dateFromConverted = transferDateFromFile(dateFrom);
        if (findById(rooms,roomId).getDateAvailableFrom().getTime() > dateFromConverted.getTime()) {
            throw new Exception("На указанные даты комната не доступна");
        }

        //Есть ли такой юзер
        ArrayList<User> users = userRepository.getUsers();
        if (findById(users, userId) == null) {
            throw new Exception("Пользователь не найден");
        }

        orderRepository.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        generalService.verification();
        orderRepository.cancelReservation(roomId, userId);
    }

    @Override
    public Object getMappedObject(String[] object) throws Exception {
        return null;
    }
}
