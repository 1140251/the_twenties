/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.twenties.traycontroller;

import java.awt.MenuItem;
import the.twenties.TheTwenties;
import static the.twenties.TheTwenties.resource;

/**
 *
 * @author pedrotinoco
 */
public class TimerController {

    public static void timerLogic(MenuItem timer, double tempo, boolean t) throws InterruptedException {
        double timet = tempo * 60; // Convert to seconds
        int delay = (int) timet * 1000;

        do {
            
            int minutes = (int) (timet / 60);
            int seconds = (int) (timet % 60);
            //if (t) { 
            String result = String.format("%s %s %s %s", minutes,resource.getString("minutesText"), seconds,resource.getString("secondsText"));
            timer.setLabel(result);
            //}
            Thread.sleep(1000);
            timet = timet - 1;
            delay = delay - 1000;

        } while (delay != 0 && TheTwenties.stopCount==false);
        //System.out.println("Time's Up!");
    }
}
