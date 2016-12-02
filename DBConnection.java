
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private String url;
	private String user;
	private String password;
	private Connection dbConn;

	public DBConnection() {
	}

	public DBConnection(String user, String pwd, String url) {
		this.user = user;
		this.password = pwd;
		this.url = url;
	}

	public void openConnection() throws Exception {

		Class.forName("org.postgresql.Driver");
		dbConn = DriverManager.getConnection(url, user, password);

	}

	public void closeConnection() {
		try {
			dbConn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet executeQueryStatement(String sqlCommand) {
		ResultSet result = null;
		try {
			Statement statement = dbConn.createStatement();
			result = statement.executeQuery(sqlCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean executeInsertUpdateStatement(String sqlCommand) throws SQLException {

		Statement statement = dbConn.createStatement();
		statement.execute(sqlCommand);

		return true;
	}
	public boolean executeCreateTableUpdateStatement(String sqlCommand) throws SQLException {

		Statement statement = dbConn.createStatement();
		statement.executeUpdate(sqlCommand);
		return true;
	}

	public boolean isOpen() {
		try {
			return !dbConn.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}

	}

}
