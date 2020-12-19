package infrastructures.Factories;

public class PolicyFactory implements IPolicyFactory{

    public Policy create(String type, String firstName, String lastName, Long startDay, String remarks) {
        Policy policy = new Policy();
        policy.type = type;
        policy.firstName = firstName;
        policy.lastName = lastName;
        policy.startDay = startDay;
        policy.remarks = remarks;
        policy.id = java.util.UUID.randomUUID().toString();
        return policy;
    }

}
