package scheduler.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import scheduler.core.models.entities.Organization;
import sun.misc.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c113554 on 05/11/2016.
 */
public class OrganizationListResource extends ResourceSupport {

    private List<OrganizationResource> organizations;

    public List<OrganizationResource> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrganizationResource> organizations) {
        this.organizations = organizations;
    }
}
