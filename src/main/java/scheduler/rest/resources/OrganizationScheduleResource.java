package scheduler.rest.resources;

import java.sql.Time;
import java.util.Date;

/**
 * Created by C113554 on 05/13/2016.
 */
public class OrganizationScheduleResource {
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
}
