package ir.ac.kntu;

import org.jetbrains.annotations.Nullable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HomeWork {

    private String name;

    private ArrayList<Question> questions;

    private LocalDateTime startTime;

    private LocalDateTime deadLine;

    private boolean isTable;

    private String explanation;

    private boolean isOnTest;

    private int delayCoefficient;

    private LocalDateTime sendWithDelay;

    private ArrayList<User> participated;

    public HomeWork(String name, LocalDateTime startTime, LocalDateTime deadLine, String explanation,
                    int delayCoefficient, LocalDateTime sendWithDelay, boolean isTable, boolean isOnTest) {
        this.name = name;
        this.startTime = startTime;
        this.deadLine = deadLine;
        this.explanation = explanation;
        this.delayCoefficient = delayCoefficient;
        this.sendWithDelay = sendWithDelay;
        this.isTable = isTable;
        questions = new ArrayList<>();
        participated = new ArrayList<>();
        this.isOnTest = isOnTest;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setDeadLine(LocalDateTime deadLine, LocalDateTime localDateTime) {
        this.deadLine = deadLine;
    }

    public void setDelayCoefficient(int delayCoefficient) {
        this.delayCoefficient = delayCoefficient;
    }

    public void setOnTest(boolean onTest) {
        isOnTest = onTest;
    }

    public void setSendWithDelay(LocalDateTime sendWithDelay) {
        this.sendWithDelay = sendWithDelay;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setTable(boolean table) {
        isTable = table;
    }

    public int getDelayCoefficient() {
        return delayCoefficient;
    }

    public boolean isTable() {
        return isTable;
    }

    public void setIsTable(boolean isTable) {
        this.isTable = isTable;
    }

    public LocalDateTime getSendWithDelay() {
        return sendWithDelay;
    }

    public boolean isOnTest() {
        return isOnTest;
    }

    public ArrayList<User> getParticipated() {
        return new ArrayList<>(participated);
    }

    public void addParticipatedUser(User user) {
        participated.add(user);
    }

    public void scoreBoard() {
        System.out.println("*************************************SCORE BOARD*************************************");
        ArrayList<Long> seconds = new ArrayList<>();
        ArrayList<Double> sumScores = new ArrayList<>();
        for (int i = 0; i < participated.size(); i++) {
            ArrayList<Double> output = HelperMethods.scores(this, participated.get(i).getEmail());
            int index = output.size();
            sumScores.add(output.get(index - 1));
            seconds.add(HelperMethods.differenceDates(this, participated.get(i).getEmail()));
        }

        ArrayList<Integer> helperArr = new ArrayList<>();
        for (int i = 0; i < sumScores.size(); i++) {
            helperArr.add(i);
        }
        sortBaseOnScore(seconds, sumScores, helperArr);
        for (int i = 0; i < participated.size(); i++) {
            System.out.print(participated.get(helperArr.get(i)).getName() + "\t");
            for (int j = 0; j < questions.size() + 1; j++) {
                if (HelperMethods.scores(this, participated.get(helperArr.get(i)).getEmail()).get(j) != -1) {
                    System.out.print(HelperMethods.scores(this,
                            participated.get(helperArr.get(i)).getEmail()).get(j) + "  ");
                } else {
                    System.out.print("-\t");
                }
            }
            System.out.println("\n");
        }
        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    private void sortBaseOnScore(ArrayList<Long> seconds, ArrayList<Double> sumScores, ArrayList<Integer> helperArr) {
        for (int i = 0; i < sumScores.size() - 1; i++) {
            for (int j = 0; j < sumScores.size() - 1; j++) {
                if (sumScores.get(j) < sumScores.get(j + 1)) {
                    int temp = helperArr.get(j);
                    helperArr.set(j, helperArr.get(j + 1));
                    helperArr.set(j + 1, temp);
                } else if (sumScores.get(j) == sumScores.get(j + 1)) {
                    if (seconds.get(j) > seconds.get(j + 1)) {
                        int temp = helperArr.get(j);
                        helperArr.set(j, helperArr.get(j + 1));
                        helperArr.set(j + 1, temp);
                    }
                }
            }
        }
    }

    public void addQuestionToHomeWrk() {
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
                    addQuestion(newQuestion);
                    System.out.println("Question added successfully:)");
                    System.out.println("Do you want to add this question to questions bax?");
                    String enterChoice = ScannerWrapper.getInstance().nextLine();
                    switch (enterChoice) {
                        case "Yes":
                            Question question=writQ();
                            AppliedList.getInstance().addQuestionBax(question);
                            AppliedList.getInstance().addDateAndTime(LocalDateTime.now());
                            break;
                        case "No":
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
                        addQuestion(assignment);
                    }
                    break;
                default:
                    return;
            }
        }
    }

    public Question writQ() {
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

    public Question test() {
        boolean isTest = true;
        System.out.println("Do you prefer auto-correction?");
        String ans = ScannerWrapper.getInstance().nextLine();
        switch (ans) {
            case "Yes":
                return getQuestion1(isTest);
            case "No":
                boolean autoCorrection1 = false;
                String name1 = getQuestionInf("Name: ");
                if (name1 == null) {
                    return null;
                }
                String explanation1 = getQuestionInf("Explanation :");
                if (explanation1 == null) {
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
            default:
                return null;
        }
    }

    @Nullable
    private Question getQuestion1(boolean isTest) {
        boolean autoCorrection = true;
        String name = getQuestionInf("Name: ");
        if (name == null) {
            return null;
        }
        String explanation = getQuestionInf("Explanation :");
        if (explanation == null) {
            return null;
        }
        System.out.print("Score :");
        double score = ScannerWrapper.getInstance().nextDouble();
        if (score == 0) {
            return null;
        }
        ArrayList<String> options = getCases();
        if (options == null) {
            return null;
        }
        String correctAns = getQuestionInf("CorrectAns :");
        if (correctAns == null) {
            return null;
        }
        Level level = getLevel();
        if (level == null) {
            return null;
        }
        return new Question(name, explanation, options, correctAns, level, score, autoCorrection, isTest);
    }

    @Nullable
    private ArrayList<String> getCases() {
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
        return options;
    }

    public Question shortAns() {
        boolean isShortAns = true;
        System.out.println("Do you prefer auto-correction?");
        String ans = ScannerWrapper.getInstance().nextLine();
        switch (ans) {
            case "Yes":
                return getQuestion(isShortAns);
            case "No":
                boolean autoCorrection1 = false;
                String name1 = getQuestionInf("Name: ");
                if (name1 == null) {
                    return null;
                }
                String explanation1 = getQuestionInf("Explanation :");
                if (explanation1 == null) {
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
    private Question getQuestion(boolean isShortAns) {
        boolean autoCorrection = true;
        String name = getQuestionInf("Name: ");
        if (name == null) {
            return null;
        }
        String explanation = getQuestionInf("Explanation :");
        if (explanation == null) {
            return null;
        }
        String correctAns = getQuestionInf("CorrectAns :");
        if (correctAns == null) {
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

    @Nullable
    private String getQuestionInf(String s) {
        System.out.print(s);
        String str = ScannerWrapper.getInstance().nextLine();
        if (str.equals("0")) {
            return null;
        }
        return str;
    }

    @Nullable
    private Level getLevel() {
        System.out.print("Level :");
        System.out.println("1-Complex  2-Difficult  3-Standard  4-Easy");
        System.out.println("Choose the correct number");
        String num = ScannerWrapper.getInstance().nextLine();
        Level level = Level.EASY;
        switch (num) {
            case "1":
                level = Level.COMPLEX;
                break;
            case "2":
                level = Level.DIFFICULT;
                break;
            case "3":
                level = Level.STANDARD;
                break;
            case "4":
                break;
            default:
                return null;
        }
        return level;
    }

    public Question descriptive() {
        boolean isDescriptive = true;
        boolean autoCorrection1 = false;
        String name1 = getQuestionInf("Name: ");
        if (name1 == null) {
            return null;
        }
        String explanation1 = getQuestionInf("Explanation :");
        if (explanation1 == null) {
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

    @Override
    public String toString() {
        return "HomeWork{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", deadLine=" + deadLine +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}

