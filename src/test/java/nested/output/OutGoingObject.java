package nested.output;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import nested.converter2.CustomOutputSerializer;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@JsonDeserialize(using = CustomOutputSerializer.class)
public class OutGoingObject {

    String userName;

    String userAddress;

    String type;

    String lastUpdate;
}
