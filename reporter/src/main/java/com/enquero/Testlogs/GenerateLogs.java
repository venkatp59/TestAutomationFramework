package com.enquero.Testlogs;

import org.apache.log4j.PropertyConfigurator;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GenerateLogs {

    private static String propertyFileName = "log4j" + ".properties";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String propertyFilepath = "C:\\Enquero_Automation_Framework\\reporter\\src\\main\\java\\com\\enquero\\Testlogs";
    private static String PropertyFileLocation = propertyFilepath + fileSeperator + propertyFileName;

    public static void loadLogPropertyFile() {
        File file;
        String filepath= getLogPath(propertyFilepath);
        try {
            System.out.println("logfilepath: "+filepath);
            file = new File(filepath);
            Properties props = new Properties();
            props.load(new FileInputStream(filepath));
            PropertyConfigurator.configure(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getLogPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!");
                return PropertyFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
        return PropertyFileLocation;
    }

}
