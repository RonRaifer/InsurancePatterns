package infrastructures.Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import infrastructures.Factories.Claim;
import infrastructures.Logger.Logger;

public class ClaimDao implements IDao<Claim> {
	
	//SINGLETON
	static ClaimDao _instance=null;

	private ClaimDao()
	{
		String tblCreateQuery = "create table IF NOT EXISTS Claims(cID INTEGER PRIMARY KEY AUTOINCREMENT, pID char(256) not null, "
				+ "amount char(256) not null,"
				+ "dateSued date not null, "
				+ "remarks char(256) not null, "
				+ "status char(256) not null)";
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
		String insertSQL = "INSERT INTO Claims (pID, amount, dateSued, remarks, status) VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(insertSQL);
	    preparedStatement.setString(1, obj.pID);
	    preparedStatement.setString(2, obj.amount);
	    preparedStatement.setDate(3, obj.getDateSued());
	    preparedStatement.setString(4, obj.remarks);
	    preparedStatement.setString(5, obj.status);
	    preparedStatement.executeUpdate(); 
		
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
