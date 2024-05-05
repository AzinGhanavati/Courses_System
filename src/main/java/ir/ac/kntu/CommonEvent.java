package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonEvent extends Event {

    public CommonEvent(String name, LocalDateTime startTime, LocalDateTime deadLine, int maxField) {
        super(name, startTime, deadLine, maxField);
    }

    @Override
    public void donate() {
        System.out.println("*************************************SCORE BOARD*************************************");
        ArrayList<Long> seconds = new ArrayList<>();
        ArrayList<Double> sumScores = new ArrayList<>();
        for (int i = 0; i < getParticipated().size(); i++) {
            ArrayList<Double> output = HelperMethods.scoresForEvent(this, getParticipated().get(i).getEmail());
            int index = output.size();
            sumScores.add(output.get(index - 1));
            seconds.add(HelperMethods.differenceDatesDorEvent(this, getParticipated().get(i).getEmail()));
        }
        ArrayList<Integer> helperArr = new ArrayList<>();
        for (int i = 0; i < sumScores.size(); i++) {
            helperArr.add(i);
        }
        sortScoreAndDates(seconds, sumScores, helperArr);
        for (int i = 0; i < getParticipated().size(); i++) {
            System.out.print(getParticipated().get(helperArr.get(i)).getName() + "\t");
            for (int j = 0; j < getParticipated().size() + 1; j++) {
                if (HelperMethods.scoresForEvent(this, getParticipated().get(helperArr.get(i)).getEmail()).get(j) != -1) {
                    System.out.print(HelperMethods.scoresForEvent(this, getParticipated().get(helperArr.get(i)).getEmail()).get(j) + "  ");
                } else {
                    System.out.print("-\t");
                }
            }
            System.out.println("\n");
        }
        int num;
        if (getParticipated().size() < 5) {
            num = getParticipated().size();
        } else {
            num = 5;
        }
        if (getParticipated().size() > 0) {
            //get 20 score to the first 5 customer
            for (int i = 0; i < num; i++) {
                getParticipated().get(helperArr.get(i)).addScore(20.0);
            }
        }
        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    private void sortScoreAndDates(ArrayList<Long> seconds, ArrayList<Double> sumScores,
                                   ArrayList<Integer> helperArr) {
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


    public void showQ(Customer customer) {
        while (true) {
            System.out.println("-----------------------------------------------------------------------------------------");
            showQuestion();
            System.out.println("1.Select question\n" +
                    "0.Exit\n");
            int select = ScannerWrapper.getInstance().nextInt();
            if (select <= 0 || select > getQuestions().size()) {
                return;
            }
            getQuestions().get(select - 1).showQuestionAndUploadAnsForEvent(customer, this);
        }
    }

}
