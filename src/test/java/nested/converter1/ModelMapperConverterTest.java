package nested.converter1;

import nested.input.IncomingObject;
import nested.output.OutGoingObject;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import static org.junit.Assert.assertEquals;


/**
 * This is using modal mapper
 * And having detail of incoming/outgoing object
 * <p>
 * so just need to have a customized propertyMap config
 */
public class ModelMapperConverterTest {

    @Test
    void testConvert() {
        final IncomingObject incomingObject =
                new IncomingObject("type", "timestamp", "firstName", "lastName", "address");
        final OutGoingObject convert = convert(incomingObject);

        assertEquals("OutGoingObject(userName=firstName, userAddress=address, type=type, lastUpdate=timestamp)",
                convert.toString());
    }

    OutGoingObject convert(IncomingObject incomingObject) {
        ModelMapper modelMapper = new ModelMapper();

        PropertyMap<IncomingObject, OutGoingObject> objectMap = new PropertyMap<IncomingObject, OutGoingObject>() {
            protected void configure() {
                map().setType(source.getType());
                map().setLastUpdate(source.getTimeStamp());
                map().setUserName(source.getNestedUser().getFirstName());
                map().setUserAddress(source.getNestedUser().getAddress().getAddr());
            }
        };

        modelMapper.addMappings(objectMap);

        return modelMapper.map(incomingObject, OutGoingObject.class);
    }
}
