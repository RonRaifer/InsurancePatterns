package infrastructures.Factories;

public class Claim {
	public String id;
	public String policyId;
	public String remarks;
	public String status;
	public Double amount;
	
	 public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getPolicyId()
	{
		return policyId;
	}

	public void setPolicyId(String policyId)
	{
		this.policyId = policyId;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	@Override
	    public String toString() {
			return "Status: " + status + " ClaimId: "+ id + " PolicyId: " + policyId;
	    	
	    }
}
