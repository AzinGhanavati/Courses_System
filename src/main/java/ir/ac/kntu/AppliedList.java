package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AppliedList {

    private static AppliedList instance = new AppliedList();

    public static AppliedList getInstance() {
        return instance;
    }

    private static ArrayList<Customer> users = new ArrayList<>();

    private static ArrayList<Course> allCourses = new ArrayList<>();

    private static ArrayList<Question> questionBax = new ArrayList<>();

    private static ArrayList<LocalDateTime> dateAndTime = new ArrayList<>();

    private static ArrayList<Admin> admins = new ArrayList<>();

    private static ArrayList<Event> events = new ArrayList<>();

    private static ArrayList<Event> publicEvent = new ArrayList<>();

    private static ArrayList<PrivateEvent> privateEvent = new ArrayList<>();

    public static ArrayList<Course> getAllCourses() {
        return allCourses;
    }

    public static ArrayList<Question> getQuestionBax() {
        return questionBax;
    }

    public static ArrayList<LocalDateTime> getDateAndTime() {
        return new ArrayList<>(dateAndTime);
    }

    public static ArrayList<Admin> getAdmins() {
        return new ArrayList<>(admins);
    }

    public static ArrayList<Event> getEvents() {
        return new ArrayList<>(events);
    }

    public static ArrayList<Customer> getUsers() {
        return new ArrayList<>(users);
    }

    public static ArrayList<Event> getPublicEvent() {
        return new ArrayList<>(publicEvent);
    }

    public static ArrayList<PrivateEvent> getPrivateEvent() {
        return new ArrayList<>(privateEvent);
    }

    public void addQuestionBax(Question question) {
        questionBax.add(question);
    }

    public void addDateAndTime(LocalDateTime dateTime) {
        dateAndTime.add(dateTime);
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addUser(Customer customer) {
        users.add(customer);
    }

    public void addCourse(Course course) {
        allCourses.add(course);
    }

    public void addPublicEvent(Event event) {
        publicEvent.add(event);
    }

    public void addPrivateEvent(PrivateEvent p) {
        privateEvent.add(p);
    }

}
