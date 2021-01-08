package infrastructures.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.sqlite.SQLiteDataSource;

import infrastructures.Factories.Claim;
import infrastructures.Logger.Logger;

public class ClaimDao implements IDao<Claim> {
	
	//SINGLETON
	static ClaimDao _instance=null;
	
	private ClaimDao()
	{
		String tblCreateQuery = "create table IF NOT EXISTS Claims(cID INTEGER PRIMARY KEY AUTOINCREMENT, pID INTEGER not null, "
				+ "firstName char(256) not null, "
				+ "lastName char(256) not null, "
				+ "sDate date not null, "
				+ "remarks char(256) not null, "
				+ "insType char(256) not null, "
				+ "amount char(256) not null)";
		try {
            PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(tblCreateQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.GetInstance().log(e.getMessage());
        }
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
