package infrastructures.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.sqlite.SQLiteDataSource;

import infrastructures.Factories.IFactory;
import infrastructures.Factories.Policy;
import infrastructures.Logger.Logger;

public class PolicyDao implements IDao<Policy>{

	//SINGLETON
	static PolicyDao _instance=null;
	
	//Default private constructor
	private PolicyDao()
	{
		String tblCreateQuery = "create table IF NOT EXISTS Policies(id char(256) not null, "
				+ "firstName char(256) not null, "
				+ "lastName char(256) not null, "
				+ "sDate date not null, "
				+ "remarks char(256) not null, "
				+ "insType char(256) not null)";
		try {
            PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(tblCreateQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.GetInstance().log(e.getMessage());
        }
	}
	
	public static PolicyDao GetInstance()
	{
		if(_instance==null)
		{
			_instance= new PolicyDao();
			return _instance;
		}
		else 
		{
			return _instance;
		}
	}
	@Override
	public void insert(Policy obj) throws SQLException 
	{
		//TODO: check ID duplicate exception type to catch and show message
		 String insertSQL = "INSERT INTO Policies (id, firstName, lastName, sDate, remarks, insType) VALUES (?, ?, ?, ?, ?, ?)";
	     PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(insertSQL);
	     preparedStatement.setString(1, obj.id);
	     preparedStatement.setString(2, obj.firstName);
	     preparedStatement.setString(3, obj.lastName);
	     preparedStatement.setDate(4, obj.getStartDay());
	     preparedStatement.setString(5, obj.remarks);
	     preparedStatement.setString(6, obj.type);
	     preparedStatement.executeUpdate();
	}
	

	@Override
	public void update(Policy obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Policy obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Policy getByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Policy> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
