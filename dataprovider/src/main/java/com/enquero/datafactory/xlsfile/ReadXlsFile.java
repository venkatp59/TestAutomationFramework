package com.enquero.datafactory.xlsfile;

import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ReadXlsFile {

    public static void main(String[] args) {
        ReadXlsFile rd = new ReadXlsFile();
        Iterator<Object[]> hello;
        hello=rd.getTestData("C:\\Users\\VivekVerma\\OneDrive - Enquero\\IntelleJ-Workspace\\Enquero_Automation_Framework\\TestExecutor\\src\\main\\resources\\testData.xlsx","testcase","testLogin");
        while(hello.hasNext()) {
            System.out.println(hello.next().toString());
        }
    }

    public Iterator<Object[]> getTestData(String xlsFilepath, String sheetName,String methodName){
        Collection<Object[]> provider = new ArrayList<Object[]>();
        try {
            TestDataFactory dataFactory = new TestDataFactory();
            File xlsFile = new File(xlsFilepath);
            FileInputStream fileInputStream = new FileInputStream(xlsFile);
            Workbook wb = WorkbookFactory.create(fileInputStream);
            Sheet sheet = wb.getSheet(sheetName);
            Iterator<Row> rowIterator = sheet.iterator();
            String name=null;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String testCaseName = row.getCell(1).getStringCellValue();
                if (methodName.equalsIgnoreCase(testCaseName)) {
                    dataFactory.setTestCaseId(row.getCell(0).getStringCellValue());
                    dataFactory.setTestCaseName(row.getCell(1).getStringCellValue());
                    JSONObject input_Obj = new JSONObject(row.getCell(2).getStringCellValue());
                    dataFactory.setInputParameters(input_Obj);
                    JSONObject validation_Obj = new JSONObject(row.getCell(3).getStringCellValue());
                    dataFactory.setValidationParameters(validation_Obj);
                    provider.add(new Object[]{dataFactory});
                } else {
                    //System.out.println("Test case " + methodName + "is not enabled for Execution");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return provider.iterator();
    }
}
