package ir.ac.kntu;

import java.util.Scanner;

public class AdminHelper {

    public static void inviteToEventList(Admin admin) {
        System.out.println("-----------------------------------------------------------------------------------------");
        admin.showPrivteEventList();
        System.out.print("0. Exit\n"
                + "Select the Event : ");
        Scanner input = new Scanner(System.in);
        int indexEvent = input.nextInt();
        if (indexEvent == 0) {
            return;
        }
        if (indexEvent > AppliedList.getPrivateEvent().size()) {
            System.out.println("\nyour option NOT exist\n");
            return;
        }
        showUserList();
        System.out.print("0. Exit\n"
                + "Select the customer : ");
        int indexCustomer = input.nextInt();
        if (indexCustomer <= 0 || indexCustomer >AppliedList.getPrivateEvent().size()) {
            System.out.println("\nyour option NOT exist\n");
            return;
        }
        AppliedList.getUsers().get(indexCustomer - 1).addInvitation(AppliedList.getPrivateEvent().get(indexEvent - 1));
    }

    public static  void addAdmin() {
        showUserList();
        System.out.print("0. Exit\n"
                + "Select the user: ");
        Scanner input = new Scanner(System.in);
        int selector = input.nextInt();
        if (selector == 0) {
            return;
        }
        if (selector <= AppliedList.getUsers().size()) {
            Admin temp = AppliedList.getUsers().get(selector - 1).clonee();
            AppliedList.getInstance().addAdmin(temp);
            AppliedList.getUsers().remove(AppliedList.getUsers().get(selector - 1));
        } else {
            System.out.println("\nyour option not exist\n");
        }
    }

    public static void showUserList() {
        System.out.println("number : name\tEmail\t\tId");
        for (int i = 0; i < AppliedList.getUsers().size(); i++) {
            System.out.println((i + 1) + "      : " + AppliedList.getUsers().get(i).userIfo());
        }
        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    public static void setProfessor(Admin admin) {
        while (true) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Classes");
            for (int i = 0; i < AppliedList.getAllCourses().size(); i++) {
                System.out.println(i + 1 + "-" + AppliedList.getAllCourses().get(i).getName());
            }
            System.out.println("Choose a course:\n");
            int select = ScannerWrapper.getInstance().nextInt();
            if(select<=0 || select>AppliedList.getAllCourses().size()){
                return;
            }
            MnuForOwner.setProfessor(AppliedList.getAllCourses().get(select-1),admin);
        }
    }
}
