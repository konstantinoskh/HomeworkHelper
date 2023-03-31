import java.io.File;


public class Main {
    public static void main(String[] args) throws Exception {
        // Create a new Folder object with the name "Test" in the current directory
        Folder folder = new Folder("ESS.dir");

        // Test the createFolder() method
        folder.createFolder();

        // Test the changeFileName() method

        // Test the newFileInFolder() method
        folder.newFileInFolder("test.txt");
        folder.newFileInFolder("hello.txt");

        // Test the displayFilesInFolder() method
        folder.displayFilesInFolder();

        // Test the writeToFile() method
        folder.writeToFile("Hello, world!", "hello.txt");

        // Test the displayDocument() method
        folder.displayDocument("hello.txt");

        // Test the overWriteFile() method
        folder.overWriteFile("hello.txt","Goodbye, world!");

        // Test the deleteFile() method
        folder.deleteFile("test.txt");

        // Test the uploadFile() method
        Folder destinationFile = new Folder("backup.dir");
        destinationFile.createFolder();

        File file = folder.returnFile("hello.txt");
        folder.moveFile(file, destinationFile.getName());
        // Test the moveFile() method

        File file1 = destinationFile.returnFile("hello.txt");
        destinationFile.renameFile(file1, "important.txt");


        File sourceFile = new File("./test.txt");
        folder.moveFile(sourceFile, "./new-test.txt");
    }
}