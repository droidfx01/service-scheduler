package scheduler.rest.resources;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.joda.time.DateTime;
import org.springframework.hateoas.ResourceSupport;
import scheduler.core.models.entities.OrganizationSchedule;
import scheduler.core.services.exceptions.RequestBodyDidNotParseException;
import scheduler.rest.exceptions.BadRequestException;
import scheduler.rest.util.OrganizationScheduleDeserializer;

import static scheduler.core.models.entities.OrganizationSchedule.dateFormat;
import static scheduler.core.models.entities.OrganizationSchedule.timeFormat;

/**
 * Created by C113554 on 05/13/2016.
 */
@JsonDeserialize(using = OrganizationScheduleDeserializer.class)
public class OrganizationScheduleResource extends ResourceSupport {
    private String scheduleDate;
    private String scheduleOpen;
    private String scheduleClose;
    private String isOpen;

    public OrganizationScheduleResource() {
    }

    public OrganizationScheduleResource(String scheduleDate, String isOpen, String scheduleOpen, String scheduleClose) {
        this.scheduleDate = scheduleDate;
        this.isOpen = isOpen;
        this.scheduleOpen = scheduleOpen;
        this.scheduleClose = scheduleClose;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getScheduleDate(){return scheduleDate;}

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleOpen() {
        if(scheduleOpen != null)
           return scheduleOpen;
        else
            return "null";
    }

    public void setScheduleOpen(String scheduleOpen) {
        if(scheduleOpen != null)
            this.scheduleOpen = scheduleOpen;
        else
            scheduleOpen = "null";
    }

    public String getScheduleClose() {
        if(scheduleClose != null)
            return scheduleClose;
        else
            return "null";
    }

    public void setScheduleClose(String scheduleClose) {
        if(scheduleClose != null)
            this.scheduleClose = scheduleClose;
        else
            scheduleClose = "null";
    }

    public OrganizationSchedule toSchedule() {
        try{
            boolean open;
            OrganizationSchedule schedule = new OrganizationSchedule();
            schedule.setScheduleDate(dateFormat.parseDateTime(this.getScheduleDate()));
            if(this.isOpen.equals("true")) {
                open = true;
                schedule.setScheduleOpen(timeFormat.parseDateTime(this.getScheduleOpen()));
                schedule.setScheduleClose(timeFormat.parseDateTime(this.getScheduleClose()));
            }
            else if(this.isOpen.equals("false")) {
                open = false;
                schedule.setScheduleOpen(null);
                schedule.setScheduleClose(null);
            }
            else{
                open = false;
                throw new RequestBodyDidNotParseException();
            }
            schedule.setOrgOpen(open);

            return schedule;
        }catch (RequestBodyDidNotParseException exception){
            throw new BadRequestException(exception);
        }
    }
}
