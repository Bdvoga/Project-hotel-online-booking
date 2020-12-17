package lesson35.repository;

import lesson35.model.Order;
import lesson35.model.Room;
import lesson35.model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class OrderRepository extends GeneralRepository {
    UserRepository userRepository = new UserRepository();
    RoomRepository roomRepository = new RoomRepository();

    public void bookRoom(long roomId, long userId, String dateFrom, String dateTo) throws Exception {
        Date dateFromConverted = transferDateFromFile(dateFrom);
        Date dateToConverted = transferDateFromFile(dateTo);
        String path = "E:/Gromcode/Java Core/DB/OrderDb.txt";
        ArrayList<Order> orders = getAllObjects(path);
        ArrayList<Room> rooms = roomRepository.getAllObjects("E:/Gromcode/Java Core/DB/RoomDb.txt");
        Room room = (Room) findById(rooms, roomId);
        ArrayList<User> users = userRepository.getAllObjects("E:/Gromcode/Java Core/DB/UserDb.txt");

        long orderId = generationId(orders);
        User user = (User) findById(users, userId);
        double moneyPaid = ((dateToConverted.getTime() - dateFromConverted.getTime()) / 86400000) * ((Room) findById(rooms, roomId)).getPrice();

        Order order = new Order(orderId, user, room, dateFromConverted, dateToConverted, moneyPaid);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.append("\n");
            bw.append(order.toString());
        } catch (IOException e) {
            throw new IOException("Обшибка записи в файл " + path);
        }
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        ArrayList<Order> orders = getAllObjects("E:/Gromcode/Java Core/DB/OrderDb.txt");
        for (Order el : orders) {
            if (el.getRoom().getId() == roomId && el.getUser().getId() == userId) {
                long orderId = el.getId();
                delete(orderId, "E:/Gromcode/Java Core/DB/OrderDb.txt");
                return;
            }
        }
    }

    @Override
    public Order getMappedObject(String[] object) throws Exception {
        try {
            User user = (User) findById(userRepository.getUsers(), Long.parseLong(object[1]));
            Room room = (Room) findById(roomRepository.getRooms(), Long.parseLong(object[2]));
            return new Order(Long.parseLong(object[0]), user, room,
                    transferDateFromFile(object[3]),
                    transferDateFromFile(object[4]), Double.parseDouble(object[5]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("Неправильный формат данных в файле E:/Gromcode/Java Core/DB/OrderDb.txt");
        } catch (NumberFormatException e) {
            throw new Exception("Неправильный формат id в файле E:/Gromcode/Java Core/DB/OrderDb.txt");
        }
    }
}
