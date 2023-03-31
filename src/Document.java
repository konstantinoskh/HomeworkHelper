import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Document {

    public static void uploadFile(File sourceFile, String destination) throws IOException {
        File destFile = new File(destination);
        Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        System.out.println(sourceFile.getName() + " uploaded to " + destFile.getName());
    }

    public static void addContent(File file, String content) {
        FileUtilities.writeToFile(file.getPath(), content + "\n", false);
        System.out.println(file.getName() + " updated");
    }

    public static void overWriteContent(File file, String content) {
        FileUtilities.writeToFile(file.getPath(), content + "\n", false);
        System.out.println(file.getName() + " overwritten");
    }

    public static void deleteFile(File file) {
        boolean success = file.delete();
        if (success) {
            System.out.println(file.getName() + " deleted successfully");
        } else {
            System.out.println(file.getName() + " not found");
        }
    }

    public static void createNewFile(String path) throws IOException {
        File newFile = new File(path);
        boolean success = newFile.createNewFile();
        if (success) {
            System.out.println(newFile.getName() + " created successfully");
        } else {
            System.out.println(newFile.getName() + " already exists");
        }
    }

    public static void moveFile(File documentFilePath, String stringFilePath) throws IOException {
        String filePath = stringFilePath + "/" + documentFilePath.getName();

        documentFilePath.renameTo(new File(filePath));
        System.out.println(documentFilePath.getName() + " moved to " + stringFilePath);
    }

    public static void renameFile(File documentFilePath, String stringFilePath, String newName) throws IOException {
        String filePath = stringFilePath + "/" + newName;

        documentFilePath.renameTo(new File(filePath));
        System.out.println(documentFilePath.getName() + " renamed to " + newName);
    }

    public static void displayDocument(File document) {
        ArrayList<String> content = FileUtilities.returnFileContents(document.getPath());
        String text = "";
        for (String line : content) {
            text = text + line + "\n";
        }
        System.out.println(text);
    }
}