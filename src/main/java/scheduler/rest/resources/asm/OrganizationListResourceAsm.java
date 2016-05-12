package scheduler.rest.resources.asm;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scheduler.core.services.util.OrganizationList;
import scheduler.rest.mvc.OrganizationController;
import scheduler.rest.resources.OrganizationListResource;
import scheduler.rest.resources.OrganizationResource;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by c113554 on 05/11/2016.
 */
public class OrganizationListResourceAsm extends ResourceAssemblerSupport<OrganizationList, OrganizationListResource> {

    public OrganizationListResourceAsm() {
        super(OrganizationController.class, OrganizationListResource.class);
    }

    @Override
    public OrganizationListResource toResource(OrganizationList list) {
        List<OrganizationResource> resources = new OrganizationResourceAsm().toResources(list.getOrganizations());
        OrganizationListResource listResource = new OrganizationListResource();
        listResource.setOrganizations(resources);
        listResource.add(linkTo(methodOn(OrganizationController.class).getAllOrganizations()).withSelfRel());
        return listResource;
    }
}
