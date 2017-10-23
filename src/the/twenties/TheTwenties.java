/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.twenties;

import java.io.IOException;
import java.util.ResourceBundle;
import the.twenties.traycontroller.ConfigModel;
import the.twenties.traycontroller.TrayIconController;
import static the.twenties.utils.ConfigFile.*;
/**
 *
 * @author pedrotinoco
 */
public class TheTwenties {
    public static ResourceBundle resource;
    public static ConfigModel config;
    public static boolean stopCount;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        config =loadConfig();
        stopCount=false;
        resource = loadLanguage(config.getLanguage());
        
        TrayIconController t = new TrayIconController();
        t.showIcon();

    }
}
