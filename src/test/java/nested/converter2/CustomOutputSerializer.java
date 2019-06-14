package nested.converter2;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import nested.output.OutGoingObject;

import java.io.IOException;
import java.util.Optional;

public class CustomOutputSerializer extends StdDeserializer<OutGoingObject> {


    private CustomOutputSerializer(Class<?> vc) {
        super(vc);
    }

    public CustomOutputSerializer() {
        this(null);
    }


    @Override
    public OutGoingObject deserialize(JsonParser jsonParser,
                                      DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        final OutGoingObject outGoingObject = new OutGoingObject();

        Optional.ofNullable(node.get("timeStamp")).ifPresent(jsonNode -> outGoingObject.setLastUpdate(jsonNode.asText()));
        Optional.ofNullable(node.get("type")).ifPresent(jsonNode -> outGoingObject.setType(jsonNode.asText()));
        Optional.ofNullable(node.get("nestedUser")).ifPresent(nestedUser -> {
            Optional.ofNullable(nestedUser.get("lastName")).ifPresent(lastName -> outGoingObject.setUserName(lastName.asText()));
            Optional.ofNullable(nestedUser.get("address")).ifPresent(address -> {
                Optional.ofNullable(address.get("address")).ifPresent(addr -> outGoingObject.setUserAddress(addr.asText()));
            });
        });

        return outGoingObject;
    }
}
