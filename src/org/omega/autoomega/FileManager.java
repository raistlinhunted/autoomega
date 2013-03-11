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
        File file = new File(path);
        if (file.exists())
            return file;
        return null;
    }

    public static BufferedReader readFile(File file) {
        try {
            return new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedWriter writeFile(File file) {
        try {
            FileWriter fw = new FileWriter(file);
            return new BufferedWriter(fw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean fileExists(String path) {
        return new File(path).exists();
    }

    public static boolean createDirectory(String path) {
        return new File(path).mkdir();
    }

    public static boolean createFile(String path) {
        try {
            File file = new File(path);
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String[] readLines(BufferedReader br) {
        try {
            List<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
            return lines.toArray(new String[lines.size()]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
