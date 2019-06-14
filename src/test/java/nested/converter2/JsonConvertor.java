package nested.converter2;

import com.fasterxml.jackson.databind.ObjectMapper;
import nested.output.OutGoingObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsonConvertor {

    JSONObject jsonObj;

    String json = "{\"type\":\"type\",\"timeStamp\":\"timestamp\",\"nestedUser\":{\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"address\":{\"address\":\"address\"}}}";

    @BeforeEach
    void setUp() throws JSONException {
        jsonObj = new JSONObject(json);
    }

    @Test
    void testJsonObject() throws IOException {
        System.out.println(jsonObj.toString());

        ObjectMapper objectMapper = new ObjectMapper();

//        Either using below way to reg, or using annotation @JsonDeserialize(using = CustomOutputSerializer.class)
//
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(OutGoingObject.class, new CustomOutputSerializer());
//        objectMapper.registerModule(module);

        final OutGoingObject out = objectMapper.readValue(json, OutGoingObject.class);
        System.out.println(out);

    }
}
