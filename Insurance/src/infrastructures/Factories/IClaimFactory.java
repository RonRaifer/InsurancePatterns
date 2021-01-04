package infrastructures.Factories;

public interface IClaimFactory 
{
	public Claim create(String firstName, String lastName, String ID, Long startDay, String remarks);
}
