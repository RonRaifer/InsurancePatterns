package infrastructures.Factories;

import java.sql.Date;

public class Policy {
		public String pID;
		public String id;
	    public String firstName;
	    public String lastName;
	    public Long startDay;
	    public String type;
	    public String remarks;
	    
	    public String getpID() {
	        return pID;
	    }
	    
	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

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
			return firstName +" " + lastName + " Type: "+ type + " Id: " + id;
	    	
	    }
}
