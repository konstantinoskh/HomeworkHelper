import java.util.ArrayList;

public class NotesSection {
    public static void createNotesSection(){
        FileUtilities.writeToFile("notes_section.txt","",false);
    }

    public static void addNotes(String notes){
        FileUtilities.writeToFile("notes_section.txt",notes + "\n",true);
    }

    public static void deleteNotes(){
        FileUtilities.writeToFile("notes_section.txt", "", false);
    }

    public static void overWriteNotes(String notes){
        FileUtilities.writeToFile("notes_section.txt", notes+ "\n", false);
    }
    public static void seeCurrentNotes(){
        ArrayList<String> notes = FileUtilities.returnFileContents("notes_section.txt");
        String text = "";
        for (String element:notes){
            text = text + element + "\n";
        }
        System.out.println(text);
    }
}
