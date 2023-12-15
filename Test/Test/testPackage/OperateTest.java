package Test.testPackage;

import Control.BaseHandler;
import Control.Operate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 * Test for Operate
 */
class OperateTest {

    @Test
    void GPAhandler() {
        String studentID="123";
        assertEquals(86.1,Operate.GPAhandler(studentID,1),0.01);
        assertEquals(2.3,Operate.GPAhandler(studentID,2),0.01);
        assertEquals(3.06,Operate.GPAhandler(studentID,3),0.01);
    }


    @Test
    void calculateGradePoints() {
        // Test cases
        double score1 = 95;
        double expectedPoints1 = 4.0;
        double score2 = 85;
        double expectedPoints2 = 3.7;
        double score3 = 82;
        double expectedPoints3 = 3.3;
        double score4 = 79;
        double expectedPoints4 = 3.0;
        double score5 = 76;
        double expectedPoints5 = 2.7;
        double score6 = 73;
        double expectedPoints6 = 2.3;
        double score7 = 69;
        double expectedPoints7 = 2.0;
        double score8 = 65;
        double expectedPoints8 = 1.5;
        double score9 = 61;
        double expectedPoints9 = 1.0;
        double score10 = 59;
        double expectedPoints10 = 0.0;

        // Test execution and assertions
        assertEquals(expectedPoints1, Operate.calculateGradePoints(score1), 0.01);
        assertEquals(expectedPoints2, Operate.calculateGradePoints(score2), 0.01);
        assertEquals(expectedPoints3, Operate.calculateGradePoints(score3), 0.01);
        assertEquals(expectedPoints4, Operate.calculateGradePoints(score4), 0.01);
        assertEquals(expectedPoints5, Operate.calculateGradePoints(score5), 0.01);
        assertEquals(expectedPoints6, Operate.calculateGradePoints(score6), 0.01);
        assertEquals(expectedPoints7, Operate.calculateGradePoints(score7), 0.01);
        assertEquals(expectedPoints8, Operate.calculateGradePoints(score8), 0.01);
        assertEquals(expectedPoints9, Operate.calculateGradePoints(score9), 0.01);
        assertEquals(expectedPoints10, Operate.calculateGradePoints(score10), 0.01);
    }

    @Test
    void addItem() {
        // Test data
        String ID = "123";
        String header = "Achievements";
        String val1 = "item1";
        String val2=  "item2";
        int result1=Operate.addItem(ID,header,val1);
        int result2=Operate.addItem(ID,header,val2);
        assertEquals(0,result1);
        assertEquals(0,result2);
    }

    @Test
    void deleteItem() {
        // Test data
        String ID = "123";
        String header = "Achievements";
        String val1 = "item1";

        // Test execution
        int result1=Operate.deleteItem(ID,header,val1);

        // Assertion
        assertEquals(0,result1);
    }

    @Test
    void changeItem() {
        // Test data
        String ID = "123";
        String header = "Achievements";
        String val2 = "item2";

        // Test execution
        int result=Operate.changeItem(ID,header,val2,"new Item");

        // Assertion
        assertEquals(0,result);

        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/Achievements/123.csv");

        // Assertion
        assertTrue(baseHandler.CheckExist("new Item")>=0);
        baseHandler.close();
    }

    @Test
    void getHighestMark() {
        // Test data
        String studentID = "123";

        // Test execution
        HashMap<String, Integer> result = Operate.getHighestMark(studentID);

        // Expected result
        HashMap<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("Personal Development Plan", 100);

        // Assertion
        assertEquals(expectedMap, result);
    }

    @Test
    void failedExam() {
        // Test data
        String studentID = "123";

        // Test execution
        int result=Operate.failedExam(studentID);

        // Assertion
        assertEquals(1,result);
    }

    @Test
    void getgoodmarks() {
        // Test data
        String studentID = "123";

        // Test execution
        ArrayList<String> result = Operate.getgoodmarks(studentID);

        // Expected result
        List<String> expectedList = Arrays.asList("College English", "AdvancedMath","Internet Protocols","Telecommunications Systems","Personal Development Plan");

        // Assertion
        assertEquals(result,expectedList);
    }

    @Test
    void analyse() {
        // Test data
        String studentID="123";

        // Test execution
        int result=Operate.Analyse(studentID);

        // Assertion
        assertEquals(5,result);

    }
}