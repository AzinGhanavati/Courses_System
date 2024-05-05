package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User {

    private double score = 0;

    private int gradation = 0;

    private ArrayList<Event> invitations;

    private ArrayList<Event> events;


    public Customer(String name, String userName, String email, String password, String id, String phoneNumber) {
        super(name, userName, email, password, id, phoneNumber);
        this.events = new ArrayList<>();
        this.invitations = new ArrayList<>();
    }

    @Override
    public ArrayList<Event> getInvitations() {
        return new ArrayList<>(invitations);
    }

    public void addInvitation(PrivateEvent privateEvent) {
        invitations.add(privateEvent);
    }

    public void showMenu() {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Customer menu :\n"
                    + "1. join event\n"
                    + "2. Continue the race\n"
                    + "0. Exit\n"
                    + "Select The Desired Option : ");
            Scanner input = new Scanner(System.in);
            switch (input.next()) {
                case "1":
                    joinEvent();
                    break;
                case "2":
                    continueTheRace();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\nyour option NOT exist\n");
                    break;
            }
        }
    }

    static void showAllClassList() {
        System.out.println("info : name\tinstitute\tmasterName\townerName");
        for (int i = 0; i < AppliedList.getAllCourses().size(); i++) {
            System.out.println((i + 1) + " : " + AppliedList.getAllCourses().get(i).courseInfo());
        }
    }

    public void joinEvent() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.print("1. EspecialEvent & Common Event\n"
                + "2. Private Event\n"
                + "0. Exit\n"
                + "Select the Class to join : ");
        Scanner input = new Scanner(System.in);
        switch (input.next()) {
            case "1":
                joinPublicEvent();
                break;
            case "2":
                joinPrivateEvent();
                break;
            case "0":
                return;
            default:
                System.out.println("\nyour option NOT exist\n");
                return;
        }
    }

    public void joinPublicEvent() {
        System.out.println("-----------------------------------------------------------------------------------------");
        Admin.showPublicEventList();
        System.out.print("0. Exit\n"
                + "Select the Event to join : ");
        Scanner input = new Scanner(System.in);
        int index = input.nextInt();
        if (index == 0 || index > AppliedList.getPublicEvent().size()) {
            System.out.println("\nyour option NOT exist\n");
        } else if (AppliedList.getPublicEvent().get(index - 1).addEntry(this)) {
            events.add(AppliedList.getPublicEvent().get(index - 1));
            System.out.println("You joined the event successfully");
        }
    }

    public void showInvitationsList() {
        System.out.println("-----------------------------------------------------------------------------------------");
        if (invitations.size() == 0) {
            System.out.println("There isn't any invitation!");
            return;
        }
        System.out.println("info : name\tstartTime\tdeadLine\tRemaining capacity");
        for (int i = 0; i < invitations.size(); i++) {
            System.out.println((i + 1) + " : " + invitations.get(i).eventInfo());
        }
    }

    public void joinPrivateEvent() {
        System.out.println("-----------------------------------------------------------------------------------------");
        showInvitationsList();
        System.out.print("0. Exit\n"
                + "Select the Event to join : ");
        Scanner input = new Scanner(System.in);
        int index = input.nextInt();
        if (index == 0 || index > AppliedList.getPrivateEvent().size()) {
            System.out.println("\nyour option NOT exist\n");
            return;
        }
        int index2 = AppliedList.getPrivateEvent().indexOf(invitations.get(index - 1));
        if (AppliedList.getPrivateEvent().get(index2).addEntry(this)) {
            events.add(AppliedList.getPrivateEvent().get(index2));
            invitations.remove(index - 1);//check for index
            System.out.println("You joined the event successfully");
        }
    }

    public void deleteEvent(Event event) {
        events.remove(event);
    }

    public void showCustomerEvent() {
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + " : " + events.get(i).getName());
        }
    }

    public void continueTheRace() {
        while (true) {
            if (events.size() == 0) {
                System.out.println("There isn't any event!");
                return;
            }
            showCustomerEvent();
            System.out.println("Select the Event : ");
            int select = ScannerWrapper.getInstance().nextInt();
            if (select <= 0 || select > events.size()) {
                return;
            }
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println(events.get(select - 1).eventInfo());
            if (events.get(select - 1) instanceof EspecialEvent especialEvent) {
                methodForeEspecialEvent(especialEvent);
            } else if (events.get(select - 1) instanceof CommonEvent commonEvent) {
                methodForCommonEvent(commonEvent);
            } else if (events.get(select - 1) instanceof PrivateEvent privateEvent) {
                methodForPrivateEvent(privateEvent);
            }
        }
    }

    public void methodForeEspecialEvent(EspecialEvent especialEvent) {
        while (true) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("1.Show and answer\n" +
                    "2.All answers\n" +
                    "3.Final answers\n" +
                    "4.Score Board\n" +
                    "0.Exit\n");
            int num = ScannerWrapper.getInstance().nextInt();
            switch (num) {
                case 1:
                    especialEvent.showQ(especialEvent.findGroup(this));
                    break;
                case 2:

                    especialEvent.allAnswersInEvent(especialEvent.findGroup(this).getMembers().get(0));
                    break;
                case 3:
                    especialEvent.finalAnswersInEvent(especialEvent.findGroup(this).getMembers().get(0));
                    break;
                case 4:
                    especialEvent.donate();
                default:
                    return;
            }
        }
    }

    public void methodForCommonEvent(CommonEvent commonEvent) {
        while (true) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("1.Show and answer\n" +
                    "2.All answers\n" +
                    "3.Final answers\n" +
                    "4.Score Board\n" +
                    "0.Exit\n");
            int num = ScannerWrapper.getInstance().nextInt();
            switch (num) {
                case 1:
                    commonEvent.showQ(this);
                    break;
                case 2:
                    commonEvent.allAnswersInEvent(this);
                    break;
                case 3:
                    commonEvent.finalAnswersInEvent(this);
                    break;
                case 4:
                    commonEvent.donate();
                default:
                    return;
            }
        }
    }

    public void methodForPrivateEvent(PrivateEvent privateEvent) {
        while (true) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("1.Show and answer\n" +
                    "2.All answers\n" +
                    "3.Final answers\n" +
                    "4.Score Board\n" +
                    "0.Exit\n");
            int num = ScannerWrapper.getInstance().nextInt();
            switch (num) {
                case 1:
                    privateEvent.showQ(this);
                    break;
                case 2:
                    privateEvent.allAnswersInEvent(this);
                    break;
                case 3:
                    privateEvent.finalAnswersInEvent(this);
                    break;
                case 4:
                    privateEvent.donate();
                default:
                    return;
            }
        }
    }

    public void addScore(double newScore) {
        score += newScore;
        gradation = (int) score / 50;
    }

    public Admin clonee() {////ers bari
        Admin clone = new Admin(getName(), getUserName(), getEmail(), getPassword(), getId(), getPhoneNumber());
        return clone;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "score:" + score + "\n" +
                "gradation:" + gradation +
                "\n";
    }

    @Override
    public void printInfUser() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(toString());
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Press <<0>> to exit");
        String enterNum;
        do {
            enterNum = ScannerWrapper.getInstance().nextLine();
        } while (!enterNum.equals("0"));

    }

    public void addEvent(Event event) {
        if (!events.contains(event)) {
            events.add(event);
        } else {
            System.out.println("This event has already exist!");
        }
    }
}
