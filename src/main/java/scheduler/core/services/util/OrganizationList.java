package scheduler.core.services.util;

import scheduler.core.models.entities.Organization;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c113554 on 05/10/2016.
 */
public class OrganizationList {
    private List<Organization> organizations = new ArrayList<Organization>();

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
}
