import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class otherOptions extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage stage) throws Exception {
		
		Backend b = new Backend();
		
//		Box 1
		VBox facultyBox = new VBox(10);
		facultyBox.getStyleClass().add("vbox1");
		facultyBox.setPadding(new Insets(20));
		
			Label facultyIdlbl = new Label("Faculty ID:                ");
			Text facultyIdtxt = new Text("Faculty ID Text");
			HBox a1 = new HBox(facultyIdlbl,facultyIdtxt);
			
			Label facultyNamelbl = new Label("Faculty Name:          ");
			ArrayList<String> fnList = b.getFacultyList();
			ComboBox fnListCombo = new ComboBox(FXCollections .observableArrayList(fnList));
			fnListCombo.setMinWidth(150);
			HBox b1 = new HBox(facultyNamelbl,fnListCombo);
			
			Button addbtn1 = new Button("Add");
			Button updatebtn1 = new Button("Update");
			Button dltbtn1 = new Button("Delete");
			HBox c1 = new HBox(10,addbtn1,updatebtn1,dltbtn1);
			
			TextField fnListF = new TextField();
			fnListF.setPromptText("Enter Name");
			Button newaddbtn1 = new Button("Add");
			Button cancelbtn1 = new Button("Cancel");
			TextField ufnListF = new TextField();
			Button unewupdatebtn1 = new Button("Update");
			Button ucancelbtn1 = new Button("Cancel");
			
			updatebtn1.setOnAction(e->{
				try {
					ufnListF.setText(fnListCombo.getValue().toString());
					
					b1.getChildren().removeAll(fnListCombo);
					b1.getChildren().addAll(ufnListF);
					c1.getChildren().removeAll(addbtn1,updatebtn1,dltbtn1);
					c1.getChildren().addAll(unewupdatebtn1,ucancelbtn1); 
				} catch (Exception e1) {
				}
			});
			unewupdatebtn1.setOnAction(e->{
				if(!(fnListCombo.valueProperty().get() == null || fnListCombo.getValue() == null)) {
					b.updateFaculty(ufnListF.getText(),facultyIdtxt.getText());
				}
				
				fnListCombo.valueProperty().set(null);
				ArrayList<String> newfnList = b.getFacultyList();
				fnListCombo.setItems(FXCollections .observableArrayList(newfnList));
				b1.getChildren().addAll(fnListCombo);
				b1.getChildren().removeAll(ufnListF);
				c1.getChildren().addAll(addbtn1,updatebtn1,dltbtn1);
				c1.getChildren().removeAll(unewupdatebtn1,ucancelbtn1); 
				
			});
			
			ucancelbtn1.setOnAction(e->{
				try {
					b1.getChildren().addAll(fnListCombo);
					b1.getChildren().removeAll(ufnListF);
					c1.getChildren().addAll(addbtn1,updatebtn1,dltbtn1);
					c1.getChildren().removeAll(unewupdatebtn1,ucancelbtn1); 
				} catch (Exception e1) {
				}
			});
		
			fnListCombo.setOnAction(e->{
				System.out.println(fnListCombo.getValue());
				
				
				if(!(fnListCombo.valueProperty().get() == null || fnListCombo.getValue() == null)) {
					facultyIdtxt.setText(b.getFacultyId(fnListCombo.getValue().toString()).get(0));
				}
				
				});
			
			
			addbtn1.setOnAction(e->{
				try {
					b1.getChildren().removeAll(fnListCombo);
					b1.getChildren().addAll(fnListF);
					c1.getChildren().removeAll(addbtn1,updatebtn1,dltbtn1);
					c1.getChildren().addAll(newaddbtn1,cancelbtn1);
				} catch (Exception e1) {
				}
			});
			
			dltbtn1.setOnAction(e->{
				

				if(!(fnListCombo.valueProperty().get() == null || fnListCombo.getValue() == null)) {
					b.deleteFaculty(facultyIdtxt.getText());
				}
				fnListCombo.valueProperty().set(null);
				ArrayList<String> newfnList = b.getFacultyList();
				fnListCombo.setItems(FXCollections .observableArrayList(newfnList));
				
				
			});
			
			newaddbtn1.setOnAction(e->{
				
				b.addFaulty(fnListF.getText());
				
				fnListCombo.valueProperty().set(null);
				ArrayList<String> newfnList = b.getFacultyList();
				fnListCombo.setItems(FXCollections .observableArrayList(newfnList));
				b1.getChildren().addAll(fnListCombo);
				b1.getChildren().removeAll(fnListF);
				c1.getChildren().addAll(addbtn1,updatebtn1,dltbtn1);
				c1.getChildren().removeAll(newaddbtn1,cancelbtn1);
				
			});
			
			cancelbtn1.setOnAction(e->{
				try {
					b1.getChildren().addAll(fnListCombo);
					b1.getChildren().removeAll(fnListF);
					c1.getChildren().addAll(addbtn1,updatebtn1,dltbtn1);
					c1.getChildren().removeAll(newaddbtn1,cancelbtn1);
				} catch (Exception e1) {
				}
			});
			
			
			
			
			
		facultyBox.getChildren().addAll(a1,b1,c1);
		
		
//		Box 2
		VBox courseBox = new VBox(10);
		courseBox.getStyleClass().add("vbox1");
		courseBox.setPadding(new Insets(20));
		
			Label courseIdlbl = new Label("Course ID:                  ");
			Text courseIdtxt = new Text("Course ID Text");
			HBox a2 = new HBox(courseIdlbl,courseIdtxt);
			
			Label courseNamelbl = new Label("Course Name:            ");
			ArrayList<String> courseList = b.getCourseList();
			ComboBox courseListCombo = new ComboBox(FXCollections .observableArrayList(courseList));
			courseListCombo.setMinWidth(150);
			HBox b2 = new HBox(courseNamelbl,courseListCombo);
			
			Button addbtn2 = new Button("Add");
			Button updatebtn2 = new Button("Update");
			Button dltbtn2 = new Button("Delete");
			HBox c2 = new HBox(10,addbtn2,updatebtn2,dltbtn2);
			
			TextField courseListF = new TextField();
			Button newaddbtn2 = new Button("Add");
			Button cancelbtn2 = new Button("Cancel");
			
			TextField ucourseListF = new TextField();
			Button unewupdatebtn2 = new Button("Update");
			Button ucancelbtn2 = new Button("Cancel");
			
			updatebtn2.setOnAction(e->{
				try {
					ucourseListF.setText(courseListCombo.getValue().toString());
					
					b2.getChildren().removeAll(courseListCombo);
					b2.getChildren().addAll(ucourseListF);
					c2.getChildren().removeAll(addbtn2,updatebtn2,dltbtn2);
					c2.getChildren().addAll(unewupdatebtn2,ucancelbtn2); 
				} catch (Exception e1) {
				}
			});
			
			
			unewupdatebtn2.setOnAction(e->{
				if (!(courseListCombo.valueProperty().get() == null || courseListCombo.getValue() == null)) {
					b.updateCourse(ucourseListF.getText(), courseIdtxt.getText());
				}
				courseListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getCourseList();
				courseListCombo.setItems(FXCollections .observableArrayList(newList));
				b2.getChildren().addAll(courseListCombo);
				b2.getChildren().removeAll(ucourseListF);
				c2.getChildren().addAll(addbtn2,updatebtn2,dltbtn2);
				c2.getChildren().removeAll(unewupdatebtn2,ucancelbtn2); 
			});
			
			
			ucancelbtn2.setOnAction(e->{
				try {
					b2.getChildren().addAll(courseListCombo);
					b2.getChildren().removeAll(ucourseListF);
					c2.getChildren().addAll(addbtn2,updatebtn2,dltbtn2);
					c2.getChildren().removeAll(unewupdatebtn2,ucancelbtn2); 
				} catch (Exception e1) {
				}
			});
			
			
			
			courseListCombo.setOnAction(e->{
				if (!(courseListCombo.valueProperty().get() == null || courseListCombo.getValue() == null)) {
				courseIdtxt.setText(b.getCourseId(courseListCombo.getValue().toString()).get(0));
			}
			});
			addbtn2.setOnAction(e->{
				try {
					b2.getChildren().removeAll(courseListCombo);
					b2.getChildren().addAll(courseListF);
					c2.getChildren().removeAll(addbtn2,updatebtn2,dltbtn2);
					c2.getChildren().addAll(newaddbtn2,cancelbtn2);
				} catch (Exception e1) {
				}
			});
			
			newaddbtn2.setOnAction(e->{
				
				b.addCourseName(courseListF.getText());
				courseListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getCourseList();
				courseListCombo.setItems(FXCollections .observableArrayList(newList));
				b2.getChildren().addAll(courseListCombo);
				b2.getChildren().removeAll(courseListF);
				c2.getChildren().addAll(addbtn2,updatebtn2,dltbtn2);
				c2.getChildren().removeAll(newaddbtn2,cancelbtn2);
				
			});
			
	
			dltbtn2.setOnAction(e->{
				if (!(courseListCombo.valueProperty().get() == null || courseListCombo.getValue() == null)) {
					b.deleteCourse(courseIdtxt.getText());
				}
				
				courseListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getCourseList();
				courseListCombo.setItems(FXCollections .observableArrayList(newList));
				
			});
			
			
			cancelbtn2.setOnAction(e->{
				try {
					b2.getChildren().addAll(courseListCombo);
					b2.getChildren().removeAll(courseListF);
					c2.getChildren().addAll(addbtn2,updatebtn2,dltbtn2);
					c2.getChildren().removeAll(newaddbtn2,cancelbtn2);
				} catch (Exception e1) {
				}
			});
			
		courseBox.getChildren().addAll(a2,b2,c2);
		
//		Box 3
		VBox setBox = new VBox(10);
		setBox.getStyleClass().add("vbox1");
		setBox.setPadding(new Insets(20));
		
			Label setIdlbl = new Label("Set ID:                       ");
			Text setIdtxt = new Text("Set ID Text");
			HBox a3 = new HBox(setIdlbl,setIdtxt);
			
			Label setNamelbl = new Label("Set Name:                 ");
			ArrayList<String> setList = b.getSetList();
			ComboBox setListCombo = new ComboBox(FXCollections .observableArrayList(setList));
			setListCombo.setMinWidth(150);
			HBox b3 = new HBox(setNamelbl,setListCombo);
			
			Button addbtn3 = new Button("Add");
			Button updatebtn3 = new Button("Update");
			Button dltbtn3 = new Button("Delete");
			HBox c3 = new HBox(10,addbtn3,updatebtn3,dltbtn3);
			
			TextField setListF = new TextField();
			Button newaddbtn3 = new Button("Add");
			Button cancelbtn3 = new Button("Cancel");
			
			
			TextField usetListF = new TextField();
			Button unewupdatebtn3 = new Button("Update");
			Button ucancelbtn3 = new Button("Cancel");
			
			updatebtn3.setOnAction(e->{

				try {
					usetListF.setText(setListCombo.getValue().toString());
					b3.getChildren().removeAll(setListCombo);
					b3.getChildren().addAll(usetListF);
					c3.getChildren().removeAll(addbtn3,updatebtn3,dltbtn3);
					c3.getChildren().addAll(unewupdatebtn3,ucancelbtn3); 
				} catch (Exception e1) {
				}
			});

			unewupdatebtn3.setOnAction(e->{
				if(!(setListCombo.valueProperty().get() == null || setListCombo.getValue() == null)) {
					b.updateSet(usetListF.getText(), setIdtxt.getText());
				}
				setListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getSetList();
				setListCombo.setItems(FXCollections .observableArrayList(newList));
				b3.getChildren().addAll(setListCombo);
				b3.getChildren().removeAll(usetListF);
				c3.getChildren().addAll(addbtn3,updatebtn3,dltbtn3);
				c3.getChildren().removeAll(unewupdatebtn3,ucancelbtn3); 
				
			});
			
			ucancelbtn3.setOnAction(e->{
				try {
					b3.getChildren().addAll(setListCombo);
					b3.getChildren().removeAll(usetListF);
					c3.getChildren().addAll(addbtn3,updatebtn3,dltbtn3);
					c3.getChildren().removeAll(unewupdatebtn3,ucancelbtn3); 
				} catch (Exception e1) {
				}
			});
			
			
			
			
			
			setListCombo.setOnAction(e->{
				if(!(setListCombo.valueProperty().get() == null || setListCombo.getValue() == null)) {
				setIdtxt.setText(b.getSetId(setListCombo.getValue().toString()).get(0));
				}
				});
			
			addbtn3.setOnAction(e->{
				try {
					
					b3.getChildren().removeAll(setListCombo);
					b3.getChildren().addAll(setListF);
					c3.getChildren().removeAll(addbtn3,updatebtn3,dltbtn3);
					c3.getChildren().addAll(newaddbtn3,cancelbtn3);
				} catch (Exception e1) {
				}
			});
			
			newaddbtn3.setOnAction(e->{
				
				b.addSets(setListF.getText());
				setListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getSetList();
				setListCombo.setItems(FXCollections .observableArrayList(newList));
				b3.getChildren().addAll(setListCombo);
				b3.getChildren().removeAll(setListF);
				c3.getChildren().addAll(addbtn3,updatebtn3,dltbtn3);
				c3.getChildren().removeAll(newaddbtn3,cancelbtn3);
			});
			
			dltbtn3.setOnAction(e->{
				if(!(setListCombo.valueProperty().get() == null || setListCombo.getValue() == null)) {
					b.deleteSet(setIdtxt.getText());
				}
				setListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getSetList();
				setListCombo.setItems(FXCollections .observableArrayList(newList));
			});
			
			
			
			cancelbtn3.setOnAction(e->{
				try {
					b3.getChildren().addAll(setListCombo);
					b3.getChildren().removeAll(setListF);
					c3.getChildren().addAll(addbtn3,updatebtn3,dltbtn3);
					c3.getChildren().removeAll(newaddbtn3,cancelbtn3);
				} catch (Exception e1) {
				}
			});
			
		setBox.getChildren().addAll(a3,b3,c3);
		
//		Box 4
		VBox departmentBox = new VBox(10);
		departmentBox.getStyleClass().add("vbox1");
		departmentBox.setPadding(new Insets(20));
		
			Label departmentIdlbl = new Label("Department ID:          ");
			Text departmentIdtxt = new Text("Department ID Text");
			HBox a4 = new HBox(departmentIdlbl,departmentIdtxt);
		
			Label departmentNamelbl = new Label("Department Name:   ");
			ArrayList<String> departmentList = b.getDepartmentList();
			ComboBox departmentListCombo = new ComboBox(FXCollections .observableArrayList(departmentList));
			departmentListCombo.setMinWidth(150);
			HBox b4 = new HBox(departmentNamelbl,departmentListCombo);
			
			Button addbtn4 = new Button("Add");
			Button updatebtn4 = new Button("Update");
			Button dltbtn4 = new Button("Delete");
			HBox c4 = new HBox(10,addbtn4,updatebtn4,dltbtn4);
			
			TextField departmentListF = new TextField();
			Button newaddbtn4 = new Button("Add");
			Button cancelbtn4 = new Button("Cancel");
			
			TextField udepartmentListF = new TextField();
			Button unewupdatebtn4 = new Button("Update");
			Button ucancelbtn4 = new Button("Cancel");
			
			updatebtn4.setOnAction(e->{
				try {
					udepartmentListF.setText(departmentListCombo.getValue().toString());

					b4.getChildren().removeAll(departmentListCombo);
					b4.getChildren().addAll(udepartmentListF);
					c4.getChildren().removeAll(addbtn4,updatebtn4,dltbtn4);
					c4.getChildren().addAll(unewupdatebtn4,ucancelbtn4); 
				} catch (Exception e1) {
				}
			});
			
			
			
			unewupdatebtn4.setOnAction(e->{
				if(!(departmentListCombo.valueProperty().get() == null || departmentListCombo.getValue() == null)) {
					b.updateDepartment(udepartmentListF.getText(), departmentIdtxt.getText());
				}
				departmentListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getDepartmentList();
				departmentListCombo.setItems(FXCollections .observableArrayList(newList));
				b4.getChildren().addAll(departmentListCombo);
				b4.getChildren().removeAll(udepartmentListF);
				c4.getChildren().addAll(addbtn4,updatebtn4,dltbtn4);
				c4.getChildren().removeAll(unewupdatebtn4,ucancelbtn4); 
				
			});
			
			
			ucancelbtn4.setOnAction(e->{
				try {
					b4.getChildren().addAll(departmentListCombo);
					b4.getChildren().removeAll(udepartmentListF);
					c4.getChildren().addAll(addbtn4,updatebtn4,dltbtn4);
					c4.getChildren().removeAll(unewupdatebtn4,ucancelbtn4); 
				} catch (Exception e1) {
				}
			});
			
			addbtn4.setOnAction(e->{
				try {
					b4.getChildren().removeAll(departmentListCombo);
					b4.getChildren().addAll(departmentListF);
					c4.getChildren().removeAll(addbtn4,updatebtn4,dltbtn4);
					c4.getChildren().addAll(newaddbtn4,cancelbtn4);
				} catch (Exception e1) {
				}
			});
			
			departmentListCombo.setOnAction(e->{
				if(!(departmentListCombo.valueProperty().get() == null || departmentListCombo.getValue() == null)) {
					departmentIdtxt.setText(b.getDepartmentId(departmentListCombo.getValue().toString()).get(0));
				}
				});
			
			
			dltbtn4.setOnAction(e->{
				
				if(!(departmentListCombo.valueProperty().get() == null || departmentListCombo.getValue() == null)) {
					b.deleteDepartment(departmentIdtxt.getText());
				}
				departmentListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getDepartmentList();
				departmentListCombo.setItems(FXCollections .observableArrayList(newList));
				
			});
			
			newaddbtn4.setOnAction(e->{
				
				b.addDepartment(departmentListF.getText());
				departmentListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getDepartmentList();
				departmentListCombo.setItems(FXCollections .observableArrayList(newList));
				b4.getChildren().addAll(departmentListCombo);
				b4.getChildren().removeAll(departmentListF);
				c4.getChildren().addAll(addbtn4,updatebtn4,dltbtn4);
				c4.getChildren().removeAll(newaddbtn4,cancelbtn4);
			});
			
		
			
			cancelbtn4.setOnAction(e->{
				try {
					b4.getChildren().addAll(departmentListCombo);
					b4.getChildren().removeAll(departmentListF);
					c4.getChildren().addAll(addbtn4,updatebtn4,dltbtn4);
					c4.getChildren().removeAll(newaddbtn4,cancelbtn4);
				} catch (Exception e1) {
				}
			});
			
		departmentBox.getChildren().addAll(a4,b4,c4);
		
		
		
//		Box 5
		VBox authorBox = new VBox(10);
		authorBox.getStyleClass().add("vbox1");
		authorBox.setPadding(new Insets(20));
		
			Label authorNamelbl = new Label("Author Name:           ");
			ArrayList<String> authorList = b.getAuthorList();
			ComboBox authorListCombo = new ComboBox(FXCollections .observableArrayList(authorList));
			authorListCombo.setMinWidth(150);
			HBox a5 = new HBox(authorNamelbl,authorListCombo);
		
			Label titlelbl = new Label("Title:                            ");
			Text titletxt = new Text("Title Text");
			HBox b5 = new HBox(titlelbl,titletxt);
			
			Label namelbl = new Label("Id:                                ");
			Text nametxt = new Text("Name Text");
			HBox c5 = new HBox(namelbl,nametxt);
			
			Label surnamelbl = new Label("Surname:                    ");
			Text surnametxt = new Text("Surname Text");
			HBox d5 = new HBox(surnamelbl,surnametxt);
			
			Label addresslbl = new Label("Address:                     ");
			Text addresstxt = new Text("Address Text");
			HBox e5 = new HBox(addresslbl,addresstxt);
			  
			Label phonelbl = new Label("Phone:                        ");
			Text phonetxt = new Text("Phone Text");
			HBox f5 = new HBox(phonelbl,phonetxt);
			
			
			Button addbtn5 = new Button("Add");
			Button updatebtn5 = new Button("Update");
			Button dltbtn5 = new Button("Delete");
			HBox g5 = new HBox(10,addbtn5,updatebtn5,dltbtn5);
			
			
			
			TextField authorListF = new TextField();
			Button newaddbtn5 = new Button("Add");
			Button cancelbtn5 = new Button("Cancel");
			
			TextField titleF = new TextField();
			TextField nameF = new TextField();
			TextField surnameF = new TextField();
			TextField addressF = new TextField();
			TextField phoneF = new TextField();
			
		
			TextField uauthorListF = new TextField();
			Button unewaddbtn5 = new Button("Add");
			Button ucancelbtn5 = new Button("Cancel");
			
			TextField utitleF = new TextField();
			TextField unameF = new TextField();
			TextField usurnameF = new TextField();
			TextField uaddressF = new TextField();
			TextField uphoneF = new TextField();
			
			updatebtn5.setOnAction(e->{
				try {
					System.out.println(authorListCombo.getValue().toString());
					uauthorListF.setText(authorListCombo.getValue().toString());
					usurnameF.setText(surnametxt.getText());
					utitleF.setText(titletxt.getText());
					uaddressF.setText(addresstxt.getText());
					uphoneF.setText(phonetxt.getText());
					
					a5.getChildren().removeAll(authorListCombo);
					a5.getChildren().addAll(uauthorListF);
					g5.getChildren().removeAll(addbtn5,updatebtn5,dltbtn5);
					g5.getChildren().addAll(unewaddbtn5,ucancelbtn5);
					
					b5.getChildren().removeAll(titletxt);
					b5.getChildren().addAll(utitleF);
					c5.getChildren().removeAll(nametxt);
					c5.getChildren().removeAll(namelbl);
					d5.getChildren().removeAll(surnametxt);
					d5.getChildren().addAll(usurnameF);
					e5.getChildren().removeAll(addresstxt);
					e5.getChildren().addAll(uaddressF);
					f5.getChildren().removeAll(phonetxt);
					f5.getChildren().addAll(uphoneF);
					
				} catch (Exception e1) {
				}
			});
			unewaddbtn5.setOnAction(e->{
				
				
				
				if(!(authorListCombo.valueProperty().get() == null || authorListCombo.getValue() == null)) 
				{b.updateAuthor(uauthorListF.getText(), usurnameF.getText(), utitleF.getText(), uaddressF.getText(), uphoneF.getText() , nametxt.getText());
				}
				authorListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getAuthorList();
				authorListCombo.setItems(FXCollections .observableArrayList(newList));
				
				a5.getChildren().addAll(authorListCombo);
				a5.getChildren().removeAll(uauthorListF);
				g5.getChildren().addAll(addbtn5,updatebtn5,dltbtn5);
				g5.getChildren().removeAll(unewaddbtn5,ucancelbtn5);
				b5.getChildren().addAll(titletxt);
				b5.getChildren().removeAll(utitleF);
				c5.getChildren().addAll(namelbl);
				c5.getChildren().addAll(nametxt);
				c5.getChildren().removeAll(unameF);
				d5.getChildren().addAll(surnametxt);
				d5.getChildren().removeAll(usurnameF);
				e5.getChildren().addAll(addresstxt);
				e5.getChildren().removeAll(uaddressF);
				f5.getChildren().addAll(phonetxt);
				f5.getChildren().removeAll(uphoneF);
			});
			
			
			ucancelbtn5.setOnAction(e->{
				try {
					a5.getChildren().addAll(authorListCombo);
					a5.getChildren().removeAll(uauthorListF);
					g5.getChildren().addAll(addbtn5,updatebtn5,dltbtn5);
					g5.getChildren().removeAll(unewaddbtn5,ucancelbtn5);
					
					b5.getChildren().addAll(titletxt);
					b5.getChildren().removeAll(utitleF);
					c5.getChildren().addAll(namelbl);
					c5.getChildren().addAll(nametxt);
					c5.getChildren().removeAll(unameF);
					d5.getChildren().addAll(surnametxt);
					d5.getChildren().removeAll(usurnameF);
					e5.getChildren().addAll(addresstxt);
					e5.getChildren().removeAll(uaddressF);
					f5.getChildren().addAll(phonetxt);
					f5.getChildren().removeAll(uphoneF);
				} catch (Exception e1) {
				}
			});

			addbtn5.setOnAction(e->{
				try {
					a5.getChildren().removeAll(authorListCombo);
					a5.getChildren().addAll(authorListF);
					g5.getChildren().removeAll(addbtn5,updatebtn5,dltbtn5);
					g5.getChildren().addAll(newaddbtn5,cancelbtn5);
					b5.getChildren().removeAll(titletxt);
					b5.getChildren().addAll(titleF);
					c5.getChildren().removeAll(namelbl);
					c5.getChildren().removeAll(nametxt);
					d5.getChildren().removeAll(surnametxt);
					d5.getChildren().addAll(surnameF);
					e5.getChildren().removeAll(addresstxt);
					e5.getChildren().addAll(addressF);
					f5.getChildren().removeAll(phonetxt);
					f5.getChildren().addAll(phoneF);
					
				} catch (Exception e1) {
				}
			});
			
			
			authorListCombo.setOnAction(e->{if(!(authorListCombo.valueProperty().get() == null || authorListCombo.getValue() == null)) 
			{
				Labeled authorIdtxt = null;
				nametxt.setText(b.getAuthorId(authorListCombo.getValue().toString()).get(0));
				ArrayList<String> arr = b.getOneAuthorDeatils(nametxt.getText());
				titletxt.setText(arr.get(0));
				surnametxt.setText(arr.get(1));
				phonetxt.setText(arr.get(3));
				addresstxt.setText(arr.get(2));
				
				
				
			}
			});
				
				
			newaddbtn5.setOnAction(e->{
				b.addAuthor(titleF.getText(), authorListF.getText(), addressF.getText(), phoneF.getText(), surnameF.getText());
				authorListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getAuthorList();
				authorListCombo.setItems(FXCollections .observableArrayList(newList));
				
				a5.getChildren().addAll(authorListCombo);
				a5.getChildren().removeAll(authorListF);
				g5.getChildren().addAll(addbtn5,updatebtn5,dltbtn5);
				g5.getChildren().removeAll(newaddbtn5,cancelbtn5);
				b5.getChildren().addAll(titletxt);
				b5.getChildren().removeAll(titleF);
				c5.getChildren().addAll(nametxt);
				c5.getChildren().removeAll(nameF);
				d5.getChildren().addAll(surnametxt);
				d5.getChildren().removeAll(surnameF);
				e5.getChildren().addAll(addresstxt);
				e5.getChildren().removeAll(addressF);
				f5.getChildren().addAll(phonetxt);
				f5.getChildren().removeAll(phoneF);
			});
			cancelbtn5.setOnAction(e->{
				try {
					a5.getChildren().addAll(authorListCombo);
					a5.getChildren().removeAll(authorListF);
					g5.getChildren().addAll(addbtn5,updatebtn5,dltbtn5);
					g5.getChildren().removeAll(newaddbtn5,cancelbtn5);
					b5.getChildren().addAll(titletxt);
					b5.getChildren().removeAll(titleF);
					c5.getChildren().addAll(namelbl);
					c5.getChildren().addAll(nametxt);
					c5.getChildren().removeAll(nameF);
					d5.getChildren().addAll(surnametxt);
					d5.getChildren().removeAll(surnameF);
					e5.getChildren().addAll(addresstxt);
					e5.getChildren().removeAll(addressF);
					f5.getChildren().addAll(phonetxt);
					f5.getChildren().removeAll(phoneF);
				} catch (Exception e1) {
				}
			});
			
			dltbtn5.setOnAction(e->{
				b.deleteAuthor(nametxt.getText());
				authorListCombo.valueProperty().set(null);
				ArrayList<String> newList = b.getAuthorList();
				authorListCombo.setItems(FXCollections .observableArrayList(newList));
			});
			
			
			
		authorBox.getChildren().addAll(a5,b5,c5,d5,e5,f5,g5);
		
		Button backbtn = new Button("   Go Back   ");
		
		backbtn.setOnAction(e->{
	        mainScreen ms = new mainScreen();
	        try {
				ms.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        	
	        });		FlowPane fp = new FlowPane(facultyBox,courseBox,setBox,departmentBox,authorBox);
		fp.setHgap(20);
		fp.setVgap(20);
		fp.getStyleClass().add("mainBox");
		VBox root = new VBox(20,backbtn,fp);
		root.getStyleClass().add("mainBox");
		Scene scene = new Scene(root,1280,600);
		scene.getStylesheets().add("css.css");
		stage.setScene(scene);
		stage.show();
	}

}
