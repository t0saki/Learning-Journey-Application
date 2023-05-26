package Control;

import java.util.ArrayList;
import java.util.HashMap;

public class Operate {
    public static double GPAhandler(String studentID, int type) {
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("Data/Modules&Marks.csv");
        int index = baseHandler.getFirstRowIndexByHeaderAndVal("StudentId", studentID);
        BaseHandler baseHandler1 = new BaseHandler();
        baseHandler1.open("Data/credits.csv");
        int linecount = baseHandler1.getLineCount();
        String line[] = baseHandler.getRow(index);
        double GPA = 0;
        int total_credit = 0;
        switch (type) {
            case 1:// Standard calculation method
                for (int i = 0; i < linecount; i++) {
                    double mark = Double.parseDouble(line[i]);
                    int credit = Integer.parseInt(baseHandler1.getElement("credits", i));
                    total_credit += credit;
                    GPA += mark * credit;
                }
                GPA = GPA / total_credit;
                break;
            case 2:// Simple 4-point scale algorithm
                double creditmark = 0;
                for (int i = 0; i < linecount; i++) {
                    double mark = Double.parseDouble(line[i]);
                    int credit = Integer.parseInt(baseHandler1.getElement("credits", i));
                    if (mark >= 90 && mark <= 100) {
                        creditmark += credit * 4;
                    } else if (mark >= 80 && mark < 89) {
                        creditmark += credit * 3;
                    } else if (mark >= 70 && mark < 79) {
                        creditmark += credit * 2;
                    } else if (mark > 60 && mark < 69) {
                        creditmark += credit;
                    } else {
                        creditmark += 0;
                    }
                    total_credit += credit;
                }
                GPA = creditmark / total_credit;
                break;
            case 3:// Peking University GPA Algorithm
                double totalGradePoints = 0;
                for (int i = 0; i < linecount; i++) {
                    double mark = Double.parseDouble(line[i]);
                    int credit = Integer.parseInt(baseHandler1.getElement("credits", i));
                    double gradePoints = calculateGradePoints(mark);
                    totalGradePoints += credit * gradePoints;
                    total_credit += credit;
                }
                GPA = totalGradePoints / total_credit;
                break;
            // case 4:
            // double totalgradePoints=0;
            // Map<String, Double> gradePoints = new HashMap<>();
            // gradePoints.put("A+", 4.0);
            // gradePoints.put("A", 4.0);
            // gradePoints.put("A-", 3.7);
            // gradePoints.put("B+", 3.3);
            // gradePoints.put("B", 3.0);
            // gradePoints.put("B-", 2.7);
            // gradePoints.put("C+", 2.3);
            // gradePoints.put("C", 2.0);
            // gradePoints.put("C-", 1.7);
            // gradePoints.put("D", 1.0);
            // gradePoints.put("F", 0.0);
            // for(int i=0;i<linecount;i++){
            // double mark=Double.parseDouble(line[i]);
            // int credit=Integer.parseInt(baseHandler1.getElement("credits",i));
            // }

        }
        return GPA;
    }

    public static double calculateGradePoints(double score) {
        if (score >= 90) {
            return 4.0;
        } else if (score >= 85) {
            return 3.7;
        } else if (score >= 82) {
            return 3.3;
        } else if (score >= 78) {
            return 3.0;
        } else if (score >= 75) {
            return 2.7;
        } else if (score >= 72) {
            return 2.3;
        } else if (score >= 68) {
            return 2.0;
        } else if (score >= 64) {
            return 1.5;
        } else if (score >= 60) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    public static int addItem(String ID, String header, String val) {
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("Data/" + header + "/" + ID + ".csv");
        baseHandler.append(val + ",");
        baseHandler.close();
        return 0;
    }

    public static int deleteItem(String ID, String header, String val) {
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("Data/" + header + "/" + ID + ".csv");
        int row = baseHandler.CheckExist(val);
        if (row < 0) {
            System.out.println("Item not exist!");
            return 1;
        }
        baseHandler.DeleteLine(val, "Data/" + header + "/" + ID + ".csv");
        baseHandler.close();
        return 0;
    }

    public static int changeItem(String ID, String header, String target, String val) {
        ItemFileHandler itemFileHandler = new ItemFileHandler();
        itemFileHandler.open("Data/" + header + "/" + ID + ".csv");
        int row = itemFileHandler.CheckExist(target);
        if (row < 0) {
            System.out.println("Item not exist!");
            return 1;
        }
        // baseHandler.DeleteLine(val,"Data/"+header+"/"+ID+".csv");
        itemFileHandler.ChangeItem(header, target, val, "Data/" + header + "/" + ID + ".csv");
        itemFileHandler.close();
        return 0;
    }

    public static HashMap<String, Integer> getHighestMark(String studentID) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("Data/Modules&Marks.csv");
        int row = baseHandler.CheckExist(studentID);
        if (row < 0) {
            return null;
        }
        String marks[] = baseHandler.getFileData()[row];
        int mark = 0;
        for (int i = 1; i < marks.length; i++) {
            if (i == 1) {
                mark = Integer.parseInt(marks[i]);
                continue;
            } else
                mark = mark < Integer.parseInt(marks[i]) ? Integer.parseInt(marks[i]) : mark;
        }
        for (int i = 1; i < marks.length; i++) {
            if (Integer.parseInt(marks[i]) == mark) {
                map.put(baseHandler.getHeaders()[i], mark);
            }
        }
        baseHandler.close();
        return map;
    }

