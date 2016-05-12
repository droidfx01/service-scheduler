package scheduler.core.models.entities;

import org.omg.CORBA.Object;

/**
 * Created by c113554 on 05/10/2016.
 */
public class Organization{

    private Long orgId;
    private String orgName;
    private String orgDescription;
    private String orgAddress1;
    private String orgAddress2;
    private String orgCity;
    private String orgState;
    private int orgZip;
    private String orgCountry;

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
}
