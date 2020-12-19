package infrastructures.Factories;

public interface IClaimFactory 
{
	public Claim create(Double amount, String statusStr,String remarks,String policyId);
}
