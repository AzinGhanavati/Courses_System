package ir.ac.kntu;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.*;

public class Test {

    @org.junit.Test
    public void getName() {
        User user1 = new Customer("azin", "azin-gha", "azin.com", "2357", "13", "0903");
        assertEquals("azin", user1.getName());
    }


    @org.junit.Test
    public void getInstitute() {
        Course course1 = new Course("matlab", "sharif", "alizade", 2022, "matlab", "azin", false, true, "2357");
        assertEquals("sharif", course1.getInstitute());
    }


    @org.junit.Test
    public void getEmail() {
        Answers answer = new Answers("negar-ahm", "negar.com");
        assertEquals("negar.com", answer.getEmail());
    }


    @org.junit.Test
    public void getExplanation() {
        Course course1 = new Course("discrete", "khajeNasir", "khaste", 2022, "discrete math", "zahra", false, true);
        assertEquals("discrete math", course1.getExplanation());
    }

    @org.junit.Test
    public void getStartTime() {
        HomeWork homeWork1 = new HomeWork("first", HelperMethods.convertStringToDate("2022-06-22 07:03:10"), HelperMethods.convertStringToDate("2022-06-22 23:25:10"), "recursion", 20, HelperMethods.convertStringToDate("2022-06-22 23:40:10"), true, false);
        assertEquals(HelperMethods.convertStringToDate("2022-06-22 07:03:10"), homeWork1.getStartTime());
    }


    @org.junit.Test
    public void setName() {
        HomeWork homeWork1 = new HomeWork("observer", HelperMethods.convertStringToDate("2022-06-22 07:03:10"), HelperMethods.convertStringToDate("2022-06-22 23:25:10"), "notification", 20, HelperMethods.convertStringToDate("2022-06-22 23:40:10"), true, false);
        homeWork1.setName("Notification");
        assertEquals(homeWork1.getName(), "Notification");
    }

    @org.junit.Test
    public void isProfessor() {
        User c1 = new Customer("c11", "c1", "c1.com", "1111", "15", "0912");
        c1.setProfessor(true);
        assertTrue(c1.isProfessor());
    }


    @org.junit.Test
    public void testEquals() {
        User u1 = new User("u11", "u1", "u1.com", "1234", "17", "0916");
        User u2 = new User("u11", "u1", "u1.com", "1234", "17", "0903");
        Boolean equal = u1.equals(u2);
        assertTrue(equal);
    }

    @org.junit.Test
    public void testEquals1() {
        User u1 = new User("negar", "negara", "neg.com", "6037", "17", "0916");
        User u2 = new User("negar", "negara", "neg.com", "6037", "18", "0903");
        Boolean equal = u1.equals(u2);
        assertFalse(equal);
    }


    @org.junit.Test
    public void testHashCode() {
        User u1 = new User("Ali", "Ali-sh", "ali.com", "4532", "17", "0916");
        User u2 = new User("Ali", "Ali-sh", "ali.com", "4532", "17", "0903");
        int hashCode1 = u1.hashCode();
        int hashCode2 = u2.hashCode();
        Assert.assertTrue(hashCode1 == hashCode2);

    }

    @org.junit.Test
    public void testHashCode1() {
        User u1 = new User("sara", "sara-t", "sara.com", "4537", "17", "0916");
        User u2 = new User("sara", "sara-t", "sara.com", "4532", "17", "0903");
        int hashCode1 = u1.hashCode();
        int hashCode2 = u2.hashCode();
        assertNotEquals(hashCode1, hashCode2);
    }

    @org.junit.Test
    public void getQuestions() {
        ArrayList<String> cases = new ArrayList<String>(Arrays.asList("c", "c++", "java"));
        Question question1 = new Question("Programing language", "languages", cases, "c++", Level.EASY, 10.0, true, true);
        Question question2 = new Question("strings", "work with arraylist", "for", Level.COMPLEX, 25.2, true, true);
        Question question3 = new Question("hashMap", "work hashMap", "equals", Level.DIFFICULT, 15.2, true, true);
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        Event event = new Event("Event", HelperMethods.convertStringToDate("2022-06-28 05:12:12"), HelperMethods.convertStringToDate("2022-06-29 12:12:12"), 50);
        event.setQuestions(questions);
        assertEquals(questions, event.getQuestions());
    }

    @org.junit.Test
    public void testGetName() {
        Event event = new Event("programingEvent", HelperMethods.convertStringToDate("2022-06-30 05:12:12"), HelperMethods.convertStringToDate("2022-06-29 12:12:12"), 50);
        assertEquals("programingEvent", event.getName());
    }

    @org.junit.Test
    public void setStartTime() {
        Event event = new Event("MathEvent", HelperMethods.convertStringToDate("2022-06-17 05:12:12"), HelperMethods.convertStringToDate("2022-06-20 12:12:12"), 20);
        event.setStartTime(HelperMethods.convertStringToDate("2022-06-10 05:12:12"));
        assertEquals(event.getStartTime(), HelperMethods.convertStringToDate("2022-06-10 05:12:12"));

    }


    @org.junit.Test
    public void getMembers() {
        Customer c1 = new Customer("reza", "reza-e", "reza.com", "6712", "21", "0917");
        Customer c2 = new Customer("sara", "sara-t", "sara.com", "4532", "17", "0903");
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);
        Group group = new Group("Power", 20);
        group.setMembers(customers);
        assertEquals(customers, group.getMembers());

    }
}