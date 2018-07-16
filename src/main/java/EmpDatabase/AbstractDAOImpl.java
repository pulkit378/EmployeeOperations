package EmpDatabase;

import java.sql.Connection;


public abstract class AbstractDAOImpl	{
	public AbstractDAOImpl() {
	}

	Connection getConnection() {
		try {
			return JdbcConnectionFactory.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
