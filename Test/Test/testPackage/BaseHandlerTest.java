package Test.testPackage;

import Control.BaseHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 *
 * Test for BaseHandler
 */
class BaseHandlerTest {

    @Test
    void open() {
        BaseHandler baseHandler=new BaseHandler();
        int result=baseHandler.open("Data/Achievements/124.csv");
        assertEquals(0,result);
        baseHandler.close();
    }

    @Test
    void create() {
        BaseHandler baseHandler=new BaseHandler();
        int result=baseHandler.create("Data/Achievements/124.csv");
        assertEquals(0,result);
        baseHandler.close();
    }

    @Test
    void loadAllCsvData() {
        BaseHandler baseHandler=new BaseHandler();
        int result=baseHandler.open("Data/Achievements/124.csv");
        assertEquals(0,result);
        assertNotNull(baseHandler.getFileData());
        baseHandler.close();
    }

    @Test
    void append() {
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/Achievements/124.csv");
        int result=baseHandler.append("Append test,");
        assertEquals(0,result);
        BaseHandler baseHandler1=new BaseHandler();
        baseHandler1.open("Data/Achievements/124.csv");
        assertTrue(baseHandler1.CheckExist("Append test")>=0);
        baseHandler.close();
        baseHandler1.close();
    }

    @Test
    void getFirstRowIndexByHeaderAndVal() {
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/UserInfo.csv");
        int result = baseHandler.getFirstRowIndexByHeaderAndVal("Username","Tester");
        assertEquals(1,result);
        baseHandler.close();
    }

    @Test
    void getElement() {
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/Achievements/124.csv");
        baseHandler.append("item1,");
        baseHandler.close();
        baseHandler.open("Data/Achievements/124.csv");
        String result1=baseHandler.getElement("Achievements",1);
        assertEquals("item1",result1);

        String result2=baseHandler.getElement(0,1);
        assertEquals("item1",result2);
        baseHandler.close();
    }

    @Test
    void getRow() {
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/Achievements/124.csv");
        String[] result=baseHandler.getRow(0);
        String[] expected={"Append test"};
        assertEquals(1,result.length);
        assertEquals(result[0],expected[0]);
        baseHandler.close();
    }

    @Test
    void checkExist() {
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/Achievements/124.csv");
        assertTrue(baseHandler.CheckExist("item1")>=0);
        baseHandler.close();
    }

    @Test
    void deleteLine() {
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/Achievements/124.csv");
        int result=baseHandler.DeleteLine("Append test","Data/Achievements/124.csv");
        assertEquals(0,result);

        BaseHandler baseHandler1=new BaseHandler();
        baseHandler1.open("Data/Achievements/124.csv");
        assertTrue(baseHandler1.CheckExist("Append test")<0);

        baseHandler.close();
        baseHandler1.close();
    }

    @Test
    void changeItem() {
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/Modules&Marks.csv");
        int result=baseHandler.ChangeItem("English","124","88","Data/Modules&Marks.csv");
        assertEquals(0,result);
        baseHandler.close();

        BaseHandler baseHandler1=new BaseHandler();
        baseHandler1.open("Data/Modules&Marks.csv");
        assertEquals("88",baseHandler1.getElement("English",4));
        baseHandler1.close();

    }
}