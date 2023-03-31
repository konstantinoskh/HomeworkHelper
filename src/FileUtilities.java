import java.io.*;
import java.util.ArrayList;

public class FileUtilities {

    //write text to a file
    public static void writeToFile(String fileName, String text, boolean append) {
        //try with resources creating print writer, automatically closing the file at the end
        try (FileWriter fw = new FileWriter(fileName, append);
        PrintWriter pw = new PrintWriter(fw)) {
            pw.write(text);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //returns a file's contents as a string arraylist
    public static ArrayList<String> returnFileContents(String filename) {
        ArrayList<String> text = new ArrayList<>();
        //creates a buffered reader
        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr)){
            String line = br.readLine();
            //while the buffered reader reads a string in the file, add it to the arraylist
            while (line != null) {
                text.add(line);
                line = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //return arraylist
        return text;
    }
    //checks two strings on the same line that are split by ":" against two strings taken as parameters
    public static boolean checkStringsTogether(String filename, String first, String second) {
        String checkedSecond = "";
        String checkedFirst;
        //create a buffered reader
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            //while the reader reads a line, store the first string of the line
            while (line != null) {
                checkedFirst = line.split(":")[0].strip();
                //if the first string on the line matches the string first in the parameter, it stores the second string from that same line
                if (checkedFirst.equals(first)) {
                    checkedSecond = line.split(":")[1].strip();
                }
                //compares the second string to the string second in the parameter
                if (checkedSecond.equals(second)) {
                    //string first and second are found on the same line
                    return true;
                } else {
                    //continue reading
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
