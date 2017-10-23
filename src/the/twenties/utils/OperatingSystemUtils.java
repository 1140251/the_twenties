/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.twenties.utils;

/**
 *
 * @author pedrotinoco
 */
public class OperatingSystemUtils {

    private static String os = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return (os.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (os.indexOf("mac") >= 0);
    }

}
