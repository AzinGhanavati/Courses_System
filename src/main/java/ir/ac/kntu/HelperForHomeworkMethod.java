/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ir.ac.kntu;

/**
 * @author Markazi.co
 */


import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HelperForHomeworkMethod {
    public static void editSpecialHomeWork(HomeWork homeWork) {
        int enteredNum = menuAndChoose();
        switch (enteredNum) {
            case 1:
                if (case1(homeWork)) {
                    return;
                }
                break;
            case 2:
                if (case2(homeWork)) {
                    return;
                }
                break;
            case 3:
                if (case3(homeWork)) {
                    return;
                }
                break;
            case 4:
                if (case4(homeWork)) {
                    return;
                }
                break;
            case 5:
                if (case5(homeWork)) {
                    return;
                }
                break;
            case 6:
                if (case6(homeWork)) {
                    return;
                }
                break;
            case 7:
                if (case7(homeWork)) {
                    return;
                }
                break;
            case 8:
                HelperForHomeworkMethod.editQuestion(homeWork);
                break;
            default:
                return;
        }
    }

    private static int menuAndChoose() {
        System.out.println("1-Name\n2-StartTime\n3-DeadLine\n4-Explanation\n" +
                "5-DelayCoefficient\n6-SendWithDelay\n7-Table\n8-Edit questions\nexit");
        int enteredNum = ScannerWrapper.getInstance().nextInt();
        return enteredNum;
    }

    private static boolean case5(HomeWork homeWork) {
        System.out.print("DelayCoefficient : ");
        int delayCoefficient = ScannerWrapper.getInstance().nextInt();
        if (delayCoefficient == 0) {
            return true;
        }
        homeWork.setDelayCoefficient(delayCoefficient);
        return false;
    }

    private static boolean case4(HomeWork homeWork) {
        System.out.print("Explanation :");
        String explanation = ScannerWrapper.getInstance().nextLine();
        if (explanation.equals("0")) {
            return true;
        }
        homeWork.setExplanation(explanation);
        return false;
    }

    private static boolean case7(HomeWork homeWork) {
        System.out.println("Table");
        String chooseAns;
        boolean isTAble = false;
        do {
            chooseAns = ScannerWrapper.getInstance().nextLine();
        } while (!chooseAns.equals("Yes") && !chooseAns.equals("No") && !chooseAns.equals("0"));
        if (chooseAns.equals("Yes")) {
            isTAble = true;
        } else if (chooseAns.equals("0")) {
            return true;
        }
        homeWork.setIsTable(isTAble);
        return false;
    }

    private static boolean case6(HomeWork homeWork) {
        System.out.print("DelayTime :");
        String delayTime;
        do {
            delayTime = ScannerWrapper.getInstance().nextLine();
        } while (!delayTime.equals("0") && !delayTime.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));

        if (delayTime.equals("0")) {
            return true;
        }
        LocalDateTime sendWithDelay = HelperMethods.convertStringToDate(delayTime);
        homeWork.setSendWithDelay(sendWithDelay);
        return false;
    }

    private static boolean case3(HomeWork homeWork) {
        System.out.print("deadLine :");
        String dead;
        do {
            dead = ScannerWrapper.getInstance().nextLine();
        } while (!dead.equals("0") && !dead.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));
        if (dead.equals("0")) {
            return true;
        }
        LocalDateTime deadLine = HelperMethods.convertStringToDate(dead);
        homeWork.setDeadLine(deadLine, HelperMethods.convertStringToDate("2022-06-16 23:25:10"));
        return false;
    }

    private static boolean case2(HomeWork homeWork) {
        System.out.print("StartTime :");
        String start;
        do {
            start = ScannerWrapper.getInstance().nextLine();
        } while (!start.equals("0") && !start.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}"));
        if (start.equals("0")) {
            return true;
        }
        LocalDateTime startTime = HelperMethods.convertStringToDate(start);
        homeWork.setStartTime(startTime);
        return false;
    }

    private static boolean case1(HomeWork homeWork) {
        System.out.print("Name : ");
        String name = ScannerWrapper.getInstance().nextLine();
        if (name.equals("0")) {
            return true;
        }
        homeWork.setName(name);
        return false;
    }

    public static void editQuestion(HomeWork homeWork) {
        boolean varForLoop = true;
        while (varForLoop) {
            for (int i = 0; i < homeWork.getQuestions().size(); i++) {
                int j = i + 1;
                System.out.println(j + "-" + homeWork.getQuestions().get(i).getName());
            }
            int enteredNum = ScannerWrapper.getInstance().nextInt();
            if (enteredNum > 0 && enteredNum <= homeWork.getQuestions().size()) {
                System.out.println("1-Delete question\n2-Edit question\nExit");
                int chooseNum = ScannerWrapper.getInstance().nextInt();
                switch (chooseNum) {
                    case 1:
                        homeWork.getQuestions().remove(homeWork.getQuestions().get(enteredNum - 1));
                        System.out.println("**This question removed successfully**");
                        break;
                    case 2:
                        Question newQuestion = homeWork.writQ();
                        homeWork.getQuestions().set(homeWork.getQuestions().
                                indexOf(homeWork.getQuestions().get(enteredNum - 1)), newQuestion);
                        newQuestion.setAnswers(homeWork.getQuestions().get(enteredNum - 1).getAnswers());
                        System.out.println("**THis question edited successfully**");
                        break;
                    default:
                        return;
                }
            }
        }
    }

    public static void showQuestionToOwner(HomeWork homeWork, User user) {
        boolean varForLoop = true;
        while (varForLoop) {
            Integer num = ownerChooseQustion(homeWork);
            if (num == null) {
                return;
            }
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("QuestionName :" + homeWork.getQuestions().get(num - 1).getName() + "\n" + "Level :" +
                    homeWork.getQuestions().get(num - 1).getLevel() + "\t" + "Total score :" +
                    homeWork.getQuestions().get(num - 1).getTotalScore() + "\n" + "Explanation :" +
                    homeWork.getQuestions().get(num - 1).getExplanation());
            if (homeWork.getQuestions().get(num - 1).isTest()) {
                for (int i = 0; i < homeWork.getQuestions().get(num - 1).getCases().size(); i++) {
                    int j = i + 1;
                    System.out.println(j + "-" + homeWork.getQuestions().get(num - 1).getCases().get(i));
                }
            }
            System.out.println("--------------------------------------------------------------------------");
            if (user.isProfessor()) {
                if (showAndMarkAns(homeWork, num)) {
                    return;
                }
            } else {
                System.out.println("1-Show all Answers of special user\n0-Exit");
                String enteredNum = ScannerWrapper.getInstance().nextLine();
                switch (enteredNum) {
                    case "1":
                        homeWork.getQuestions().get(num - 1).allUserAnswer();
                        break;
                    default:
                        return;
                }
            }
        }
    }

    private static boolean showAndMarkAns(HomeWork homeWork, Integer num) {
        System.out.println("1-Show all Answers of special user\n2-Mark the answers\n0-Exit");
        String enteredNum = ScannerWrapper.getInstance().nextLine();
        switch (enteredNum) {
            case "1":
                homeWork.getQuestions().get(num - 1).allUserAnswer();
                break;
            case "2":
                homeWork.getQuestions().get(num - 1).markAnswers();
            default:
                return true;
        }
        return false;
    }

    @Nullable
    private static Integer ownerChooseQustion(HomeWork homeWork) {
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < homeWork.getQuestions().size(); i++) {
            int j = i + 1;
            System.out.println(j + "-" + homeWork.getQuestions().get(i).getName());
        }
        System.out.println("Choose the question:");
        int num = ScannerWrapper.getInstance().nextInt();
        if (num <= 0 | num > homeWork.getQuestions().size()) {
            return null;
        }
        return num;
    }


    public static void finalAnswers(HomeWork homeWork, User user) {
        System.out.println("--------------------------------------------------------------------------");
        ArrayList<LocalDateTime> allDates = new ArrayList<>();
        ArrayList<String> allAnswerStr = new ArrayList<>();
        ArrayList<Double> allScore = new ArrayList<>();
        ArrayList<Integer> questionNumbers = new ArrayList<>();
        for (int i = 0; i < homeWork.getQuestions().size(); i++) {
            int index1 = -1;
            if (homeWork.getQuestions().get(i).getAnswers().size() == 0) {
                System.out.println("There is no answer!");
                exit();
                return;
            }
            for (int j = 0; j < homeWork.getQuestions().get(i).getAnswers().size(); j++) {
                if (user.getEmail().equals(homeWork.getQuestions().get(i).getAnswers().get(j).getEmail())) {
                    index1 = j;
                    break;
                }
            }
            if (index1 != -1) {
                checkAutoCorrection(homeWork, allDates, allAnswerStr, allScore, questionNumbers, i, index1);
            }
        }
        ArrayList<LocalDateTime> newAllDates = new ArrayList<>();
        ArrayList<String> newAllAnswerStr = new ArrayList<>();
        ArrayList<Double> newAllScore = new ArrayList<>();
        ArrayList<Integer> newQuestionNumbers = new ArrayList<>();
        addNewData(allDates, allAnswerStr, allScore, questionNumbers, newAllDates, newAllAnswerStr,
                newAllScore, newQuestionNumbers);
        System.out.println("**Final Answers**\nQuestionNumber\tAnswers\tScores");
        for (int i = 0; i < newAllDates.size(); i++) {
            if (newAllScore.get(i) == -1) {
                System.out.println("Number" + newQuestionNumbers.get(i) + "\t" + newAllAnswerStr.get(i) + "\t" +
                        "No score given");
            } else {
                System.out.println("Number" + newQuestionNumbers.get(i) + "\t" + newAllAnswerStr.get(i) + "\t" +
                        newAllScore.get(i));
            }
        }
        exit();
    }

    private static void exit() {
        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    private static void addNewData(ArrayList<LocalDateTime> allDates, ArrayList<String> allAnswerStr,
                                   ArrayList<Double> allScore, ArrayList<Integer> questionNumbers,
                                   ArrayList<LocalDateTime> newAllDates, ArrayList<String> newAllAnswerStr,
                                   ArrayList<Double> newAllScore, ArrayList<Integer> newQuestionNumbers) {
        for (int i = 0; i < HelperMethods.sortDates(allDates).size(); i++) {
            newAllDates.add(allDates.get(HelperMethods.sortDates(allDates).get(i)));
            newAllAnswerStr.add(allAnswerStr.get(HelperMethods.sortDates(allDates).get(i)));
            newAllScore.add(allScore.get(HelperMethods.sortDates(allDates).get(i)));
            newQuestionNumbers.add(questionNumbers.get(HelperMethods.sortDates(allDates).get(i)));
        }
    }

    private static void checkAutoCorrection(HomeWork homeWork, ArrayList<LocalDateTime> allDates,
                                            ArrayList<String> allAnswerStr, ArrayList<Double> allScore,
                                            ArrayList<Integer> questionNumbers, int i, int index1) {
        int size = homeWork.getQuestions().get(i).getAnswers().get(index1).getDates().size();
        if (homeWork.getQuestions().get(i).isAutoCorrection()) {
            int index2 = -1;
            double totalScore = homeWork.getQuestions().get(i).getTotalScore();
            for (int j = 0; j < homeWork.getQuestions().get(i).getAnswers().get(index1).getSocres().size(); j++) {
                if (homeWork.getQuestions().get(i).getAnswers().get(index1).getSocres().get(j) == totalScore) {
                    index2 = j;
                    break;
                }
            }
            if (index2 == -1) {
                allDates.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getDates().get(0));
                allAnswerStr.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getSolution().get(0));
                allScore.add(0.0);
                questionNumbers.add(i + 1);

            } else {
                allDates.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getDates().get(index2));
                allAnswerStr.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getSolution().get(index2));
                allScore.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getSocres().get(index2));
                questionNumbers.add(i + 1);
            }
        } else {
            allDates.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getDates().get(size - 1));
            allAnswerStr.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getSolution().get(size - 1));
            allScore.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getFinalScore());//check plz
            questionNumbers.add(i + 1);
        }
    }

    public static void allAnswers(HomeWork homeWork, User user) {
        System.out.println("--------------------------------------------------------------------------");
        ArrayList<LocalDateTime> allDates = new ArrayList<>();
        ArrayList<String> allAnswerStr = new ArrayList<>();
        ArrayList<Double> allScore = new ArrayList<>();
        ArrayList<Integer> questionNumbers = new ArrayList<>();
        if (checkAndAddData(homeWork, user, allDates, allAnswerStr, allScore, questionNumbers)) {
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
        exit();
    }

    private static boolean checkAndAddData(HomeWork homeWork, User user, ArrayList<LocalDateTime> allDates,
                                           ArrayList<String> allAnswerStr, ArrayList<Double> allScore,
                                           ArrayList<Integer> questionNumbers) {
        for (int i = 0; i < homeWork.getQuestions().size(); i++) {
            int index2 = -1;
            if (homeWork.getQuestions().get(i).getAnswers().size() == 0) {
                System.out.println("There is no answer!");
                exit();
                return true;
            }
            for (int j = 0; j < homeWork.getQuestions().get(i).getAnswers().size(); j++) {
                if (user.getEmail().equals(homeWork.getQuestions().get(i).getAnswers().get(j).getEmail())) {
                    index2 = j;
                    break;
                }
            }
            if (index2 != -1) {
                for (int e = 0; e < homeWork.getQuestions().get(i).getAnswers().get(index2).getSolution().size(); e++) {
                    questionNumbers.add(i + 1);
                }
                allAnswerStr.addAll(homeWork.getQuestions().get(i).getAnswers().get(index2).getSolution());
                allDates.addAll(homeWork.getQuestions().get(i).getAnswers().get(index2).getDates());
                allScore.addAll(homeWork.getQuestions().get(i).getAnswers().get(index2).getSocres());
            }
        }
        return false;
    }


}

