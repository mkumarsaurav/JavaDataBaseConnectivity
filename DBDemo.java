import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;



public class DBDemo {

	private Scanner sc = new Scanner(System.in);
	
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
		Connection conn = null;
		conn = run();
		try {
			String createString = "CREATE TABLE " + this.tableName + " ( " + "ID INTEGER NOT NULL, "
					+ "NAME varchar(40) NOT NULL, " + "STREET varchar(40) NOT NULL, " + "CITY varchar(20) NOT NULL, "
					+ "STATE varchar(20) NOT NULL, " + "ZIP varchar(6), " + "PRIMARY KEY (ID))";
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
		Connection conn = null;
		conn = run();
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

	public void insert() {
		Connection conn = run();
		int id;
		String name,street,city,state,zip;
		System.out.println("Enter id:");
		id=sc.nextInt();
		sc.nextLine();                            // (For consuming the "\n" character)
		System.out.println("Enter Name:");
		name=sc.nextLine();
		System.out.println("Enter Street:");
		street=sc.nextLine();
		System.out.println("Enter City:");
		city=sc.nextLine();
		System.out.println("Enter State:");
		state=sc.nextLine();
		System.out.println("Enter ZIP:"); 
		zip=sc.nextLine();
		try {
			String insertString = "INSERT INTO " + this.tableName + " VALUES ('"+id+"','"+name+"','"+street+"','"+city+"','"+state+"','"+zip+"')";
			this.executeUpdate(conn, insertString);
			System.out.println("Inserted Successfully in table: "+this.tableName);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not insert into the table");
			e.printStackTrace();
			return;
		}
	}
}
