package infrastructures.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import infrastructures.Logger.Logger;
import models.Claim;
import models.Policy;

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
		String updateSQL = "UPDATE Claims SET amount=?, remarks=?, status=? WHERE cID=?";
        PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(updateSQL);
	    preparedStatement.setString(1, obj.amount);
	    preparedStatement.setString(2, obj.remarks);
	    preparedStatement.setString(3, obj.status);
	    preparedStatement.setString(4, obj.cID);
        preparedStatement.executeUpdate();
	}

	@Override
	public void delete(Claim obj) throws SQLException {
		String deleteClaim = "DELETE FROM Claims WHERE cID = ?";
		try 
	    {
			PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(deleteClaim);
			preparedStatement.setString(1, obj.cID);
			preparedStatement.executeUpdate();
		} 
	    catch (SQLException e) 
	    {
			e.printStackTrace();
			Logger.GetInstance().log("Unable to delete the Following Claim:\n"
					+ "Claim ID: "+ obj.cID+"\n"
					+ "Policy ID: "+obj.pID+"\n"
					+ "Amount: "+obj.amount+"\n"
					+ "Remarks: "+obj.remarks+"\n");
		}

		Logger.GetInstance().log("Successfully deleted the Following Claim:\n"
				+ "Claim ID: "+ obj.cID+"\n"
				+ "Policy ID: "+obj.pID+"\n"
				+ "Amount: "+obj.amount+"\n"
				+ "Remarks: "+obj.remarks+"\n");
	}
	
	@Override
	public List<Claim> getByID(String id) {
		String getAllClaims = "SELECT * FROM Claims WHERE pID = ?";
		List<Claim> cl = new ArrayList<Claim>();
	    try 
	    {
			PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(getAllClaims);
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				Claim c = new Claim();
				c.cID = rs.getString(1);
				c.pID = rs.getString(2);
				c.amount = rs.getString(3);	
				c.dateSued = rs.getLong(4);
				c.remarks = rs.getString(5);
				c.status = rs.getString(6);
				cl.add(c);
			}
		} 
	    catch (SQLException e) 
	    {
			e.printStackTrace();
		}
	     
	    if(cl.isEmpty())
	    	return null;
	    else return cl;
	}
	
	public List<Claim> getByIDaddType(Policy p) {
		String getAllClaims = "SELECT * FROM Claims WHERE pID = ?";
		List<Claim> cl = new ArrayList<Claim>();
	    try 
	    {
			PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(getAllClaims);
			preparedStatement.setString(1, p.pID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				Claim c = new Claim();
				c.cID = rs.getString(1);
				c.pID = rs.getString(2);
				c.amount = rs.getString(3);	
				c.dateSued = rs.getLong(4);
				c.remarks = rs.getString(5);
				c.status = rs.getString(6);
				c.type = p.type;
				cl.add(c);
			}
		} 
	    catch (SQLException e) 
	    {
			e.printStackTrace();
		}
	     
	    if(cl.isEmpty())
	    	return null;
	    else return cl;
	}
	@Override
	public List<Claim> getAll() {
		String getAllClaims = "SELECT * FROM Claims";
		List<Claim> cl = new ArrayList<Claim>();
	    try 
	    {
			PreparedStatement preparedStatement = DBConnection.GetDBConnection().prepareStatement(getAllClaims);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				Claim c = new Claim();
				c.cID = rs.getString(1);
				c.pID = rs.getString(2);
				c.amount = rs.getString(3);	
				c.dateSued = rs.getLong(4);
				c.remarks = rs.getString(5);
				c.status = rs.getString(6);
				cl.add(c);
			}
		} 
	    catch (SQLException e) 
	    {
			e.printStackTrace();
		}
	     
	    if(cl.isEmpty())
	    	return null;
	    else return cl;
	}

}
