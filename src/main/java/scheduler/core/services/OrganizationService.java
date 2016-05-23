package scheduler.core.services;

import scheduler.core.models.entities.Employee;
import scheduler.core.models.entities.Organization;
import scheduler.core.models.entities.OrganizationSchedule;
import scheduler.core.services.util.EmployeeList;
import scheduler.core.services.util.OrganizationList;
import scheduler.core.services.util.OrganizationScheduleList;

import java.util.Date;

/**
 * Created by c113554 on 05/10/2016.
 */
public interface OrganizationService {
    public Organization findOrg(Long id);
    public OrganizationList findAllOrgs();
    public Organization createOrg(Organization data);
    public Organization deleteOrg(Long id);
    public Organization updateOrg(Long id, Organization data);

    public Employee createEmployee(Long orgId, Employee data);
    public EmployeeList findAllEmployeesByOrg(Long orgId);

    public OrganizationSchedule createOrgSchedule(Long orgId, OrganizationSchedule data);
    public OrganizationScheduleList bulkCreateOrgSchedule(Long orgId,  OrganizationScheduleList data);
}
