package infrastructures.Factories;

public interface IPolicyFactory {
    Policy create(String type, String firstName, String lastName, Long startDay, String remarks);
}