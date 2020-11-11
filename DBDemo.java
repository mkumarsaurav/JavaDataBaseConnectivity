import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBDemo {

	/** The name of the MySQL account to use */
	private final String userName = "root";

	/** The password for the MySQL account */
	private final String password = "root";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with */
	private final String dbName = "test";

	/** The name of the table we are testing with */
	private final String tableName = "JDBC_TEST";

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection(
				"jdbc:mysql://" + this.serverName + ":" + this.portNumber + "/" + this.dbName, connectionProps);

		return conn;
	}

	public boolean executeUpdate(Connection conn, String command) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(command); // This will throw a SQLException if it fails
			return true;
		} finally {

			// This will run whether we throw an exception or not
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	// Connect to MySQL and do some stuff.

	public Connection run() {

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		return conn;
	}

	public void create_table() {
		// Create a table
		Connection conn=null;
		conn=run();
		try {
			String createString = "CREATE TABLE " + this.tableName + " ( " + "ID INTEGER NOT NULL, "
					+ "NAME varchar(40) NOT NULL, " + "STREET varchar(40) NOT NULL, " + "CITY varchar(20) NOT NULL, "
					+ "STATE varchar(20) NOT NULL, " + "ZIP varchar(5), " + "PRIMARY KEY (ID))";
			this.executeUpdate(conn, createString);
			System.out.println("Created a table");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
	}
	
	public void drop_table() {
		// Drop the table
		Connection conn=null;
		conn=run();
		try {
			String dropString = "DROP TABLE " + this.tableName;
			this.executeUpdate(conn, dropString);
			System.out.println("Dropped the table");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
			return;
		}
	}
}
