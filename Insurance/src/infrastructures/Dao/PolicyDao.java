package infrastructures.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String tblCreateQuery = "create table IF NOT EXISTS Policies(pID INTEGER PRIMARY KEY AUTOINCREMENT, id char(256) not null, "
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
		
	}

	
	//UNCHECKED METHOD, NEED PROPER SCREEN FOR IT
	@Override
	public void delete(Policy obj) throws SQLException {		
		String getAllPolicies = "DELETE FROM Policies WHERE pID = ?";
		try 
	    {
			PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(getAllPolicies);
			preparedStatement.setString(1, obj.pID);
			preparedStatement.executeUpdate();
		} 
	    catch (SQLException e) 
	    {
			e.printStackTrace();
			Logger.GetInstance().log("Unable to delete the Following Policy:\n"
					+ "First name: "+ obj.firstName+"\n"
					+ "Last name: "+obj.lastName+"\n"
					+ "ID: "+obj.pID+"\n"
					+ "Insurance type: "+obj.type+"\n"
					+ "Remarks: "+obj.remarks+"\n");
		}

		Logger.GetInstance().log("Successfully deleted the Following Policy:\n"
				+ "Policy ID: "+obj.pID+"\n"
				+ "First name: "+ obj.firstName+"\n"
				+ "Last name: "+obj.lastName+"\n"
				+ "Insurance type: "+obj.type+"\n"
				+ "Remarks: "+obj.remarks+"\n");

	}

	@Override
	public List<Policy> getByID(String id) 
	{
		String getAllPolicies = "SELECT * FROM Policies WHERE id = ?";
		List<Policy> pl = new ArrayList<Policy>();
	    try 
	    {
			PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(getAllPolicies);
			preparedStatement.setString(1, (String)id);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				Policy p = new Policy();
				p.pID = rs.getString(1);
				p.id = rs.getString(2);	
				p.firstName = rs.getString(3);
				p.lastName = rs.getString(4);
				p.startDay = rs.getLong(5);
				p.remarks = rs.getString(6);
				p.type = rs.getString(7);
				pl.add(p);
			}
		} 
	    catch (SQLException e) 
	    {
			e.printStackTrace();
		}
	     
	    if(pl.isEmpty())
	    	return null;
	    else return pl;
	}

	@Override
	public List<Policy> getAll() 
	{
		String getAllPolicies = "SELECT * FROM Policies";
		List<Policy> pl = new ArrayList<Policy>();
	    try 
	    {
			PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(getAllPolicies);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				Policy p = new Policy();
				p.pID = rs.getString(1);
				p.id = rs.getString(2);	
				p.firstName = rs.getString(3);
				p.lastName = rs.getString(4);
				p.startDay = rs.getLong(5);
				p.remarks = rs.getString(6);
				p.type = rs.getString(7);
				pl.add(p);
			}
		} 
	    catch (SQLException e) 
	    {
			e.printStackTrace();
		}
	     
	    if(pl.isEmpty())
	    	return null;
	    else return pl;
	}


}
