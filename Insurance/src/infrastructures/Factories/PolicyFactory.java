package infrastructures.Factories;

import java.sql.SQLException;

import infrastructures.Dao.PolicyDao;
import infrastructures.Logger.Logger;
import java.sql.Date;

public class PolicyFactory implements IPolicyFactory{

	@Override
	public Policy create(String type, String firstName, String lastName, String ID, Long startDay, String remarks) {
        //logic level creation
    	Policy policy = new Policy();
        policy.type = type;
        policy.firstName = firstName;
        policy.lastName = lastName;
        policy.startDay = startDay;
        policy.remarks = remarks;
        policy.id = ID;
        
        //DAO creation and DB update
        try {
			PolicyDao.GetInstance().insert(policy);
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.GetInstance().log(e.getMessage());
			return null;
		}
        Logger.GetInstance().log("customer: " + policy.firstName + " " + policy.lastName + ", ID: " + policy.id + " joined " + type + " insurance. starting Date: ");
        return policy;
	}

}
