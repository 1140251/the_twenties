/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.twenties.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import the.twenties.traycontroller.ConfigModel;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Daniel
 */
public class ConfigFile {

    public static void saveConfig(ConfigModel config) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");
            // set the properties value
            prop.setProperty("time", Integer.toString(config.getTime()));
            prop.setProperty("language", config.getLanguage());

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static ConfigModel loadConfig() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            ConfigModel config = new ConfigModel();
            config.setTime(Integer.parseInt(prop.getProperty("time")));
            config.setLanguage(prop.getProperty("language"));

            return config;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    public static  ResourceBundle loadLanguage(String language) {        
        Locale.setDefault(new Locale(language));
        return ResourceBundle.getBundle("config");
    }

}
