/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.twenties.traycontroller;

import the.twenties.utils.OperatingSystemUtils;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static the.twenties.TheTwenties.config;
import static the.twenties.TheTwenties.resource;
import static the.twenties.TheTwenties.stopCount;

/**
 *
 * @author pedrotinoco
 */
public class TrayIconController {

    static MenuItem aboutItem;
    static MenuItem exitItem;
    static MenuItem settingsItem;
    static MenuItem timerItem;
    static TrayIcon trayIcon;

    public void showIcon() throws InterruptedException, IOException {

        trayIcon = null;

        if (SystemTray.isSupported()) {
            // get the SystemTray instance
            SystemTray tray = SystemTray.getSystemTray();
            // load an image
            Image image = Toolkit.getDefaultToolkit().getImage("20s.png");

            // create a action listener to listen for default action executed on the tray icon
            ActionListener listenerAbout = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AboutUI a = new AboutUI();
                    a.setVisible(true);
                    //JOptionPane.showMessageDialog(null, AboutController.about());
                }
            };

            ActionListener listenerExit = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            };

            ActionListener listenerSettings = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SettingsUI s = new SettingsUI();
                    s.setVisible(true);
                }
            };

            // create a popup menu
            PopupMenu popup = new PopupMenu();

            // create menu item for the default action
            aboutItem = new MenuItem(resource.getString("about"));
            timerItem = new MenuItem("");
            exitItem = new MenuItem(resource.getString("exit"));
            settingsItem = new MenuItem(resource.getString("settings"));

            // create listener to button
            aboutItem.addActionListener(listenerAbout);
            exitItem.addActionListener(listenerExit);
            settingsItem.addActionListener(listenerSettings);

            // disable operation in button
            timerItem.setEnabled(false);

            // add button to tray
            popup.add(timerItem);

            popup.addSeparator();

            popup.add(settingsItem);
            popup.add(aboutItem);

            popup.addSeparator();

            popup.add(exitItem);

            // construct a TrayIcon
            trayIcon = new TrayIcon(image, "The Twenties", popup);

            // set the TrayIcon properties
            //trayIcon.addActionListener(listenerAbout);
            trayIcon.setImageAutoSize(true);

            // add the tray image
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }

            startCount();
        } else {
            // disable tray option in your application or
            // perform other actions
        }

    }

    public static void showTimer(MenuItem timerItem, double tempo, boolean t) throws InterruptedException {
        TimerController.timerLogic(timerItem, tempo, t);
    }

    public static void changeMenu() {
        aboutItem.setLabel(resource.getString("about"));
        exitItem.setLabel(resource.getString("exit"));
        settingsItem.setLabel(resource.getString("settings"));
    }

    public static void startCount() throws InterruptedException, IOException {
        while (true) {

            if (OperatingSystemUtils.isMac()) {
                double olha = 0.33;
                //show the timer of the 20 minutes
                showTimer(timerItem, config.getTime(), true);
                if (stopCount != true) {
                    Runtime.getRuntime().exec(new String[]{"osascript", "-e", "display notification \"" + resource.getString("startNotification") + "\" with title \"" + resource.getString("Attention") + "\" sound name \"solemn.mp3\""});
                }
                showTimer(timerItem, olha, false);
                if (stopCount != true) {
                    Runtime.getRuntime().exec(new String[]{"osascript", "-e", "display notification \"" + resource.getString("EndNotification") + "\" with title \"" + resource.getString("Attention") + "\"  sound name \"solemn.mp3\""});
                }
            } else {
                //show the timer of the 20 minutes

                double olha = 0.33;
                showTimer(timerItem, config.getTime(), true);
                if (stopCount != true) {
                    Toolkit.getDefaultToolkit().beep();
                    trayIcon.displayMessage(resource.getString("Attention"), resource.getString("startNotification"), MessageType.INFO);
                }
                showTimer(timerItem, olha, false);
                if (stopCount != true) {
                    Toolkit.getDefaultToolkit().beep();
                    trayIcon.displayMessage(resource.getString("Attention"), resource.getString("EndNotification"), MessageType.INFO);
                }
            }

            if (stopCount == true) {
                stopCount = false;
            }
        }
    }

}
