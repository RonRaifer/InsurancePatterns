package models;

import java.sql.Date;

public class Claim {
	public String cID;
	public String pID;
	public String amount;
    public Long dateSued;
    public String remarks;
	public String status;
    public String type;
    
    public String getPID() {
		return pID;
	}
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getcID() {
		return cID;
	}
	public void setcID(String cID) {
		this.cID = cID;
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
