package scheduler.rest.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.corba.se.impl.orbutil.graph.Node;
import org.joda.time.DateTime;
import scheduler.core.services.exceptions.DateDidNotParseException;
import scheduler.rest.exceptions.BadRequestException;
import scheduler.rest.resources.OrganizationScheduleResource;
import static scheduler.core.models.entities.OrganizationSchedule.dateFormat;
import static scheduler.core.models.entities.OrganizationSchedule.timeFormat;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;

/**
 * Created by C113554 on 05/19/2016.
 */
public class OrganizationScheduleDeserializer extends JsonDeserializer<OrganizationScheduleResource> {

    @Override
    public OrganizationScheduleResource deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        try {
            ObjectCodec oc = jsonParser.getCodec();
            JsonNode node = oc.readTree(jsonParser);
            return new OrganizationScheduleResource(node.get("scheduleDate").textValue(), node.get("isOpen").textValue(), node.get("scheduleOpen").textValue(), node.get("scheduleClose").textValue());
        } catch (DateDidNotParseException exception) {
            throw new BadRequestException(exception);
        }
    }
}
