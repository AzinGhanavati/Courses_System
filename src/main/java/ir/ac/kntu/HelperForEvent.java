package ir.ac.kntu;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HelperForEvent {
    public static void addQuestionToEvent(Event event) {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("1-Write Question\n2-Add question from QuestionBox\nExit");
            String chooseNum;
            do {
                chooseNum = ScannerWrapper.getInstance().nextLine();
            } while (!chooseNum.equals("0") && !chooseNum.equals("1") && !chooseNum.equals("2"));
            switch (chooseNum) {
                case "1":
                    Question newQuestion = writQ();
                    if (newQuestion == null) {
                        continue;
                    }
                    event.addQuestion(newQuestion);
                    System.out.println("Question added successfully:)\n" +
                            "Do you want to add this question to questions bax?");
                    String enterChoice = ScannerWrapper.getInstance().nextLine();
                    switch (enterChoice) {
                        case "Yes":
                            AppliedList.getInstance().addQuestionBax(writQ());
                            AppliedList.getInstance().addDateAndTime(LocalDateTime.now());
                            break;
                        default:
                            return;
                    }
                    break;
                case "2":
                    Question assignment = addQuestionFromQuestionBax();
                    if (assignment == null) {
                        continue;
                    } else {
                        event.addQuestion(assignment);
                    }
                    break;
                default:
                    return;
            }
        }
    }

    public static Question writQ() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Choose the type of question");
        System.out.println("1-Test question  2-Short Answer question  3-Descriptive question  Exit");
        String enteredNum = ScannerWrapper.getInstance().nextLine();
        switch (enteredNum) {
            case "1":
                Question assignment = test();
                if (assignment == null) {
                    return null;
                }
                return assignment;
            case "2":
                Question assignment1 = shortAns();
                if (assignment1 == null) {
                    return null;
                }
                return assignment1;
            case "3":
                Question assignment2 = descriptive();
                if (assignment2 == null) {
                    return null;
                }
                return assignment2;
            default:
                return null;
        }
    }

    public static Question test() {
        boolean isTest = true;
        System.out.println("Do you prefer auto-correction?");
        String ans = ScannerWrapper.getInstance().nextLine();
        switch (ans) {
            case "Yes":
                return getQuestion(isTest);
            case "No":
                return notAutoCorrection(isTest);
            default:
                return null;
        }
    }

    @Nullable
    private static Question notAutoCorrection(boolean isTest) {
        boolean autoCorrection1 = false;
        System.out.print("Name: ");
        String name1 = ScannerWrapper.getInstance().nextLine();
        if (name1.equals("0")) {
            return null;
        }
        System.out.print("Explanation :");
        String explanation1 = ScannerWrapper.getInstance().nextLine();
        if (explanation1.equals("0")) {
            return null;
        }
        Level level1 = getLevel();
        if (level1 == null) {
            return null;
        }
        System.out.print("Score :");
        double score1 = ScannerWrapper.getInstance().nextDouble();
        if (score1 == 0) {
            return null;
        }
        System.out.println("Cases :");
        ArrayList<String> options1 = new ArrayList<>();
        while (true) {
            String cases = ScannerWrapper.getInstance().nextLine();

            if (cases.equals("0")) {
                return null;
            }
            if (cases.equals("*")) {
                break;
            }
            options1.add(cases);
        }
        return new Question(name1, explanation1, options1, level1, score1, autoCorrection1, isTest);
    }

    @Nullable
    private static Level getLevel() {
        System.out.print("Level :");
        System.out.println("1-Complex  2-Difficult  3-Standard  4-Easy");
        System.out.println("Choose the correct number");
        String num1 = ScannerWrapper.getInstance().nextLine();
        Level level1 = Level.EASY;
        switch (num1) {
            case "1":
                level1 = Level.COMPLEX;
                break;
            case "2":
                level1 = Level.DIFFICULT;
                break;
            case "3":
                level1 = Level.STANDARD;
                break;
            case "4":
                break;
            default:
                return null;
        }
        return level1;
    }

    @Nullable
    private static Question getQuestion(boolean isTest) {
        boolean autoCorrection = true;
        System.out.print("Name: ");
        String name = ScannerWrapper.getInstance().nextLine();
        if (name.equals("0")) {
            return null;
        }
        System.out.print("Explanation :");
        String explanation = ScannerWrapper.getInstance().nextLine();
        if (explanation.equals("0")) {
            return null;
        }
        System.out.print("Score :");
        double score = ScannerWrapper.getInstance().nextDouble();
        if (score == 0) {
            return null;
        }
        System.out.println("Cases :");
        System.out.println("Stop add cases(press <<*>>)\nExit(press <<0>>)");
        ArrayList<String> options = new ArrayList<>();
        while (true) {
            String cases = ScannerWrapper.getInstance().nextLine();

            if (cases.equals("0")) {
                return null;
            }
            if (cases.equals("*")) {
                break;
            }
            options.add(cases);
        }
        System.out.print("CorrectAns :");
        String correctAns = ScannerWrapper.getInstance().nextLine();
        if (correctAns.equals("0")) {
            return null;
        }
        Level level = getLevel();
        if (level == null) {
            return null;
        }
        return new Question(name, explanation, options, correctAns, level, score, autoCorrection, isTest);
    }

    public static Question shortAns() {
        boolean isShortAns = true;
        System.out.println("Do you prefer auto-correction?");
        String ans = ScannerWrapper.getInstance().nextLine();
        switch (ans) {
            case "Yes":
                return getQuestion1(isShortAns);
            case "No":
                boolean autoCorrection1 = false;
                System.out.print("Name: ");
                String name1 = ScannerWrapper.getInstance().nextLine();
                if (name1.equals("0")) {
                    return null;
                }
                System.out.print("Explanation :");
                String explanation1 = ScannerWrapper.getInstance().nextLine();
                if (explanation1.equals("0")) {
                    return null;
                }
                Level level1 = getLevel();
                if (level1 == null) {
                    return null;
                }
                System.out.print("Score :");
                double score1 = ScannerWrapper.getInstance().nextDouble();
                if (score1 == 0) {
                    return null;
                }
                return new Question(name1, explanation1, level1, score1, autoCorrection1, isShortAns);
            default:
                return null;
        }
    }

    @Nullable
    private static Question getQuestion1(boolean isShortAns) {
        boolean autoCorrection = true;
        System.out.print("Name: ");
        String name = ScannerWrapper.getInstance().nextLine();
        if (name.equals("0")) {
            return null;
        }
        System.out.print("Explanation :");
        String explanation = ScannerWrapper.getInstance().nextLine();
        if (explanation.equals("0")) {
            return null;
        }
        System.out.print("CorrectAns :");
        String correctAns = ScannerWrapper.getInstance().nextLine();
        if (correctAns.equals("0")) {
            return null;
        }
        Level level = getLevel();
        if (level == null) {
            return null;
        }
        System.out.print("Score :");
        double score = ScannerWrapper.getInstance().nextDouble();
        if (score == 0) {
            return null;
        }
        return new Question(name, explanation, correctAns, level, score, autoCorrection, isShortAns);
    }

    public static Question descriptive() {
        boolean isDescriptive = true;
        boolean autoCorrection1 = false;
        System.out.print("Name: ");
        String name1 = ScannerWrapper.getInstance().nextLine();
        if (name1.equals("0")) {
            return null;
        }
        System.out.print("Explanation :");
        String explanation1 = ScannerWrapper.getInstance().nextLine();
        if (explanation1.equals("0")) {
            return null;
        }

        Level level1 = getLevel();
        if (level1 == null) {
            return null;
        }
        System.out.print("Score :");
        double score1 = ScannerWrapper.getInstance().nextDouble();
        if (score1 == 0) {
            return null;
        }
        return new Question(name1, explanation1, level1, score1, autoCorrection1, isDescriptive);
    }

    public static Question addQuestionFromQuestionBax() {
        System.out.println("-----------------------------------------------------------------------------------------");
        if (AppliedList.getQuestionBax().size() == 0) {
            System.out.println("there is no question in question bax!");
            System.out.println("Exit");
            String exit1;
            do {
                exit1 = ScannerWrapper.getInstance().nextLine();
            } while (!exit1.equals("0"));
            return null;
        }
        for (int i = 0; i < AppliedList.getQuestionBax().size(); i++) {
            int j = i + 1;
            System.out.println(j + "-" + AppliedList.getQuestionBax().get(i).getName());
        }
        System.out.println("Choose the question");
        int num = ScannerWrapper.getInstance().nextInt();
        if (num <= 0 || num > AppliedList.getQuestionBax().size()) {
            return null;
        } else {
            return AppliedList.getQuestionBax().get(num - 1);
        }
    }


    public static void editQuestion(Event event) {
        boolean varForLoop = true;
        while (varForLoop) {
            for (int i = 0; i < event.getQuestions().size(); i++) {
                int j = i + 1;
                System.out.println(j + "-" + event.getQuestions().get(i).getName());
            }
            int enteredNum = ScannerWrapper.getInstance().nextInt();
            if (enteredNum > 0 && enteredNum <= event.getQuestions().size()) {
                System.out.println("1-Delete question\n2-Edit question\nExit");
                int chooseNum = ScannerWrapper.getInstance().nextInt();
                switch (chooseNum) {
                    case 1:
                        event.getQuestions().remove(event.getQuestions().get(enteredNum - 1));
                        System.out.println("**This question removed successfully**");
                        break;
                    case 2:
                        Question newQuestion = writQ();
                        event.getQuestions().set(event.getQuestions().
                                indexOf(event.getQuestions().get(enteredNum - 1)), newQuestion);
                        newQuestion.setAnswers(event.getQuestions().get(enteredNum - 1).getAnswers());
                        System.out.println("**THis question edited successfully**");
                        break;
                    default:
                        return;
                }
            }
        }
    }

}
