package scheduler.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scheduler.core.models.entities.OrganizationSchedule;
import scheduler.core.services.exceptions.DateDidNotParseException;
import scheduler.rest.exceptions.BadRequestException;
import scheduler.rest.mvc.OrganizationController;
import scheduler.rest.mvc.OrganizationScheduleController;
import scheduler.rest.resources.OrganizationScheduleResource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static scheduler.core.models.entities.OrganizationSchedule.dateFormat;
import static scheduler.core.models.entities.OrganizationSchedule.timeFormat;

/**
 * Created by C113554 on 05/13/2016.
 */
public class OrganizationScheduleResourceAsm extends ResourceAssemblerSupport<OrganizationSchedule, OrganizationScheduleResource> {
    public OrganizationScheduleResourceAsm() {
        super(OrganizationScheduleController.class, OrganizationScheduleResource.class);
    }

    @Override
    public OrganizationScheduleResource toResource(OrganizationSchedule organizationSchedule) {
        OrganizationScheduleResource organizationScheduleResource = new OrganizationScheduleResource();
        try {
            organizationScheduleResource.setScheduleDate(organizationSchedule.getScheduleDateString());
        }catch(DateDidNotParseException exception){
            throw new BadRequestException();
        }
        String open;
        if(organizationSchedule.getOrgOpen() == true) {
            open = "true";
            organizationScheduleResource.setScheduleOpen(organizationSchedule.getScheduleOpen());
            organizationScheduleResource.setScheduleClose(organizationSchedule.getScheduleClose());
        }
        else {
            open = "false";
            organizationScheduleResource.setScheduleOpen(null);
            organizationScheduleResource.setScheduleClose(null);
        }
        organizationScheduleResource.setIsOpen(open);
        organizationScheduleResource.add(linkTo(OrganizationController.class).slash(organizationSchedule.getOrganization().getOrgId()).slash("schedules").slash("?date=" + organizationScheduleResource.getScheduleDate()).withSelfRel());
        organizationScheduleResource.add(linkTo(OrganizationController.class).slash(organizationSchedule.getOrganization().getOrgId()).withRel("organization"));
        organizationScheduleResource.add(linkTo(OrganizationController.class).slash(organizationSchedule.getOrganization().getOrgId()).slash("schedule").withRel("schedule"));
        return organizationScheduleResource;
    }
}
