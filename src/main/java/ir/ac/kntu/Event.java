package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Event {
    private String name;

    private LocalDateTime startTime;

    private LocalDateTime deadLine;

    private int maxQuestion;

    private int maxEntry;

    private ArrayList<Question> questions;

    private ArrayList<Customer> participated;

    private ArrayList<Customer> entryList;

    public Event(String name, LocalDateTime startTime, LocalDateTime deadLine, int maxEntry) {
        this.name = name;
        this.startTime = startTime;
        this.deadLine = deadLine;
        this.maxEntry = maxEntry;
        this.entryList = new ArrayList<>();
        this.participated = new ArrayList<>();
        this.questions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public ArrayList<Customer> getEntryList() {
        return entryList;
    }

    public int getMaxEntry() {
        return maxEntry;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void showEntryList() {
        for (int i = 0; i < entryList.size(); i++) {
            System.out.println((i + 1) + " : " + entryList.get(i).getName());
        }
    }

    public boolean addEntry(Customer customer) {
        if (entryList.size() >= maxEntry) {//class home work !?
            System.out.println("The capacity is full !");
            return false;
        } else {
            if (!entryList.contains(customer)) {
                entryList.add(customer);
                return true;
            } else {
                System.out.println("This user has already joined!!");
            }
        }
        return false;
    }

    public void donate() {//table

    }

    public void editInfo() {
        System.out.print("1. change name !\n"
                + "2. change start time !\n"
                + "3. change deadline !\n"
                + "4. delete entry !\n"
                + "5. \n"
                + "0. Exit\n"
                + "Select the Class to join : ");
        Scanner input = new Scanner(System.in);
        switch (input.next()) {
            case "1":
                changeName();
                break;
            case "2":
                changeStratTIme();
                break;
            case "3":
                changeDeadline();
                break;
            case "4":
                deleteEntry();
                break;
            case "0":
                return;
            default:
                System.out.println("\nyour option NOT exist\n");
                return;
        }
    }

    public void changeName() {
        System.out.println("name : " + name);
        System.out.print("Enter a new name : ");
        Scanner input = new Scanner(System.in);
        name = input.next();
    }

    public void changeStratTIme() {////////////
        System.out.println("Enter your new startTime: ");
        String start;
        do {
            start = ScannerWrapper.getInstance().next();
        } while (!start.equals("0") && !start.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));
        if (start.equals("0")) {
            return;
        }
        LocalDateTime startTime = HelperMethods.convertStringToDate(start);
        setStartTime(startTime);

    }

    public void changeDeadline() {//////////////
        System.out.println("Enter your new startTime: ");
        String dead;
        do {
            dead = ScannerWrapper.getInstance().next();
        } while (!dead.equals("0") && !dead.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));
        if (dead.equals("0")) {
            return;
        }
        LocalDateTime deadLine = HelperMethods.convertStringToDate(dead);
        this.deadLine = deadLine;
    }

    public void deleteEntry() {
        showEntryList();
        System.out.print("0. Exit\n"
                + "Select the Class to join : ");
        Scanner input = new Scanner(System.in);
        int selector = input.nextInt();
        if (selector == 0) {
            return;
        }
        if (selector <= entryList.size()) {
            entryList.get(selector - 1).deleteEvent(this);
            entryList.remove(entryList.get(selector - 1));
        } else {
            System.out.println("\nyour option NOT exist\n");
        }
    }

    public void deleteAllEntry() {
        for (int i = 0; i < entryList.size(); i++) {
            entryList.get(i).deleteEvent(this);
        }
    }

    public String eventInfo() {
        return name + "\t" + startTime + "\t" + deadLine + "\t" + (maxEntry - entryList.size());
    }

    public ArrayList<Question> getQuestions() {
        return new ArrayList<>(questions);
    }


    public void addParticipation(Customer customer) {
        participated.add(customer);
    }

    public ArrayList<Customer> getParticipated() {
        return new ArrayList<>(participated);
    }

    public void showQuestion() {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(i + 1 + "-" + questions.get(i).getName());
        }
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", deadLine=" + deadLine;
    }

    public void finalAnswersInEvent(User user) {
        System.out.println("--------------------------------------------------------------------------");
        ArrayList<LocalDateTime> allDates = new ArrayList<>();
        ArrayList<String> allAnswerStr = new ArrayList<>();
        ArrayList<Double> allScore = new ArrayList<>();
        ArrayList<Integer> questionNumbers = new ArrayList<>();
        if (checkEmail(user, allDates, allAnswerStr, allScore, questionNumbers)) {
            return;
        }
        ArrayList<LocalDateTime> newAllDates = new ArrayList<>();
        ArrayList<String> newAllAnswerStr = new ArrayList<>();
        ArrayList<Double> newAllScore = new ArrayList<>();
        ArrayList<Integer> newQuestionNumbers = new ArrayList<>();
        for (int i = 0; i < HelperMethods.sortDates(allDates).size(); i++) {
            newAllDates.add(allDates.get(HelperMethods.sortDates(allDates).get(i)));
            newAllAnswerStr.add(allAnswerStr.get(HelperMethods.sortDates(allDates).get(i)));
            newAllScore.add(allScore.get(HelperMethods.sortDates(allDates).get(i)));
            newQuestionNumbers.add(questionNumbers.get(HelperMethods.sortDates(allDates).get(i)));
        }
        System.out.println("**Final Answers**");
        System.out.println("QuestionNumber\tAnswers\tScores");
        for (int i = 0; i < newAllDates.size(); i++) {
            if (newAllScore.get(i) == -1) {
                System.out.println("Number" + newQuestionNumbers.get(i) + "\t" + newAllAnswerStr.get(i) + "\t" +
                        "No score given");
            } else {
                System.out.println("Number" + newQuestionNumbers.get(i) + "\t" + newAllAnswerStr.get(i) + "\t" +
                        newAllScore.get(i));
            }
        }
        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    private boolean checkEmail(User user, ArrayList<LocalDateTime> allDates,
                               ArrayList<String> allAnswerStr, ArrayList<Double> allScore,
                               ArrayList<Integer> questionNumbers) {
        for (int i = 0; i < questions.size(); i++) {
            int index1 = -1;
            if (checkExistAns(i)) {
                return true;
            }
            for (int j = 0; j < questions.get(i).getAnswers().size(); j++) {
                if (user.getEmail().equals(questions.get(i).getAnswers().get(j).getEmail())) {
                    index1 = j;
                    break;
                }
            }
            if (index1 != -1) {
                checkAutoCorrection(allDates, allAnswerStr, allScore, questionNumbers, i, index1);
            }
        }
        return false;
    }

    private void checkAutoCorrection(ArrayList<LocalDateTime> allDates,
                                     ArrayList<String> allAnswerStr, ArrayList<Double> allScore,
                                     ArrayList<Integer> questionNumbers, int i, int index1) {
        int size = questions.get(i).getAnswers().get(index1).getDates().size();
        if (questions.get(i).isAutoCorrection()) {
            int index2 = -1;
            double totalScore = questions.get(i).getTotalScore();
            for (int j = 0; j < questions.get(i).getAnswers().get(index1).getSocres().size(); j++) {
                if (questions.get(i).getAnswers().get(index1).getSocres().get(j) == totalScore) {
                    index2 = j;
                    break;
                }
            }
            if (index2 == -1) {
                allDates.add(questions.get(i).getAnswers().get(index1).getDates().get(0));
                allAnswerStr.add(questions.get(i).getAnswers().get(index1).getSolution().get(0));
                allScore.add(0.0);
                questionNumbers.add(i + 1);
            } else {
                allDates.add(questions.get(i).getAnswers().get(index1).getDates().get(index2));
                allAnswerStr.add(questions.get(i).getAnswers().get(index1).getSolution().get(index2));
                allScore.add(questions.get(i).getAnswers().get(index1).getSocres().get(index2));
                questionNumbers.add(i + 1);
            }
        } else {
            allDates.add(questions.get(i).getAnswers().get(index1).getDates().get(size - 1));
            allAnswerStr.add(questions.get(i).getAnswers().get(index1).getSolution().get(size - 1));
            allScore.add(questions.get(i).getAnswers().get(index1).getFinalScore());//check plz
            questionNumbers.add(i + 1);
        }
    }

    private boolean checkExistAns(int i) {
        if (questions.get(i).getAnswers().size() == 0) {
            System.out.println("There is no answer!");
            System.out.println("Exit");
            String exit;
            do {
                exit = ScannerWrapper.getInstance().nextLine();
            } while (!exit.equals("0"));
            return true;
        }
        return false;
    }

    public void allAnswersInEvent(User user) {
        System.out.println("--------------------------------------------------------------------------");
        ArrayList<LocalDateTime> allDates = new ArrayList<>();
        ArrayList<String> allAnswerStr = new ArrayList<>();
        ArrayList<Double> allScore = new ArrayList<>();
        ArrayList<Integer> questionNumbers = new ArrayList<>();
        if (sortDatas(user, allDates, allAnswerStr, allScore, questionNumbers)) {
            return;
        }
        ArrayList<LocalDateTime> newAllDates = new ArrayList<>();
        ArrayList<String> newAllAnswerStr = new ArrayList<>();
        ArrayList<Double> newAllScore = new ArrayList<>();
        ArrayList<Integer> newQuestionNumbers = new ArrayList<>();
        ArrayList<Integer> indexes = HelperMethods.sortDates(allDates);
        for (int i = 0; i < indexes.size(); i++) {
            newAllDates.add(allDates.get(indexes.get(i)));
            newAllAnswerStr.add(allAnswerStr.get(indexes.get(i)));
            newAllScore.add(allScore.get(indexes.get(i)));
            newQuestionNumbers.add(questionNumbers.get(indexes.get(i)));
        }
        System.out.println("**All Answers**");
        System.out.println("QuestionNumber\t\tAnswers\t\tScores");
        for (int i = 0; i < newAllDates.size(); i++) {
            if (newAllScore.get(i) == -1) {
                System.out.println("Number" + newQuestionNumbers.get(i) + "\t\t\t\t" + newAllAnswerStr.get(i) + "\t\t\t\t" +
                        "No score given");
            } else {
                System.out.println("Number" + newQuestionNumbers.get(i) + "\t\t\t\t" + newAllAnswerStr.get(i) + "\t\t\t\t" +
                        newAllScore.get(i));
            }
        }
        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    private boolean sortDatas(User user, ArrayList<LocalDateTime> allDates,
                              ArrayList<String> allAnswerStr, ArrayList<Double> allScore,
                              ArrayList<Integer> questionNumbers) {
        for (int i = 0; i < questions.size(); i++) {
            int index2 = -1;
            if (checkExistAns(i)) {
                return true;
            }
            for (int j = 0; j < questions.get(i).getAnswers().size(); j++) {
                if (user.getEmail().equals(questions.get(i).getAnswers().get(j).getEmail())) {
                    index2 = j;
                    break;
                }
            }
            if (index2 != -1) {
                for (int e = 0; e < questions.get(i).getAnswers().get(index2).getSolution().size(); e++) {
                    questionNumbers.add(i + 1);
                }
                allAnswerStr.addAll(questions.get(i).getAnswers().get(index2).getSolution());
                allDates.addAll(questions.get(i).getAnswers().get(index2).getDates());
                allScore.addAll(questions.get(i).getAnswers().get(index2).getSocres());
            }
        }
        return false;
    }

}
