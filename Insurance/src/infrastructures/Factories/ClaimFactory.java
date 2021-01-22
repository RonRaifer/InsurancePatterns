package infrastructures.Factories;

import java.sql.SQLException;

import infrastructures.Dao.ClaimDao;
import infrastructures.Dao.PolicyDao;
import infrastructures.Logger.Logger;

public class ClaimFactory implements IClaimFactory{
	@Override
	public Claim create(String cID, String pID, String amount, String status, Long dateSued, String remarks)
	{
		Claim claim = new Claim();
		claim.cID = cID;
		claim.pID = pID;
		claim.amount = amount;
		claim.status = status;
		claim.dateSued = dateSued;
		claim.remarks = remarks;
		
		//DAO creation and DB update
        try {
			ClaimDao.GetInstance().insert(claim);
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.GetInstance().log(e.getMessage());
			return null;
		}
        Logger.GetInstance().log("Sue ID: " + claim.cID + " For policy ID: " + claim.pID + " claimed: "+ claim.remarks);
		
        return claim;
    }
}
