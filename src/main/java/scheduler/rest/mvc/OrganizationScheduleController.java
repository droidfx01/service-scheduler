package scheduler.rest.mvc;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scheduler.core.models.entities.OrganizationSchedule;
import scheduler.core.services.OrganizationScheduleService;
import scheduler.core.services.exceptions.ScheduleNotFoundException;
import scheduler.rest.exceptions.ConflictException;
import scheduler.rest.exceptions.NotFoundException;
import scheduler.rest.resources.OrganizationScheduleResource;
import scheduler.rest.resources.asm.OrganizationScheduleResourceAsm;

import java.net.URI;
import java.util.Date;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by C113554 on 05/13/2016.
 */
@Controller
@RequestMapping(value = "/rest/organizations/{orgId}/schedule")
public class OrganizationScheduleController {
    private OrganizationScheduleService service;

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public ResponseEntity<OrganizationScheduleResource> findOrgSchedule(@PathVariable Long orgId, @PathVariable Date date)
    {
        try {
            OrganizationSchedule schedule = service.findOrgSchedule(orgId, date);
            OrganizationScheduleResource resource = new OrganizationScheduleResourceAsm().toResource(schedule);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<OrganizationScheduleResource>(resource, headers, HttpStatus.OK);
        } catch (ScheduleNotFoundException exception) {
            throw new NotFoundException(exception);
        }

    }
    @RequestMapping(value = "/{date}", method = RequestMethod.DELETE)
    public ResponseEntity<OrganizationScheduleResource> deleteOrgSchedule(@PathVariable Long orgId, @PathVariable Date date)
    {
        try {
            OrganizationSchedule schedule = service.deleteOrgSchedule(orgId, date);
            OrganizationScheduleResource resource = new OrganizationScheduleResourceAsm().toResource(schedule);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("organization").getHref()));
            return new ResponseEntity<OrganizationScheduleResource>(resource, headers, HttpStatus.OK);
        } catch (ScheduleNotFoundException exception) {
            throw new NotFoundException(exception);
        }

    }
    @RequestMapping(value = "/{date}", method = RequestMethod.PUT)
    public ResponseEntity<OrganizationScheduleResource> updateOrgSchedule(@PathVariable Long orgId, @PathVariable Date date, @RequestBody OrganizationScheduleResource sentSchedule)
    {
        try {
            OrganizationSchedule schedule = service.updateOrgSchedule(orgId,date,sentSchedule.toSchedule());
            OrganizationScheduleResource resource = new OrganizationScheduleResourceAsm().toResource(schedule);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<OrganizationScheduleResource>(resource, headers, HttpStatus.OK);
        } catch (ScheduleNotFoundException exception) {
            throw new ConflictException(exception);
        }

    }

}
