package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class importResources {

    public static void main(String args[]) throws SQLException, FileNotFoundException {
        File file = new File("C:\\Users\\Farzaneh\\Eighth-Assignment-Steam\\src\\main\\java\\Server\\Resources");

        File[] files = file.listFiles();

        String url = "jdbc:postgresql://localhost:5432/steam";
        String username = "postgres";
        String password = "09farzaneh";

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("connected to the postgresql database stream");

        Statement statement = connection.createStatement();

        for (File file1 : files) {
            if (file1.getName().endsWith(".txt")) {
                Scanner scanner = new Scanner(file1);
                String id = scanner.nextLine();
                String path = ("C:\\Users\\Farzaneh\\Eighth-Assignment-Steam\\src\\main\\java\\Server\\Resources" + id + ".png");
                String query = "'" + id + "'" + " ,";
                while (scanner.hasNextLine()) {
                    query += ("'" + scanner.nextLine() + "'" + " ,");
                }
                query += ("'" + path + "'");

                statement.executeUpdate("INSERT INTO game VALUES (" + query + ")");

            }
        }

        statement.close();
        connection.close();
    }
}
