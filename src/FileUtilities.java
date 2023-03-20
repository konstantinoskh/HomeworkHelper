import java.io.*;
import java.util.ArrayList;
public class FileUtilities {
public static void writeToFile(String fileName, String text, boolean append) {
    try (FileWriter fw = new FileWriter(fileName, append);
    PrintWriter pw = new PrintWriter(fw)) {
        pw.print(text);
    }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkString(String string, String filepath){
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line;

            while ((line = br.readLine()) != null){
                if (line.equals(string)){
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
