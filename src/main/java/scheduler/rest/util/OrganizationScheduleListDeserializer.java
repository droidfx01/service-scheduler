package scheduler.rest.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import scheduler.rest.resources.OrganizationScheduleListResource;
import scheduler.rest.resources.OrganizationScheduleResource;

import java.awt.color.ICC_ProfileRGB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static scheduler.core.models.entities.OrganizationSchedule.dateFormat;
import static scheduler.core.models.entities.OrganizationSchedule.timeFormat;

/**
 * Created by C113554 on 05/20/2016.
 */
public class OrganizationScheduleListDeserializer extends JsonDeserializer<OrganizationScheduleListResource> {
    @Override
    public OrganizationScheduleListResource deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        OrganizationScheduleListResource resource = new OrganizationScheduleListResource();
//        ObjectCodec oc = jsonParser.getCodec();
//        JsonNode node = oc.readTree(jsonParser);
//        JsonNodeType node1 = node.getNodeType();
        final JsonNode arrNode = new ObjectMapper().readTree(jsonParser);
        List<OrganizationScheduleResource> list = new ArrayList<>();
        int i = 0;
        for(JsonNode node : arrNode){
            OrganizationScheduleResource resource = new OrganizationScheduleResource();
            resource.setScheduleDate(node.get("scheduleDate").asText());
            String tes = node.get("scheduleOpen").asText();
            resource.setIsOpen(node.get("isOpen").asText());
            resource.setScheduleOpen(node.get("scheduleOpen").asText());
            resource.setScheduleClose(node.get("scheduleClose").asText());
            list.add(resource);
        }
        return new OrganizationScheduleListResource(list);
    }
}
