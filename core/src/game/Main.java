package game;

import game.view.View;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage stage) {
		View myView = new View();
		myView.init(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
