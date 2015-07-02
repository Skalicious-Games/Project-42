//not used
package game.view;

import game.model.Model;
import game.model.Reciever;
import javafx.stage.Stage;

public class View {

	private Model myModel;
	private Stage myStage;
	private Display myDisplay;

	public void init(Stage stage) {
		myModel = new Model();
		myStage = stage;
		myStage.setTitle("Project-42");
		myDisplay = new Display((Reciever) myModel);
		myStage.setScene(myDisplay.init());
		myStage.show();
	}
}