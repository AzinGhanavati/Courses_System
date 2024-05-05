/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ir.ac.kntu;


import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

public class MnuForOwner {
    public static void ownerEnterClass(Course ownCourse, User owner) {
        boolean varForLoop = true;
        while (varForLoop) {
            courseInfo(ownCourse);
            menu();
            String enteredNum = ScannerWrapper.getInstance().nextLine();
            switch (enteredNum) {
                case "1":
                    case1(ownCourse);
                    break;
                case "2":
                    deleteHomeWork(ownCourse);
                    break;
                case "3":
                    ownCourse.ownerAddStudent();
                    break;
                case "4":
                    ownCourse.deleteStudent();
                    break;
                case "5":
                    editHomeWorks(ownCourse);
                    break;
                case "6":
                    CourseHelper.showHomeworkToOwner(owner, ownCourse);
                    break;
                case "7":
                    owner.editClass(ownCourse);
                    break;
                case "8":
                    owner.deleteClass(ownCourse);
                    varForLoop = false;
                    break;
                case "9":
                    setProfessor(ownCourse, owner);
                    break;
                case "10":
                    HelperForQuestionBax.questionBaxPage(owner);
                    break;
                default:
                    return;
            }
        }
    }

    private static void menu() {
        System.out.println("Select the intended number");
        System.out.println("1-Add HomeWork\n2-Delete HomeWork\n3-Add student\n4-Delete student\n" +
                "5-Edit HomeWork\n6-Show Homework\n7-Edit class information\n8-delete class\n9-set professor" +
                "\n10-Question Bax\n");
    }

    private static void case1(Course ownCourse) {
        HomeWork homeWork = createHomeWork();
        if (homeWork == null) {
            return;
        } else {
            ownCourse.addHomeWorks(homeWork);
            System.out.println("Homework created successfully:)");
            System.out.println("Do you want to add question to this homework?");
            String enteredChoice = ScannerWrapper.getInstance().nextLine();
            if (enteredChoice.equals("Yes")) {
                homeWork.addQuestionToHomeWrk();
            }
            System.out.println("1-Previous page");//in behtare dar else bashe
            String back;
            do {
                back = ScannerWrapper.getInstance().nextLine();
            } while (!back.equals("1"));
        }
    }

