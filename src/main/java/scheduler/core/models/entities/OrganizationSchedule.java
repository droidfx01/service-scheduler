package scheduler.core.models.entities;

import com.sun.istack.internal.Nullable;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by C113554 on 05/13/2016.
 */
public class OrganizationSchedule {
    //public static DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    public static DateTimeFormatter dateFormat = DateTimeFormat.forPattern("yyyyMMdd");
    public static DateTimeFormatter timeFormat = DateTimeFormat.forPattern("HH:mm:ss");

    private Organization organization;
    private DateTime scheduleDate;
    private boolean isOrgOpen;
    private String sScheduleDate;
    @Nullable
    private DateTime scheduleOpen;
    @Nullable
    private DateTime scheduleClose;


    public boolean getOrgOpen(){
        return this.isOrgOpen;
    }

    public void setOrgOpen(boolean orgOpen) {
        this.isOrgOpen = orgOpen;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public DateTime getScheduleDate() {
        return scheduleDate;
    }

    public String getScheduleDateString(){
        return sScheduleDate;
    }

    public void setScheduleDate(DateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
        this.sScheduleDate = dateFormat.print(scheduleDate);
    }

    public String getScheduleOpen() {
        if(scheduleOpen != null)
            return timeFormat.print(scheduleOpen);
        else
            return "null";
    }

    public void setScheduleOpen(DateTime scheduleOpen) {
        this.scheduleOpen = scheduleOpen;
    }

    public String getScheduleClose() {
        if(scheduleClose != null)
            return timeFormat.print(scheduleClose);
        else
            return "null";
    }

    public void setScheduleClose(DateTime scheduleClose) {
            this.scheduleClose = scheduleClose;
    }
}