    public static int failedExam(String studentID) {
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("Data/Modules&Marks.csv");
        int row = baseHandler.CheckExist(studentID);
        if (row < 0) {
            return -1;
        }
        int count = 0;
        for (int i = 1; i < baseHandler.getFileData()[row].length; i++) {
            if (Integer.parseInt(baseHandler.getFileData()[row][i]) < 60) {
                count++;
            }
        }
        baseHandler.close();
        return count;
    }

    public static ArrayList<String> getgoodmarks(String studentID) {
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("Data/Modules&Marks.csv");
        int row = baseHandler.CheckExist(studentID);
        if (row < 0) {
            return null;
        }
        ArrayList<String> modules = new ArrayList<>();
        for (int i = 1; i < baseHandler.getHeaders().length; i++) {
            if (Integer.parseInt(baseHandler.getFileData()[row][i]) >= 85) {
                modules.add(baseHandler.getHeaders()[i]);
            }
        }
        baseHandler.close();
        return modules;

    }

    public static int Analyse(String studentID) {
        boolean choice1 = false;
        boolean choice2 = false;
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("Data/Modules&Marks.csv");
        int row = baseHandler.CheckExist(studentID);
        if (row < 0) {
            return -1;
        }
        BaseHandler analyser = new BaseHandler();
        analyser.open("Data/Analyse.csv");
        ArrayList<String> modules = Operate.getgoodmarks(studentID);
        for (String module : modules) {
            if (analyser.getElement(1, analyser.CheckExist(module)).startsWith("1")) {
                choice1 = true;
            } else if (analyser.getElement(1, analyser.CheckExist(module)).startsWith("2")) {
                choice2 = true;
            }
        }
        baseHandler.close();
        analyser.close();
        if (Operate.failedExam(studentID) > 0) {
            return 5;// worst situation
        } else {
            if (choice1 && choice2) {
                return 1;// Both way works
            } else if (choice1 && !choice2) {
                return 2;// 1 works
            } else if (!choice1 && choice2) {
                return 3;// 2 works
            } else {
                return 4;// keep working
            }
        }
    }

    public static String getComment(int type) {
        String type1 = "";
        String type2 = "";
        String type3 = "";
        String type4 = "";
        String type5 = "";
        type1 = "Your scores in programming-related courses and basic subjects are high enough " +
                "for you to consider trying an internship and putting in work, " +
                "or to consider continuing your education for graduate and doctoral studies. " +
                "We suggest you to try more and prepare for both and grab more opportunities.";

        type2 = "Your scores in basic subjects are high, but your scores in other programming-related " +
                "subjects are not outstanding, and you may want to consider continuing your education for graduate studies. "
                +
                "This can help you prompt your level and get a better job position.";

        type3 = "You have high scores in programming-related courses, but not in other basic subjects. " +
                "You may consider applying for internships and putting in work, " +
                "where your programming skills can help you get better job opportunities. " +
                "At the same time, you need to continue to improve your abilities to get better opportunities.";

        type4 = "Your scores in both basic subjects and programming-related courses are not outstanding," +
                " but you can still improve your grades further. I hope you will continue to work hard to get higher grades "
                +
                "and improve your strengths.";

        type5 = "You have failed a subject, which is a case of failing a course, and you need to give this situation enough attention. "
                +
                "Failing a subject may affect your graduation and even your future employment, " +
                "so focus on your studies and try to avoid failing a subject.";

        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add(type1);
        stringArrayList.add(type2);
        stringArrayList.add(type3);
        stringArrayList.add(type4);
        stringArrayList.add(type5);
        return stringArrayList.get(type - 1);

    }
}
