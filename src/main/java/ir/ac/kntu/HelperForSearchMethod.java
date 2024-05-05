/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ir.ac.kntu;


import java.util.ArrayList;

public class HelperForSearchMethod {

    public static void searchUser() {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Please Choose How you prefer searchUser");
            System.out.println("1-Email\t2-Id");
            String enterNum;
            do {
                enterNum = ScannerWrapper.getInstance().nextLine();
            } while (!enterNum.equals("0") && !enterNum.equals("1") && !enterNum.equals("2"));
            if (enterNum.equals("0")) {
                return;
            } else if (enterNum.equals("1")) {
                System.out.print("Email :\t");
                String email = ScannerWrapper.getInstance().nextLine();
                if (email.equals("0")) {
                    return;
                } else {
                    searchUserWithEmail(email);
                }
            } else {
                System.out.println("Id :");
                String id = ScannerWrapper.getInstance().nextLine();
                if (id.equals("0")) {
                    return;
                } else {
                    searchUserWithId(id);
                }
            }
        }
    }

    public static void searchUserWithEmail(String email) {
        int existUser = -1;
        for (int i = 0; i < AppliedList.getUsers().size(); i++) {
            if (email.equals(AppliedList.getUsers().get(i).getEmail())) {
                existUser = i;
                break;
            }
        }
        if (existUser == -1) {
            System.out.println("This user isn't exist!");
            System.out.println("1-Retry\nExit(press <<0>>)");
            String chooseNum;
            do {
                chooseNum = ScannerWrapper.getInstance().nextLine();
            } while (!chooseNum.equals("0"));
            return;
        } else {
            AppliedList.getUsers().get(existUser).printInfUser();
        }
    }

    public static void searchUserWithId(String id) {
        int existUser = -1;
        for (int i = 0; i < AppliedList.getUsers().size(); i++) {
            if (id.equals(AppliedList.getUsers().get(i).getId())) {
                existUser = i;
                break;
            }
        }
        if (existUser == -1) {
            System.out.println("This user isn't exist!");
            System.out.println("Exit(press <<0>>)");
            String chooseNum;
            do {
                chooseNum = ScannerWrapper.getInstance().nextLine();
            } while (!chooseNum.equals("0"));
            return;
        } else {
            ((User)(AppliedList.getUsers().get(existUser))).printInfUser();//to print score ane grade
        }
    }

    public static void searchForClasses() {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Please choose how you prefer search class");
            System.out.println("1-Institute\n2-MasterName\n3-ClassName\nExit(press <<0>>)");
            String enterNum;
            do {
                enterNum = ScannerWrapper.getInstance().nextLine();
            } while (!enterNum.equals("0") && !enterNum.equals("1") && !enterNum.equals("2")
                    && !enterNum.equals("3"));
            if (enterNum.equals("0")) {
                return;
            } else if (enterNum.equals("1")) {
                System.out.print("Institute :\t");
                String institute = ScannerWrapper.getInstance().nextLine();
                if (institute.equals("0")) {
                    return;
                } else {
                    searchClassWithInstitute(institute);
                }

            } else if (enterNum.equals("2")) {
                System.out.println("MasterName :\t");
                String masterName = ScannerWrapper.getInstance().nextLine();
                if (masterName.equals("0")) {
                    return;
                } else {
                    searchForClassWithMasterName(masterName);
                }
            } else {
                System.out.println("ClassName :\t");
                String name = ScannerWrapper.getInstance().nextLine();
                if (name.equals("0")) {
                    return;
                } else {
                    searchForClassWithClassName(name);
                }
            }
        }
    }

    public static void searchClassWithInstitute(String institute) {
        System.out.println("-----------------------------------------------------------------------------------------");
        ArrayList<Course> jointCoursesInInstitute = new ArrayList<>();
        int numExist = -1;
        for (int i = 0; i < AppliedList.getAllCourses().size(); i++) {
            if (institute.equals(AppliedList.getAllCourses().get(i).getInstitute())) {
                numExist = i;
                jointCoursesInInstitute.add(AppliedList.getAllCourses().get(i));
            }
        }
        if (numExist == -1) {
            System.out.println("This class doesn't exist");
            System.out.println("Press <<0>> to exit");
            String numForExit;
            do {
                numForExit = ScannerWrapper.getInstance().nextLine();
            } while (!numForExit.equals("0"));
            //return;
        } else {
            for (int i = 0; i < jointCoursesInInstitute.size(); i++) {
                int j = i + 1;
                System.out.println(j + "-" + jointCoursesInInstitute.get(i).getName());
            }
            System.out.println("Enter class(press correct number)");
            int num = ScannerWrapper.getInstance().nextInt();
            if (num <= 0 || num > jointCoursesInInstitute.size()) {
                return;
            }
            printClassInfAndJoin(jointCoursesInInstitute.get(num - 1));
        }

    }


    public static void searchForClassWithMasterName(String masterName) {
        System.out.println("--------------------------------------------------------------------------");
        ArrayList<Course> jointClassesInMaster = new ArrayList<>();
        int numExist = -1;
        for (int i = 0; i < AppliedList.getAllCourses().size(); i++) {
            if (masterName.equals(AppliedList.getAllCourses().get(i).getMasterName())) {
                jointClassesInMaster.add(AppliedList.getAllCourses().get(i));
                numExist = i;

            }
        }
        if (numExist == -1) {
            System.out.println("This class doesn't exist");
            System.out.println("Press <<0>> to exit");
            String numForExit;
            do {
                numForExit = ScannerWrapper.getInstance().nextLine();
            } while (!numForExit.equals("0"));
            //return;
        } else {
            for (int i = 0; i < jointClassesInMaster.size(); i++) {
                int j = i + 1;
                System.out.println(j + "-" + jointClassesInMaster.get(i).getName());
            }
            System.out.println("Enter class(press correct number)");
            int num = ScannerWrapper.getInstance().nextInt();
            if (num <= 0 || num > jointClassesInMaster.size()) {
                return;
            }
            printClassInfAndJoin(AppliedList.getAllCourses().get(numExist));
        }
    }

    public static void searchForClassWithClassName(String name) {
        int numExist = -1;
        for (int i = 0; i < AppliedList.getAllCourses().size(); i++) {
            if (name.equals(AppliedList.getAllCourses().get(i).getName())) {
                numExist = i;
                break;
            }
        }
        if (numExist == -1) {
            System.out.println("This class doesn't exist");
            System.out.println("Press <<0>> to exit");
            String numForExit;
            do {
                numForExit = ScannerWrapper.getInstance().nextLine();
            } while (!numForExit.equals("0"));
            return;
        } else {
            printClassInfAndJoin(AppliedList.getAllCourses().get(numExist));
        }
    }

    public static void printClassInfAndJoin(Course course) {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            String chooseNum;
            if (course.isClose()) {
                System.out.println("ClassNAme :" + course.getName() + "\n" + "MasterName :" + course.getMasterName() +
                        "\n" + "Institute :" + course.getInstitute());
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Exit(pres <<0>>");
                do {
                    chooseNum = ScannerWrapper.getInstance().nextLine();
                } while (!chooseNum.equals("0"));
                return;
            } else {
                System.out.println("ClassNAme :" + course.getName() + "\n" + "MasterName :" + course.getMasterName() +
                        "\n" + "Institute :" + course.getInstitute() + "\n" + "Explanation :" + course.getExplanation());
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("1-Join Class\nExit(press <<0>>)");
                do {
                    chooseNum = ScannerWrapper.getInstance().nextLine();
                } while (!chooseNum.equals("0") && !chooseNum.equals("1"));
                if (chooseNum.equals("0")) {
                    return;
                } else {
                    course.joinClass();
                }
            }
        }
    }



}

