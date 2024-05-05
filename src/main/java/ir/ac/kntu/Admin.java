package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;


public class Admin extends User {

    public Admin(String name, String userName, String email, String password, String id, String phoneNumber) {
        super(name, userName, email, password, id, phoneNumber);
    }


    public void showMenu() {
        while (true) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.print("Admin menu :\n" + "1 . create event\n" + "2 . create class\n" + "3 . show & edit class\n"//edit class can remove because it is in owner menu
                    + "4 . show & edit event\n" + "5 . question bank\n" + "6 . show user list\n" + "7 . invite to private event\n" + "8 . add admin\n" + "9 . set professor for a class\n" + "10. Enter a class\n" + "11. Enter Event\n" + "0 . Exit\n" + "Select The Desired Option : ");
            if (chooseOption()) {
                return;
            }
        }
    }

    private boolean chooseOption() {
        Scanner input = new Scanner(System.in);
        switch (input.next()) {
            case "1":
                createEvent();
                break;
            case "2":
                createClass();
                break;
            case "3":
                editClasss();
                break;
            case "4":
                editEvent();
                break;
            case "5":
                HelperForQuestionBax.questionBaxPage(this);
                break;
            case "6":
                AdminHelper.showUserList();
                break;
            case "7":
                inviteToEvent();
                break;
            case "8":
                AdminHelper.addAdmin();
                break;
            case "9":
                AdminHelper.setProfessor(this);
                break;
            case "10":
                adminEnterClass();
                break;
            case "11":
                enterEvent();
                break;
            case "0":
                return true;
            default:
                System.out.println("\nyour option NOT exist\n");
                break;
        }
        return false;
    }

    public void enterEvent() {
        while (true) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("1.PublicEvent(CommonEvent,EspecialEvent)\n2.PrivateEvent");
            String select = ScannerWrapper.getInstance().next();
            switch (select) {
                case "1":
                    for (int i = 0; i < AppliedList.getPublicEvent().size(); i++) {
                        System.out.println(i + 1 + "-" + AppliedList.getPublicEvent().get(i).getName());
                    }
                    int choose = ScannerWrapper.getInstance().nextInt();
                    if (choose <= 0 || choose > AppliedList.getPublicEvent().size()) {
                        return;
                    }
                    eventMenu(AppliedList.getPublicEvent().get(choose - 1));
                    break;
                case "2":
                    for (int i = 0; i < AppliedList.getPrivateEvent().size(); i++) {
                        System.out.println(i + 1 + "-" + AppliedList.getPrivateEvent().get(i).getName());
                    }
                    int choose1 = ScannerWrapper.getInstance().nextInt();
                    if (choose1 <= 0 || choose1 > AppliedList.getPrivateEvent().size()) {
                        return;
                    }
                    eventMenu(AppliedList.getPrivateEvent().get(choose1 - 1));
                    break;
                default:
                    return;
            }
        }
    }

    public void eventMenu(Event event) {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("1.Add question\n" + "2.Edit question\n" + "3.Mark answers\n" + "4.See all the answers of especial user\n" + "0.Exit");
        String choose = ScannerWrapper.getInstance().next();
        switch (choose) {
            case "1":
                HelperForEvent.addQuestionToEvent(event);
                break;
            case "2":
                HelperForEvent.editQuestion(event);
                break;
            case "3":
                event.showQuestion();
                int choose1 = ScannerWrapper.getInstance().nextInt();
                if (choose1 <= 0 || choose1 > event.getQuestions().size()) {
                    return;
                }
                event.getQuestions().get(choose1 - 1).markAnswers();
                break;
            case "4":
                event.showQuestion();
                int choose2 = ScannerWrapper.getInstance().nextInt();
                if (choose2 <= 0 || choose2 > event.getQuestions().size()) {
                    return;
                }
                event.getQuestions().get(choose2 - 1).allUserAnswer();
                break;
            default:
                return;
        }
    }

    public void adminEnterClass() {
        while (true) {
            System.out.println("Select the number of class:");
            for (int i = 0; i < AppliedList.getAllCourses().size(); i++) {
                System.out.println(i + 1 + "-" + AppliedList.getAllCourses().get(i).getName());
            }
            System.out.println("Case:");
            int enteredNum = ScannerWrapper.getInstance().nextInt();
            if (enteredNum <= 0 || enteredNum > AppliedList.getAllCourses().size()) {
                return;
            }
            MnuForOwner.ownerEnterClass(AppliedList.getAllCourses().get(enteredNum - 1), this);
        }
    }

    public void editClasss() {//////////////
        //show class list
        //select class
        //editClass(.get(index);
        while (true) {
            System.out.println("Select the number of class:");
            for (int i = 0; i < AppliedList.getAllCourses().size(); i++) {
                System.out.println(i + 1 + "-" + AppliedList.getAllCourses().get(i).getName());
            }
            System.out.println("Case:");
            int enteredNum = ScannerWrapper.getInstance().nextInt();
            if (enteredNum <= 0 || enteredNum > AppliedList.getAllCourses().size()) {
                return;
            }
            editClass(AppliedList.getAllCourses().get(enteredNum - 1));
        }
    }

    public void createEvent() {
        while (true) {
            System.out.print("1. create especial event\n" + "2. create private event\n" + "3. create common event\n" + "0. Exit\n" + "Select The Desired Option : ");
            String input = ScannerWrapper.getInstance().next();
            switch (input) {
                case "1":
                    createEspecialEvent();
                    break;
                case "2":
                    createPrivateEvent();
                    break;
                case "3":
                    createCommonEvent();
                    break;
                default:
                    System.out.println("\nyour option NOT exist\n");
                    return;
            }
        }
    }

    public void createEspecialEvent() {
        System.out.print("Event name ?\n");
        String name = ScannerWrapper.getInstance().next();
        if (name.equals("0")) {
            return;
        }
        System.out.print("Event start time ? \n");
        String start;
        do {
            start = ScannerWrapper.getInstance().nextLine();
        } while (!start.equals("0") && !start.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));
        if (start.equals("0")) {
            return;
        }
        System.out.print("Event dead line ?\n");
        String dead;
        do {
            dead = ScannerWrapper.getInstance().nextLine();
        } while (!dead.equals("0") && !dead.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));
        if (dead.equals("0")) {
            return;
        }
        System.out.print("Most group members(except 0) ?\n");
        int most = ScannerWrapper.getInstance().nextInt();
        if (most == 0) {
            return;
        }
        EspecialEvent temp = new EspecialEvent(name, HelperMethods.convertStringToDate(start), HelperMethods.convertStringToDate(dead), 100, most);
        AppliedList.getInstance().addPublicEvent(temp);
        AppliedList.getInstance().addEvent(temp);
    }

    public void createPrivateEvent() {
        System.out.print("Event name ?\n");
        String name = ScannerWrapper.getInstance().next();
        if (name.equals("0")) {
            return;
        }
        System.out.print("Event start time ?\n");
        String start;
        do {
            start = ScannerWrapper.getInstance().nextLine();
        } while (!start.equals("0") && !start.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));
        if (start.equals("0")) {
            return;
        }
        System.out.print("Event dead line ?\n");
        String dead;
        do {
            dead = ScannerWrapper.getInstance().nextLine();
        } while (!dead.equals("0") && !dead.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));
        if (dead.equals("0")) {
            return;
        }
        PrivateEvent temp = new PrivateEvent(name, HelperMethods.convertStringToDate(start), HelperMethods.convertStringToDate(dead), 20);
        AppliedList.getInstance().addPrivateEvent(temp);
        AppliedList.getInstance().addEvent(temp);
    }

    public void createCommonEvent() {
        System.out.print("Event name ?\n");
        String name = ScannerWrapper.getInstance().next();
        if (name.equals("0")) {
            return;
        }
        System.out.print("Event start time ?\n");
        String start;
        do {
            start = ScannerWrapper.getInstance().nextLine();
        } while (!start.equals("0") && !start.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));
        if (start.equals("0")) {
            return;
        }
        System.out.print("Event dead line ?\n");
        String dead;
        do {
            dead = ScannerWrapper.getInstance().nextLine();
        } while (!dead.equals("0") && !dead.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));
        if (dead.equals("0")) {
            return;
        }
        System.out.println("Event successfully created");
        CommonEvent temp = new CommonEvent(name, HelperMethods.convertStringToDate(start), HelperMethods.convertStringToDate(dead), 50);
        AppliedList.getInstance().addPublicEvent(temp);
        AppliedList.getInstance().addEvent(temp);
    }

    static void showPublicEventList() {
        System.out.println("info : name\t\tstartTime\t\tdeadLine\t\tRemainingCapacity");
        for (int i = 0; i < AppliedList.getPublicEvent().size(); i++) {
            System.out.println((i + 1) + " : " + AppliedList.getPublicEvent().get(i).eventInfo());
        }
    }

    public void showPrivteEventList() {
        System.out.println("info : name\tstartTime\tdeadLine\tRemaining capacity");
        for (int i = 0; i < AppliedList.getPrivateEvent().size(); i++) {
            System.out.println((i + 1) + " : " + AppliedList.getPrivateEvent().get(i).eventInfo());
        }
    }

    public void editEvent() {
        System.out.print("1. edit event info\n" + " 2. delete event\n" + "0. Exit\n" + "Select The Desired Option : ");
        Scanner input = new Scanner(System.in);
        switch (input.next()) {
            case "1":
                editEventInfo();
                break;
            case "2":
                deleteEvent();
                break;
            case "0":
                return;
            default:
                System.out.println("\nyour option NOT exist\n");
        }
    }

    public void editEventInfo() {
        while (true) {
            System.out.print("1. edit public event\n" + " 2. edit private event\n" + "0. Exit\n" + "Select The Desired Option : ");
            Scanner input = new Scanner(System.in);
            switch (input.next()) {
                case "1":
                    editPublicEvent();
                    break;
                case "2":
                    editPrivateEvent();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\nyour option NOT exist\n");
            }
        }
    }

    public void editPublicEvent() {
        showPublicEventList();
        System.out.print("0. Exit\n" + "Select the Event : ");
        int selector = ScannerWrapper.getInstance().nextInt();
        if (selector <= 0 || selector > AppliedList.getPublicEvent().size()) {
            return;
        }
        AppliedList.getPublicEvent().get(selector - 1).editInfo();
        System.out.println("Exit(press 0)");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));

    }

    public void editPrivateEvent() {
        showPrivteEventList();
        System.out.print("0. Exit\n" + "Select the Event : ");
        int selector = ScannerWrapper.getInstance().nextInt();
        if (selector <= 0 || selector > AppliedList.getPrivateEvent().size()) {
            return;
        }
        AppliedList.getPrivateEvent().get(selector - 1).editInfo();
        System.out.println("Exit(press 0)");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    public void deleteEvent() {///add delete from customer list
        while (true) {
            System.out.print("1. delete public event\n" + " 2. delete private event\n" + "0. Exit\n" + "Select The Desired Option : ");
            Scanner input = new Scanner(System.in);
            switch (input.next()) {
                case "1":
                    deletePublicEvent();
                    break;
                case "2":
                    deletePrivateEvent();
                    break;
                default:
                    System.out.println("\nyour option NOT exist\n");
                    return;
            }
        }
    }

    public void deletePublicEvent() {
        showPublicEventList();
        System.out.print("0. Exit\n" + "Select the Event : ");
        int selector = ScannerWrapper.getInstance().nextInt();
        if (selector <= 0 || selector > AppliedList.getPublicEvent().size()) {
            return;
        }
        AppliedList.getPublicEvent().get(selector).deleteAllEntry();
        AppliedList.getPublicEvent().remove(AppliedList.getPublicEvent().get(selector - 1));
        System.out.println("Exit(press 0)");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    public void deletePrivateEvent() {
        showPrivteEventList();
        System.out.print("0. Exit\n" + "Select the Event : ");
        Scanner input = new Scanner(System.in);
        int selector = input.nextInt();
        if (selector <= 0 || selector > AppliedList.getPrivateEvent().size()) {
            return;
        }
        AppliedList.getPrivateEvent().get(selector).deleteAllEntry();
        AppliedList.getPrivateEvent().remove(AppliedList.getPrivateEvent().get(selector - 1));
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    public void inviteToEvent() {
        while (true) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.err.println("1. Invite by search user\n" + "2. Invite by selecting from the list\n" + "0. Exit\n" + "Select The Desired Option : ");
            String input = ScannerWrapper.getInstance().next();
            switch (input) {
                case "1":
                    inviteToEventSearch();
                    break;
                case "2":
                    AdminHelper.inviteToEventList(this);
                    break;
                default:
                    System.out.println("\nyour option NOT exist\n");
                    return;
            }
        }
    }

    public void inviteToEventSearch() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.err.print("0.Exit\n" + "Enter the desired national number or email : \n");
        String scan = ScannerWrapper.getInstance().next();
        if (scan.endsWith("0")) {
            return;
        }
        boolean find = false;
        for (int i = 0; i < AppliedList.getUsers().size(); i++) {
            if (AppliedList.getUsers().get(i).getId().equals(scan) || AppliedList.getUsers().get(i).getEmail().equals(scan)) {
                find = true;
                showPrivteEventList();
                System.out.print("0. Exit\n" + "Select the Event : ");
                int indexEvent = ScannerWrapper.getInstance().nextInt();
                if (indexEvent == 0) {
                    return;
                }
                if (indexEvent > AppliedList.getPrivateEvent().size()) {
                    System.out.println("\nyour option NOT exist\n");
                    return;
                }
                AppliedList.getUsers().get(i).addInvitation(AppliedList.getPrivateEvent().get(indexEvent - 1));
            }
        }
        if (!find) {
            System.out.println("User NOT found !");
        }
    }
}
