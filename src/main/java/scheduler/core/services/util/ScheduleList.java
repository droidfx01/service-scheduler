package scheduler.core.services.util;

import scheduler.core.models.entities.OrganizationSchedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C113554 on 05/13/2016.
 */
public class ScheduleList {
    private List<OrganizationSchedule> schedules = new ArrayList<>();

    public List<OrganizationSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<OrganizationSchedule> schedules) {
        this.schedules = schedules;
    }
}
