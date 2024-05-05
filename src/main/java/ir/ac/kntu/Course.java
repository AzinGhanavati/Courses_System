/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ir.ac.kntu;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Course {
    private String name;

    private String institute;

    private String masterName;

    private int academicYear;

    private boolean isPrivate;

    private boolean isClose;

    private String password;

    private String explanation;

    private String ownerName;

    private ArrayList<HomeWork> homeWorks;

    private ArrayList<User> users;

    private User owner;

    private ArrayList<User> professor;

    public Course() {
    }

    public Course(String name, String institute, String masterName, int academicYear,
                  String explanation, String ownerName, boolean isClose, boolean isPrivate, String password) {
        this.name = name;
        this.institute = institute;
        this.masterName = masterName;
        this.academicYear = academicYear;
        this.isPrivate = isPrivate;
        this.isClose = isClose;
        this.password = password;
        this.explanation = explanation;
        this.ownerName = ownerName;
        homeWorks = new ArrayList<>();
        users = new ArrayList<>();
        this.professor = new ArrayList<>();
    }

    public Course(String name, String institute, String masterName, int academicYear,
                  String explanation, String ownerName, boolean isClose) {
        this.name = name;
        this.institute = institute;
        this.masterName = masterName;
        this.academicYear = academicYear;
        //this.isPrivate = isPrivate;
        this.isClose = isClose;
        //this.isPassword=isPassword;
        //this.password = password;
        this.explanation = explanation;
        this.ownerName = ownerName;
        homeWorks = new ArrayList<>();
        users = new ArrayList<>();
        this.professor = new ArrayList<>();

    }

    public Course(String name, String institute, String masterName, int academicYear,
                  String explanation, String ownerName, boolean isClose, boolean isPrivate) {
        this.name = name;
        this.institute = institute;
        this.masterName = masterName;
        this.academicYear = academicYear;
        this.isPrivate = isPrivate;
        this.isClose = isClose;
        this.explanation = explanation;
        this.ownerName = ownerName;
        homeWorks = new ArrayList<>();
        this.professor = new ArrayList<>();
        users = new ArrayList<>();

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setHomeWorks(ArrayList<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setProfessor(ArrayList<User> professor) {
        this.professor = professor;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getName() {
        return name;
    }

    public ArrayList<HomeWork> getHomeWorks() {
        return new ArrayList<>(homeWorks);
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getInstitute() {
        return institute;
    }

    public String getMasterName() {
        return masterName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<User> getProfessor() {
        return new ArrayList<>(professor);
    }

    public void addProfessor(User user) {
        professor.add(user);
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isClose() {
        return isClose;
    }

    public boolean isPrivate() {
        return isPrivate;
    }


    public void addUser(User user) {
        users.add(user);

    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void addHomeWorks(HomeWork homeWork) {
        homeWorks.add(homeWork);
    }

    public void joinClass() {
        System.out.println("--------------------------------------------------------------------------");
        if (this.isPrivate) {
            System.out.println("class Password :\t");
            String password = ScannerWrapper.getInstance().nextLine();
            if (password.equals("0")) {
                return;
            }
            if (password.equals(this.password)) {
                System.out.print("Email :\t");
                String email = ScannerWrapper.getInstance().nextLine();
                if (email.equals("0")) {
                    return;
                }
                if (HelperMethods.isExistUser(email) == -1) {
                    return;//back to inf page of class
                }
                System.out.println(checkOwner(email));
                if (checkOwner(email)) {
                    return;
                }
                if (checkProfessor(email)) {
                    return;
                }
                if (checkExist(email)) {
                    return;
                }
                addUser(AppliedList.getUsers().get(HelperMethods.isExistUser(email)));
                AppliedList.getUsers().get(HelperMethods.isExistUser(email)).addCourses(this);
                System.out.println("You joined successfully\nExit(press <<0>>)");
                String choosePage;
                do {
                    choosePage = ScannerWrapper.getInstance().nextLine();
                } while (!choosePage.equals("0"));
            } else {
                System.out.println("Password isn't correct!\nExit(press <<0>>)");
                String exit;
                do {
                    exit = ScannerWrapper.getInstance().nextLine();
                } while (!exit.equals("0"));
            }
        } else {
            getEmail();
        }
    }

    private void getEmail() {
        System.out.print("Email :\t");
        String email = ScannerWrapper.getInstance().nextLine();
        if (email.equals("0")) {
            return;
        }
        if (HelperMethods.isExistUser(email) == -1) {
            return;
        }
        if (checkOwner(email)) {
            return;
        }
        if (checkExist(email)) {
            return;
        }
        addUser(AppliedList.getUsers().get(HelperMethods.isExistUser(email)));
        System.out.println("You joined successfully\nExit(press <<0>>)");
        String choosePage;
        do {
            choosePage = ScannerWrapper.getInstance().nextLine();
        } while (!choosePage.equals("0"));
    }

    private boolean checkExist(String email) {
        if (users.contains(AppliedList.getUsers().get(HelperMethods.isExistUser(email)))) {
            System.out.println("This user has already joined the class!");
            System.out.println("Exit(pres <<0>>)");
            String exit1;
            do {
                exit1 = ScannerWrapper.getInstance().nextLine();
            } while (!exit1.equals("0"));
            return true;
        }
        return false;
    }

    private boolean checkOwner(String email) {//check
        if (AppliedList.getUsers().get(HelperMethods.isExistUser(email)).getOwnerCourse().contains(this)) {
            System.out.println(this.getOwner().getName());
            System.out.println("You are owner!");
            System.out.println("Do you want to join the class?(Yes/No)");
            String exit2;
            do {
                exit2 = ScannerWrapper.getInstance().nextLine();
            } while (!exit2.equals("No") && !exit2.equals("Yes"));
            if (exit2.equals("Yes")) {
                MnuForOwner.ownerEnterClass(this, AppliedList.getUsers().get(HelperMethods.isExistUser(email)));
            }
            return true;
        }
        return false;
    }

    private boolean checkProfessor(String email) {//should check for size 0
        if (professor.contains(AppliedList.getUsers().get(HelperMethods.isExistUser(email)))) {
            System.out.println("You are professor!");
            System.out.println("Do you want to join the class?(Yes/No)");
            String exit2;
            do {
                exit2 = ScannerWrapper.getInstance().nextLine();
            } while (!exit2.equals("No") && !exit2.equals("Yes"));
            if (exit2.equals("Yes")) {
                MnuForOwner.ownerEnterClass(this, AppliedList.getUsers().get(HelperMethods.isExistUser(email)));
            }
            return true;
        }
        return false;
    }

    public void showHomeWorks(User user) {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            if (homeWorks.size() == 0) {
                System.out.println("No homework has been created!");
                String exit1;
                do {
                    exit1 = ScannerWrapper.getInstance().nextLine();
                } while (!exit1.equals("0"));
                return;
            }
            for (int i = 0; i < homeWorks.size(); i++) {
                int j = i + 1;
                System.out.println(j + "-" + homeWorks.get(i).getName());
            }
            System.out.println("Choose HomeWork");
            int num = ScannerWrapper.getInstance().nextInt();
            if (num <= 0 || num > homeWorks.size()) {
                return;
            }
            if (LocalDateTime.now().compareTo(homeWorks.get(num - 1).getStartTime()) < 0) {
                System.out.println("It's not time to show question!");
                System.out.println("Press <<0>> to exit");
                String back;
                do {
                    back = ScannerWrapper.getInstance().nextLine();
                } while (!back.equals("0"));
                continue;
            }
            if (homeWorks.get(num - 1).isOnTest()) {
                System.out.println("This Homework is on test!");
                String exit1;
                do {
                    exit1 = ScannerWrapper.getInstance().nextLine();
                } while (!exit1.equals("0"));
                continue;
            }
            showHomeWorkInf(homeWorks.get(num - 1), user);
        }
    }

    public void showHomeWorkInf(HomeWork homeWork, User user) {
        while (true) {
            showQuestionInfo(homeWork);
            String chooseNum = ScannerWrapper.getInstance().nextLine();
            switch (chooseNum) {
                case "2":
                    if (!homeWork.isTable()) {
                        System.out.println("Unable to view table!");
                        String exit1;
                        do {
                            exit1 = ScannerWrapper.getInstance().nextLine();
                        } while (!exit1.equals("0"));
                        continue;
                    } else {
                        homeWork.scoreBoard();
                    }
                    break;
                case "1":
                    System.out.println("Choose the question:(Enter the number of question)");
                    int num = ScannerWrapper.getInstance().nextInt();
                    if (num <= 0 || num > homeWork.getQuestions().size()) {
                        return;
                    }
                    if (homeWork.getQuestions().size() == 0) {
                        System.out.println("No question has been created");
                        String exit1;
                        System.out.println("Exit");
                        do {
                            exit1 = ScannerWrapper.getInstance().nextLine();
                        } while (!exit1.equals("0"));
                        return;
                    }
                    homeWork.getQuestions().get(num - 1).showQuestionAndUploadAns(user, homeWork);
                    break;
                case "3":
                    HelperForHomeworkMethod.allAnswers(homeWork, user);
                    break;
                case "4":
                    HelperForHomeworkMethod.finalAnswers(homeWork, user);
                    break;
                default:
                    return;
            }
        }
    }

    private void showQuestionInfo(HomeWork homeWork) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("HomeworkName :" + homeWork.getName() + "\n" + "Explanation :" +
                homeWork.getExplanation() + "\n"
                + "StartTime :" + homeWork.getStartTime() + "\t" + "DeadLine :" + homeWork.getDeadLine());
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < homeWork.getQuestions().size(); i++) {
            int j = i + 1;
            System.out.println(j + "-" + homeWork.getQuestions().get(i).getName());
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("*page for user*");
        System.out.println("1-Choose question\n2-ScoreBoard\n3-All answers\n4-Final answers");
    }

    public void ownerAddStudent() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Please enter student's email:");
        String email = ScannerWrapper.getInstance().nextLine();
        if (email.equals("0")) {
            return;
        } else {
            if (HelperMethods.isExistUser(email) == -1) {
                System.out.println("This user doesn't exist");
                String exit1;
                do {
                    exit1 = ScannerWrapper.getInstance().nextLine();
                } while (!exit1.equals("0"));
            } else {
                if (HelperMethods.isExistUser(email) == -1) {
                    return;
                }
                addUser(AppliedList.getUsers().get(HelperMethods.isExistUser(email)));
                AppliedList.getUsers().get(HelperMethods.isExistUser(email)).addCourses(this);
                System.out.println("This user added successfully");
                System.out.println("Exit");
                String exit2;
                do {
                    exit2 = ScannerWrapper.getInstance().nextLine();
                } while (!exit2.equals("0"));
            }
        }
    }

    public void deleteStudent() {
        System.out.println("--------------------------------------------------------------------------");
        if (users.size() == 0) {
            System.out.println("There is no user in this class!");
            System.out.println("Exit");
            String exit1;
            do {
                exit1 = ScannerWrapper.getInstance().nextLine();
            } while (!exit1.equals("0"));
            return;
        }
        for (int i = 0; i < users.size(); i++) {
            int j = i + 1;
            System.out.println(j + "-" + users.get(i).getName());
        }
        System.out.println("Choose the student :");
        int num = ScannerWrapper.getInstance().nextInt();
        if (num <= 0 || num > users.size()) {
            return;
        } else {
            users.get(num - 1).getCourses().remove(this);
            users.remove(num - 1);

        }
        System.out.println("This student removed successfully");
        int exit;
        do {
            exit = ScannerWrapper.getInstance().nextInt();
        } while (exit != 0);

    }

    public String courseInfo() {
        return name + "\t" + institute + "\t" + masterName + "\t" + ownerName;
    }

}