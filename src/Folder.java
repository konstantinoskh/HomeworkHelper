import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Folder {
    private String name;
    private File file;


    public Folder(String name){
        this.name = name;
    }

    public void createFolder(){
        file = new File(name);
        boolean success = file.mkdirs();
        if (success){
            System.out.println(name + " created successfully");
        }else {
            System.out.println(name + " already exists");
        }
    }

    public Folder(){
        this.name = "default.dir";
    }

    public void renameFile(File currentFile, String newName) throws IOException {
        File[] files = getFile().listFiles();
        if (files == null) {
            System.out.println("Folder doesn't contain any files");
            return;
        }
        for (File file : files){
            if (file.isFile() && file.getName().equals(currentFile.getName())){
                Document.renameFile(currentFile, this.file.getName(), newName);
                return;
            }
        }
    }

    File getFile() {
        return file;
    }

    public void newFileInFolder(String fileName) throws IOException {
        String path = this.name + "/" + fileName;
        Document.createNewFile(path);
    }

    public void deleteFile(String name){
        File[] files = file.listFiles();

        if (files == null) {
            System.out.println("Folder doesn't contain any files");
            return;
        }
        for (File file : files){
            if (file.isFile() && file.getName().equals(name)){
                Document.deleteFile(file);
                return;
            }
        }
    }

    public void writeToFile(String text, String name){
        File[] files = getFile().listFiles();
        if (files == null) {
            System.out.println("Folder doesn't contain any files");
            return;
        }
        for (File file : files){
            if (file.isFile() && file.getName().equals(name)){
                Document.addContent(file, text);
                break;
            }
        }
    }

    public void overWriteFile(String fileName, String text){
        File[] files = file.listFiles();
        if (files == null) {
            System.out.println("Folder doesn't contain any files");
            return;
        }
        for (File file : files){
            if (file.isFile() && file.getName().equals(fileName)){
                Document.overWriteContent(file, text);
                return;
            }
        }
    }

    public void uploadFile(File sourceFile, String destination ) throws IOException {
        File[] files = getFile().listFiles();
        if (files == null) {
            System.out.println("Folder doesn't contain any files");
            return;
        }
        for (File file : files){
            if (file.isFile() && file.getName().equals(name)){
                Document.uploadFile(sourceFile, destination);
                return;
            }
        }
    }

    public void moveFile(File sourceFile, String destinationFile) throws IOException {
        File[] files = getFile().listFiles();
        if (files == null) {
            System.out.println("Folder doesn't contain any files");
            return;
        }
        for (File file : files){
            if (file.isFile() && file.getName().equals(sourceFile.getName())){
                Document.moveFile(sourceFile, destinationFile);
                return;
            }
        }
    }
    public void displayDocument(String fileName){
        File[] files = file.listFiles();
        if (files == null){
            System.out.println("Folder doesn't contain any files");
            return;
        }
        for (File file : files){
            if (file.getName().equals(fileName)){
                Document.displayDocument(file);
                break;
            }
        }
    }
    public File returnFile(String fileName){
        File[] files = getFile().listFiles();
        File existingFile = null;
        if (files == null){
            System.out.println("Folder doesn't contain any files");
            return null;
        }
        for (File file : files){
            if (file.isFile() && file.getName().equals(fileName)){
                existingFile = file;
            }
        }
        return existingFile;
    }

    public void displayFilesInFolder(){
        File[] files = getFile().listFiles();
        String text = "";
        if (files == null) {
            System.out.println("Folder doesn't contain any files");
            return;
        }
        for (File file : files ){
            text = text + file.getName() + "\n";
        }
        System.out.println("Files in " + getFile().getName() + ": \n" + text);
    }

    public String getName() {
        return name;
    }
}
