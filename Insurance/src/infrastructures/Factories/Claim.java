package infrastructures.Factories;

import java.sql.Date;

public class Claim {
	public String cID;
	public String pID;
    public String amount;
    public Long dateSued;
    public String remarks;
	public String status;
    
    public String getcID() {
		return cID;
	}
	public void setcID(String cID) {
		this.cID = cID;
	}
	public String getpID() {
		return pID;
	}
	public void setId(String pID) {
		this.pID = pID;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getDateSued() {
        return new Date(dateSued);
    }

    public void getDateSued(Date dateSued) {
        this.dateSued = dateSued.getTime();
    }
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    @Override
    public String toString() {
		return "Sue ID: "+ cID +" Policy: " + pID +" Sue Amount: "+ amount;
    	
    }
}
