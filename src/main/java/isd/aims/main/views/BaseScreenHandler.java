package isd.aims.main.views;

import java.io.IOException;
import java.util.Hashtable;

import isd.aims.main.controller.BaseController;
import isd.aims.main.views.home.HomeScreenHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BaseScreenHandler extends FXMLScreenHandler {

	private Scene scene;
	private BaseScreenHandler prev;
	protected final Stage stage;
	protected HomeScreenHandler homeScreenHandler;
	protected Hashtable<String, String> messages;
	private BaseController bController;

	private BaseScreenHandler(String screenPath) throws IOException {
		super(screenPath);
		this.stage = new Stage();
	}

	public void setPreviousScreen(BaseScreenHandler prev) {
		this.prev = prev;
	}

	public BaseScreenHandler getPreviousScreen() {
		return this.prev;
	}

	@SuppressWarnings("exports")
	public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = stage;
	}

	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}

	@SuppressWarnings("exports")
	public void setBController(BaseController bController){
		this.bController = bController;
	}

	@SuppressWarnings("exports")
	public BaseController getBController(){
		return this.bController;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void forward(Hashtable messages) {
		this.messages = messages;
	}

	@SuppressWarnings("exports")
	public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
		this.homeScreenHandler = HomeScreenHandler;
	}

}
