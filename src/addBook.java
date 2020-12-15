import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class addBook extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage stage) throws Exception {
		HBox booksListBox = new HBox();
		HBox.setHgrow(booksListBox, Priority.ALWAYS);
		booksListBox.setAlignment(Pos.CENTER_LEFT);
		booksListBox.setPadding(new javafx.geometry.Insets(0, 30 , 0 , 30 ));
		Backend be = new Backend();
		
			Label bookNamelbl = new Label("Book Name");
			TextField bookNameF = new TextField();
			bookNameF.setMinWidth(200);
			Button backbtn = new Button("Go Back");
			HBox a = new HBox(20,backbtn,bookNamelbl,bookNameF);
			HBox.setHgrow(a, Priority.ALWAYS);
			a.setAlignment(Pos.CENTER_LEFT);
			
			Button addbookbtn = new Button("Add Book");
			Button clearbtn = new Button("Clear Fields");
			
			HBox b = new HBox(15,addbookbtn,clearbtn);
			HBox.setHgrow(b, Priority.ALWAYS);
			b.setAlignment(Pos.CENTER_RIGHT);
			
		booksListBox.getChildren().addAll(a,b);
		
		HBox booksDetailBox = new HBox();
		booksDetailBox.setHgrow(booksListBox, Priority.ALWAYS);
		booksDetailBox.setAlignment(Pos.CENTER_LEFT);
		
			GridPane bookDetailList1 = new GridPane();
			bookDetailList1.setPadding(new javafx.geometry.Insets(30));
			bookDetailList1.setVgap(20);
			bookDetailList1.setHgap(20);
			
			Label BookIdlbl = new Label("Book ID: ");
			TextField BookIdtxt = new TextField();
			BookIdtxt.setDisable(true);
			BookIdtxt.setText("Auto-Generated");
			Label BookCodelbl = new Label("Book Code: ");
			TextField BookCodetxt = new TextField();
			Label isbnlbl = new Label("ISBN: ");
			TextField isbntxt = new TextField();
			Label publisherNumlbl = new Label("Publisher Number: ");
			TextField publisherNumtxt = new TextField();
			Label printNumlbl = new Label("Print Number: ");
			TextField printNumtxt = new TextField();
			Label printDatelbl = new Label("Print Date: ");
			TextField printDatetxt = new TextField();
			Label setlbl = new Label("Set: ");
			ArrayList<String> setList = be.getSetList();
			ComboBox setListCombo = new ComboBox(FXCollections .observableArrayList(setList));
			setListCombo.setMinWidth(150);
			
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
			bookDetailList1.add(setlbl, 0, 6);
			bookDetailList1.add(setListCombo, 1, 6);
			
			
			GridPane bookDetailList2 = new GridPane();
			bookDetailList2.setPadding(new javafx.geometry.Insets(30));
			bookDetailList2.setVgap(20);
			bookDetailList2.setHgap(20);
			
			Label numOfPageslbl = new Label("Number Of Pages: ");
			TextField numOfPagestxt = new TextField();
			Label authorNamelbl = new Label("Author Name: ");
			ArrayList<String> anList = be.getAuthorList();
			ComboBox anListCombo = new ComboBox(FXCollections .observableArrayList(anList));
			anListCombo.setMinWidth(150);
			Label courselbl = new Label("Course: ");
			ArrayList<String> courseList = be.getCourseList();
			ComboBox courseListCombo = new ComboBox(FXCollections .observableArrayList(courseList));
			courseListCombo.setMinWidth(150);
			Label chapterslbl = new Label("Chapters: ");
			TextField chaptertf = new TextField();
			Label facultylbl = new Label("Faculty: ");
			ArrayList<String> facultyList = be.getFacultyList();
			ComboBox facultyListCombo = new ComboBox(FXCollections .observableArrayList(facultyList));
			facultyListCombo.setMinWidth(150);
			Label departmentlbl = new Label("Department: ");
			ArrayList<String> departmentList = be.getDepartmentList();
			ComboBox departmentListCombo = new ComboBox(FXCollections .observableArrayList(departmentList));
			departmentListCombo.setMinWidth(150);
			
			
			
			
			bookDetailList2.add(numOfPageslbl, 2, 0);
			bookDetailList2.add(numOfPagestxt, 3, 0);
			bookDetailList2.add(authorNamelbl, 2, 1);
			bookDetailList2.add(anListCombo, 3, 1);
			bookDetailList2.add(courselbl, 2, 2);
			bookDetailList2.add(courseListCombo, 3, 2);
			bookDetailList2.add(chapterslbl, 2, 3);
			bookDetailList2.add(chaptertf, 3, 3);
			bookDetailList2.add(facultylbl, 2, 4);
			bookDetailList2.add(facultyListCombo, 3, 4);
			bookDetailList2.add(departmentlbl, 2, 5);
			bookDetailList2.add(departmentListCombo, 3, 5);

			addbookbtn.setOnAction(e->{
				be.addBook(bookNameF.getText(), BookCodetxt.getText(), isbntxt.getText(), publisherNumtxt.getText(), printNumtxt.getText(), printDatetxt.getText(), numOfPagestxt.getText(), be.getCourseId(courseListCombo.getValue().toString()).get(0), be.getSetId(setListCombo.getValue().toString()).get(0), chaptertf.getText(), be.getAuthorId(anListCombo.getValue().toString()).get(0), facultyListCombo.getValue().toString(), courseListCombo.getValue().toString(), departmentListCombo.getValue().toString());

				bookNameF.clear();
				BookCodetxt.clear();
				isbntxt.clear();
				publisherNumtxt.clear();
				printNumtxt.clear();
				printDatetxt.clear();
				numOfPagestxt.clear();
				chaptertf.clear();
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
			
			clearbtn.setOnAction(e->{
				
				
				bookNameF.clear();
				BookCodetxt.clear();
				isbntxt.clear();
				publisherNumtxt.clear();
				printNumtxt.clear();
				printDatetxt.clear();
				numOfPagestxt.clear();
				chaptertf.clear();
				
			});
		booksDetailBox.getChildren().addAll(bookDetailList1,bookDetailList2);
		
		VBox root = new VBox(30,booksListBox,booksDetailBox);
		root.getStyleClass().add("mainBox1");
		Scene scene = new Scene(root,810,470);
		scene.getStylesheets().add("css.css");
		stage.setScene(scene);
		stage.show();
	}

}
