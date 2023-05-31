package Client;

import Shared.ResponseBrowse;
import Shared.RequestCreate;
import Shared.RequestLogin;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientMain {
    private static Socket socket   = null;


    public ClientMain(String address, int port) {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
            runMenu();

            socket.close();

        } catch(IOException u)
        {
            System.out.println(u);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void runMenu() throws IOException, ClassNotFoundException {
        // to send data to the server
        DataOutputStream dos
                = new DataOutputStream(
                socket.getOutputStream());

        // to read data coming from the server
        BufferedReader br
                = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));

        // to read data from the keyboard
        BufferedReader kb
                = new BufferedReader(
                new InputStreamReader(System.in));

        // to send object to the server
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        // to receive object from server
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());


        String command;
        String message;
        String message1;
        String message2;
        String nameOfTheVideoGame;
        String message3;
        String message4;
        String responseMain;
        String response2;

        System.out.println("Welcome to STREAM \n1.I have an account(enter \"login\") \t 2.don't have an account(enter \"create\")");
        command = kb.readLine();
        if (command.equals("login")) {
            dos.writeBytes(command + "\n");
            message = br.readLine();
            System.out.println(message);
            String username = kb.readLine();
            String password = kb.readLine();
            RequestLogin newLogin = new RequestLogin(username, password);
            oos.writeObject(newLogin);
            message1 = br.readLine();
            System.out.println(message1);

            // if user enters correct username and password they can access to the next menu
            if (message1.contains("welcome")) {
                System.out.println("what do you want to do?\n1.browse the available video games(write \"browse\")\t2.view a game's details(write \"details\")" +
                        "\t3.download a video game(write \"download\")");

                message2 = kb.readLine();
                dos.writeBytes(message2 + "\n");
                responseMain = br.readLine();
                System.out.println(responseMain);
                if (responseMain.contains("browse")) {
                    message3 = br.readLine();
                    System.out.println(message3);
                    System.out.println("1");
                    ResponseBrowse rb = (ResponseBrowse) ois.readObject();
                    System.out.println("2");
                    System.out.println(rb.toString());
                    System.out.println("3");
                    String newline = kb.readLine();
                    dos.writeBytes(newline);

                } else if (responseMain.contains("details")) {
                    response2 = br.readLine();
                    System.out.println(response2);
//                    String nameOfTheGame = kb.readLine();
//                    System.out.println("1");
//                    dos.writeBytes(nameOfTheGame + "\n");
//                    System.out.println("2");

                    String nameOfGame = kb.readLine();
                    while (nameOfGame != null) {
                        dos.writeBytes(nameOfGame + "\n");
                        String details = br.readLine();
                        System.out.println(details);
                    }


                } else if (responseMain.contains("download")) {
                    nameOfTheVideoGame = kb.readLine();
                    dos.writeBytes(nameOfTheVideoGame + "\n");
                    String pathOfGame = br.readLine();
                    Path source= Paths.get(pathOfGame);
                    String gitKeep = "C:\\Users\\Farzaneh\\Eighth-Assignment-Steam\\src\\main\\java\\Client\\Downloads\\.gitkeep";
                    System.out.println("done");

                }



            } else {
                System.out.println("please try again");
                runMenu();
            }
        }
         else if (command.equals("create")) {
            dos.writeBytes(command + "\n");
            message = br.readLine();
            System.out.println(message);
            String username = kb.readLine();
            String password = kb.readLine();
            String dateOfBirth = kb.readLine();
            RequestCreate newCreate = new RequestCreate(username, password, dateOfBirth);
            oos.writeObject(newCreate);
            message1 = br.readLine();
            System.out.println(message1);

            if (message1.contains("welcome")) {

                System.out.println("what do you want to do?\n1.browse the available video games(write \"browse\")\t2.view a game's details(write \"details\")" +
                        "\t3.download a video game(write \"download\")");

                message2 = kb.readLine();
                dos.writeBytes(message2 + "\n");
                responseMain = br.readLine();
                System.out.println(responseMain);
                if (responseMain.contains("browse")) {
                    message3 = br.readLine();
                    System.out.println(message3);
                    System.out.println("1");
                    ResponseBrowse rb = (ResponseBrowse) ois.readObject();
                    System.out.println("2");
                    System.out.println(rb.toString());
                    System.out.println("3");
                    String newline = kb.readLine();
                    dos.writeBytes(newline);

                } else if (responseMain.contains("details")) {
                    response2 = br.readLine();
                    System.out.println(response2);
                    String nameOfGame = kb.readLine();
                    while (nameOfGame != null) {
                        dos.writeBytes(nameOfGame + "\n");
                        String details = br.readLine();
                        System.out.println(details);
                    }


                } else if (responseMain.contains("download")) {
                    nameOfTheVideoGame = kb.readLine();
                    dos.writeBytes(nameOfTheVideoGame + "\n");
                    String pathOfGame = br.readLine();
                    Path source= Paths.get(pathOfGame);
                    String gitKeep = "C:\\Users\\Farzaneh\\Eighth-Assignment-Steam\\src\\main\\java\\Client\\Downloads\\.gitkeep";
                    System.out.println("done");

                }

            } else {
                System.out.println("please try again");
                runMenu();
            }
        }
        else {
            System.out.println("please enter a valid command");
        }
        dos.close();
        br.close();
        kb.close();
    }

    public static void runMenu2() throws IOException {
        // to send data to the server
        DataOutputStream dos
                = new DataOutputStream(
                socket.getOutputStream());

        // to read data coming from the server
        BufferedReader br
                = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));

        // to read data from the keyboard
        BufferedReader kb
                = new BufferedReader(
                new InputStreamReader(System.in));

        // to send object to the server
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        // to receive object from server
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());


        String command;
        String message;
        String message1;
        String response1;

        System.out.println("please enter your username again");
        String username = kb.readLine();

        System.out.println("What do you want to do " + username + "?");
        System.out.println("1.browse video games(write \"browse\")\t2.see your downloads(write \"downloads\"");
        command = kb.readLine();
        if (command.equals("browse")) {
            message = "browse";
            dos.writeBytes(message);
            response1 = br.readLine();
            System.out.println(response1);


        } else if (command.equals("downloads")) {
            System.out.println("came to download");
        } else {
            System.out.println("please enter a valid command");

        }
    }

    public static void main(String[] args) throws IOException {
        ClientMain client = new ClientMain("127.0.0.1", 5000);

    }

}