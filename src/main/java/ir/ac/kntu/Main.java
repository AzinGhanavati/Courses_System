/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ir.ac.kntu;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Enter any key to enter site :");
        Scanner input = new Scanner(System.in);
        String allowWord = input.next();
        Data.data();
        Data.eventTest();
        Data.addToQuestionBax();
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Welcome!");
            System.out.println("Select The Desired Option(Type The Number)" +
                    "\n**In each level press 0 to back**");
            System.out.println(""
                    + "1. Log in\n"
                    + "2. Register\n"
                    + "3. Continue as a guest\n"
                    + "4. Exit");
            String numForEnter = input.next();
            switch (numForEnter) {
                case "1":
                    logIn();
                    break;
                case "2":
                    register2();
                    break;
                case "3":
                    Guest.showMenu();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("\nyour option NOT exist\n");
                    break;
            }
        }
    }

    public static void register2() {
        boolean varForLoop = true;
        while (varForLoop) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Registration page");
            varForLoop = false;
            System.out.print("NAME:\t");
            String name = ScannerWrapper.getInstance().nextLine();
            if (name.equals("0")) {
                return;
            }
            System.out.print("USERNAME:\t");
            String userName = getString();
            if (userName == null) {
                return;
            }

            String email = getEmail();
            if (email == null) {
                return;
            }
            String password = getPassword();
            if (password == null) {
                return;
            }
            String id = getId();
            if (id == null) {
                return;
            }
            System.out.print("PHONNUMBER:\t");
            String phoneNumber = ScannerWrapper.getInstance().nextLine();
            if (phoneNumber.equals("0")) {
                return;
            }
            Customer addedUser = new Customer(name, userName, email, password, id, phoneNumber);/////
            AppliedList.getInstance().addUser(addedUser);
            System.out.println("Registration completed successfully:)");

        }
    }

    //    @Nullable
    private static String getId() {
        System.out.print("ID:\t");
        String id = ScannerWrapper.getInstance().nextLine();
        if (id.equals("0")) {
            return null;
        }
        for (int i = 0; i < AppliedList.getUsers().size(); i++) {
            if (AppliedList.getUsers().get(i).getId().equals(id)) {
                System.out.println("This user's id has already exist!");
                System.out.println("Exit");
                String exit1;
                do {
                    exit1 = ScannerWrapper.getInstance().nextLine();
                } while (!exit1.equals("0"));
                return null;
            }
        }
        return id;
    }

    //    @Nullable
    private static String getPassword() {
        System.out.print("PASSWORD:\t");
        String password = ScannerWrapper.getInstance().nextLine();
        if (password.equals("0")) {
            return null;
        }
        for (int i = 0; i < AppliedList.getUsers().size(); i++) {
            if (AppliedList.getUsers().get(i).getPassword().equals(password)) {
                System.out.println("This password has already exist!");
                System.out.println("Exit");
                String exit1;
                do {
                    exit1 = ScannerWrapper.getInstance().nextLine();
                } while (!exit1.equals("0"));
                return null;
            }
        }
        return password;
    }

    //    @Nullable
    private static String getEmail() {
        System.out.print("EMAIL:\t");
        String email = ScannerWrapper.getInstance().nextLine();
        if (email.equals("0")) {
            return null;
        }
        for (int i = 0; i < AppliedList.getUsers().size(); i++) {
            if (AppliedList.getUsers().get(i).getEmail().equals(email)) {
                System.out.println("This email has already exist!");
                System.out.println("Exit");
                String exit1;
                do {
                    exit1 = ScannerWrapper.getInstance().nextLine();
                } while (!exit1.equals("0"));
                return null;
            }
        }
        return email;
    }

    //    @Nullable
    private static String getString() {
        String userName = ScannerWrapper.getInstance().nextLine();
        if (userName.equals("0")) {
            return null;
        }
        for (int i = 0; i < AppliedList.getUsers().size(); i++) {
            if (AppliedList.getUsers().get(i).getUserName().equals(userName)) {
                System.out.println("This user name has already exist!");
                System.out.println("Exit");
                String exit1;
                do {
                    exit1 = ScannerWrapper.getInstance().nextLine();
                } while (!exit1.equals("0"));
                return null;
            }
        }
        return userName;
    }


    public static void classesYouAreJustUser(User user, Course course) {
        boolean varForLoop = true;
        while (varForLoop) {
            varForLoop = false;
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Name :" + course.getName() + "|" + "Institute :" + course.getInstitute() + "|"
                    + "MasterName :" + course.getMasterName() + "\n" + "OwnerName :" +
                    course.getOwnerName() + "|" + "AcademicYear :" +
                    course.getAcademicYear() + "|" + "Explanation :" + course.getExplanation());
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("1-HomeWorks\nExit(press <<0>>)");
            String chooseNum;
            do {
                chooseNum = ScannerWrapper.getInstance().nextLine();
            } while (!chooseNum.equals("0") && !chooseNum.equals("1"));
            if (chooseNum.equals("0")) {
                return;
            } else {
                course.showHomeWorks(user);
            }
        }
    }

    public static void logIn() {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Please Enter The Correct Information");
            System.out.print("UserName:\t");
            String userName = ScannerWrapper.getInstance().nextLine();
            if (userName.equals("0")) {
                return;
            }
            int numOfUser = findUserIndex(userName);
            int numOfAdmin = findAdminIndex(userName);
            if (numOfUser == -1 && numOfAdmin == -1) {
                System.out.println("This username doesn't exist");
                System.out.println("1-Retry\t2-Exit(press <<0>>)");
                String numForEnter;
                do {
                    numForEnter = ScannerWrapper.getInstance().nextLine();
                } while (!numForEnter.equals("0") && !numForEnter.equals("1"));
                if (numForEnter.equals("0")) {
                    return;
                }
            } else if ( numOfAdmin != -1) {
                adminPass(numOfAdmin);
            } else {
                System.out.print("Password:\t");
                String enteredPass = ScannerWrapper.getInstance().nextLine();
                if (AppliedList.getUsers().get(numOfUser).getPassword().equals(enteredPass)) {
                    AppliedList.getUsers().get(numOfUser).enterAfterRegistration();
                } else {
                    System.out.println("This password isn't for user");
                    System.out.println("1-Retry\t2-Exit(press <<0>>)");
                    String numOfEnter1;
                    do {
                        numOfEnter1 = ScannerWrapper.getInstance().nextLine();
                    } while (!numOfEnter1.equals("0") && !numOfEnter1.equals("1"));
                    if (numOfEnter1.equals("0")) {
                        return;
                    }
                }
            }
        }
    }

    private static int findAdminIndex(String userName) {
        int numOfAdmin = -1;
        for (int i = 0; i < AppliedList.getAdmins().size(); i++) {
            if (AppliedList.getAdmins().get(i).getUserName().equals(userName)) {
                numOfAdmin = i;
                break;
            }
        }
        return numOfAdmin;
    }

    private static int findUserIndex(String userName) {
        int numOfUser = -1;
        for (int i = 0; i < AppliedList.getUsers().size(); i++) {
            if (AppliedList.getUsers().get(i).getUserName().equals(userName)) {
                numOfUser = i;
                break;
            }
        }
        return numOfUser;
    }

    public static void adminPass(int index) {
        System.out.print("Password:\t");
        String enteredPass = ScannerWrapper.getInstance().nextLine();
        if (AppliedList.getAdmins().get(index).getPassword().equals(enteredPass)) {
            AppliedList.getAdmins().get(index).showMenu();
        } else {
            System.out.println("This password isn't for admin");
            System.out.println("Choose your desired number");
            System.out.println("1-Retry");
            String numOfEnter1;
            do {
                numOfEnter1 = ScannerWrapper.getInstance().nextLine();
            } while (!numOfEnter1.equals("1"));
        }
    }

    ////////////////////////////////////////////////////////////////////////////////


}

