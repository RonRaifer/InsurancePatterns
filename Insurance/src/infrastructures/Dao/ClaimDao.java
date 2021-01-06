package infrastructures.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.sqlite.SQLiteDataSource;

import infrastructures.Factories.Claim;
import infrastructures.Logger.Logger;

public class ClaimDao implements IDao<Claim> {
	
	//SINGLETON
	static ClaimDao _instance=null;
	
	
	//DB CONNECTION
	protected Connection _conn;
	protected Logger _logger;
	private SQLiteDataSource ds = null;
	
	private ClaimDao()
	{
		//ESTABLISH DATABASE CONNECTION HERE
	}
	
	public static ClaimDao GetInstance()
	{
		if(_instance==null)
		{
			_instance= new ClaimDao();
			return _instance;
		}
		else 
		{
			return _instance;
		}
	}
	
	@Override
	public void insert(Claim obj) throws SQLException {

		
	}

	@Override
	public void update(Claim obj) throws SQLException {

		
	}

	@Override
	public void delete(Claim obj) throws SQLException {

		
	}

	@Override
	public List<Claim> getByID(String id) {

		return null;
	}

	@Override
	public List<Claim> getAll() {

		return null;
	}

}
