import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class mainScreen extends Application{

	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		
		Label Mainheading = new Label("Library Management\r\n" + 
				"System");
		Mainheading.getStyleClass().add("heading");
		
		Button addbook = new Button("Add Books");
		addbook.getStyleClass().add("button");
		addbook.setPrefWidth(250);
		Button bookDetails = new Button("Book Details");
		bookDetails.getStyleClass().add("button");
		bookDetails.setPrefWidth(250);
		Button otherOptions = new Button("Other Options");
		otherOptions.getStyleClass().add("button");
		otherOptions.setPrefWidth(250);
		
		Button exit = new Button("Exit");
		exit.getStyleClass().add("exitbtn");
		exit.setPrefWidth(250);
		
		VBox buttonBox = new VBox(20,addbook,bookDetails,otherOptions,exit);
		VBox.setVgrow(buttonBox, Priority.ALWAYS);
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		
		VBox root = new VBox(20,Mainheading,buttonBox);
		VBox.setVgrow(root, Priority.ALWAYS);
		root.getStyleClass().add("mainBox1");
		Scene scene = new Scene(root,300,400);
		scene.getStylesheets().add("css.css");
		stage.setScene(scene);
		stage.setTitle("LMS");
		stage.show();
		
		
		addbook.setOnAction(e->{
			addBook ad = new addBook();
			
			try {
				ad.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		bookDetails.setOnAction(e->{
			BookDetails bd = new BookDetails();
			
			try {
				bd.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		otherOptions.setOnAction(e->{
			otherOptions o = new otherOptions();
			try {
				o.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
	
		
		exit.setOnAction(e->{
			
			System.exit(0);
		});
	}

}
