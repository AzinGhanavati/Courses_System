package ir.ac.kntu;

import java.util.ArrayList;

public class Group {
    private String name;

    private final int maxMembers;

    private ArrayList<Customer> members;

    public Group(String name, int maxMembers) {
        this.name = name;
        this.maxMembers = maxMembers;
        this.members = new ArrayList<>();
    }

    public ArrayList<Customer> getMembers() {
        return new ArrayList<>(members);
    }

    public boolean addMember(Customer customer) {
        if (!members.contains(customer)) {
            if (members.size() >= maxMembers) {
                System.out.println("The capacity is full !");
                return false;
            } else {
                members.add(customer);
                return true;
            }
        } else {
            System.out.println("This member has already joined!!");
            return false;
        }

    }

    public int searchMembers(User customer) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).equals(customer)) {
                return i;
            }
        }
        return -1;
    }

    public String groupInfo() {
        return name + "\t" + (maxMembers - members.size());
    }

    public String getName() {
        return name;
    }

    public void setMembers(ArrayList<Customer> members) {
        this.members = members;
    }
}
