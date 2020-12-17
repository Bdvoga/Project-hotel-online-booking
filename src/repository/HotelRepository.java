package repository;

import model.Hotel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HotelRepository extends GeneralRepository {

    public ArrayList<Hotel> findHotelByName(String name) throws Exception {
        String path = "E:/Gromcode/Java Core/DB/HotelDb.txt";
        ArrayList<Hotel> hotels = getAllObjects(path);
        ArrayList<Hotel> foundHotels = new ArrayList<>();

        for (Hotel el : hotels) {
            if (el.getName().equals(name)) {
                //формируем массив отелей
                foundHotels.add(el);
            }
        }

        return foundHotels;
    }

    public ArrayList<Hotel> findHotelByCity(String city) throws Exception {
        ArrayList<Hotel> hotels = getAllObjects("E:/Gromcode/Java Core/DB/HotelDb.txt");
        ArrayList<Hotel> foundHotels = new ArrayList<>();

        for (Hotel el : hotels) {
            if (el.getCity().equals(city)) {
                foundHotels.add(el);
            }
        }

        return foundHotels;
    }

    public Hotel addHotel(Hotel hotel) throws Exception {
        String path = "E:/Gromcode/Java Core/DB/HotelDb.txt";
        ArrayList<Hotel> hotels = getAllObjects(path);
        long id = generationId(hotels);
        hotel.setId(id);
        String str = hotel.toString();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.append("\n");
            bw.append(str);
            System.out.println("Отель " + hotel.getName() + " добавлен в базу");
            return hotel;
        } catch (IOException e) {
            throw new IOException("Обшибка записи d файл " + path);
        }
    }

    public void deleteHotel(long hotelId) throws Exception {
        String path = "E:/Gromcode/Java Core/DB/HotelDb.txt";
        delete(hotelId,path);
    }

    // получаем список отелей для других методов
    public ArrayList<Hotel> getHotels () throws Exception {

        return getAllObjects("E:/Gromcode/Java Core/DB/HotelDb.txt");
    }

    @Override
    public Hotel getMappedObject(String[] object) throws Exception {
        try {
            return new Hotel(Long.parseLong(object[0]), object[1], object[2], object[3], object[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("Неправильный формат данных в файле E:/Gromcode/Java Core/DB/HotelDb.txt");
        } catch (NumberFormatException e) {
            throw new Exception("Неправильный формат id в файле E:/Gromcode/Java Core/DB/HotelDb.txt");
        }
    }
}
