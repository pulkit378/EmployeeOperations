package EmpDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public final class JdbcConnectionFactory {

	private static JdbcConnectionFactory connectionFactory;
	
	private Connection connection;

	private JdbcConnectionFactory() {

	}

	public static JdbcConnectionFactory createInstance() {
		if (connectionFactory == null) {
			connectionFactory = new JdbcConnectionFactory();
		}
		
		return connectionFactory;
	}
	
	public static Connection getConnection() {
//		return createInstance().createConnection();
		try {
			return getMySQLDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private Connection createConnection() {

		Properties credentialsProps = null;
		boolean error = true;
		try {
			// File file = new File("credentials.properties");
			credentialsProps = new Properties();
			// InputStream inStream = new FileInputStream(file);
			InputStream stream = ClassLoader.getSystemResourceAsStream("credentials.properties");

			if (stream == null) {
				System.out.println("Error in loading the credentials for JDBC, "
						+ "credentials.properties file with jdbc credentials in the following foramt is required \n"
						+ "userName=userName\npassword=password");
				return null;
			}
			credentialsProps.load(stream);
			error = false;
		} catch (FileNotFoundException e1) {
			System.out.println("Error in loading the credentials for JDBC, "
					+ "credentials.properties file with jdbc credentials in the following foramt is required \n"
					+ "userName=userName\npassword=password");
		} catch (IOException e1) {
			System.out.println("Failed to load the file credentials.properties");
		}

		if (error) {
			return null;
		}

		try {
			Class.forName(credentialsProps.getProperty("driver.name"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = credentialsProps.getProperty("connectionUrl");
		String user = credentialsProps.getProperty("userName");
		String password = credentialsProps.getProperty("password");

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static Connection getDSConnection() {
		Connection con = null;
		System.out.println("Executing getDSConnection ");
		javax.sql.DataSource ds = null;
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, "t3://localhost:7001");

		try {
			Context context = new InitialContext(env);
			// you will need to have create a Data Source with JNDI name testDS
			ds = (javax.sql.DataSource) context.lookup("jdbc/MySQL");
			con = ds.getConnection();
			System.out.println("Connection object details : " + con);
			// con.close();
		} catch (Exception ex) {
			// handle the exception
			ex.printStackTrace();
		}
		return con;
	}

	public static DataSource getMySQLDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
		MysqlDataSource mysqlDS = null;
		try {
			fis = new FileInputStream("credentials.properties");
			props.load(fis);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("connectionUrl"));
			mysqlDS.setUser(props.getProperty("userName"));
			mysqlDS.setPassword(props.getProperty("password"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mysqlDS;
	}
	
	public static void main(String args[]) {
		Connection connction = getDSConnection();
		try {
			Statement st = connction.createStatement();
			ResultSet rs = st.executeQuery("SELECT name from emp");
			rs.next();
			System.out.println(rs.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert (connction != null);
	}
}
