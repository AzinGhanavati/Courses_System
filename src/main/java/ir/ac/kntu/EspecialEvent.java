package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class EspecialEvent extends Event {
    private final int maxGroupMember;

    ArrayList<Group> groups;

    ArrayList<Group> participatedGroup;


    public EspecialEvent(String name, LocalDateTime startTime, LocalDateTime deadLine, int maxEntry, int maxGroupMember) {
        super(name, startTime, deadLine, maxEntry);
        this.maxGroupMember = maxGroupMember;
        this.groups = new ArrayList<>();
        this.participatedGroup = new ArrayList<>();
    }

    public void showGroupList() {
        System.out.println("info : name\tRemaining capacity");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println((i + 1) + " : " + groups.get(i).groupInfo());
        }
    }

    @Override
    public boolean addEntry(Customer customer) {
        if (getEntryList().size() >= getMaxEntry()) {//class home work !?
            System.out.println("The capacity is full !");
            return false;
        }
        System.out.println("1. Join the group\n"
                + "2. create new group\n"
                + "0. Exit\n"
                + "Select The Desired Option");
        Scanner input = new Scanner(System.in);
        switch (input.next()) {
            case "1":
                return joinGroup(customer);
            case "2":
                return createGroup(customer);
            case "0":
                return false;
            default:
                System.out.println("\nyour option NOT exist\n");
                return false;
        }
        //return super.addEntry(customer); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean joinGroup(Customer customer) {
        showGroupList();
        System.out.print("0. Exit\n"
                + "Select the Group to join : ");
        Scanner input = new Scanner(System.in);
        int index = input.nextInt();
        if (index == 0) {
            return false;
        } else if (index > groups.size()) {
            System.out.println("\nyour option NOT exist\n");
            return false;
        } else if (groups.get(index - 1).addMember(customer)) {
            System.out.println("You joined " + groups.get(index - 1).getName() + "group successfully");
            getEntryList().add(customer);
            return true;
        }
        return false;
    }

    public boolean createGroup(Customer customer) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 0 to exit !");
        System.out.print("Group name ? ");
        String name = input.next();
        if (name.equals("0")) {
            return false;
        }
        Group temp = new Group(name, maxGroupMember);
        temp.addMember(customer);
        groups.add(temp);
        getEntryList().add(customer);
        return true;
    }

    public int searchGroup(User customer) {
        for (int i = 0; i < groups.size(); i++) {
            int index = groups.get(i).searchMembers(customer);
            if (index > -1) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public void deleteEntry() {
        showEntryList();
        System.out.print("0. Exit\n"
                + "Select the Class to join : ");
        Scanner input = new Scanner(System.in);
        int selector = input.nextInt();
        if (selector == 0) {
            return;
        }
        if (selector <= getEntryList().size()) {
            groups.get(searchGroup(getEntryList().get(selector - 1))).getMembers().remove(getEntryList().get(selector - 1));
            getEntryList().get(selector - 1).deleteEvent(this);
            getEntryList().remove(getEntryList().get(selector - 1));
        } else {
            System.out.println("\nyour option NOT exist\n");
        }
    }


    @Override
    public void donate() {
        System.out.println("*************************************SCORE BOARD*************************************");
        ArrayList<Long> seconds = new ArrayList<>();
        ArrayList<Double> sumScores = new ArrayList<>();
        for (int i = 0; i < getParticipated().size(); i++) {
            ArrayList<Double> output = HelperMethods.scoresForEvent(this, participatedGroup.get(i).getMembers().get(0)
                    .getEmail());
            int index = output.size();
            sumScores.add(output.get(index - 1));
            seconds.add(HelperMethods.differenceDatesDorEvent(this, participatedGroup.get(i).getMembers().get(0)
                    .getEmail()));
        }

        ArrayList<Integer> helperArr = new ArrayList<>();
        for (int i = 0; i < sumScores.size(); i++) {
            helperArr.add(i);
        }
        sortScoreAndDate(seconds, sumScores, helperArr);
        for (int i = 0; i < getParticipatedGroup().size(); i++) {
            System.out.print(getParticipatedGroup().get(helperArr.get(i)).getName() + "\t");
            System.out.print(HelperMethods.scoresForEvent(this, participatedGroup.get(helperArr.get(i)).
                    getMembers().get(0).getEmail()) + "  ");
            System.out.println("\n");
        }
        int num;
        if (participatedGroup.size() < 10) {
            num = participatedGroup.size();
        } else {
            num = 10;
        }
        for (int j = 0; j < num; j++) {
            for (int i = 0; i < participatedGroup.get(helperArr.get(j)).getMembers().size(); i++) {
                participatedGroup.get(helperArr.get(j)).getMembers().get(i).addScore(25.0);
            }
        }
        //I did it
        System.out.println("Exit");
        String exit;
        do {
            exit = ScannerWrapper.getInstance().nextLine();
        } while (!exit.equals("0"));
    }

    private void sortScoreAndDate(ArrayList<Long> seconds, ArrayList<Double> sumScores, ArrayList<Integer> helperArr) {
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

    public Group findGroup(User customer) {
        for (int i = 0; i < groups.size(); i++) {
            for (int j = 0; j < groups.get(i).getMembers().size(); j++) {
                if (groups.get(i).getMembers().get(j).equals(customer)) {
                    return groups.get(i);
                }
            }
        }
        return null;
    }

    public void showQ(Group group) {
        while (true) {
            System.out.println("-----------------------------------------------------------------------------------------");
            showQuestion();
            System.out.println("1.Select question\n" +
                    "0.Exit\n");
            int select = ScannerWrapper.getInstance().nextInt();
            if (select <= 0 || select > getQuestions().size()) {
                return;
            }

            getQuestions().get(select - 1).showQuestionAndUploadAnsForEvent(group.getMembers().get(0), this);
        }
    }


    public ArrayList<Group> getParticipatedGroup() {
        return participatedGroup;
    }

    public void addParticipatedGroup(Group group) {
        //if (!groups.contains(group)) {
        participatedGroup.add(group);
        // } //else {
        //System.out.println("This group has already created");
        // }
    }

    public void addGroups(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
        }
    }
}
