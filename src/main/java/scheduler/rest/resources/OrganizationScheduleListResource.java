package scheduler.rest.resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C113554 on 05/13/2016.
 */
public class OrganizationScheduleListResource {
    private List<OrganizationScheduleResource> scheduleResourceList = new ArrayList<>();

    public List<OrganizationScheduleResource> getScheduleResourceList() {
        return scheduleResourceList;
    }

    public void setScheduleResourceList(List<OrganizationScheduleResource> scheduleResourceList) {
        this.scheduleResourceList = scheduleResourceList;
    }
}
