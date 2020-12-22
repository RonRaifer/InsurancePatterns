package infrastructures.Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import infrastructures.Factories.IFactory;
import infrastructures.Logger.Logger;

public class PolicyDao extends InsuranceDao<PolicyDao>{

	public PolicyDao(Logger logger, IFactory<PolicyDao> factory) {
		super(logger, factory);
		String tblCreateQuery = "create table IF NOT EXISTS Policies(id char(256) not null constraint users_pk primary key, policyId char(256) not null,firstName char(256) not null, lastName char(256) not null, sDate date not null, remarks char(256) not null, insType char(256) not null)";
        try {
        	
            PreparedStatement preparedStatement = _conn.prepareStatement(tblCreateQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(e.getMessage());
        }
	}

	@Override
	public void insert(PolicyDao obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PolicyDao obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PolicyDao obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
