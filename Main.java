/**
 * Main class designed to run the TaskList system
 * In the main method, we do the following:
 *     ** Open the file containing the user tasks
 *     ** Copy tasks into a task list
 *     ** Allow users to make changes to current list
 *     ** Save their changes to a new file with same name
 */

// Necessary import statements
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        TaskList tl = new TaskList(); // Create a list of tasks
        final String FILE_NAME = "Tasks.txt"; // Name of file containing tasks

        // Attempt to read file. A file is created if one is not found at the end
        try{
            File f = new File(FILE_NAME);
            Scanner readFile = new Scanner(f);

            /* Read the tasks from the file
               Each task is stored in this order: Title, due date, is complete
             */
            while(readFile.hasNextLine()){
                String data = readFile.nextLine();
                String[] splitTask = data.split(", "); // Split using ","
                Task t = new Task(splitTask[0], splitTask[1]); // Create a new task object
                if(splitTask[2].equals("true")){
                    t.markComplete();
                }
                tl.addTask(t);
            }
            readFile.close();
            if(!f.delete()){
                System.out.println("Critical error has occurred.");
            }
        }catch(FileNotFoundException e){

        }

        // Displays menu interface that introduces user to software, including features
        System.out.println(
                "Hello! Welcome to Tasky!\nThis is a user-friendly to-do list application " +
                "designed to make it easy for you to add, remove, or change any tasks. To get started:\n" +
                "* Type \"v\" to view your tasks\n" +
                "* Type \"a\" to add a task\n" +
                "* Type \"r\" to remove a task\n" +
                "* Type \"c\" to change a task\n" +
                "* Type \"x\" to exit. **WARNING** Tasks will not saved without typing this!");

        // User chooses their option from here
        Scanner scan = new Scanner(System.in);
        String option = scan.next().toLowerCase();

        // User will continue to be asked for input until "x" is inputted
        while(!option.equals("x")){
            switch(option){
                case "v": // View tasks
                    tl.displayTasks();
                    break;
                case "a": // Adding a task
                    System.out.print("Enter a name for this task: ");
                    String title = scan.next();
                    System.out.print("Enter a due date in the format mm/dd/year: ");
                    String date = scan.next();
                    Task t = new Task(title, date);
                    tl.addTask(t);
                    System.out.printf("Success! The following task was added: %s\n", t);
                    break;
                case "r": // Removing a task
                    if(tl.tasks.isEmpty()){
                        System.out.println("You have 0 tasks");
                        break;
                    }
                    System.out.println("Please enter the number indicating the task you wish to remove:");
                    tl.displayTasks();
                    int removeThis = Integer.parseInt(scan.next()) - 1;
                    Task tempTask = tl.tasks.get(removeThis);
                    tl.removeTask(tl.tasks.get(removeThis));
                    System.out.printf("Success! The following task was removed: %s\n", tempTask);
                    break;
                case "c": // Changing a field of a task. This involves another option menu
                    tl.displayTasks();
                    System.out.print("Please enter the number for the task you wish to change: ");
                    int changeThis = Integer.parseInt(scan.next()) - 1;
                    System.out.print(
                            "Please choose an option:\n" +
                            "* Type \"n\" to change the name\n" +
                            "* Type \"d\" to change the due date\n");
                    if(!tl.tasks.get(changeThis).isComplete()){ // Only non-completed tasks can be marked complete
                        System.out.println("* Type \"cs\" to mark this task complete");
                    }
                    System.out.println("* Typing anything else will cancel the change");
                    option = scan.next().toLowerCase();
                    scan.nextLine();
                    switch(option){
                        case "n": // Change title of task
                            System.out.print("Enter a new title: ");
                            String newTitle = scan.nextLine();
                            tl.tasks.get(changeThis).changeTitle(newTitle);
                            System.out.printf("Success! Name changed. Task is now: %s\n", tl.tasks.get(changeThis));
                            break;
                        case "d": // Change due date of task
                            System.out.print("Enter a new due date in the format mm/dd/year: ");
                            String newDate = scan.next();
                            tl.tasks.get(changeThis).changeDueDate(newDate);
                            System.out.printf("Success! Due date changed. Task is now: %s\n", tl.tasks.get(changeThis));
                            break;
                        case "cs": // Mark task complete, if applicable
                            tl.tasks.get(changeThis).markComplete();
                            System.out.printf("Success! Task \"%s\" is now complete\n",
                                    tl.tasks.get(changeThis).getTitle());
                            break;
                    }
                    break;
                default:
                    System.out.println("Please choose one of the options from the menu");
                    break;
            }
            option = scan.next().toLowerCase();
        }

        /* We will assume the user will not have such a large amount of tasks.
           Because of that, we can simply create a new file.
           Doing so will allow us to copy over changes as well.
         */
        try{
            File f = new File("Tasks.txt");
            FileWriter fw = new FileWriter("Tasks.txt");
            for(Task t : tl.tasks){
                String task = t.getTitle() + ", " + t.getDueDate() + ", ";
                if(t.isComplete()){
                    task += "true\n";
                }
                else{
                    task += "false\n";
                }
                fw.write(task);
            }
            fw.close();
        }catch(IOException e){
            System.out.println("Critical error.");
        }
        System.out.print("Bye-bye!");
        scan.close();
    }
}