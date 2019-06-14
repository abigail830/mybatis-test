package nested.input;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class NestedUser {
    String firstName;
    String lastName;
    Address address;
}
