//package seatech.uiTest.util;
//
//import com.univocity.parsers.csv.CsvParserSettings;
//import com.univocity.parsers.csv.CsvRoutines;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.testng.annotations.DataProvider;
//import seatech.ibv.data.BaseData;
//import seatech.ibv.data.LoginData;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//public  class dataProvider {
//    @DataProvider(name="login")
//    public Object[][] getDataFromDataprovider() throws IOException{
//        Object[][] object = null;
//        ReadExcelFile file = new ReadExcelFile();
////Read keyword sheet
//        Sheet sheet = file.readExcel("C:\\Users\\Admin\\Desktop\\automation-test-master\\src\\test\\java\\seatech\\uiTest\\ibv\\testcase\\hubspot_scenarios.xlsx","login" );
////Find number of rows in excel file
//        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
//        object = new Object[rowCount][5];
//        for (int i = 0; i < rowCount; i++) {
//            //Loop over all the rows
//            Row row = sheet.getRow(i+1);
//            //Create a loop to print cell values in a row
//            for (int j = 0; j < row.getLastCellNum(); j++) {
//                //Print excel data in console
//                object[i][j] = row.getCell(j).toString();
//            }
//        }
//        System.out.println(" ");
//        return object;
//    }
//}
