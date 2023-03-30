import java.io.*;
import java.util.ArrayList;

public class FileUtilities {
    public static void writeToFile(String fileName, String text, boolean append) {
        try (FileWriter fw = new FileWriter(fileName, append);
        PrintWriter pw = new PrintWriter(fw)) {
            pw.write(text);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> returnFileContents(String filename) {
        ArrayList<String> text = new ArrayList<>();
        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr)){
            String line = br.readLine();
            while (line != null) {
                text.add(line);
                line = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
    public static boolean checkStringsTogether(String filename, String first, String second) {
        String checkedSecond = "";
        String checkedFirst;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while (line != null) {
                checkedFirst = line.split(":")[0].strip();
                if (checkedFirst.equals(first)) {
                    checkedSecond = line.split(":")[1].strip();
                }
                if (checkedSecond.equals(second)) {
                    return true;
                } else {
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void changeSecondElement(String filename, String searchString, String newValue){
        try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")){
            while (raf.getFilePointer() < raf.length()){
                String line = raf.readLine();
                String firstElement = line.split(":")[0].strip();

                if (firstElement.equals(searchString)){
                    long filePointer = raf.getFilePointer() - 6;
                    raf.seek(filePointer);
                    raf.write(newValue.getBytes());
                    raf.writeChars("");
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void changeFirstElement(String filename, String searchString, String newString){
        try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")){
            while (raf.getFilePointer() < raf.length()){
                String line = raf.readLine();
                String firstElement = line.split(":")[1].strip();

                if (firstElement.equals(searchString)){
                    long filePointer = raf.getFilePointer() - line.length();
                    raf.seek(filePointer);
                    raf.write(newString.getBytes());
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
