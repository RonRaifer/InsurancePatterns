package infrastructures.Factories;

import java.sql.SQLException;

import infrastructures.Dao.ClaimDao;
import infrastructures.Dao.PolicyDao;
import infrastructures.Logger.Logger;

public class ClaimFactory implements IClaimFactory{
	@Override
	public Claim create(String firstName, String lastName, String ID, Long startDay, String remarks)
	{
		Claim claim = new Claim();
		claim.firstName = firstName;
		claim.lastName = lastName;
		claim.startDay = startDay;
		claim.remarks = remarks;
		
		//DAO creation and DB update
        try {
			ClaimDao.GetInstance().insert(claim);
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.GetInstance().log(e.getMessage());
		}
        Logger.GetInstance().log("customer: " + claim.firstName + " " + claim.lastName + " claimed: "+ claim.remarks);
		
        return claim;
    }
}
