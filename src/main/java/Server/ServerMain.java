package Server;

import Shared.RequestCreate;
import Shared.RequestLogin;
import Shared.ResponseBrowse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.*;
import java.io.IOException;
import java.time.LocalDate;

public class ServerMain {
    private static Socket socket = null;
    private ServerSocket server = null;

    private static final String FOLDER_PATH = "C:\\Users\\Farzaneh\\Eighth-Assignment-Steam\\src\\main\\java\\Server\\Resources";





    public ServerMain(int port) throws SQLException, IOException {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");



            getMessage1();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        socket.close();
    }

    public static void getMessage1() throws IOException, ClassNotFoundException, SQLException {

        // to send data to the client
        PrintStream ps = new PrintStream(socket.getOutputStream());

        // to read data from client
        BufferedReader br = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));

        // to read data from the keyboard
        BufferedReader kb
                = new BufferedReader(
                new InputStreamReader(System.in));

        // to send object to the client
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        // to receive object from client
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        String message;
        String message1;
        String message2;
        String response;
        String response2;
        String command2;
        String nameOfTheVideoGame;

        while (true) {

            String command;
            while ((command = br.readLine()) != null) {
                System.out.println(command);
                if (command.equals("login")) {
                    message = "enter your username and password";
                    ps.println(message);
                    RequestLogin rl = (RequestLogin) ois.readObject();
                    System.out.println(rl.toString());
                    message1 = loginDo(rl);
                    ps.println(message1);

                    // get the new command after login
                    command2 = br.readLine();
                    System.out.println("user choosed " + command2);

                    switch (command2) {
                        case "browse" -> {
                            message1 = "let's browse Steam";
                            ps.println(message1);
                            String browse = Browse();
                            ps.println(browse);
                            String response3 = br.readLine();
                            System.out.println(response3);
                        }
                        case "details" -> {
                            message1 = "enter name of the video game you want to see it's details";
                            ps.println(message1);
                            System.out.println("1");
                            nameOfTheVideoGame = br.readLine();
                            System.out.println("2");
                            System.out.println(nameOfTheVideoGame);
                            response2 = showDetail(nameOfTheVideoGame);
                            ps.println(response2);
                        }
                        case "download" -> {
                            message1 = "enter name of the video game you want to download";
                            ps.println(message1);
                            nameOfTheVideoGame = br.readLine();
                            System.out.println(nameOfTheVideoGame);
                            String pathOfGame = findPNGPath(nameOfTheVideoGame);
                            ps.println(pathOfGame);

                        }
                        default -> {
                            message1 = "you came to download management";
                            ps.println(message1);
                        }
                    }

                }
                if (command.equals("create")) {
                    message = "please enter a username, password and your birthday";
                    ps.println(message);
                    RequestCreate rc = (RequestCreate) ois.readObject();
                    System.out.println(rc.toString());
                    message1 = createDo(rc);
                    ps.println(message1);

                    // get the command after creating a new account
                    command2 = br.readLine();
                    System.out.println("user choosed " + command2);

                    switch (command2) {
                        case "browse" -> {
                            message1 = "let's browse Steam";
                            ps.println(message1);
                            String browse = Browse();
                            ps.println(browse);
                            String response3 = br.readLine();
                            System.out.println(response3);
                        }
                        case "details" -> {
                            message1 = "enter name of the video game you want to see it's details";
                            ps.println(message1);
                            System.out.println("1");
                            nameOfTheVideoGame = br.readLine();
                            System.out.println("2");
                            System.out.println(nameOfTheVideoGame);
                            response2 = showDetail(nameOfTheVideoGame);
                            ps.println(response2);
                        }
                        case "download" -> {
                            message1 = "enter name of the video game you want to download";
                            ps.println(message1);
                            nameOfTheVideoGame = br.readLine();
                            System.out.println(nameOfTheVideoGame);
                            String pathOfGame = findPNGPath(nameOfTheVideoGame);
                            ps.println(pathOfGame);

                        }
                        default -> {
                            message1 = "you came to download management";
                            ps.println(message1);
                        }
                    }
                }
                else {
                    message = "enter valid command";
                    ps.println(message);
                }
            }

            ps.close();
            br.close();
            kb.close();
        }
    }


    public static String loginDo(RequestLogin rl) throws SQLException {
        String message;

        String url = "jdbc:postgresql://localhost:5432/steam";
        String username = "postgres";
        String password = "09farzaneh";

        Connection connectionLogin = DriverManager.getConnection(url, username, password);
        Statement statementLogin = connectionLogin.createStatement();

        String usernameUser = rl.getUsername();
        String passwordUser = rl.getPassword();

        String queryString = "SELECT * FROM account WHERE username = '" + usernameUser +  "' AND password = '" + passwordUser + "';";

        ResultSet resultSetLogin = statementLogin.executeQuery(queryString);
        if (resultSetLogin.next()) {
            message = "correct username and password, welcome to your page";
        } else {
            message = "password wrong";
        }
        statementLogin.close();
        connectionLogin.close();

        return message;
    }

    public static String createDo(RequestCreate rc) throws SQLException {
        String message  = "account crated successfully, welcome";

        String url = "jdbc:postgresql://localhost:5432/steam";
        String username = "postgres";
        String password = "09farzaneh";

        Connection connectionCreate = DriverManager.getConnection(url, username, password);

        String usernameUser = rc.getUsername();
        String passwordUser = rc.getPassword();
        String date = rc.getDateOfBirth();
        LocalDate dateOfBirth = LocalDate.parse(date);


        String queryString = "INSERT INTO account(username, password, date_of_birth) "
                + "VALUES(?, ?, ?)";

        PreparedStatement preparedStatement = connectionCreate.prepareStatement(queryString);
        preparedStatement.setString(1, usernameUser);
        preparedStatement.setString(2, passwordUser);
        preparedStatement.setDate(3, Date.valueOf(dateOfBirth));

        preparedStatement.execute();


        connectionCreate.close();


        return message;
    }


    public static String Browse() throws SQLException {


        String url = "jdbc:postgresql://localhost:5432/steam";
        String username = "postgres";
        String password = "09farzaneh";



        String games = "";

        try {
            Connection connectionBrowse = DriverManager.getConnection(url, username, password);
            Statement statementBrowse  = (Statement) connectionBrowse.createStatement();

            String queryBrowse;
            queryBrowse = "SELECT title FROM game";
            ResultSet resultSetBrowse = statementBrowse.executeQuery(queryBrowse);
            while (resultSetBrowse.next()) {
                games += "title : ";
                games += resultSetBrowse.getString("title");
                games += "\n";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return games;
    }

    public static ResponseBrowse browseDo() throws SQLException {
        ResponseBrowse responseBrowse = new ResponseBrowse();

        String url = "jdbc:postgresql://localhost:5432/steam";
        String username = "postgres";
        String password = "09farzaneh";


        Connection connectionBrowse1 = DriverManager.getConnection(url, username, password);
        Statement statementBrowse1 = (Statement) connectionBrowse1.createStatement();

        String games = "";

        try {
            String queryBrowse;
            queryBrowse = "SELECT title, developer, genre, price, release_year, size FROM game";
            ResultSet resultSetBrowse = statementBrowse1.executeQuery(queryBrowse);
            while (resultSetBrowse.next()) {
                games += resultSetBrowse.getString("title");
                games += ", ";
                games += resultSetBrowse.getString("developer");
                games += ", ";
                games += resultSetBrowse.getString("genre");
                games += ", ";
                games += resultSetBrowse.getString("price");
                games += ", ";
                games += resultSetBrowse.getString("release_year");
                games += ", ";
                games += resultSetBrowse.getString("size");
                responseBrowse.addToGames(games);
                games = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectionBrowse1.close();
        statementBrowse1.close();
        return responseBrowse;
    }

    public static String showDetail(String nameOfGame) throws SQLException {

        String result = "";
        String url = "jdbc:postgresql://localhost:5432/steam";
        String username = "postgres";
        String password = "09farzaneh";

        Connection connectionDetail = DriverManager.getConnection(url, username, password);
        Statement statementDetail = (Statement) connectionDetail.createStatement();

        String queryDetail = "SELECT title, developer, genre, price, release_year, size FROM game WHERE title = '"
                + nameOfGame + "';";
        ResultSet resultSetBrowse = statementDetail.executeQuery(queryDetail);
        while (resultSetBrowse.next()) {
            result += resultSetBrowse.getString("title");
            result += ", ";
            result += resultSetBrowse.getString("developer");
            result += ", ";
            result += resultSetBrowse.getString("genre");
            result += ", ";
            result += resultSetBrowse.getString("price");
            result += ", ";
            result += resultSetBrowse.getString("release_year");
            result += ", ";
            result += resultSetBrowse.getString("size");
        }
        connectionDetail.close();
        statementDetail.close();
        return result;
    }


    public static String findPNGPath(String nameOfGame) throws SQLException {

        String path = "";

        String url = "jdbc:postgresql://localhost:5432/steam";
        String username = "postgres";
        String password = "09farzaneh";

        Connection connectionFindPath = DriverManager.getConnection(url, username, password);
        String queryFindPth = "SELECT file_path FROM game where title ='" + nameOfGame + "';";
        Statement statementFindPath = connectionFindPath.createStatement();
        ResultSet resultSetFind = statementFindPath.executeQuery(queryFindPth);
        while (resultSetFind.next()) {
            path = resultSetFind.getString("file_path");
        }

        return path;

    }

   public static void increaseCountOFDownload(RequestLogin rl, String nameOfGame) throws SQLException {

       String url = "jdbc:postgresql://localhost:5432/steam";
       String username = "postgres";
       String password = "09farzaneh";

       Connection connectionId = DriverManager.getConnection(url, username, password);
       Connection connectionGame = DriverManager.getConnection(url, username, password);
       Connection connectionincreaseCount = DriverManager.getConnection(url, username, password);
       Connection connectionGetCount = DriverManager.getConnection(url, username, password);

       String id = "";
       String game = "";
       int count = 0;
       String usernameUser = rl.getUsername();

       // query to get the id of user
       String queryGetId = "SELECT id FROM account WHERE username = '" + usernameUser + "';";
       Statement statementGetId = connectionId.createStatement();
       ResultSet resultSetGetId = statementGetId.executeQuery(queryGetId);
       while (resultSetGetId.next()) {
           id = resultSetGetId.getString("id");
       }

       // query to get the id of game
       String queryFindGame = "SELECT id FROM game WHERE title = '" + nameOfGame + "';";
       Statement statementGame = connectionGame.createStatement();
       ResultSet resultSetgame = statementGame.executeQuery(queryFindGame);
       while (resultSetgame.next()) {
           game = resultSetgame.getString("id");
       }

       // query to get the count
       String queryFindCount = "SELECT download_count FROM download WHERE title = '" + nameOfGame + "';";
       Statement statementGetCount = connectionGetCount.createStatement();
       ResultSet resultSetGetCount = statementGetCount.executeQuery(queryFindCount);
       while (resultSetGetCount.next()) {
           count = Integer.parseInt(resultSetgame.getString("download_count"));
       }
       count += 1;

       // query to increase the download and create one if it was not an id
       String queryIncrease = "REPLACE INTO download (account_id, game_id, count) VALUES ('"+  id + ", " + game + ", " + count + ");";

       PreparedStatement pstmt = connectionincreaseCount.prepareStatement(queryIncrease);
       pstmt.executeUpdate();
   }

    public static void main(String args[]) throws IOException, SQLException {


        ServerMain server = new ServerMain(5000);
    }
}