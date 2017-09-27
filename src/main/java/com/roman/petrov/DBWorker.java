package com.roman.petrov;

import java.sql.*;

public class DBWorker {

    private static String URL = "jdbc:sqlite:C:/RomanPetrov/MyProjects/Redirect/redirect_db.s3db";
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    public static void openConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
            System.out.println("Подключение к базе прошло УСПЕШНО");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не найден");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Подключение к базе НЕ УДАЛОСЬ");
            e.printStackTrace();
        }

    }
    public static void closeConnection(){
        try {
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveRedirect(Redirect redirect){
        try {
            openConnection();
            String sql = String.format("INSERT INTO 'redirects' ('url_source', 'url_destination', 'code', 'user_agent') VALUES ('%s', '%s', '%d', '%s');",
                    redirect.getUrlSource(),
                    redirect.getUrlDestination(),
                    redirect.getCode(),
                    redirect.getUserAgent());

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Redirect сохранен");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }


}
