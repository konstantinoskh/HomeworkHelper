public class ToDoListObject {
    private String contents;
    private boolean isFinished;

    public ToDoListObject(String contents){
        this.contents = contents;
        isFinished = false;
    }
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
