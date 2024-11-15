package com.doctor.utils;

import com.doctor.pages.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    //! Передаёт в тест набор строк
    @DataProvider
    public Iterator<Object[]> addContactString() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Name1", "LastName1", "1234567891", "email1@gmail.com", "Germany 1", "Description 1"});
        list.add(new Object[]{"Name2", "LastName2", "1234567892", "email2@gmail.com", "Germany 2", "Description 2"});
        list.add(new Object[]{"Name3", "LastName3", "1234567893", "email3@gmail.com", "Germany 3", "Description 3"});
        list.add(new Object[]{"Name4", "LastName4", "1234567894", "email4@gmail.com", "Germany 4", "Description 4"});
        return list.iterator();
    }

    //! Передаёт в тест объекты класса Contact
    @DataProvider
    public Iterator<Object[]> addContactObject() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new Contact().setName("Name5").setLastName("LastName5").setPhone("1234567895").setEmail("email5@gmail.com").setAddress("Germany 5").setDescription("Description 5")});
        list.add(new Object[]{new Contact().setName("Name6").setLastName("LastName6").setPhone("1234567896").setEmail("email6@gmail.com").setAddress("Germany 6")});
        list.add(new Object[]{new Contact().setName("Name7").setLastName("LastName7").setPhone("1234567897").setEmail("email7@gmail.com").setAddress("Germany 7")});
        list.add(new Object[]{new Contact().setName("Name8").setLastName("LastName8").setPhone("1234567898").setEmail("email8@gmail.com").setAddress("Germany 8")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addContactFromCSV() throws IOException {
        // Создаём массив со списком данных для тестов
        List<Object[]> list = new ArrayList<>();
        // Открываем файл .csv для чтения с него данных по адресу src/test/resources/data_csv/contacts.csv
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data_csv/contacts.csv"));
        // Читаем первую строку
        String line = reader.readLine();
        // Обрабатываем каждую строку дол конца строки
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new Contact()
                    .setName(split[0])
                    .setLastName(split[1])
                    .setPhone(split[2])
                    .setEmail(split[3])
                    .setAddress(split[4])
                    .setDescription(split[5])
            });
            line = reader.readLine();
        }
        // Закрываем чтение файла
        reader.close();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data_csv/login.csv"));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new Contact()
                    .setName(split[0])
                    .setLastName(split[1])
            });
            line = reader.readLine();
        }
        reader.close();
        return list.iterator();
    }

}
