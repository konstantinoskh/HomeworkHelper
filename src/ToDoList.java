import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    private ArrayList<ToDoListObject> toDoList;

    public ToDoList(ArrayList<ToDoListObject> toDoList) {
        this.toDoList = toDoList;
    }

    //overrides constructor in case no arraylist is passed
    public ToDoList(){
        this.toDoList = new ArrayList<>();
    }

    private void setToDoList(ArrayList<ToDoListObject> toDoList) {
        this.toDoList = toDoList;
    }

    //returns the string contents of the to do list
    private String returnToDoList(ArrayList<ToDoListObject> list) {
        String stringToDoList = "";
        for (ToDoListObject element : list) {
            stringToDoList = stringToDoList + element.getContents() + ": " +  element.isFinished()+ "\n";
        }
        return stringToDoList;
    }

    //creates to do list with no parameters entered
    public void createToDoItems() {
        createToDoItemsHelper(new ArrayList<ToDoListObject>());
    }

    //asks the user to enter to do list elements and adds them to a to-do list file using recursion
    private void createToDoItemsHelper(ArrayList<ToDoListObject> toDoList) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a To-Do List element: ");
        //stores user input
        String contents = sc.nextLine();
        //if user enters stop to do list, it terminates the method
        if (contents.equalsIgnoreCase("stoptodolist")) {
            //set the to do list to the arraylist
            setToDoList(toDoList);
            //adds the content of the arraylist to the to do list file
            FileUtilities.writeToFile("to_do_list.txt", returnToDoList(this.toDoList), false);
            System.out.println();
            return;
        }
        //creates to do list element based on user's input and adds it to the arraylist
        ToDoListObject toDoListObject = new ToDoListObject(contents);
        toDoList.add(toDoListObject);
        //uses recursion to update the arraylist
        createToDoItemsHelper(toDoList);
    }

    //reads the contents of the to do list file and shows it to the user
    public static void seeExistingToDoList(){
        ArrayList<String> contents = FileUtilities.returnFileContents("to_do_list.txt");
        String text = "";
        for (String line: contents){
            text = text + line + "\n";
        }
        System.out.println(text);
    }

    //changes the boolean value of a to-do list object
    public void tickItemOff(String item){
        //creates a random access file
        try (RandomAccessFile raf = new RandomAccessFile("to_do_list.txt", "rw")){
            //reads the whole file's contents
            while (raf.getFilePointer() < raf.length()){
                String line = raf.readLine();
                //splits the line into two based on the regex ":"
                String firstElement = line.split(":")[0].strip();
                String secondElement = line.split(":")[1].strip();

                //if the passed item exists in the to-do list, check its boolean value
                if (firstElement.equals(item)){
                    if (secondElement.equals("true")){
                        System.out.println("Item is already ticked off");
                        return;
                    }
                    //set the file pointer back right before where "false" is
                    long filePointer = raf.getFilePointer() - 6;
                    raf.seek(filePointer);
                    //replace false with true
                    raf.write("true ".getBytes());
                    //set the file pointer to right after true
                    filePointer = raf.getFilePointer() - 1;
                    raf.seek(filePointer);
                    break;
                }
                System.out.println("Item not found");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //change a to-do list object's string content
    public void rewriteItem(String text, String newText){
        for (ToDoListObject element: this.toDoList){
            if(element.getContents().strip().equalsIgnoreCase(text)){
                element.setContents(newText);
                return;
            }
        }
        System.out.println("Element doesn't exist");
    }

    //same as the create to-do list method but it appends an item rather than overwrite the list
    public void addToDoItems(ArrayList<ToDoListObject> toDoList) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ToDoListObject> newItems = new ArrayList<>();
        System.out.print("Enter a To-Do List element: ");
        String contents = sc.nextLine();
        while (!contents.equalsIgnoreCase("stoptodolist")) {
            ToDoListObject toDoListObject = new ToDoListObject(contents);
            newItems.add(toDoListObject); // add the new item to the new list
            System.out.print("Enter a To-Do List element: ");
            contents = sc.nextLine();
        }
        this.toDoList.addAll(newItems); // add only the new items to the current list
        setToDoList(this.toDoList);
        FileUtilities.writeToFile("to_do_list.txt", returnToDoList(this.toDoList), true);
        System.out.println();
    }
}