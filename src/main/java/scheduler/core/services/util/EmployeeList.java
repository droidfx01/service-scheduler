package scheduler.core.services.util;

import scheduler.core.models.entities.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c113554 on 05/11/2016.
 */
public class EmployeeList {
    private List<Employee> employees = new ArrayList<>();
    private Long orgId;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
