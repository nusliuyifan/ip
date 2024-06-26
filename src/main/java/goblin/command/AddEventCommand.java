package goblin.command;

import goblin.Storage;
import goblin.TaskList;
import goblin.Ui;
import goblin.exception.OrkException;
import goblin.task.Events;
import goblin.task.Task;

public class AddEventCommand extends Command {
    protected String description;

    /**
     * create a new AddEventCommand
     * @param description for the event object
     */
    public AddEventCommand(String description) {
        super();
        this.description = description;
    }

    /**
     * add the event to the list
     * @param list the list of tasks
     * @param ui handle ui
     * @param storage handle storage
     * @throws OrkException when the description is not complete
     */
    //    //Solution below inspired by https://github.com/nus-cs2103-AY1920S1/duke/pull/23/commits
    public String execute(TaskList list, Ui ui, Storage storage) throws OrkException {
        String[] descriptionSplit = description.split( "/from");
        String message;
        try {
            if (descriptionSplit.length == 0 || descriptionSplit[0].trim().isEmpty()) {
                throw new OrkException("You need to tell me what the event is! You dumb meat!");
            }
            if (descriptionSplit.length < 2 || descriptionSplit[1].trim().isEmpty()) {
                throw new OrkException("Come on, dumb meat. I need both the task and the date.");
            }
            String event = descriptionSplit[0].trim();
            String time = descriptionSplit[1].trim();
            String[] timeSplit = time.split("/to");
            if (timeSplit[0].trim().isEmpty()) {
                throw new OrkException("What's the start time?");
            } else if (timeSplit.length > 2) {
                throw new OrkException("Follow the format!");
            }
                String startDateTime = timeSplit[0].trim();
                String endDateTime = timeSplit[1].trim();
                Task taskEvent = new Events(event, startDateTime, endDateTime);
                TaskList.list.add(taskEvent);
                int numberOfTasks = TaskList.list.size();
                storage.writeToDisk(list);
                message = ui.printAddedMessage(taskEvent, numberOfTasks);

        } catch (OrkException exception) {
            message = exception.getMessage();
        }
        return message;
    }
}



