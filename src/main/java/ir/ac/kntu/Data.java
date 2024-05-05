package ir.ac.kntu;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    static Admin admin = new Admin("admin", "admin", "admin", "admin", "admin", "admin");

    public static void data() {
        AppliedList.getInstance().addAdmin(admin);
        Customer user1 = new Customer("azin", "azin-gha", "azin.com", "2357", "13", "0903");
        Customer user2 = new Customer("negar", "negar-ahm", "negar.com", "1381", "12", "0904");
        Customer user3 = new Customer("zahra", "zahra-sh", "zahra.com", "1382", "11", "0905");
        Course course1 = new Course("java", "khaje", "zamanian", 2022, "java programing", "azin", false, true, "2357");
        Course course2 = new Course("riazi", "khaje", "hasan poor", 2022, "riazi omomi2", "azin", false, true, "1381");
        HomeWork homeWork1 = new HomeWork("seri1", HelperMethods.convertStringToDate("2022-06-22 07:03:10"), HelperMethods.convertStringToDate("2022-06-22 23:25:10"), "recursion", 20, HelperMethods.convertStringToDate("2022-06-22 23:40:10"), true, false);
        HomeWork homeWork2 = new HomeWork("seri2", HelperMethods.convertStringToDate("2022-06-22 07:01:10"), HelperMethods.convertStringToDate("2022-06-22 23:25:10"), "java enum", 20, HelperMethods.convertStringToDate("2022-06-22 23:40:10"), true, false);
        ArrayList<String> gfg = new ArrayList<String>(Arrays.asList("Geeks", "for", "java"));
        Answers answer = new Answers("negar-ahm", "negar.com");
        answer.addScore(0.0);
        answer.addDates(HelperMethods.convertStringToDate("2022-06-22 20:25:10"));
        answer.addSolution("Geeks");
        answer.addScore(12.0);
        Answers answer2 = new Answers("negar-ahm", "negar.com");
        answer2.addScore(0.0);
        answer2.addDates(HelperMethods.convertStringToDate("2022-06-22 21:00:10"));
        answer2.addSolution("fo");
        Answers answer3 = getAnswers();
        Question question1 = new Question("firebase", "notification", gfg, "Geeks", Level.EASY, 12.0, true, true);
        Question question2 = new Question("strings", "work with arraylist", "define", Level.COMPLEX, 20.2, true, true);
        question1.addAnswer(answer);
        question2.addAnswer(answer2);
        question2.addAnswer(answer3);
        homeWork1.addQuestion(question1);
        homeWork1.addQuestion(question2);
        homeWork2.addQuestion(question1);
        homeWork1.addParticipatedUser(user2);
        course1.addHomeWorks(homeWork1);
        course1.addHomeWorks(homeWork2);
        AppliedList.getInstance().addCourse(course1);
        AppliedList.getInstance().addCourse(course2);
        course1.addUser(user2);
        user1.addOwnerCourse(course1);
        user1.addOwnerCourse(course2);
        user2.addCourses(course1);
        course1.setOwner(user1);
        AppliedList.getInstance().addUser(user1);
        AppliedList.getInstance().addUser(user2);
    }

    public static void eventTest() {
        ArrayList<String> gfg = new ArrayList<String>(Arrays.asList("Geeks", "for", "java"));
        Question question1 = new Question("Notification", "notification", gfg, "Geeks", Level.EASY, 5.0, true, true);
        Question question2 = new Question("Comparable", "compare objects", "comparator", Level.COMPLEX, 15.0, true, true);
        CommonEvent commonEvent = new CommonEvent("Common", HelperMethods.convertStringToDate("2022-06-28 05:12:12"), HelperMethods.convertStringToDate("2022-07-09 23:12:12"), 50);
        AppliedList.getInstance().addPublicEvent(commonEvent);
        AppliedList.getInstance().addEvent(commonEvent);
        commonEvent.addQuestion(question1);
        commonEvent.addQuestion(question2);
        PrivateEvent privateEvent = new PrivateEvent("private1", HelperMethods.convertStringToDate("2022-06-27 05:12:12"), HelperMethods.convertStringToDate("2022-07-09 23:12:12"), 20);
        PrivateEvent privateEvent2 = new PrivateEvent("private2", HelperMethods.convertStringToDate("2022-07-09 05:12:12"), HelperMethods.convertStringToDate("2022-07-09 23:12:12"), 20);
        AppliedList.getInstance().addPrivateEvent(privateEvent);
        AppliedList.getInstance().addPrivateEvent(privateEvent2);
        privateEvent.addQuestion(question1);
        privateEvent.addQuestion(question2);
        privateEvent2.addQuestion(question1);
        privateEvent2.addQuestion(question2);
        EspecialEvent especialEvent = new EspecialEvent("especialEvent", HelperMethods.convertStringToDate("2022-06-28 05:12:12"), HelperMethods.convertStringToDate("2022-07-09 23:12:12"), 100, 5);
        AppliedList.getInstance().addPublicEvent(especialEvent);
        especialEvent.addQuestion(question1);
        especialEvent.addQuestion(question2);
        AppliedList.getInstance().addEvent(especialEvent);
        AppliedList.getInstance().addEvent(privateEvent);
        Group group = new Group("Power", 3);
        Customer c1 = new Customer("c11", "c1", "c1.com", "1111", "15", "0912");
        Customer c2 = new Customer("c22", "c2", "c2.com", "2222", "16", "0913");
        group.addMember(c1);
        group.addMember(c2);
        Customer q1 = new Customer("q11", "q1", "q1.com", "8888", "18", "0988");
        Customer q2 = new Customer("q22", "q2", "q2.com", "9999", "19", "0933");
        Group group2 = new Group("Win", 2);
        group2.addMember(q1);
        group2.addMember(q2);
        AppliedList.getInstance().addUser(c1);
        AppliedList.getInstance().addUser(c2);
        AppliedList.getInstance().addUser(q1);
        AppliedList.getInstance().addUser(q2);
        c1.addEvent(especialEvent);
        c2.addEvent(especialEvent);
        q1.addEvent(especialEvent);
        q2.addEvent(especialEvent);
        especialEvent.addGroups(group);
        especialEvent.addGroups(group2);
    }

    @NotNull
    private static Answers getAnswers() {
        Answers answer3 = new Answers("zahra-sh", "zahra.com");
        answer3.addScore(20.2);
        answer3.addDates(HelperMethods.convertStringToDate("2022-06-22 20:41:10"));
        answer3.addSolution("WTTT");

        answer3.addScore(0.0);
        answer3.addDates(HelperMethods.convertStringToDate("2022-06-22 20:35:10"));
        answer3.addSolution("BBBBB");

        answer3.addScore(0.0);
        answer3.addDates(HelperMethods.convertStringToDate("2022-06-22 20:25:10"));
        answer3.addSolution("for");
        return answer3;
    }

    public static void addToQuestionBax() {
        ArrayList<String> gfg = new ArrayList<String>(Arrays.asList("36", "48", "24"));
        Question question1 = new Question("Sum", "12*3=?", gfg, "36", Level.EASY, 10.0, true, true);
        Question question2 = new Question("Graph", "find shortest path", "dijkstra", Level.COMPLEX, 20.0, true, true);
        Question question3 = new Question("Array", "maxValue", "ab", Level.COMPLEX, 30.0, true, true);
        AppliedList.getInstance().addQuestionBax(question1);
        AppliedList.getInstance().addQuestionBax(question2);
        AppliedList.getInstance().addQuestionBax(question3);
        CommonEvent commonEvent = new CommonEvent("calculusEvent", HelperMethods.convertStringToDate("2022-06-02 05:12:12"), HelperMethods.convertStringToDate("2022-06-10 23:12:12"), 50);
        Question question4 = new Question("Subtraction", "61-13=?", gfg, "48", Level.EASY, 10.0, true, true);
        commonEvent.addQuestion(question4);
        AppliedList.getInstance().addPublicEvent(commonEvent);
        AppliedList.getInstance().addEvent(commonEvent);
    }
}
