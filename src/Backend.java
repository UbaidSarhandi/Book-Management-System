import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Backend {
	public static void main(String[] args) {
		Backend b = new Backend();
		
		try {
			b.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	///////////// ADD METHOD START ////////////////////////
	public void addBook(String BookName, String  Barcode , String  ISBN , String  PubNo, String  printNo, String date , String  NoOfPages, String Course ,String setid ,String Chapters,String Author, String Faculty, String CourseName, String Department) {

		try {
			Connection c = getConnection();
			
			PreparedStatement post = c.prepareStatement("INSERT INTO books(Barcode,BookName,ISBN,PublicationNumbers ) VALUES ('"+Barcode+"','"+BookName+"','"+ISBN+"','"+PubNo+"')");
			post.executeUpdate();
			post = c.prepareStatement("SELECT * FROM books ORDER BY BookID DESC LIMIT 1");
			ResultSet id = post.executeQuery();
			String bid = null;
			while (id.next()) {
				 bid =id.getString("BookID");	
				 
			}
			post = c.prepareStatement("INSERT INTO printing(BookID,PrintingNumber,DATE,NumberOfPages) VALUES ('"+bid+"','"+printNo+"','"+date+"','"+NoOfPages+"')");
			post.executeUpdate();
			System.out.println(bid);
			post = c.prepareStatement("INSERT INTO selection(BookID,Course) VALUES ('"+bid+"','"+Course+"')");
			post.executeUpdate();
			post = c.prepareStatement("INSERT INTO booksets(SetID, BookID) VALUES ('"+setid+"','"+bid+"')");
			post.executeUpdate();
			post = c.prepareStatement("INSERT INTO chapters(BookID,Chapters,AuthorID) VALUES ('"+bid+"','"+Chapters+"','"+Author+"');");
			post.executeUpdate();
			post = c.prepareStatement("INSERT INTO courses(BookID,CourseID,CourseName,Faculty,Department) VALUES ('"+bid+"','"+Course+"','"+CourseName+"','"+Faculty+"','"+Department+"');");
			post.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void addAuthor(String Title, String Name,String Address, String PhoneNo, String Surname) {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("INSERT INTO AUTHORS(Title,NAME,surname,Address,PhoneNo) VALUES ('"+Title+"','"+Name+"','"+Surname+"','"+Address+"','"+PhoneNo+"');");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void addSets( String Setname) {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("INSERT INTO sets(SetNames) VALUES ('"+Setname+"')");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addFaulty(String FacultyName) {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("INSERT INTO faculties(FacultyName) VALUES ('"+FacultyName+"')");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addDepartment(String DepName) {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("INSERT INTO departments(DepartmentName) VALUES ('"+DepName+"');");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void addCourseName(String CourseName) {

		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("INSERT INTO coursenames(Course) VALUES ('"+CourseName+"');");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/////////////// ADD METHODS END  ////////////////////
	
	
	///////////// SINGLE BOOK VIEW DETAILS METHOD ////////////////////////
	
	public ArrayList<String> GetOneBookDetail(String BookName) {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("SELECT b.bookid,b.BookName,a.name ,a.phoneno,bs.setid, b.ISBN,b.PublicationNumbers,p.PrintingNumber,p.date,p.NumberOfPages,c.Chapters,cou.CourseName,Faculty,Department\r\n" + 
					"FROM books AS b\r\n" + 
					" INNER JOIN printing AS p ON b.BookID = p.BookID\r\n" + 
					" INNER JOIN chapters AS c ON b.BookID = c.BookID\r\n" + 
					" INNER JOIN courses AS cou ON b.BookID = cou.BookID\r\n" + 
					" INNER JOIN AUTHORS AS a ON a.AuthorID = c.AuthorID\r\n" + 
					" INNER JOIN booksets AS bs ON bs.bookID = b.bookID\r\n" + 
					" WHERE b.BookName='"+BookName+"'");
			ResultSet result = post.executeQuery() ;
			
			
			
			ArrayList<String> list = new ArrayList<String>();
				while (result.next()) {
					
				
				list.add(result.getString("bookID"));
				list.add(result.getString("BookName"));
				list.add(result.getString("name"));
				list.add(result.getString("phoneno"));
				list.add(result.getString("ISBN"));
				list.add(result.getString("PublicationNumbers"));
				list.add(result.getString("PrintingNumber"));
				list.add(result.getString("date"));
				list.add(result.getString("NumberOfPages"));
				list.add(result.getString("Chapters"));
				list.add(result.getString("CourseName"));
				list.add(result.getString("Faculty"));
				list.add(result.getString("Department"));
				list.add(result.getString("setid"));
				
				}
				return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
	
	/////////////// COMPLETE BOOK LIST AND TABLE METOD ///////////////////
	public ArrayList<ArrayList<String>> GetBooksList() {

		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("SELECT b.bookid,b.BookName,a.name ,a.phoneno, b.ISBN,b.PublicationNumbers,p.PrintingNumber,p.date,p.NumberOfPages,c.Chapters,cou.CourseName,Faculty,Department\r\n" + 
					"FROM books AS b\r\n" + 
					" INNER JOIN printing AS p ON b.BookID = p.BookID\r\n" + 
					" INNER JOIN chapters AS c ON b.BookID = c.BookID\r\n" + 
					" INNER JOIN courses AS cou ON b.BookID = cou.BookID\r\n" + 
					" INNER JOIN AUTHORS AS a ON a.AuthorID = c.AuthorID");
			ResultSet result = post.executeQuery() ;
			
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			
			while(result.next()){
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(result.getString("bookID"));
				list.add(result.getString("BookName"));
				list.add(result.getString("name"));
				list.add(result.getString("phoneno"));
				list.add(result.getString("ISBN"));
				list.add(result.getString("PublicationNumbers"));
				list.add(result.getString("PrintingNumber"));
				list.add(result.getString("date"));
				list.add(result.getString("NumberOfPages"));
				list.add(result.getString("Chapters"));
				list.add(result.getString("CourseName"));
				list.add(result.getString("Faculty"));
				list.add(result.getString("Department"));
				
				
				array.add(list);
				
			}
			return array;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	////////////////////// GET LIST METHOD START //////////////////////
	
	 public ArrayList<String> getBookNames() {

		 Connection c;
			try {
				c = getConnection();
				PreparedStatement post = c.prepareStatement("SELECT BookName FROM Books");
				ResultSet result = post.executeQuery() ;
				ArrayList<String> list = new ArrayList<String>();
				while(result.next()){
				list.add(result.getString("BookName"));	
				
				}
				return list;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		 
	}
	 
	 
	 public ArrayList<String> getAuthorList() {
		 Connection c;
			try {
				c = getConnection();
				PreparedStatement post = c.prepareStatement("SELECT  Name FROM authors");
				ResultSet result = post.executeQuery() ;
				ArrayList<String> list = new ArrayList<String>();
				while(result.next()){
					
				
				list.add(result.getString("Name"));	
				}
				return list;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;


	}
	 
	 
	 public ArrayList<String> getDepartmentList() {

		 Connection c;
			try {
				c = getConnection();
				PreparedStatement post = c.prepareStatement("SELECT  DepartmentName FROM Departments");
				ResultSet result = post.executeQuery() ;
				ArrayList<String> list = new ArrayList<String>();
				while(result.next()){
				list.add(result.getString("DepartmentName"));	
				}
				return list;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		 
	}
	 
	 
	 public ArrayList<String> getFacultyList() {
	// TODO Auto-generated method stub
		 Connection c;
			try {
				c = getConnection();
				PreparedStatement post = c.prepareStatement("SELECT  FacultyName FROM faculties");
				ResultSet result = post.executeQuery() ;
				ArrayList<String> list = new ArrayList<String>();
				while(result.next()){
			
				list.add(result.getString("FacultyName"));	
				}
				return list;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

	}


	 public ArrayList<String> getCourseList() {
	// TODO Auto-generated method stub
		 Connection c;
			try {
				c = getConnection();
				PreparedStatement post = c.prepareStatement("SELECT  course FROM coursenames");
				ResultSet result = post.executeQuery() ;
				ArrayList<String> list = new ArrayList<String>();
				
				while(result.next()){
					
				list.add(result.getString("course"));	
				}
				return list;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

	}

	 public ArrayList<String> getSetList() {
	// TODO Auto-generated method stub
		 Connection c;
			try {
				c = getConnection();
				PreparedStatement post = c.prepareStatement("SELECT  setNames FROM sets");
				ResultSet result = post.executeQuery() ;
				ArrayList<String> list = new ArrayList<String>();
				
				while(result.next()){
					
				list.add(result.getString("setNames"));	
				}
				return list;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}
	 
	 /////////////// GET LIST METHOD END ///////////////////////////
	 
	 
	 /////////// Get Id Method /////////////////
	 
	 
	 public ArrayList<String> getFacultyId(String name) {
			// TODO Auto-generated method stub
				 Connection c;
					try {
						c = getConnection();
						PreparedStatement post = c.prepareStatement("SELECT FacultyID FROM faculties where facultyname='"+name+"'");
						ResultSet result = post.executeQuery() ;
						ArrayList<String> list = new ArrayList<String>();
						while(result.next()){
						list.add(result.getString("FacultyID"));
						}
						return list;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;

			}
	 
	 
	 public ArrayList<String> getCourseId(String name) {
			// TODO Auto-generated method stub
				 Connection c;
					try {
						c = getConnection();
						PreparedStatement post = c.prepareStatement("SELECT CourseID FROM coursenames where Course='"+name+"'");
						ResultSet result = post.executeQuery() ;
						ArrayList<String> list = new ArrayList<String>();
						while(result.next()){
						list.add(result.getString("CourseID"));
						}
						return list;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;

			}
	 
	 public ArrayList<String> getDepartmentId(String name) {
			// TODO Auto-generated method stub
				 Connection c;
					try {
						c = getConnection();
						PreparedStatement post = c.prepareStatement("SELECT DepartmentID FROM departments where Departmentname='"+name+"'");
						ResultSet result = post.executeQuery() ;
						ArrayList<String> list = new ArrayList<String>();
						while(result.next()){
						list.add(result.getString("DepartmentID"));
						}
						return list;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;

			}
	 
	 public ArrayList<String> getSetId(String name) {
			// TODO Auto-generated method stub
				 Connection c;
					try {
						c = getConnection();
						PreparedStatement post = c.prepareStatement("SELECT SetID FROM sets where Setnames='"+name+"'");
						ResultSet result = post.executeQuery() ;
						ArrayList<String> list = new ArrayList<String>();
						while(result.next()){
						list.add(result.getString("SetID"));
						}
						return list;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;

			}
	 
	 public ArrayList<String> getAuthorId(String name) {
			// TODO Auto-generated method stub
				 Connection c;
					try {
						c = getConnection();
						PreparedStatement post = c.prepareStatement("SELECT authorID FROM authors where name='"+name+"'");
						ResultSet result = post.executeQuery() ;
						ArrayList<String> list = new ArrayList<String>();
						while(result.next()){
						list.add(result.getString("authorID"));
						}
						return list;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;

			}
	 ////////////////// END GET ID METHODS   ///////////////////////
	 ////////////////// ALL AUTHOR DETAILS /////////////////////////
	public ArrayList<ArrayList<String>> getAuthorDetails() {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("SELECT * FROM authors");
			ResultSet result = post.executeQuery() ;
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			
			while(result.next()){
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(result.getString("authorID"));
				list.add(result.getString("Title"));
				list.add(result.getString("name"));
				list.add(result.getString("surname"));
				list.add(result.getString("Address"));
				list.add(result.getString("phoneno"));
				
			
				
				
				array.add(list);
				
			}
			
			return array;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
	
	/////////////////// DELETE METHOD START ///////////////////////
	
	public void deleteBook(String bookName) {
		
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement(" DELETE  b,p,c,cou,bs,s FROM books AS b\r\n" + 
					" INNER JOIN printing AS p ON b.BookID = p.BookID\r\n" + 
					" INNER JOIN chapters AS c ON b.BookID = c.BookID\r\n" + 
					" INNER JOIN courses AS cou ON b.BookID = cou.BookID\r\n" + 
					" INNER JOIN booksets AS bs ON b.BookID = bs.BookID\r\n" + 
					" INNER JOIN selection AS s ON b.BookID = s.BookID\r\n" + 
					" WHERE b.BookName='"+bookName+"'");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteAuthor(String ID) {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("Delete FROM Authors Where AuthorID="+ID+"");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void deleteDepartment(String ID) {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("Delete FROM Departments Where DepartmentID="+ID+"");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteFaculty(String ID) {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("DELETE FROM Faculties WHERE FacultyID='"+ID+"'");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void deleteCourse(String ID) {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("Delete FROM Coursenames Where CourseID='"+ID+"'");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteSet(String ID) {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("Delete FROM sets Where setID="+ID+"");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	///////////// DELETE METHOD END //////////////////
	
	///////////// UPDATE METHOD START ////////////////
	
	
	public void updateAuthor(String Name, String surname, String title, String address, String Pno, String id) {
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("UPDATE Authors Set Name='"+Name+"', surname='"+surname+"', Title='"+title+"', address='"+address+"', phoneno='"+Pno+"' where authorid='"+id+"'");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void updateCourse(String Name, String ID) {
		// TODO Auto-generated method stub
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("Update coursenames SET course='"+Name+"' where courseid='"+ID+"' ");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateFaculty(String Name, String ID) {
		// TODO Auto-generated method stub
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("Update faculties SET facultyName='"+Name+"' where facultyid='"+ID+"'");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateDepartment(String Name, String id) {
		// TODO Auto-generated method stub
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("Update departments SET departmentName='"+Name+"' where departmentid='"+id+"' ");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateSet(String Name, String id) {
		// TODO Auto-generated method stub
		Connection c;
		try {
			c = getConnection();
			PreparedStatement post = c.prepareStatement("Update sets SET SetNames='"+Name+"' where setid='"+id+"'");
			post.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	///////// UPDATE METHODS END ////////////
	
	public ArrayList<String> getOneAuthorDeatils(String ID) {
		
		try {
			Connection c = null;
			
				try {
					c = getConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			PreparedStatement post;
			post = c.prepareStatement("SELECT * FROM authors where Authorid="+ID+"");
			ResultSet result = post.executeQuery() ;
			
			ArrayList<String> list = new ArrayList<String>();
			while(result.next()){
				
				
				
				list.add(result.getString("Title"));
				list.add(result.getString("surname"));
				list.add(result.getString("Address"));
				list.add(result.getString("phoneno"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	////////////////////////////////////
	
	private  Connection getConnection() throws Exception {
		 

		Connection c = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			c= DriverManager.getConnection("jdbc:mysql://root@localhost:3306/dbmsproject?serverTimezone=UTC");
			System.out.println("SQL Db is connected");
		} catch(Exception e){
			System.out.println(e);
			
		}
		
		return c;
		 
 }
}
