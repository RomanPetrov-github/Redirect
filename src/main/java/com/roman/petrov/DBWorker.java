package com.roman.petrov;

import java.sql.*;
import java.util.List;

public class DBWorker {
    private static String URL = "jdbc:mysql://localhost:3306/redirect_database";
    private static String LOGIN = "root";
    private static String PASSWORD = "root";


    public static Integer saveScript(ScriptBPM script){

        try {
            Class.forName ("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(script.getSqlInsert()))
        {
            System.out.println("Подключение к базе прошло УСПЕШНО");

            preparedStatement.setString(1, script.getAuthor());
            preparedStatement.setString(2, script.getNumber());
            preparedStatement.setString(3, script.getDescription());
            preparedStatement.setString(4, script.getLink());
            preparedStatement.executeUpdate();

            ResultSet resultSet = connection.prepareStatement("SELECT MAX(id) FROM requests;").executeQuery();

            resultSet.next();
            int requestID = resultSet.getInt(1);

            System.out.println("Запись в базу прошла успешно");
            System.out.println("requestID - " + requestID);

            return requestID;
        }
        catch (SQLException e){
            System.out.println("Подключение к базе НЕ УДАЛОСЬ");
            e.printStackTrace();
        }
        return null;
    }
    public static boolean saveRedirects(int id, List<Redirect> redirects){
        for (Redirect redirect: redirects) {
            try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(redirect.getSqlInsert()))
            {
                System.out.println("Подключение к базе прошло УСПЕШНО");
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, redirect.getUrlFrom());
                preparedStatement.setString(3, redirect.getUrlTo());
                preparedStatement.setInt(4, redirect.getCode());
                preparedStatement.setString(5, redirect.getUserAgent());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Подключение к базе НЕ УДАЛОСЬ");
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
