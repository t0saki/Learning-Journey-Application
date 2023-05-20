package Control;

import java.util.HashMap;
import java.util.Map;

public class Operate {
    public static double GPAhandler(String syudentID,int type){
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/Modules&Marks.csv");
        int index= baseHandler.getFirstRowIndexByHeaderAndVal("StudentId",syudentID);
        int headerlen=baseHandler.getHeaders().length;
        BaseHandler baseHandler1=new BaseHandler();
        baseHandler1.open("Data/credits.csv");
        int linecount=baseHandler1.getLineCount();
        String line[]=baseHandler.getRow(index);
        double GPA=0;
        int total_credit=0;
        switch (type){
            case 1://Standard calculation method
                for(int i=0;i<linecount;i++){
                    double mark=Double.parseDouble(line[i]);
                    int credit=Integer.parseInt(baseHandler1.getElement("credits",i));
                    total_credit+=credit;
                    GPA+=mark*credit;
                }
                GPA=GPA/total_credit;
                break;
            case 2://Simple 4-point scale algorithm
                int creditmark=0;
                for (int i=0;i<linecount;i++){
                    double mark=Double.parseDouble(line[i]);
                    int credit=Integer.parseInt(baseHandler1.getElement("credits",i));
                    if(mark>=90&&mark<=100){
                        creditmark+=credit*4;
                    }else if(mark>=80&&mark<89){
                        creditmark+=credit*3;
                    }else if(mark>=70&&mark<79){
                        creditmark+=credit*2;
                    }else if(mark>60&&mark<69){
                        creditmark+=credit;
                    }else{
                        creditmark+=0;
                    }
                    total_credit+=credit;
                }
                GPA=creditmark/total_credit;
                break;
            case 3://Peking University GPA Algorithm
                double totalGradePoints=0;
                for(int i=0;i<linecount;i++){
                    double mark=Double.parseDouble(line[i]);
                    int credit=Integer.parseInt(baseHandler1.getElement("credits",i));
                    double gradePoints = calculateGradePoints(mark);
                    totalGradePoints += credit * gradePoints;
                    total_credit += credit;
                }
                GPA= totalGradePoints / total_credit;
                break;
//            case 4:
//                double totalgradePoints=0;
//                Map<String, Double> gradePoints = new HashMap<>();
//                gradePoints.put("A+", 4.0);
//                gradePoints.put("A", 4.0);
//                gradePoints.put("A-", 3.7);
//                gradePoints.put("B+", 3.3);
//                gradePoints.put("B", 3.0);
//                gradePoints.put("B-", 2.7);
//                gradePoints.put("C+", 2.3);
//                gradePoints.put("C", 2.0);
//                gradePoints.put("C-", 1.7);
//                gradePoints.put("D", 1.0);
//                gradePoints.put("F", 0.0);
//                for(int i=0;i<linecount;i++){
//                    double mark=Double.parseDouble(line[i]);
//                    int credit=Integer.parseInt(baseHandler1.getElement("credits",i));
//                }

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

    public static int addItem(String ID,String header,String val){
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/"+header+"/"+ID+".csv");
        baseHandler.append(val+",");
        baseHandler.close();
        return 0;
    }

    public static int deleteItem(String ID,String header,String val){
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/"+header+"/"+ID+".csv");
        int row=baseHandler.CheckExist(val);
        if(row<0){
            System.out.println("Item not exist!");
            return 1;
        }
        baseHandler.DeleteLine(val,"Data/"+header+"/"+ID+".csv");
        baseHandler.close();
        return 0;
    }
    public static int changeItem(String ID,String header,String target,String val){
        ItemFileHandler itemFileHandler=new ItemFileHandler();
        itemFileHandler.open("Data/"+header+"/"+ID+".csv");
        int row=itemFileHandler.CheckExist(target);
        if(row<0){
            System.out.println("Item not exist!");
            return 1;
        }
        //baseHandler.DeleteLine(val,"Data/"+header+"/"+ID+".csv");
        itemFileHandler.ChangeItem(header,target,val,"Data/"+header+"/"+ID+".csv");
        itemFileHandler.close();
        return 0;
    }

}

