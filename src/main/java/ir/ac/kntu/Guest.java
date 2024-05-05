package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Guest {
    static void showMenu() {
        while (true) {
            System.out.println("Guest menu :\n"
                    + "1. bank list\n"
                    + "2. event list\n"
                    + "0. Exit\n"
                    + "Select The Desired Option : ");
            Scanner input = new Scanner(System.in);
            switch (input.next()) {
                case "1":
                    showBankList();
                    break;
                case "2":
                    showEventList();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\nyour option NOT exist\n");
                    return;
            }
        }

    }

    public static void showBankList() {
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < AppliedList.getQuestionBax().size(); i++) {
            System.out.println(i + 1 + "-" + AppliedList.getQuestionBax().get(i).getName()+"\n");
        }
        System.out.println("Choose question:");
        int choose = ScannerWrapper.getInstance().nextInt();
        if (choose <= 0 || choose > AppliedList.getQuestionBax().size()) {
            return;
        }
        Question question = AppliedList.getQuestionBax().get(choose - 1);
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("QuestionName :" + question.getName() + "\n" + "Level :" + question.getLevel() + "\t" + "Total score :"
                + question.getTotalScore() + "\n" + "Explanation :" + question.getExplanation());
        if (question.isTest()) {
            for (int i = 0; i < question.getCases().size(); i++) {
                int j = i + 1;
                System.out.println(j + "-" + question.getCases().get(i));
            }
        }
        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    public static void showEventList() {
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < AppliedList.getEvents().size(); i++) {
            if (AppliedList.getEvents().get(i).getDeadLine().compareTo(LocalDateTime.now()) < 1) {
                System.out.println(AppliedList.getEvents().get(i).toString());
                AppliedList.getEvents().get(i).showQuestion();
                System.out.println("Choose question:");
                int choose = ScannerWrapper.getInstance().nextInt();
                if (choose <= 0 || choose > AppliedList.getEvents().get(i).getQuestions().size()) {
                    return;
                }
                AppliedList.getEvents().get(i).getQuestions().get(choose - 1).showQInf();
            }
        }
        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }
}

