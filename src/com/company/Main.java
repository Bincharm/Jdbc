package com.company;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        int min = 1, max = 100;
        Random rand = new Random();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++){
            int n = rand.nextInt(max - min + 1) + min;
            int prevCount = map.getOrDefault(n, 0);
            map.put(n, ++prevCount);
            System.out.println(n);
        }
        System.out.println(map);

        HashMap<String, Car> cars = new HashMap<>();
        Car mercedes = CarDB.getCarById(1);
        cars.put("AB1549Z", mercedes);
//        System.out.println(cars);

        Car toyota = CarDB.getCarById(2);
        cars.put("IO3377Q", toyota);
        System.out.println(cars.get("123"));
        System.out.println(cars.get("IO3377Q"));


//        System.out.println(DataBase.getTrainersCount());
//        System.out.println("Студентов с бувой а " + DataBase.getAStudentsCount() +
//                            " штук");
//        DataBase.printGroups();

//        if(DataBase.insertCountry(1, "Kyrgyz Republic", 996, 7000000)){
//            System.out.println("Inserted successfully");
//        }
//        else{
//            System.out.println("Insert failed");
//        }
//        DataBase.insertCountry(2, "United States of America", 1, 350000000);
//        DataBase.insertCountry(3, "Russia", 7, 150000000);
//        DataBase.insertCountry(4, "China", 86, 1400000000);
//        DataBase.insertCountry(5, "Great Britain", 44, 65000000);
//
//        DataBase.insertCity(1, "Bishkek", 1, 1500000);
//        DataBase.insertCity(2, "Washington", 2, 705000);
//        DataBase.insertCity(3, "Moscow", 3, 13000000);
//        DataBase.insertCity(4, "Beijing", 4, 23000000);
//        DataBase.insertCity(5, "London", 5, 9000000);

        //System.out.println(DataBase.getCitiesWithCountries());
        //System.out.println(DataBase.getCitiesByLength(6));


//
//        Scanner sc = new Scanner(System.in);
//        User user = null;
//
//        System.out.print("Enter username: ");
//        user = new User();
//        user.setUsername(sc.nextLine());
////
//        System.out.print("Enter password: ");
//        user.setPassword(sc.nextLine());

//        System.out.print("Enter email: ");
//        user.setEmail(sc.nextLine());
//
//        register(user);

        //authorise(user.getUsername(), user.getPassword());
//
        //UserDB.setUserIsBlockedByUserIdOrUsername(user, false);

//        int n = 81;
//        int power = n / 3;
//        System.out.println(Math.pow(n, 1.0/n)/* == (int)Math.pow(n, 1.0/3)*/);
    }

    public static void register(User user){
        if (UserDB.addUser(user)){
            System.out.println("Resgistered successfully!");
        }
        else System.out.println("Registation failed");
    }

    public static void authorise(String username, String password){
        User user = UserDB.getUserByUsername(username);

        if (user == null){
            System.err.println("Username or password is incorrect");
            return;
        }
        if (user.getPassword().equals(password)){
            UserDB.addUserLog(user, "SUCCESS");
            System.out.println("Logged in successfully!");
            if (UserDB.getUserLogsCountByUserIdAndLoginResult(user, "FAIL") > 0){
                UserDB.deleteUserLogsByUserIdAndLoginResult(user, "FAIL");
            }
        }
        else {
            UserDB.addUserLog(user, "FAIL");
            System.out.println("Username or password is incorrect");
            if (UserDB.getUserLogsCountByUserIdAndLoginResult(user, "FAIL") >= 3) {
                UserDB.setUserIsBlockedByUserIdOrUsername(user, true);
                System.out.println("User " + user.getUsername() + " has been blocked");
            }
        }
    }
}
