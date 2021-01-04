package infrastructures.Factories;

import java.sql.Date;

public interface IPolicyFactory 
{
    public Policy create(String type, String firstName, String lastName, String ID, Long startDay, String remarks);
}