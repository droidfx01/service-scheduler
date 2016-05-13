package scheduler.core.services;

import scheduler.core.models.entities.Employee;
import scheduler.core.models.entities.Organization;
import scheduler.core.services.util.EmployeeList;
import scheduler.core.services.util.OrganizationList;
import scheduler.rest.resources.EmployeeResource;

import static javafx.scene.input.KeyCode.L;

/**
 * Created by c113554 on 05/11/2016.
 */
public interface EmployeeService {
    public Employee findEmployee(Long orgId, Long empId);
    public Employee deleteEmployee(Long orgId, Long empId);
    public Employee updateEmployee(Long orgId, Long empId, Employee data);
}
