package isd.aims.main.views;

import java.io.File;
import java.io.IOException;

import isd.aims.main.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FXMLScreenHandler {

	protected FXMLLoader loader;
	protected AnchorPane content;

	public FXMLScreenHandler(String screenPath) throws IOException {
		this.loader = new FXMLLoader(App.class.getResource(screenPath));
		// Set this class as the controller
		this.loader.setController(this);
		this.content = (AnchorPane) loader.load();
	}

	@SuppressWarnings("exports")
	public AnchorPane getContent() {
		return this.content;
	}

	@SuppressWarnings("exports")
	public FXMLLoader getLoader() {
		return this.loader;
	}

	@SuppressWarnings("exports")
	public void setImage(ImageView imv, String path){
		File file = new File(path);
		Image img = new Image(file.toURI().toString());
		imv.setImage(img);
	}
}
