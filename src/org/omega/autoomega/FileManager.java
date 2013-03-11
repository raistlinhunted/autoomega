package org.omega.autoomega;

import com.sun.javafx.css.CssError;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jon
 * Date: 3/10/13
 * Time: 4:29 PM
 */
public class FileManager {

    public static File loadFile(String path) {
        File file = new File(path); //Load file
        if (file.exists())  //If it exists return it
            return file;
        return null;
    }

    public static BufferedReader readFile(File file) {
        try {
            return new BufferedReader(new FileReader(file)); //return the buffered reader for the file
        } catch (Exception e) {
            e.printStackTrace(); //Print out errors
        }
        return null;
    }

    public static BufferedWriter writeFile(File file) {
        try {
            return new BufferedWriter(new FileWriter(file)); //Return the buffered writer for the file
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean fileExists(String path) {
        return new File(path).exists(); //Return if it exits (works for both directories and files)
    }

    public static boolean createDirectory(String path) {
        return new File(path).mkdir(); //Create a directory
    }

    public static boolean createFile(String path) {
        try {
            return new File(path).createNewFile(); //Create a new file
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String[] readLines(BufferedReader br) {
        try {
            List<String> lines = new ArrayList<String>(); //Create an arraylist to add the lines to
            String line = null; //Set a string that will be set to the lines
            while ((line = br.readLine()) != null) { //If not the end of the file
                lines.add(line); //Add it to the array
            }
            br.close(); //close the stream
            return lines.toArray(new String[lines.size()]); //create a new array with the size of the arraylist and return it
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
