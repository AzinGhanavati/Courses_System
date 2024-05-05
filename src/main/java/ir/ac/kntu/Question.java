/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ir.ac.kntu;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Question {

    private String name;

    private String explanation;

    private ArrayList<Answers> answers;

    private boolean autoCorrection;

    private String correctAnswer;

    private Level level;

    private double totalScore;

    private boolean isDescriptive;

    private boolean isTest;

    private boolean isShortAns;

    private ArrayList<String> cases;

    private double finalScore;

    public Question(String name, String explanation, Level level, double totalScore, boolean isDescriptive) {
        this.name = name;
        this.explanation = explanation;
        this.level = level;
        this.totalScore = totalScore;
        this.isDescriptive = isDescriptive;
        finalScore = -1;
        answers = new ArrayList<Answers>();
        cases = new ArrayList<String>();
        //scoresInAutoCorrection=new ArrayList<Double>();
        //doesn't have auto correction
    }

    public Question() {

    }


    public Question(String name, String explanation, ArrayList<String> cases, String correctAnswer, Level level, double score,
                    boolean autoCorrection, boolean isTest) {
        this.name = name;
        this.explanation = explanation;
        this.level = level;
        this.totalScore = score;
        this.correctAnswer = correctAnswer;
        this.autoCorrection = autoCorrection;
        this.isTest = isTest;
        this.cases = cases;
        finalScore = -1;
        answers = new ArrayList<Answers>();
        //cases=new ArrayList<String>();
        //scoresInAutoCorrection=new ArrayList<Double>();
    }


    public Question(String name, String explanation, ArrayList<String> cases, Level level, double score,
                    boolean autoCorrection, boolean isTest) {
        this.name = name;
        this.explanation = explanation;
        this.level = level;
        this.totalScore = score;
        this.autoCorrection = autoCorrection;
        this.isTest = isTest;
        finalScore = -1;
        answers = new ArrayList<Answers>();
        //scoresInAutoCorrection=new ArrayList<Double>();
        //cases=new ArrayList<String>();
    }


    public Question(String name, String explanation, String correctAnswer, Level level, double score,
                    boolean autoCorrection, boolean isShortAns) {
        this.name = name;
        this.explanation = explanation;
        this.level = level;
        this.totalScore = score;
        this.correctAnswer = correctAnswer;
        this.autoCorrection = autoCorrection;
        this.isShortAns = isShortAns;
        finalScore = -1;
        answers = new ArrayList<Answers>();
        cases = new ArrayList<String>();
        //scoresInAutoCorrection=new ArrayList<Double>();
    }

    public Question(String name, String explanation, Level level, double score,
                    boolean autoCorrection, boolean isShortAns) {
        this.name = name;
        this.explanation = explanation;
        this.level = level;
        this.totalScore = score;
        this.autoCorrection = autoCorrection;
        this.isShortAns = isShortAns;
        finalScore = -1;
        answers = new ArrayList<Answers>();
        cases = new ArrayList<String>();
        //scoresInAutoCorrection=new ArrayList<Double>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }

    public ArrayList<Answers> getAnswers() {
        return new ArrayList<>(answers);
    }

    public double getTotalScore() {
        return totalScore;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public Level getLevel() {
        return level;
    }

    public boolean isAutoCorrection() {
        return autoCorrection;
    }

    public void addCases(String str) {
        cases.add(str);
    }

    public boolean isDescriptive() {
        return isDescriptive;
    }

    public boolean isShortAns() {
        return isShortAns;
    }

    public boolean isTest() {
        return isTest;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public ArrayList<String> getCases() {
        return new ArrayList<>(cases);
    }

    public void setAnswers(ArrayList<Answers> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answers ans) {
        answers.add(ans);
    }


    @Override
    public String toString() {
        return "QuestionName :" + this.name + "\n" + "Level :" + this.level + "\t" + "Total score :"
                + this.totalScore + "\n" + "Explanation :" + this.explanation;
    }

    public void showQuestionAndUploadAns(User user, HomeWork homeWork) {
        showQInf();
        System.out.println("1-Send answer\nExit");
        String enteredNum;
        do {
            enteredNum = ScannerWrapper.getInstance().nextLine();
        } while (!enteredNum.equals("0") && !enteredNum.equals("1"));
        if (enteredNum.equals("1")) {
            if (LocalDateTime.now().compareTo(homeWork.getSendWithDelay()) < 0) {
                int index1 = findUserIndex(user);
                ScannerWrapper.getInstance().nextLine();
                String answer = ScannerWrapper.getInstance().nextLine();

                if (answer.equals("0")) {
                    return;
                }
                if (index1 == -1) {
                    notExistUserAns(user, homeWork, answer);
                } else {
                    answers.get(index1).addSolution(answer);
                    answers.get(index1).addDates(LocalDateTime.now());
                }
                int index2 = -1;
                System.out.println("Your answer uploaded successfully");
                index2 = getIndex2(user, index2);
                if (isAutoCorrection()) {
                    autoCorrected(homeWork, answer, index2);
                } else {
                    answers.get(index2).addScore(-1);
                }
                if (finalScore != -1) {
                    System.out.println("Your score is " + finalScore);
                }
            } else {
                System.out.println("Homework delivery time is over!!");
            }
            String exit1;
            do {
                exit1 = ScannerWrapper.getInstance().nextLine();
            } while (!exit1.equals("0"));
        }
    }

    private int getIndex2(User user, int index2) {
        for (int i = 0; i < answers.size(); i++) {
            if (user.getEmail().equals(answers.get(i).getEmail())) {
                index2 = i;
                break;
            }
        }
        return index2;
    }

    private int findUserIndex(User user) {
        int index1 = -1;
        index1 = getIndex2(user, index1);
        return index1;
    }

    private void notExistUserAns(User user, HomeWork homeWork, String answer) {
        Answers ans = new Answers(user.getUserName(), user.getEmail());
        ans.addSolution(answer);
        ans.addDates(LocalDateTime.now());
        addAnswer(ans);
        boolean exciste = false;
        for (int i = 0; i < homeWork.getParticipated().size(); i++) {
            if (homeWork.getParticipated().contains(user)) {
                exciste = true;
                break;
            }
        }
        if (!exciste) {
            homeWork.addParticipatedUser(user);
        }
    }

    private void autoCorrected(HomeWork homeWork, String answer, int index2) {
        if (answer.equals(correctAnswer)) {
            if (LocalDateTime.now().compareTo(homeWork.getDeadLine()) < 0) {
                answers.get(index2).addScore(totalScore);
                answers.get(index2).setFinalScore(totalScore);
            } else {
                answers.get(index2).addScore(totalScore * (homeWork.getDelayCoefficient() / 100));
                answers.get(index2).setFinalScore(totalScore * (homeWork.getDelayCoefficient() / 100));
            }
        } else {
            answers.get(index2).addScore(0);
            if (finalScore == -1) {
                answers.get(index2).setFinalScore(0);
            }
        }
    }

    public void showQInf() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(toString());
        if (this.isTest()) {
            for (int i = 0; i < this.cases.size(); i++) {
                int j = i + 1;
                System.out.println(j + "-" + cases.get(i));
            }
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public void showQuestionAndUploadAnsForEvent(Customer customer, Event event) {
        showQInf();
        System.out.println("1-Send answer\nExit");
        String enteredNum;
        do {
            enteredNum = ScannerWrapper.getInstance().nextLine();
        } while (!enteredNum.equals("0") && !enteredNum.equals("1"));
        if (enteredNum.equals("1")) {
            if (LocalDateTime.now().compareTo(event.getDeadLine()) < 0) {
                int index1 = findUserIndex(customer);
                //*******************************************************************************************
                ScannerWrapper.getInstance().nextLine();
                String answer = ScannerWrapper.getInstance().nextLine();

                if (answer.equals("0")) {
                    return;
                }
                if (index1 == -1) {
                    notExistUserAns(customer, event, answer);
                } else {
                    answers.get(index1).addSolution(answer);
                    answers.get(index1).addDates(LocalDateTime.now());
                }
                int index2 = -1;
                System.out.println("Your answer uploaded successfully");
                index2 = getIndex2(customer, index2);
                if (isAutoCorrection()) {
                    isAutoCorrection(answer, index2);
                } else {
                    answers.get(index2).addScore(-1);
                }
                if (finalScore != -1) {
                    System.out.println("Your score is " + finalScore);
                }
            } else {
                System.out.println("Homework delivery time is over!!");
            }
            String exit1;
            do {
                exit1 = ScannerWrapper.getInstance().nextLine();
            } while (!exit1.equals("0"));
        }
    }

    private void notExistUserAns(Customer customer, Event event, String answer) {
        Answers ans = new Answers(customer.getUserName(), customer.getEmail());
        ans.addSolution(answer);
        ans.addDates(LocalDateTime.now());
        addAnswer(ans);
        boolean exciste = false;
        for (int i = 0; i < event.getParticipated().size(); i++) {
            if (event.getParticipated().contains(customer)) {
                exciste = true;
                break;
            }
        }
        if (!exciste) {
            event.addParticipation(customer);
            if (event instanceof EspecialEvent especialEvent) {
                especialEvent.addParticipatedGroup(especialEvent.findGroup(customer));
            }
        }
    }

    private void isAutoCorrection(String answer, int index2) {
        if (answer.equals(correctAnswer)) {
            answers.get(index2).addScore(totalScore);
            answers.get(index2).setFinalScore(totalScore);
        } else {
            answers.get(index2).addScore(0);
            if (finalScore == -1) {
                answers.get(index2).setFinalScore(0);
            }
        }
    }


    public void markAnswers() {
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < answers.size(); i++) {
            int j = i + 1;
            int index = answers.get(i).getSolution().size() - 1;
            System.out.println("answer" + j + ":" + answers.get(i).getSolution().get(index));
            System.out.println("If you want to exit enter -1");
            System.out.println("Mark :");
            double mark = ScannerWrapper.getInstance().nextDouble();
            if (mark == -1) {
                return;
            }
            if (mark >= 0) {
                answers.get(i).setFinalScore(mark);
            }
        }

    }

    public void allUserAnswer() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Enter user's email :");
        String email = ScannerWrapper.getInstance().nextLine();
        if (email.equals("0")) {
            return;
        }
        int index = 0;
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).getEmail().equals(email)) {
                for (int j = answers.get(i).getSolution().size() - 1; j >= 0; j--) {
                    System.out.println(answers.get(i).getSolution().get(j));
                }
                index = 1;
                break;
            }
        }
        if (index == -1) {
            System.out.println("This user hasn't answered yet");
        }
        System.out.println("Press <<0>> to exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }


}

