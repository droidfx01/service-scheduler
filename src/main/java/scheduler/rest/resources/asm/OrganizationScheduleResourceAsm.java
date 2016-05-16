package scheduler.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scheduler.core.models.entities.OrganizationSchedule;
import scheduler.rest.mvc.OrganizationScheduleController;
import scheduler.rest.resources.OrganizationScheduleResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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
        organizationScheduleResource.setScheduleDate(organizationSchedule.getScheduleDate());
        organizationScheduleResource.setScheduleOpen(organizationSchedule.getScheduleOpen());
        organizationScheduleResource.setScheduleClose(organizationSchedule.getScheduleClose());
        organizationScheduleResource.add(linkTo(OrganizationScheduleController.class).withSelfRel());
        organizationScheduleResource.add(linkTo(OrganizationScheduleController.class).slash(organizationSchedule.getOrganization().getOrgId()).withRel("organization"));
        return organizationScheduleResource;
    }
}
