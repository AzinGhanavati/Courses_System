
package ir.ac.kntu;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    private String name;

    private String userName;

    private String email;

    private String password;

    private String id;

    private String phoneNumber;

    private ArrayList<Course> courses;

    private ArrayList<Course> ownerCourse;

    private boolean professor;

    public User(String name, String userName, String email, String password, String id, String phoneNumber) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.ownerCourse = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.professor = false;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setProfessor(boolean professor) {
        this.professor = professor;
    }

    public boolean isProfessor() {
        return professor;
    }

    public String getName() {
        return name;
    }


    public String getUserName() {
        return userName;
    }


    public String getEmail() {
        return email;
    }


    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getPassword() {
        return password;
    }


    public void addCourses(Course course) {
        courses.add(course);
    }


    public void addOwnerCourse(Course course) {
        ownerCourse.add(course);
    }

    public ArrayList<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public ArrayList<Course> getOwnerCourse() {
        return new ArrayList<>(ownerCourse);
    }

    public void createClass() {
        System.out.println("--------------------------------------------------------------------------");
        Course addedCourse1 = new Course();
        System.out.print("NAME:\t");
        String name = ScannerWrapper.getInstance().nextLine();
        if (name.equals("0")) {
            return;
        }
        System.out.print("institute:\t");
        String institute = ScannerWrapper.getInstance().nextLine();
        if (institute.equals("0")) {
            return;
        }
        System.out.print("masterName:\t");
        String masterName = ScannerWrapper.getInstance().nextLine();
        if (masterName.equals("0")) {
            return;
        }
        System.out.print("academicYear:\t");
        int academicYear = ScannerWrapper.getInstance().nextInt();
        if (academicYear == 0) {
            return;
        }
        System.out.print("Explanation:\t");
        String explanation = ScannerWrapper.getInstance().nextLine();
        if (explanation.equals("0")) {
            return;
        }
        System.out.print("Do you prefer close class?\t");
        String defineClose = ScannerWrapper.getInstance().nextLine();
        if (defineClose.equals("0")) {
            return;
        }
        addedCourse1 = checkIsClose(name, institute, masterName, academicYear, explanation, defineClose);
        if (addedCourse1 == null) {
            return;
        }
        addThisCourse(addedCourse1);
    }

    @Nullable
    private Course checkIsClose(String name, String institute, String masterName,
                                int academicYear, String explanation, String defineClose) {
        Course addedCourse1;
        boolean isClose = false;
        if (defineClose.equals("Yes")) {
            isClose = true;
        }
        if (isClose) {
            addedCourse1 = new Course(name, institute, masterName, academicYear, explanation, this.name, isClose);

        } else {
            addedCourse1 = getAddedCourse1(name, institute, masterName, academicYear, explanation, isClose);
            if (addedCourse1 == null) {
                return null;
            }
        }
        return addedCourse1;
    }

    private void addThisCourse(Course addedCourse1) {
        addOwnerCourse(addedCourse1);
        AppliedList.getInstance().addCourse(addedCourse1);
        addedCourse1.setOwner(this);
        System.out.println("Class created successfully");
        System.out.println("1-Enter class\nExit,Press <<0>>");
        chooseState(addedCourse1);
    }

    private void chooseState(Course addedCourse1) {
        int chooseNum;
        do {
            chooseNum = ScannerWrapper.getInstance().nextInt();
        } while (chooseNum != 0 && chooseNum != 1);
        if (chooseNum == 1) {
            MnuForOwner.ownerEnterClass(addedCourse1, this);
        }
    }

    @Nullable
    private Course getAddedCourse1(String name, String institute, String masterName, int academicYear,
                                   String explanation, boolean isClose) {
        Course addedCourse1;
        System.out.print("Do you prefer private class?\t");
        String definePrivate = ScannerWrapper.getInstance().nextLine();
        if (definePrivate.equals("0")) {
            return null;
        }
        boolean isPrivate = false;
        if (definePrivate.equals("Yes")) {
            isPrivate = true;
        }
        if (!isPrivate) {
            addedCourse1 = new Course(name, institute, masterName, academicYear, explanation,
                    this.name, isClose, isPrivate);

        } else {
            System.out.print("Password:\t");
            String password = ScannerWrapper.getInstance().nextLine();
            if (password.equals("0")) {
                return null;
            }
            addedCourse1 = new Course(name, institute, masterName, academicYear, explanation,
                    this.name, isClose, isPrivate, password);

        }
        return addedCourse1;
    }

    public void editClass(Course course) {
        showOwnCourseInfo();
        System.out.print("NAME:\t");
        String name = ScannerWrapper.getInstance().nextLine();
        if (name.equals("0")) {
            return;
        }
        System.out.print("institute:\t");
        String institute = ScannerWrapper.getInstance().nextLine();
        if (institute.equals("0")) {
            return;
        }
        System.out.print("masterName:\t");
        String masterName = ScannerWrapper.getInstance().nextLine();
        if (masterName.equals("0")) {
            return;
        }
        System.out.print("academicYear:\t");
        int academicYear = ScannerWrapper.getInstance().nextInt();
        if (academicYear == 0) {
            return;
        }
        System.out.print("Explanation:\t");
        String explanation = ScannerWrapper.getInstance().nextLine();
        if (explanation.equals("0")) {
            return;
        }
        String ownerName = getName();
        System.out.print("Do you prefer close class?\t");
        String defineClose = ScannerWrapper.getInstance().nextLine();
        if (defineClose.equals("0")) {
            return;
        }
        boolean isClose = false;
        if (defineClose.equals("Yes")) {
            isClose = true;
        }
        if (checkCloseCourse(course, name, institute, masterName, academicYear, explanation, ownerName, isClose)) {
            return;
        }
        exitMethod();
    }

    private boolean checkCloseCourse(Course course, String name, String institute, String masterName,
                                     int academicYear, String explanation, String ownerName, boolean isClose) {
        if (isClose) {
            isCloseCourse(course, name, institute, masterName, academicYear, explanation, ownerName, isClose);
        } else {
            if (courseNotClose(course, name, institute, masterName, academicYear, explanation, ownerName, isClose)) {
                return true;
            }
        }
        return false;
    }

    private void showOwnCourseInfo() {
        for (int i = 0; i < ownerCourse.size(); i++) {
            System.out.println(ownerCourse.get(i).courseInfo());
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    private void exitMethod() {
        System.out.println("Exit(press <<0>>)");
        int exit;
        do {
            exit = ScannerWrapper.getInstance().nextInt();
        } while (exit != 0);
    }

    private boolean courseNotClose(Course course, String name, String institute, String masterName, int academicYear,
                                   String explanation, String ownerName, boolean isClose) {
        System.out.print("Do you prefer private class?\t");
        String definePrivate = ScannerWrapper.getInstance().nextLine();
        if (definePrivate.equals("0")) {
            return true;
        }
        boolean isPrivate = false;
        if (definePrivate.equals("Yes")) {
            isPrivate = true;
        }
        if (!isPrivate) {
            courseNotPrivate(course, name, institute, masterName, academicYear, explanation,
                    ownerName, isClose, isPrivate);
        } else {
            System.out.print("Password:\t");
            String password = ScannerWrapper.getInstance().nextLine();
            if (password.equals("0")) {
                return true;
            }
            Course addedCourse3 = new Course(name, institute, masterName, academicYear, explanation,
                    ownerName, isClose, isPrivate, password);
            AppliedList.getAllCourses().set(AppliedList.getAllCourses().indexOf(course), addedCourse3);
            for (int i = 0; i < course.getUsers().size(); i++) {
                ArrayList<Course> temp = course.getUsers().get(i).getCourses();
                temp.set(temp.indexOf(course), addedCourse3);
            }
            User owner = course.getOwner();
            addedCourse3.setHomeWorks(course.getHomeWorks());
            addedCourse3.setUsers(course.getUsers());
            addedCourse3.setOwner(owner);
            addedCourse3.setProfessor(course.getProfessor());
            owner.getOwnerCourse().set(owner.getOwnerCourse().indexOf(course), addedCourse3);

        }
        return false;
    }

    private void courseNotPrivate(Course course, String name, String institute, String masterName, int academicYear,
                                  String explanation, String ownerName, boolean isClose, boolean isPrivate) {
        Course addedCourse2 = new Course(name, institute, masterName, academicYear, explanation,
                ownerName, isClose, isPrivate);
        AppliedList.getAllCourses().set(AppliedList.getAllCourses().indexOf(course), addedCourse2);
        for (int i = 0; i < course.getUsers().size(); i++) {
            ArrayList<Course> temp = course.getUsers().get(i).getCourses();
            temp.set(temp.indexOf(course), addedCourse2);
        }
        User owner = course.getOwner();
        addedCourse2.setHomeWorks(course.getHomeWorks());
        addedCourse2.setUsers(course.getUsers());
        addedCourse2.setOwner(owner);
        addedCourse2.setProfessor(course.getProfessor());
        owner.getOwnerCourse().set(owner.getOwnerCourse().indexOf(course), addedCourse2);
    }

    private void isCloseCourse(Course course, String name, String institute, String masterName, int academicYear,
                               String explanation, String ownerName, boolean isClose) {
        Course addedCourse1 = new Course(name, institute, masterName, academicYear, explanation, ownerName, isClose);
        User owner = course.getOwner();
        addedCourse1.setHomeWorks(course.getHomeWorks());
        addedCourse1.setUsers(course.getUsers());
        addedCourse1.setOwner(owner);
        addedCourse1.setProfessor(course.getProfessor());
        AppliedList.getAllCourses().set(AppliedList.getAllCourses().indexOf(course), addedCourse1);
        for (int i = 0; i < course.getUsers().size(); i++) {
            ArrayList<Course> temp = course.getUsers().get(i).getCourses();
            temp.set(temp.indexOf(course), addedCourse1);
        }
        owner.getOwnerCourse().set(owner.getOwnerCourse().indexOf(course), addedCourse1);
    }


    @Override
    public String toString() {
        return "Name :" + getName() + "\n" + "UserName :" + getUserName() + "\n" +
                "Email :" + getEmail() + "\n" + "Id :" + getId();
    }


    public void printInfUser() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(toString());
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Press <<0>> to exit");
        String enterNum;
        do {
            enterNum = ScannerWrapper.getInstance().nextLine();
        } while (!enterNum.equals("0"));
        //return;
    }


    public void enterAfterRegistration() {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("UserName:" + getUserName() + "|" + "Id:" + getId() + "|" + "Email:" + getEmail());
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Choose your desired option");
            System.out.println("1-Search own classes\n2-Create a class\n3-QuestionBax\n4-Search user" +
                    "\n5-Search class\n6-Search event\n7-More option\n0-Exit");
            String enteredNum = ScannerWrapper.getInstance().nextLine();
            switch (enteredNum) {
                case "1":
                    UserHelper.searchClassesFromAccount(this);
                    break;
                case "2":
                    createClass();
                    break;
                case "3":
                    HelperForQuestionBax.questionBaxPage(this);
                    break;
                case "4":
                    HelperForSearchMethod.searchUser();
                    break;
                case "5":
                    HelperForSearchMethod.searchForClasses();
                    break;
                case "6":
                    searchEvent();
                    break;
                case "7":
                    if (this instanceof Customer customer) {
                        customer.showMenu();
                    }
                    break;
                default:
                    return;

            }
        }
    }

    public void searchEvent() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Enter Event Name:");
        String select = ScannerWrapper.getInstance().next();
        if(select.equals("0")){
            return;
        }
        int index=-1;
        for(int i=0;i<AppliedList.getPublicEvent().size();i++){
            if(AppliedList.getPublicEvent().get(i).getName().equals(select)){
                index=i;
            }
        }
        if(index==-1){
            System.out.println("There isn't any event wih this name!!");
        }else{
            System.out.println(AppliedList.getPublicEvent().get(index));
        }

        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));

    }

    public void deleteClass(Course course) {
        for (int i = 0; i < course.getUsers().size(); i++) {
            course.getUsers().get(i).getCourses().remove(course);
        }
        ownerCourse.remove(course);
        AppliedList.getAllCourses().remove(course);
        System.out.println("class removed successfully");

    }

    public String userIfo() {
        return getName() + "\t" + getEmail() + "\t" + getId();
    }

    public ArrayList<Event> getInvitations() {
        return null;
    }

    public Admin clonee() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(userName, user.userName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userName, email, password, id);
    }

    public void deleteEvent(Event event) {
    }
}
