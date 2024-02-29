package goblin;

import goblin.task.Task;
import goblin.command.Command;
import goblin.exception.OrkException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

//Solution below inspired by https://github.com/nus-cs2103-AY1920S1/duke/pull/23/commits
public class Goblin {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Donald.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Pooh.png"));

    /**
     * Create a Goblin object.
     * Initiate ui, storage and taskList.
     *
     * @param filePath the local path to the storage file.
     */
    public Goblin(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (OrkException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Goblin() {

    }

    public String getResponse(String input) {
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(message);
        PrintStream old = System.out;
        System.setOut(ps);
        try {
            ui.line();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (OrkException e) {
            ui.printException(e);
        } finally {
            ui.line();
        }
        System.out.flush();
        System.setOut(old);
        return message.toString();
    }

    /**
     * Collect user input
     * parse them while the app is working
     */
    public void run() {
        Ui.sayHello();
        boolean working = true;
        while(working) {
            try {
            String command = ui.readCommand();
            Ui.line();;
            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage);
            working = c.isWorking();
            } catch (OrkException e) {
                ui.printException(e);
            } finally {
                Ui.line();
            }
        }
    }

    /**
     * the main method
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Goblin("src/main/java/goblin/data.txt").run();
    }
}
