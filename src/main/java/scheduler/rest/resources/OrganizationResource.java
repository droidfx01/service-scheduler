package scheduler.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import scheduler.core.models.entities.Organization;

/**
 * Created by c113554 on 05/10/2016.
 */
public class OrganizationResource extends ResourceSupport{

    private Long orgId;
    private String orgName;
    private String orgDescription;
    private String orgAddress1;
    private String orgAddress2;
    private String orgCity;
    private String orgState;
    private String orgCountry;
    private int orgZip;

    public int getOrgZip() {
        return orgZip;
    }

    public void setOrgZip(int orgZip) {
        this.orgZip = orgZip;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public String getOrgAddress1() {
        return orgAddress1;
    }

    public void setOrgAddress1(String orgAddress1) {
        this.orgAddress1 = orgAddress1;
    }

    public String getOrgAddress2() {
        return orgAddress2;
    }

    public void setOrgAddress2(String orgAddress2) {
        this.orgAddress2 = orgAddress2;
    }

    public String getOrgCity() {
        return orgCity;
    }

    public void setOrgCity(String orgCity) {
        this.orgCity = orgCity;
    }

    public String getOrgState() {
        return orgState;
    }

    public void setOrgState(String orgState) {
        this.orgState = orgState;
    }

    public String getOrgCountry() {
        return orgCountry;
    }

    public void setOrgCountry(String orgCountry) {
        this.orgCountry = orgCountry;
    }

    public Organization toOrganization() {
        Organization organization = new Organization();
        organization.setOrgName(orgName);
        organization.setOrgDescription(orgDescription);
        organization.setOrgAddress1(orgAddress1);
        organization.setOrgAddress2(orgAddress2);
        organization.setOrgCity(orgCity);
        organization.setOrgState(orgState);
        organization.setOrgCountry(orgCountry);
        return organization;
    }
}
