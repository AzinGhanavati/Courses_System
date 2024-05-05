package ir.ac.kntu;

import java.util.ArrayList;

public class UserHelper {
    public static void searchClassesFromAccount(User user) {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.print("Name :\t");
            String name = ScannerWrapper.getInstance().nextLine();
            if (name.equals("0")) {
                return;
            } else {
                int exist1 = -1;
                for (int i = 0; i < user.getCourses().size(); i++) {
                    if (user.getCourses().get(i).getName().equals(name)) {
                        exist1 = i;
                        break;
                    }
                }
                int exist2 = -1;
                for (int j = 0; j < user.getCourses().size(); j++) {
                    if (user.getCourses().get(j).getName().equals(name)) {
                        exist2 = j;
                        break;
                    }
                }
                if (exist1 == -1 && exist2 == -1) {
                    System.out.println("This class doesn't exist!");
                    System.out.println("Exit(press <<0>>)");
                    String exit1;
                    do {
                        exit1 = ScannerWrapper.getInstance().nextLine();
                    } while (!exit1.equals("0"));
                    return;
                } else {
                    if (exist1 != -1) {
                        if (user.getCourses().get(exist1).getProfessor().contains(user)) {
                            System.out.println("You are Professor!!");
                            return;
                        }
                        Main.classesYouAreJustUser(user,user.getCourses().get(exist1));
                    }
                }
            }
        }
    }
}
