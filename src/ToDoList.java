import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    private ArrayList<ToDoListObject> toDoList;

    public ToDoList(ArrayList<ToDoListObject> toDoList) {
        this.toDoList = toDoList;
    }

    public ToDoList(){
        this.toDoList = new ArrayList<>();
    }

    public ArrayList<ToDoListObject> getToDoList() {
        return toDoList;
    }

    private void setToDoList(ArrayList<ToDoListObject> toDoList) {
        this.toDoList = toDoList;
    }

    private String stringToDoList(ArrayList<ToDoListObject> list) {
        String stringToDoList = "";
        for (ToDoListObject element : list) {
            stringToDoList = stringToDoList + element.getContents() + ": " +  element.isFinished()+ "\n";
        }
        return stringToDoList;
    }

    public void createToDoItems() {
        createToDoItemsHelper(new ArrayList<ToDoListObject>());
    }

    private void createToDoItemsHelper(ArrayList<ToDoListObject> toDoList) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a To-Do List element: ");
        String contents = sc.nextLine();
        if (contents.equalsIgnoreCase("stoptodolist")) {
            setToDoList(toDoList);
            FileUtilities.writeToFile("to_do_list.txt", stringToDoList(this.toDoList), false);
            System.out.println();
            return;
        }
        ToDoListObject toDoListObject = new ToDoListObject(contents);
        toDoList.add(toDoListObject);
        createToDoItemsHelper(toDoList);
    }

    public static void seeExistingToDoList(){
        ArrayList<String> contents = FileUtilities.returnFileContents("to_do_list.txt");
        String text = "";
        for (String line: contents){
            text = text + line + "\n";
        }
        System.out.println(text);
    }

    public void markItemAsDone(String contents) {
        for (ToDoListObject element : toDoList) {
            if (element.getContents().strip().equalsIgnoreCase(contents)) {
                element.setFinished(true);
                return;
            }
        }
        System.out.println("Item not found in the to-do list");
    }

    public void rewriteItem(String text, String newText){
        for (ToDoListObject element: this.toDoList){
            if(element.getContents().strip().equalsIgnoreCase(text)){
                element.setContents(newText);
                return;
            }
        }
        System.out.println("Element doesn't exist");
    }

    public void addToDoItems(ArrayList<ToDoListObject> toDoList) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ToDoListObject> newItems = new ArrayList<>(); // create a new list to hold the new items
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
        FileUtilities.writeToFile("to_do_list.txt", stringToDoList(this.toDoList), false);
        System.out.println();
    }
}