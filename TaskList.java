/**
 * Class for the list of tasks
 * An arraylist is used to hold objects of tasks
 */
import java.util.ArrayList;

public class TaskList{
    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Method that adds task to the list.
     * Uses add from ArrayList
     * @param tsk: Task object
     */
    public void addTask(Task tsk){
        tasks.add(tsk);
    }

    /**
     * Method that removes task.
     * Uses linear search to find task.
     * Once found, ArrayList method remove() is used
     * @param tsk: Task object
     */
    public void removeTask(Task tsk){
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getTitle().equals(tsk.getTitle())){
                tasks.remove(tasks.get(i));
            }
        }
    }

    /**
     * Method for displaying the tasks.
     * Displays them in the order they are sorted in the list
     */
    public void displayTasks(){
        if(tasks.size() == 1){
            System.out.println("You have 1 task:");
        }
        else {
            System.out.printf("You have %d tasks:\n", tasks.size());
        }
        for(int i = 0; i < tasks.size(); i++){
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }
}