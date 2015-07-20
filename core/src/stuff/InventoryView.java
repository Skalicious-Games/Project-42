package stuff;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class InventoryView {
	
	private BorderPane myRoot;
	private Label myTitle;
	
	public InventoryView() {
		myTitle = new Label("Inventory");
		myRoot.setTop(myTitle);
		myRoot.setCenter(setupGrid());
	}
	
	public Pane init() {
		return myRoot;
	}
	
	private Node setupGrid() {
		//TODO: 
		return null;
	}

}
