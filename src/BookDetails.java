import java.awt.Insets;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BookDetails extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {
		Backend be = new Backend();
		HBox booksListBox = new HBox(20);
		booksListBox.setHgrow(booksListBox, Priority.ALWAYS);
		booksListBox.setAlignment(Pos.CENTER_LEFT);
		booksListBox.setPadding(new javafx.geometry.Insets(0, 30 , 0 , 30 ));
		
		
			Button backbtn = new Button("Go Back");
			Button dltbtn = new Button("Delete");
			Button addbtn = new Button("Add");
			Label selectBooklbl = new Label("Select Book");
			ArrayList<String> bookList = be.getBookNames();
			ComboBox bookListCombo = new ComboBox(FXCollections .observableArrayList(bookList));
			bookListCombo.setMinWidth(200);
			
		booksListBox.getChildren().addAll(backbtn,selectBooklbl,bookListCombo,addbtn,dltbtn);
		
		HBox booksDetailBox = new HBox();
		booksDetailBox.setHgrow(booksListBox, Priority.ALWAYS);
		booksDetailBox.setAlignment(Pos.CENTER_LEFT);
		
			GridPane bookDetailList1 = new GridPane();
			bookDetailList1.setPadding(new javafx.geometry.Insets(30));
			bookDetailList1.setVgap(20);
			bookDetailList1.setHgap(20);

			
			
			Label BookIdlbl = new Label("Book ID: ");
			Text BookIdtxt = new Text("Book ID Text");
			Label BookCodelbl = new Label("Book Code: ");
			Text BookCodetxt = new Text("Book Code Text");
			Label isbnlbl = new Label("ISBN: ");
			Text isbntxt = new Text("ISBN Text");
			Label publisherNumlbl = new Label("publisher Number: ");
			Text publisherNumtxt = new Text("publisher Number Text");
			Label printNumlbl = new Label("Print Number: ");
			Text printNumtxt = new Text("Print Number Text");
			Label printDatelbl = new Label("Print Date: ");
			Text printDatetxt = new Text("Print Date Text");
			Label courseNamelbl = new Label("course Name: ");
			Text courseNametxt = new Text("Print Date Text");
			Label setIdlbl = new Label("set Id: ");
			Text setIdtxt = new Text("Print Date Text");
			
			bookDetailList1.add(BookIdlbl, 0, 0);
			bookDetailList1.add(BookIdtxt, 1, 0);
			bookDetailList1.add(BookCodelbl, 0, 1);
			bookDetailList1.add(BookCodetxt, 1, 1);
			bookDetailList1.add(isbnlbl, 0, 2);
			bookDetailList1.add(isbntxt, 1, 2);
			bookDetailList1.add(publisherNumlbl, 0, 3);
			bookDetailList1.add(publisherNumtxt, 1, 3);
			bookDetailList1.add(printNumlbl, 0, 4);
			bookDetailList1.add(printNumtxt, 1, 4);
			bookDetailList1.add(printDatelbl, 0, 5);
			bookDetailList1.add(printDatetxt, 1, 5);
			bookDetailList1.add(courseNamelbl, 0, 6);
			bookDetailList1.add(courseNametxt, 1, 6);
			bookDetailList1.add(setIdlbl, 0, 7);
			bookDetailList1.add(setIdtxt, 1, 7);
			
			
			GridPane bookDetailList2 = new GridPane();
			bookDetailList2.setPadding(new javafx.geometry.Insets(30));
			bookDetailList2.setVgap(20);
			bookDetailList2.setHgap(20);
			
			Label numOfPageslbl = new Label("Number Of Pages: ");
			Text numOfPagestxt = new Text("Number Of Pages Text");
			Label authorNamelbl = new Label("Author Name: ");
			Text authorNametxt = new Text("Author Name Text");
			Label authorPhonelbl = new Label("Author Phone: ");
			Text authorPhonetxt = new Text("Author Phone Text");
			Label chapterslbl = new Label("Chapters: ");
			Text chapterstxt = new Text("chapters Text");
			Label facultylbl = new Label("Faculty: ");
			Text facultytxt = new Text("faculty Text");
			Label departmentlbl = new Label("Department: ");
			Text departmenttxt = new Text("Department Text");
			
			
			
			bookDetailList2.add(numOfPageslbl, 2, 0);
			bookDetailList2.add(numOfPagestxt, 3, 0);
			bookDetailList2.add(authorNamelbl, 2, 1);
			bookDetailList2.add(authorNametxt, 3, 1);
			bookDetailList2.add(authorPhonelbl, 2, 2);
			bookDetailList2.add(authorPhonetxt, 3, 2);
			bookDetailList2.add(chapterslbl, 2, 3);
			bookDetailList2.add(chapterstxt, 3, 3);
			bookDetailList2.add(facultylbl, 2, 4);
			bookDetailList2.add(facultytxt, 3, 4);
			bookDetailList2.add(departmentlbl, 2, 5);
			bookDetailList2.add(departmenttxt, 3, 5);
			
			
			bookListCombo.setOnAction(e->{
				ArrayList<String> arrl = be.GetOneBookDetail(bookListCombo.getValue().toString());
				BookIdtxt.setText(arrl.get(0));
				BookCodetxt.setText(arrl.get(1));
				authorNametxt.setText(arrl.get(2));
				authorPhonetxt.setText(arrl.get(3));
				isbntxt.setText(arrl.get(4));
				publisherNumtxt.setText(arrl.get(5));
				printNumtxt.setText(arrl.get(6));
				printDatetxt.setText(arrl.get(7));
				numOfPagestxt.setText(arrl.get(8));
				chapterstxt.setText(arrl.get(9));
				courseNametxt.setText(arrl.get(10));
				facultytxt.setText(arrl.get(11));
				departmenttxt.setText(arrl.get(12));
				setIdtxt.setText(arrl.get(13));
				
			});
			
			backbtn.setOnAction(e->{
		        mainScreen ms = new mainScreen();
		        try {
					ms.start(stage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        	
		        });
			dltbtn.setOnAction(e->{
				
				be.deleteBook(bookListCombo.getValue().toString());
				bookListCombo.valueProperty().set(null);
				ArrayList<String> newList = be.getBookNames();
				bookListCombo.setItems(FXCollections .observableArrayList(newList));
			});
			
		booksDetailBox.getChildren().addAll(bookDetailList1,bookDetailList2);
		
		VBox root = new VBox(30,booksListBox,booksDetailBox);
		root.getStyleClass().add("mainBox1");
		Scene scene = new Scene(root,800,440);
		scene.getStylesheets().add("css.css");
		stage.setScene(scene);
		stage.show();
	}

}
