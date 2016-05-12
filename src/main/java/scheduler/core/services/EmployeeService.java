package scheduler.core.services;

import scheduler.core.models.entities.Employee;
import scheduler.core.models.entities.Organization;
import scheduler.core.services.util.EmployeeList;
import scheduler.core.services.util.OrganizationList;
import scheduler.rest.resources.EmployeeResource;

/**
 * Created by c113554 on 05/11/2016.
 */
public interface EmployeeService {
    public Employee findEmployee(Long id);
    public Employee deleteEmployee(Long id);
    public Employee updateEmployee(Long id, EmployeeResource data);
}
