package nested.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class IncomingObject {

    String type;
    String timeStamp;
    NestedUser nestedUser;

    public IncomingObject(String type, String timeStamp, String firstName, String lastName, String address) {
        this.type = type;
        this.timeStamp = timeStamp;

        final Address address1 = new Address(address);
        this.nestedUser = new NestedUser(firstName, lastName, address1);
    }
}
