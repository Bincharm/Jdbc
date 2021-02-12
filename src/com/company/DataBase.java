package com.company;

import com.company.cities.City;
import com.company.cities.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static final String url = "jdbc:postgresql://localhost:5433/postgres";
    private static final String user = "postgres";
    private static final String password = "123";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }

    public static int getTrainersCount() {
        String sql = "SELECT COUNT(*) FROM Trainers";
        int count = 0;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public static int getAStudentsCount() {
        String sql = "SELECT COUNT(*) FROM students " +
                "WHERE LOWER(student_full_name) LIKE";//" '%a%'";
        int count = 0;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            rs.next();
            count = rs.getInt("count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public static void printGroups() {
        String sql = "select * from groups";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                System.out.println(rs.getString(1) + ": " +
                        rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @SuppressWarnings("Duplicates")
    public static boolean insertCountry(int id, String name, int countryCode, int countryPopulation) {
        String sql = "INSERT INTO countries(id, name, country_code, country_population) " +
                "values(?, ?, ?, ?)";
        int result = 0;

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, countryCode);
            stmt.setInt(4, countryPopulation);

            result = stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result > 0;
    }

    @SuppressWarnings("Duplicates")
    public static boolean insertCity(int id, String name, int countryId, int cityPopulation) {
        String sql = "INSERT INTO cities(id, name, country_id, city_population) " +
                "values(?, ?, ?, ?)";
        int result = 0;

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, countryId);
            stmt.setInt(4, cityPopulation);

            result = stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result > 0;
    }

    public static List<City> getCitiesWithCountries() {
        String sql = "select * from cities c " +
                "join countries cn on cn.country_id = c.country_id";
        List<City> cities = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Country country = new Country(rs.getInt("country_id"), rs.getString("country_name"),
                        rs.getInt("country_code"), rs.getInt("country_population"));
                City city = new City(rs.getInt("city_id"), rs.getString("city_name"),
                        country, rs.getInt("city_population"));

                cities.add(city);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cities;
    }

    @SuppressWarnings("Duplicates")
    public static List<City> getCitiesByLength(int length) {
        String sql = "SELECT * FROM CITIES c " +
                "JOIN countries cn ON cn.country_id = c.country_id " +
                "WHERE LENGTH(city_name) > ?";
        List<City> cities = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, length);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Country country = new Country(rs.getInt("country_id"), rs.getString("country_name"),
                            rs.getInt("country_code"), rs.getInt("country_population"));
                    City city = new City(rs.getInt("city_id"), rs.getString("city_name"),
                            country, rs.getInt("city_population"));

                    cities.add(city);
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (
                SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cities;


    }


}
