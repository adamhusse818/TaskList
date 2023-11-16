/**
 * Class designed for a task
 * Each task will have the following:
 * title: A string for the name of the task
 * dueDate: A string for the due date of the task in the format mm/dd/year
 * completionStatus: A boolean representing if task is complete or not
 */
public class Task extends TaskList{
    // Task fields
    private String title;
    private String dueDate;
    private boolean completionStatus;

    /**
     * Constructor method for initializing a task.
     * A task is assumed to be not complete when first put in
     * @param t: String representing task title
     * @param d: String representing date in format mm/dd/year
     */
    public Task(String t, String d){
        this.title = t;
        this.dueDate = d;
        this.completionStatus = false;
        addTask(this);
    }

    /**
     * Method that prints the task
     * @return task in format "title: due date: completion status"
     */
    public String toString(){
        String isComplete;
        if (completionStatus){
            isComplete = "Complete";
        }
        else{
            isComplete = "Not Complete";
        }
        return title + ": Due " + dueDate + ": " + isComplete;
    }

    /**
     * Returns the title of task
     * @return the title of "this" task
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * User can choose to change the title of their task
     * @param newTitle: String representing new chosen title
     */
    public void changeTitle(String newTitle){
        this.title = newTitle;
    }

    /**
     * Returns due date of a task
     * @return the due date of "this" task
     */
    public String getDueDate(){
        return this.dueDate;
    }

    /**
     * User may change due date of task
     * @param newDueDate: String new date in format mm/dd/year
     */
    public void changeDueDate(String newDueDate){
        this.dueDate = newDueDate;
    }

    /**
     * Method returning the completion status
     * @return the completion status of "this" task
     */
    public Boolean isComplete(){
        return this.completionStatus;
    }

    /**
     * User may complete their task.
     * This is irreversible
     */
    public void markComplete(){
        this.completionStatus = true;
    }
}