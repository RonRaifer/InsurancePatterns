package infrastructures.Factories;

import java.sql.Date;

public class Claim {
	public String cID;
    public String firstName;
    public String lastName;
    public Long startDay;
    public String remarks;
	
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getStartDay() {
        return new Date(startDay);
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay.getTime();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    @Override
    public String toString() {
		return firstName +" " + lastName +" "+ remarks;
    	
    }
}
