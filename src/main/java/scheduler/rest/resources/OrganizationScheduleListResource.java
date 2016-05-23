package scheduler.rest.resources;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.hateoas.ResourceSupport;
import scheduler.core.models.entities.OrganizationSchedule;
import scheduler.core.services.util.OrganizationScheduleList;
import scheduler.rest.util.OrganizationScheduleListDeserializer;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.L;

/**
 * Created by C113554 on 05/13/2016.
 */
@JsonDeserialize(using = OrganizationScheduleListDeserializer.class)
public class OrganizationScheduleListResource extends ResourceSupport {
    private List<OrganizationScheduleResource> scheduleResourceList = new ArrayList<>();

    public List<OrganizationScheduleResource> getScheduleResourceList() {
        return scheduleResourceList;
    }

    public OrganizationScheduleListResource() {

    }
    public OrganizationScheduleListResource(List<OrganizationScheduleResource> scheduleResourceList) {
        this.scheduleResourceList = scheduleResourceList;
    }

    public void setScheduleResourceList(List<OrganizationScheduleResource> scheduleResourceList) {
        this.scheduleResourceList = scheduleResourceList;
    }

    public OrganizationScheduleList toScheduleList(){
        OrganizationScheduleList scheduleList = new OrganizationScheduleList();
        List<OrganizationSchedule> schedules = new ArrayList<>();
        for (OrganizationScheduleResource res : this.scheduleResourceList){
            OrganizationSchedule schedule = new OrganizationSchedule();
            schedule = res.toSchedule();
            schedules.add(schedule);
        }
        scheduleList.setSchedules(schedules);
        return scheduleList;
    }
}
