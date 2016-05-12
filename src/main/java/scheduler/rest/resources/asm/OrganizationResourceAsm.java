package scheduler.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scheduler.core.models.entities.Organization;
import scheduler.rest.mvc.OrganizationController;
import scheduler.rest.resources.OrganizationResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by c113554 on 05/10/2016.
 */
public class OrganizationResourceAsm extends ResourceAssemblerSupport<Organization, OrganizationResource>{
    public OrganizationResourceAsm() {
        super(OrganizationController.class, OrganizationResource.class);
    }

    @Override
    public OrganizationResource toResource(Organization organization) {
        OrganizationResource resource = new OrganizationResource();
        resource.setOrgId(organization.getOrgId());
        resource.setOrgName(organization.getOrgName());
        resource.setOrgDescription(organization.getOrgDescription());
        resource.setOrgAddress1(organization.getOrgAddress1());
        resource.setOrgAddress2(organization.getOrgAddress2());
        resource.setOrgCity(organization.getOrgCity());
        resource.setOrgState(organization.getOrgState());
        resource.setOrgZip(organization.getOrgZip());
        resource.setOrgCountry(organization.getOrgCountry());
        Link link = linkTo(OrganizationController.class).slash(organization.getOrgId()).withSelfRel();
        Link link1 = linkTo(OrganizationController.class).slash(organization.getOrgId()).slash("employees").withRel("employees");
        resource.add(link);
        resource.add(link1);
        return resource;
    }
}