    private static void courseInfo(Course ownCourse) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Name :" + ownCourse.getName() + "|" + "Institute :" + ownCourse.getInstitute() + "|"
                + "MasterName :" + ownCourse.getMasterName() + "\n" + "OwnerName :" +
                ownCourse.getOwnerName() + "|" + "AcademicYear :" +
                ownCourse.getAcademicYear() + "|" + "Explanation :" + ownCourse.getExplanation());
        System.out.println("--------------------------------------------------------------------------");
    }

    public static void setProfessor(Course ownCourse, User owner) {
        System.out.println("Choose the user");
        for (int i = 0; i < ownCourse.getUsers().size(); i++) {
            System.out.println(i + 1 + "-" + ownCourse.getUsers().get(i).getName());
        }
        int num = ScannerWrapper.getInstance().nextInt();
        if (num <= 0 || num > ownCourse.getUsers().size()) {
            return;
        }
        if (!ownCourse.getUsers().get(num - 1).isProfessor()) {
            ownCourse.getUsers().get(num - 1).setProfessor(true);
            ownCourse.addProfessor(ownCourse.getUsers().get(num - 1));
            System.out.println("Professor added successfully");
        } else {
            System.out.println("This user was professor");
        }
        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    public static HomeWork createHomeWork() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Create homework page");
        System.out.print("Name : ");
        String name = ScannerWrapper.getInstance().nextLine();
        if (name.equals("0")) {
            return null;
        }
        String start = getDate("StartTime :");
        if (start == null) {
            return null;
        }
        LocalDateTime startTime = HelperMethods.convertStringToDate(start);
        String dead = getDate("deadLine :");
        if (dead == null) {
            return null;
        }
        LocalDateTime deadLine = HelperMethods.convertStringToDate(dead);
        System.out.print("Explanation :");
        String explanation = ScannerWrapper.getInstance().nextLine();
        if (explanation.equals("0")) {
            return null;
        }
        String delayTime = getDate("DelayTime :");
        if (delayTime == null) {
            return null;
        }
        LocalDateTime sendWithDelay = HelperMethods.convertStringToDate(delayTime);
        System.out.print("DelayCoefficient : ");
        int delayCoefficient = ScannerWrapper.getInstance().nextInt();
        if (delayCoefficient == 0) {
            return null;
        }
        Boolean isTAble = getIsTable();
        if (isTAble == null) {
            return null;
        }
        Boolean isOnTest = getIsOnTest();
        if (isOnTest == null) {
            return null;
        }
        return new HomeWork(name, startTime, deadLine, explanation, delayCoefficient, sendWithDelay, isTAble, isOnTest);
    }

    @Nullable
    private static Boolean getIsTable() {
        System.out.println("Do want to show table for user?");
        String chooseAns;
        boolean isTAble = false;
        do {
            chooseAns = ScannerWrapper.getInstance().nextLine();
        } while (!chooseAns.equals("Yes") && !chooseAns.equals("No") && !chooseAns.equals("0"));
        if (chooseAns.equals("Yes")) {
            isTAble = true;
        } else if (chooseAns.equals("0")) {
            return null;
        }
        return isTAble;
    }

    @Nullable
    private static String getDate(String s) {
        System.out.print(s);
        String start;
        do {
            start = ScannerWrapper.getInstance().nextLine();
        } while (!start.equals("0") && !start.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));

        if (start.equals("0")) {
            return null;
        }
        return start;
    }

    @Nullable
    private static Boolean getIsOnTest() {
        System.out.print("This homework is on Test? ");
        String strAns;
        boolean isOnTest = true;
        do {
            strAns = ScannerWrapper.getInstance().nextLine();
        } while (!strAns.equals("Yes") && !strAns.equals("No") && !strAns.equals("0"));
        if (strAns.equals("No")) {
            isOnTest = false;
        } else if (strAns.equals("0")) {
            return null;
        }
        return isOnTest;
    }


    public static void editHomeWorks(Course course) {
        System.out.println("-----------------------------------------------------------------------------------------");
        if (course.getHomeWorks().size() == 0) {
            System.out.println("There is no homework in this course!");
            System.out.println("Exit");
            String exit1;
            do {
                exit1 = ScannerWrapper.getInstance().nextLine();
            } while (!exit1.equals("0"));
            return;
        }
        for (int i = 0; i < course.getHomeWorks().size(); i++) {
            int j = i + 1;
            System.out.println(j + "-" + course.getHomeWorks().get(i).getName());
        }
        System.out.println("Choose the homeWork");
        int num = ScannerWrapper.getInstance().nextInt();
        if (num > 0 && num <= course.getHomeWorks().size()) {
            HelperForHomeworkMethod.editSpecialHomeWork(course.getHomeWorks().get(num - 1));
        }
    }


    public static void deleteHomeWork(Course course) {
        System.out.println("-----------------------------------------------------------------------------------------");
        if (course.getHomeWorks().size() == 0) {
            System.out.println("There is no homework yet!");
            System.out.println("Exit");
            String exit1;
            do {
                exit1 = ScannerWrapper.getInstance().nextLine();
            } while (!exit1.equals("0"));
            return;
        }
        for (int i = 0; i < course.getHomeWorks().size(); i++) {
            int j = i + 1;
            System.out.println(j + "-" + course.getHomeWorks().get(i).getName());
        }
        System.out.println("Choose the HomeWork");
        int num = ScannerWrapper.getInstance().nextInt();
        if (num <= 0 || num > course.getHomeWorks().size()) {
            return;
        } else {
            course.getHomeWorks().remove(course.getHomeWorks().get(num - 1));
        }
        System.out.println("This HomeWork deleted successfully");
        int exit;
        do {
            exit = ScannerWrapper.getInstance().nextInt();
        } while (exit != 0);

    }

}

