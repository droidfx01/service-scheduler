package scheduler.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import scheduler.core.models.entities.Organization;
import scheduler.core.models.entities.OrganizationSchedule;

import java.sql.Time;
import java.util.Date;

/**
 * Created by C113554 on 05/13/2016.
 */
public class OrganizationScheduleResource extends ResourceSupport{
    private Date scheduleDate;
    private Time scheduleOpen;
    private Time scheduleClose;

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Time getScheduleOpen() {
        return scheduleOpen;
    }

    public void setScheduleOpen(Time scheduleOpen) {
        this.scheduleOpen = scheduleOpen;
    }

    public Time getScheduleClose() {
        return scheduleClose;
    }

    public void setScheduleClose(Time scheduleClose) {
        this.scheduleClose = scheduleClose;
    }

    public OrganizationSchedule toSchedule(){
        OrganizationSchedule schedule = new OrganizationSchedule();
        schedule.setScheduleDate(this.getScheduleDate());
        schedule.setScheduleOpen(this.getScheduleOpen());
        schedule.setScheduleClose(this.getScheduleClose());
        return schedule;
    }
}
