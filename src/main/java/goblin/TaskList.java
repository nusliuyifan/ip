package goblin;

import goblin.task.Task;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> list = new ArrayList<>();

    /**
     * create a new TaskList
     *
     * @param  list an arrayList of tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * create a new TaskList
     */
    public TaskList() {

    }

    /**
     * a getter for the current list
     * @return the current list
     */
    public static ArrayList<Task> getList() {
        return list;
    }

    /**
     * add a task to the current list
     * @param task the task to be added
     */
    public void addTask(Task task){
        list.add(task);
    }

    /**
     * a getter for a task in the list
     *
     * @param index the index of the task to get
     * @return a task with the corresponding index
     */
    public Task getTask(int index) {
        return list.get(index);
    }
}
