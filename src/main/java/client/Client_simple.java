package client;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client_simple {
    private Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    private ArrayList<Course> courses;


    public void run(){
        try{
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void choice(){
        try{
            System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste de cours:");
            System.out.println("1. Automne");
            System.out.println("2. Hiver");
            System.out.println("3. Ete");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();

            if (choice == 1){
                System.out.println("> Choix: 1");
                printCourses("Automne");
            }
            else if (choice == 2){
                System.out.println("> Choix: 2");
                printCourses("Hiver");
            }

            else if (choice == 3){
                System.out.println("> Choix: 3");
                printCourses("Ete");
            }
            else{
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Veuillez rentrer une valeur valide.");
            choice();
        }
    }

    public void printCourses(String session){
        try{
            connect();
            run();
            objectOutputStream.writeObject("CHARGER " + session);
            courses = (ArrayList<Course>) objectInputStream.readObject();
            System.out.println("Les cours offerts pendant la session d'"+session+" sont:");
            for(int i=0;i<courses.size();i++){
                System.out.println(i + ". "+courses.get(i).getCode()+"\t"+courses.get(i).getName());
            }
            nextChoice();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void nextChoice(){
        try{
            System.out.println("1. Consulter les cours offerts pour une autre session");
            System.out.println("2. Inscription à un cours");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            if(choice == 1){
                System.out.println("> Choix: 1");
                choice();
            }
            else if (choice == 2){
                System.out.println("> Choix: 2");
                createForm();
                nextChoice();
            }
            else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Veuillez rentrer une valeur valide.");
            nextChoice();
        }
    }

    public void createForm(){
        Scanner scan = new Scanner(System.in);
        String[] datas = new String[5];
        try{
            System.out.println("Veuillez saisir votre prénom: ");
            datas[0] = scan.next();
            System.out.println("Veuillez saisir votre nom: ");
            datas[1] = scan.next();
            System.out.println("Veuillez saisir votre email: ");
            datas[2] = scan.next();
            System.out.println("Veuillez saisir votre matricule: ");
            datas[3] = scan.next();
            System.out.println("Veuillez saisir le code du cours: ");
            datas[4] = scan.next();

            if (datas[3].length()!=8){
                throw new IllegalArgumentException();
            }
            else if(!isNumber(datas[3])){
                throw new IllegalArgumentException();
            }

                for (Course cours : courses) {
                    if (cours.getCode().equals(datas[4])) {
                        inscription(datas, cours);
                        String success = (String) objectInputStream.readObject();
                        System.out.println(success);
                        return;
                    }
                }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Veuillez fournir des informations exactes.");
            nextChoice();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void inscription(String[] datas, Course course){
        try{
            RegistrationForm form = new RegistrationForm(datas[0],datas[1],datas[2],datas[3],course);
            connect();
            run();
            objectOutputStream.writeObject("INSCRIRE");
            objectOutputStream.writeObject(form);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isNumber(String input){
        for (int i=0; i<input.length();i++){
            if (!Character.isDigit(input.charAt(i))){
            return false;
            }
        }
        return true;
    }

    public void connect() throws IOException {
        clientSocket = new Socket("127.0.0.1", 1337);
    }
}
