/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ir.ac.kntu;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class HelperMethods {
    public static long differenceDates(HomeWork homeWork, String email) {
        ArrayList<LocalDateTime> allDates = new ArrayList<>();
        ArrayList<Double> allScore = new ArrayList<>();
        for (int i = 0; i < homeWork.getQuestions().size(); i++) {
            int index1 = -1;
            for (int j = 0; j < homeWork.getQuestions().get(i).getAnswers().size(); j++) {
                if (email.equals(homeWork.getQuestions().get(i).getAnswers().get(j).getEmail())) {
                    index1 = j;
                    break;
                }
            }
            if (index1 != -1) {
                int size = homeWork.getQuestions().get(i).getAnswers().get(index1).getDates().size();
                if (homeWork.getQuestions().get(i).isAutoCorrection()) {
                    int index2 = -1;
                    double totalScore = homeWork.getQuestions().get(i).getTotalScore();
                    for (int j = 0; j < homeWork.getQuestions().get(i).getAnswers().get(index1).getSocres().size();
                         j++) {
                        if (homeWork.getQuestions().get(i).getAnswers().get(index1).getSocres().get(j) == totalScore) {
                            index2 = j;
                            break;
                        }
                    }
                    if (index2 == -1) {
                        allDates.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getDates().get(0));
                        allScore.add(0.0);
                    } else {
                        allDates.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getDates().get(index2));
                        allScore.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getSocres().get(index2));
                    }
                } else {
                    allDates.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getDates().get(size - 1));
                    allScore.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getFinalScore());//check plz
                }
            }
        }
        long timeDifference = 0;
        for (int i = 0; i < allDates.size(); i++) {
            if (allScore.get(i) != -1) {
                timeDifference += ChronoUnit.SECONDS.between(allDates.get(i), homeWork.getStartTime());
            }
        }
        return timeDifference;
    }


    public static ArrayList<Double> scores(HomeWork homeWork, String email) {
        ArrayList<Double> allScore = new ArrayList<>();
        for (int i = 0; i < homeWork.getQuestions().size(); i++) {
            int index1 = -1;
            for (int j = 0; j < homeWork.getQuestions().get(i).getAnswers().size(); j++) {
                if (email.equals(homeWork.getQuestions().get(i).getAnswers().get(j).getEmail())) {
                    index1 = j;
                    break;
                }
            }//error:index1 shouldn't be -1
            if (index1 != -1) {
                if (homeWork.getQuestions().get(i).isAutoCorrection()) {
                    int index2 = -1;
                    double totalScore = homeWork.getQuestions().get(i).getTotalScore();
                    for (int j = 0; j < homeWork.getQuestions().get(i).getAnswers().get(index1).getSocres().size();
                         j++) {
                        if (homeWork.getQuestions().get(i).getAnswers().get(index1).getSocres().get(j) == totalScore) {
                            index2 = j;
                            break;
                        }
                    }
                    if (index2 == -1) {
                        allScore.add(0.0);
                    } else {
                        allScore.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getSocres().get(index2));
                    }
                } else {
                    allScore.add(homeWork.getQuestions().get(i).getAnswers().get(index1).getFinalScore());//check plz
                }
            } else {
                allScore.add(-1.0);
            }
        }
        double sum = 0;
        for (int i = 0; i < allScore.size(); i++) {
            if (allScore.get(i) != -1.0) {
                sum += allScore.get(i);
            }
        }
        allScore.add(sum);
        return allScore;
    }


    public static long differenceDatesDorEvent(Event event, String email) {
        ArrayList<LocalDateTime> allDates = new ArrayList<>();
        ArrayList<Double> allScore = new ArrayList<>();
        for (int i = 0; i < event.getQuestions().size(); i++) {
            int index1 = -1;
            for (int j = 0; j < event.getQuestions().get(i).getAnswers().size(); j++) {
                if (email.equals(event.getQuestions().get(i).getAnswers().get(j).getEmail())) {
                    index1 = j;
                    break;
                }
            }
            if (index1 != -1) {
                int size = event.getQuestions().get(i).getAnswers().get(index1).getDates().size();
                if (event.getQuestions().get(i).isAutoCorrection()) {
                    int index2 = -1;
                    double totalScore = event.getQuestions().get(i).getTotalScore();
                    for (int j = 0; j < event.getQuestions().get(i).getAnswers().get(index1).getSocres().size(); j++) {
                        if (event.getQuestions().get(i).getAnswers().get(index1).getSocres().get(j) == totalScore) {
                            index2 = j;
                            break;
                        }
                    }
                    if (index2 == -1) {
                        allDates.add(event.getQuestions().get(i).getAnswers().get(index1).getDates().get(0));
                        allScore.add(0.0);
                    } else {
                        allDates.add(event.getQuestions().get(i).getAnswers().get(index1).getDates().get(index2));
                        allScore.add(event.getQuestions().get(i).getAnswers().get(index1).getSocres().get(index2));
                    }
                } else {
                    allDates.add(event.getQuestions().get(i).getAnswers().get(index1).getDates().get(size - 1));
                    allScore.add(event.getQuestions().get(i).getAnswers().get(index1).getFinalScore());//check plz
                }
            }
        }
        long timeDifference = 0;
        for (int i = 0; i < allDates.size(); i++) {
            if (allScore.get(i) != -1) {
                timeDifference += ChronoUnit.SECONDS.between(allDates.get(i), event.getStartTime());
            }

        }
        return timeDifference;
    }


    public static ArrayList<Double> scoresForEvent(Event event, String email) {
        ArrayList<Double> allScore = new ArrayList<>();
        for (int i = 0; i < event.getQuestions().size(); i++) {
            int index1 = -1;
            for (int j = 0; j < event.getQuestions().get(i).getAnswers().size(); j++) {
                if (email.equals(event.getQuestions().get(i).getAnswers().get(j).getEmail())) {
                    index1 = j;
                    break;
                }
            }
            if (index1 != -1) {
                if (event.getQuestions().get(i).isAutoCorrection()) {
                    int index2 = -1;
                    double totalScore = event.getQuestions().get(i).getTotalScore();
                    for (int j = 0; j < event.getQuestions().get(i).getAnswers().get(index1).getSocres().size(); j++) {
                        if (event.getQuestions().get(i).getAnswers().get(index1).getSocres().get(j) == totalScore) {
                            index2 = j;
                            break;
                        }
                    }
                    if (index2 == -1) {
                        allScore.add(0.0);
                    } else {
                        allScore.add(event.getQuestions().get(i).getAnswers().get(index1).getSocres().get(index2));
                    }
                } else {
                    allScore.add(event.getQuestions().get(i).getAnswers().get(index1).getFinalScore());//check plz
                }
            } else {
                allScore.add(-1.0);
            }
        }
        double sum = 0;
        for (int i = 0; i < allScore.size(); i++) {
            if (allScore.get(i) != -1.0) {
                sum += allScore.get(i);
            }
        }
        allScore.add(sum);
        return allScore;
    }


    public static ArrayList<Integer> sortDates(ArrayList<LocalDateTime> allDates) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < allDates.size(); i++) {
            indexes.add(i);
        }
        for (int i = 0; i < allDates.size() - 1; i++) {
            for (int j = 0; j < allDates.size() - 1; j++) {
                if (allDates.get(j).compareTo(allDates.get(j + 1)) < 0) {
                    LocalDateTime temp = allDates.get(j);
                    int tempInt = indexes.get(j);
                    allDates.set(j, allDates.get(j + 1));
                    allDates.set(j + 1, temp);
                    indexes.set(j, indexes.get(j + 1));
                    indexes.set(j + 1, tempInt);
                }
            }
        }
        return indexes;
    }


    public static int isExistUser(String email) {
        int numExist = -1;
        for (int i = 0; i < AppliedList.getUsers().size(); i++) {
            if (email.equals(AppliedList.getUsers().get(i).getEmail())) {
                numExist = i;
                break;
            }
        }
        if (numExist == -1) {
            System.out.println("This user doesn't exist!");
        }
        return numExist;
    }


    public static LocalDateTime convertStringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date1 = LocalDateTime.parse(date, formatter);
        return date1;
    }

}

