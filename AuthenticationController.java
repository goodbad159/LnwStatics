
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationController {
	public DBConnection con;

	public AuthenticationController() throws Exception {
		con = new DBConnection("postgres", "0000", "jdbc:postgresql://localhost:5433/postgres");
		con.openConnection();
	}

//	public boolean login(String userName, String password) throws Exception {
//		String sqlCommand = "SELECT username, password FROM lnwstatic where username = " + "'" + userName + "'"
//				+ "and password = " + "'" + password + "'" + ";";
//		ResultSet result = con.executeQueryStatement(sqlCommand);
//		if (result.next()) {
//			return true;
//
//		} else {
//			return false;
//		}
//
//	}
	public String[] chackUsername(String userName) throws SQLException{
		String[] info = new String[1];
		String sqlCommand = "SELECT username  FROM "+userName+" where subject = "
				+ "'" + userName + "'" + ";";
		ResultSet result = con.executeQueryStatement(sqlCommand);
		if (result.next()) {

			for (int i = 0; i < info.length; i++) {
				info[i] = result.getString(i + 1);
			}
		}
		return info;
	
	}

	public boolean createTable(String tableName) throws SQLException {
		String sqlCommand = "CREATE TABLE " + tableName
				+ "(subject text,\"allStudent\" integer,\"comeStudent\" integer,\"absentStudent\" integer,\"lateStudent\" integer)WITH (OIDS=FALSE);";
		if (con.executeCreateTableUpdateStatement(sqlCommand)) {
			return true;
		} else
			return false;

	}
	// public void

	/*
	 * public boolean insert(String user,String password ,String subject,int as
	 * ,int cs,int abs,int ls) throws SQLException{ String sqlCommand =
	 * "INSERT INTO public.lnwstatic(username, password, subject, \"allStudent\", \"comeStudent\", \"absentStudent\", \"lateStudent\")VALUES ("
	 * +"'"+user+"'"+", "+"'"+password+"'"+","+"'"+subject+"'"+","+as+", "
	 * +cs+", "+abs+","+ls+");";
	 * 
	 * if(con.executeInsertUpdateStatement(sqlCommand)){ return true; } else
	 * return false;
	 * 
	 * 
	 * }
	 */
	public boolean insertData(String subject, int as, int cs, int abs, int ls,String id) throws SQLException {
		String sqlCommand = "INSERT INTO public."+id+"(subject, \"allStudent\", \"comeStudent\", \"absentStudent\", \"lateStudent\")VALUES ("
				+ "'" + subject + "'" + "," + as + ", " + cs + ", " + abs + "," + ls + ");";

		if (con.executeInsertUpdateStatement(sqlCommand)) {
			return true;
		} else
			return false;

	}
	public boolean insert(String password ,String namesubject,String username) throws SQLException {
		System.out.println(password+namesubject+username);
		String sqlCommand = "INSERT INTO public."+username+"(subject, \"allStudent\", \"comeStudent\", \"absentStudent\", \"lateStudent\")VALUES ("
				+ "'" + password + "'" +"'" + namesubject + "'"+ "," + 100 + ", " + 100 + ", " + 0 + "," + 0 + ");";

		if (con.executeInsertUpdateStatement(sqlCommand)) {
			return true;
		} else
			return false;

	}

	public void close() {
		con.closeConnection();
	}

	public String[] getInfo(String subject,String id) throws SQLException {
		String[] info = new String[4];
		String sqlCommand = "SELECT \"allStudent\", \"comeStudent\", \"absentStudent\",\"lateStudent\"  FROM "+id+";";
		ResultSet result = con.executeQueryStatement(sqlCommand);
		if (result.next()) {

			for (int i = 0; i < info.length; i++) {
				info[i] = result.getString(i + 1);
			}
		}
		return info;
	}

}
