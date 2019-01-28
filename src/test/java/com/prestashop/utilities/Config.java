package com.prestashop.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties pro;

    static{

      String path ="configration.properties";
        try {

            FileInputStream file=new FileInputStream(path);
            pro=new Properties();
            pro.load(file);
            file.close();

        } catch (IOException e) {
            System.out.println("Path: "+path+" -not found.");
        }

    }


    public static String getProperty(String key){
        return pro.getProperty(key);
    }





}
