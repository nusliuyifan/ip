package goblin;

import goblin.exception.OrkException;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.util.Objects;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Goblin goblin;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/stick-man.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/goblin.png"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setGoblin(Goblin d) {
        goblin = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = goblin.getResponse(input);
        dialogContainer.getChildren().addAll(
                new Group(DialogBox.getUserDialog(input, userImage)),
                new Group(DialogBox.getDukeDialog(response, dukeImage))
        );
        userInput.clear();
    }
}