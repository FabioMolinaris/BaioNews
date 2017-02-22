package it.molis.laBaioApp;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class SecondClass extends Application {

    private Scene scene;
    private Browser browser;

    @Override public void start(Stage stage) {
        stage.setTitle("La Baionetta Website");
        scene = new Scene(browser,600,750, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
    }

	public SecondClass() {
		super();
	}

	public void setBrowser(Browser b) {
		this.browser=b;
	}


}