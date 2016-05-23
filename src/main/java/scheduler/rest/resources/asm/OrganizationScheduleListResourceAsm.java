package scheduler.rest.resources.asm;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scheduler.core.models.entities.OrganizationSchedule;
import scheduler.core.services.util.OrganizationScheduleList;
import scheduler.rest.mvc.OrganizationController;
import scheduler.rest.mvc.OrganizationScheduleController;
import scheduler.rest.resources.OrganizationListResource;
import scheduler.rest.resources.OrganizationResource;
import scheduler.rest.resources.OrganizationScheduleListResource;
import scheduler.rest.resources.OrganizationScheduleResource;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by C113554 on 05/13/2016.
 */
public class OrganizationScheduleListResourceAsm extends ResourceAssemblerSupport<OrganizationScheduleList, OrganizationScheduleListResource>{

    public OrganizationScheduleListResourceAsm() {
        super(OrganizationScheduleController.class, OrganizationScheduleListResource.class);
    }

    @Override
    public OrganizationScheduleListResource toResource(OrganizationScheduleList list) {
        List<OrganizationScheduleResource> resources = new OrganizationScheduleResourceAsm().toResources(list.getSchedules());
        OrganizationScheduleListResource listResource = new OrganizationScheduleListResource();
        listResource.setScheduleResourceList(resources);
        listResource.add(linkTo(OrganizationScheduleController.class, list.getSchedules().get(0).getOrganization().getOrgId()).slash("?startDate=" + list.getSchedules().get(0).getScheduleDateString() + "&endDate=" + list.getSchedules().get((list.count()- 1)).getScheduleDateString()).withSelfRel());
        listResource.add(linkTo(OrganizationController.class).slash(  list.getSchedules().get(0).getOrganization().getOrgId()).withRel("organization"));
        listResource.add(linkTo(OrganizationScheduleController.class, list.getSchedules().get(0).getOrganization().getOrgId()).withRel("schedule"));
        return listResource;
    }
}
