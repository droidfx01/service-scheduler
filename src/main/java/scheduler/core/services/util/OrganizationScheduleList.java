package scheduler.core.services.util;

import scheduler.core.models.entities.Organization;
import scheduler.core.models.entities.OrganizationSchedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by C113554 on 05/16/2016.
 */
public class OrganizationScheduleList {
    private List<OrganizationSchedule> schedules = new ArrayList<>();
    public List<OrganizationSchedule> getSchedules() {
        return schedules;
    }
    public void setSchedules(List<OrganizationSchedule> schedules) {
        Collections.sort(schedules, new DateComparator());
        this.schedules = schedules;
    }
    public int count(){
        return schedules.size();
    }
}

class DateComparator implements Comparator<OrganizationSchedule>{
    @Override
    public int compare(OrganizationSchedule o1, OrganizationSchedule o2) {
        String date1 = o1.getScheduleDateString();
        String date2 = o2.getScheduleDateString();
        return date1.compareTo(date2);
    }
}
