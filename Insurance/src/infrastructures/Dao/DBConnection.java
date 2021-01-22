package infrastructures.Dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.sqlite.SQLiteDataSource;

public class DBConnection {

	private static Connection _conn; 
	private static SQLiteDataSource ds;
	
	private DBConnection()
	{
		createConnection();
	}
	
	public static Connection GetDBConnection()
	{
		if(_conn == null)
		{
			new DBConnection();
		}
		return _conn;
	}
	

	
	private void createConnection() 
	{
		try 
		{
			ds = new SQLiteDataSource();
	        ds.setUrl("jdbc:sqlite:db/test.db");
		} 
		catch ( Exception e ) 
		{
			e.printStackTrace();
	        System.exit(0);
	    }
		try 
		{
			_conn = ds.getConnection();
	    } 
		catch ( SQLException e ) 
		{
			e.printStackTrace();
	        System.exit( 0 );
	    }
		System.out.println("\nConnection established");
	}
}
