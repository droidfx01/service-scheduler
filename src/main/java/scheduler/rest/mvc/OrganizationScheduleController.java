package scheduler.rest.mvc;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import scheduler.core.models.entities.OrganizationSchedule;
import scheduler.rest.resources.OrganizationScheduleResource;

/**
 * Created by C113554 on 05/13/2016.
 */
@Controller
@RequestMapping(value = "/rest/organizations/{orgId}/schedule")
public class OrganizationScheduleController {

    public ResponseEntity<OrganizationScheduleResource> findOrgSchedule(){} //TODO: Populate this method
    public ResponseEntity<OrganizationScheduleResource> deleteOrgSchedule(){} //TODO: Populate this method
    public ResponseEntity<OrganizationScheduleResource> updateOrgSchedule(){} //TODO: Populate this method
    public ResponseEntity<OrganizationScheduleResource> createOrgSchedule(){} //TODO: Populate this method
}
