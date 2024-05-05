package ir.ac.kntu;

public class CourseHelper {

    public static void showHomeworkToOwner(User user, Course course) {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            if (course.getHomeWorks().size() == 0) {
                System.out.println("No homework has been created!");
                String exit1;
                do {
                    exit1 = ScannerWrapper.getInstance().nextLine();
                } while (!exit1.equals("0"));
                return;
            }
            for (int i = 0; i < course.getHomeWorks().size(); i++) {
                int j = i + 1;
                System.out.println(j + "-" + course.getHomeWorks().get(i).getName());
            }
            System.out.println("Choose the homework:");
            int num = ScannerWrapper.getInstance().nextInt();
            if (num <= 0 || num > course.getHomeWorks().size()) {
                return;
            }
            showAHomeworkInfToOwner(course.getHomeWorks().get(num - 1), user);
        }
    }

    public static void showAHomeworkInfToOwner(HomeWork homeWork, User user) {
        while (true) {
            System.out.println("--------------------------------------------------------------------------");
            if (homeWork.getQuestions().size() == 0) {
                System.out.println("There is no question in this homework");
                System.out.println("1-Add question\nExit");
                String exit;
                do {
                    exit = ScannerWrapper.getInstance().nextLine();
                } while (!exit.equals("0") && !exit.equals("1"));
                if (exit.equals("1")) {
                    homeWork.addQuestionToHomeWrk();
                }
                return;
            }
            System.out.println("1-Show questions\n2-Add question\n3-ScoreBoard");
            String enteredChoice = ScannerWrapper.getInstance().nextLine();
            switch (enteredChoice) {
                case "1":
                    HelperForHomeworkMethod.showQuestionToOwner(homeWork, user);
                    break;
                case "2":
                    homeWork.addQuestionToHomeWrk();
                    break;
                case "3":
                    homeWork.scoreBoard();
                    break;
                default:
                    return;
            }
        }
    }
}
