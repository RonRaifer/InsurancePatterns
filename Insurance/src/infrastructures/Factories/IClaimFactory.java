package infrastructures.Factories;

import models.Claim;

public interface IClaimFactory 
{
	public Claim create(String cID, String pID, String amount, String staus, Long dateSued, String remarks);
}