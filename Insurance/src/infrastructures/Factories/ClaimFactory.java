package infrastructures.Factories;

public class ClaimFactory implements IClaimFactory{
	@Override
	public Claim create(Double amount, String statusStr, String remarks, String policyId)
	{
		Claim claim = new Claim();
		claim.policyId = policyId;
		claim.status = statusStr;
		claim.amount = amount;
		claim.remarks = remarks;
		claim.id = java.util.UUID.randomUUID().toString();
        return claim;
    }
}
