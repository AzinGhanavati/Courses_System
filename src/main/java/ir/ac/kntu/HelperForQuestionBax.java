/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ir.ac.kntu;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HelperForQuestionBax {
    public static void questionBaxPage(User user) {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            if (checkExistQ()) {
                return;
            }
            ArrayList<Question> newQuestionBax1 = getNewQuestionBax1();
            ArrayList<Question> newQuestionBax2 = getQuestionArrayList();
            if (newQuestionBax2 == null) {
                return;
            }
            int x = 0;
            int y = 0;
            int z = 0;
            System.out.println("Show by difficulty level(Yes/everyKey except 0)");
            String enteredChoice = ScannerWrapper.getInstance().nextLine();
            switch (enteredChoice) {
                case "Yes":
                    System.out.println("1-Easy to Hard  2-Hard to Easy");
                    String num = ScannerWrapper.getInstance().next();
                    switch (num) {
                        case "1":
                            x = getX(newQuestionBax2);
                            break;
                        case "2":
                            y = getY(newQuestionBax2);
                            break;
                        default:
                            return;
                    }
                    break;
                case "0":
                    return;
                default:
                    z = getZ(newQuestionBax1);
            }
            if (showQInOrder(user, newQuestionBax1, newQuestionBax2, x, y, z)) {
                return;
            }
        }
    }

    private static int getX(ArrayList<Question> newQuestionBax2) {
        int x;
        x = 1;
        int j = 0;
        for (int i = newQuestionBax2.size() - 1; i >= 0; i--) {
            j++;
            System.out.println(j + "-" + newQuestionBax2.get(i).getName());
        }
        return x;
    }

    @NotNull
    private static ArrayList<Question> getNewQuestionBax1() {
        ArrayList<Question> newQuestionBax1 = new ArrayList<>();
        for (int j = AppliedList.getQuestionBax().size() - 1; j >= 0; j--) {
            newQuestionBax1.add(AppliedList.getQuestionBax().get(j));
        }
        return newQuestionBax1;
    }

    private static int getZ(ArrayList<Question> newQuestionBax1) {
        int z;
        z = 1;
        for (int i = 0; i < newQuestionBax1.size(); i++) {
            int j = i + 1;
            System.out.println(j + "-" + newQuestionBax1.get(i).getName());
        }
        return z;
    }

    @Nullable
    private static ArrayList<Question> getQuestionArrayList() {
        ArrayList<Question> complex = new ArrayList<>();
        ArrayList<Question> difficult = new ArrayList<>();
        ArrayList<Question> standard = new ArrayList<>();
        ArrayList<Question> easy = new ArrayList<>();
        if (addBasedOnLevel(complex, difficult, standard, easy)) {
            return null;
        }
        ArrayList<Question> newQuestionBax2 = new ArrayList<>();
        newQuestionBax2.addAll(complex);
        newQuestionBax2.addAll(difficult);
        newQuestionBax2.addAll(standard);
        newQuestionBax2.addAll(easy);
        return newQuestionBax2;
    }

    private static int getY(ArrayList<Question> newQuestionBax2) {
        int y;
        y = 1;
        for (int i = 0; i < newQuestionBax2.size(); i++) {
            int a = i + 1;
            System.out.println(a + "-" + newQuestionBax2.get(i).getName());
        }
        return y;
    }

    private static boolean showQInOrder(User user, ArrayList<Question> newQuestionBax1,
                                        ArrayList<Question> newQuestionBax2, int x, int y, int z) {
        System.out.println("Choose the question");
        int num = ScannerWrapper.getInstance().nextInt();
        if (num <= 0 || num > newQuestionBax2.size()) {
            return true;
        }
        if (x == 1) {
            showQuestionInQuestionBax(newQuestionBax2.get(newQuestionBax1.size() - num), user);
        }
        if (y == 1) {
            showQuestionInQuestionBax(newQuestionBax2.get(num - 1), user);
        }
        if (z == 1) {
            showQuestionInQuestionBax(newQuestionBax1.get(num - 1), user);
        }
        return false;
    }

    private static boolean addBasedOnLevel(ArrayList<Question> complex, ArrayList<Question> difficult,
                                           ArrayList<Question> standard, ArrayList<Question> easy) {
        for (int i = 0; i < AppliedList.getQuestionBax().size(); i++) {
            switch (AppliedList.getQuestionBax().get(i).getLevel()) {
                case EASY -> easy.add(AppliedList.getQuestionBax().get(i));
                case STANDARD -> standard.add(AppliedList.getQuestionBax().get(i));
                case DIFFICULT -> difficult.add(AppliedList.getQuestionBax().get(i));
                case COMPLEX -> complex.add(AppliedList.getQuestionBax().get(i));
                default -> {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkExistQ() {
        if (AppliedList.getQuestionBax().size() == 0) {
            System.out.println("There is no question in question bax!");
            System.out.println("Exit");
            String exit1;
            do {
                exit1 = ScannerWrapper.getInstance().nextLine();
            } while (!exit1.equals("0"));
            return true;
        }
        return false;
    }

    public static void showQuestionInQuestionBax(Question question, User user) {
        showQInfo(question);
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("1-Send answer\nExit");
        String enteredNum = ScannerWrapper.getInstance().nextLine();
        switch (enteredNum) {
            case "1":
                String answer = getAnswer(question, user);
                if (answer == null) {
                    return;
                }
                int index2 = -1;
                System.out.println("Your answer uploaded successfully");
                for (int i = 0; i < question.getAnswers().size(); i++) {
                    if (user.getEmail().equals(question.getAnswers().get(i).getEmail())) {
                        index2 = i;
                        break;
                    }
                }
                if (question.isAutoCorrection()) {
                    showScore(question, answer, index2);
                } else {
                    question.getAnswers().get(index2).addScore(-1);
                    System.out.println("Your Score : Not given mark yet");
                }
                break;
            default:
                return;
        }
        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    private static void showScore(Question question, String answer, int index2) {
        if (answer.equals(question.getCorrectAnswer())) {
            question.getAnswers().get(index2).addScore(question.getTotalScore());
            question.getAnswers().get(index2).setFinalScore(question.getTotalScore());
            System.out.println("Your Score :" + question.getTotalScore());
        } else {
            question.getAnswers().get(index2).addScore(0);
            if (question.getFinalScore() == -1) {
                question.getAnswers().get(index2).setFinalScore(0);
            }
        }
        System.out.println("Your Score : 0.0");
    }

    @Nullable
    private static String getAnswer(Question question, User user) {
        int index1 = -1;
        for (int i = 0; i < question.getAnswers().size(); i++) {
            if (user.getEmail().equals(question.getAnswers().get(i).getEmail())) {
                index1 = i;
                break;
            }
        }
        if (checkAdmin(user)) {
            return null;
        }
        String answer = ScannerWrapper.getInstance().nextLine();
        if (answer.equals("0")) {
            return null;
        }
        if (index1 == -1) {
            Answers ans = new Answers(user.getUserName(), user.getEmail());
            ans.addSolution(answer);
            ans.addDates(LocalDateTime.now());
            question.addAnswer(ans);

        } else {
            question.getAnswers().get(index1).addSolution(answer);
            question.getAnswers().get(index1).addDates(LocalDateTime.now());
        }
        return answer;
    }

    private static boolean checkAdmin(User user) {
        if (user instanceof Admin) {
            System.out.println("You are Admin\nYou cannot send answer!!\nExit");
            String exit;
            do {
                exit = ScannerWrapper.getInstance().nextLine();
            } while (!exit.equals("0"));
            return true;
        }
        return false;
    }

    private static void showQInfo(Question question) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("QuestionName :" + question.getName() + "\n" + "Level :" +
                question.getLevel() + "\t" + "Total score :"
                + question.getTotalScore() + "\n" + "Explanation :" + question.getExplanation());
        if (question.isTest()) {
            for (int i = 0; i < question.getCases().size(); i++) {
                int j = i + 1;
                System.out.println(j + "-" + question.getCases().get(i));
            }
        }
    }


}

