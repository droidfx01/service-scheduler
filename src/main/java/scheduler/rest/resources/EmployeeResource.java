package scheduler.rest.resources;

import com.sun.istack.internal.Nullable;
import org.springframework.hateoas.ResourceSupport;
import scheduler.core.models.entities.Employee;
import scheduler.core.models.entities.Organization;

/**
 * Created by c113554 on 05/11/2016.
 */
public class EmployeeResource extends ResourceSupport {
    private String employeeFirst;
    private String employeeLast;
    private String employeeEmail;
    @Nullable
    private String employeeBio;
    @Nullable
    private String employeeImagePath;
    @Nullable
    private Organization employeeOrg;
    @Nullable
    private Employee employeeManager;

    public Employee getEmployeeManager() {
        return employeeManager;
    }

    public void setEmployeeManager(Employee employeeManager) {
        this.employeeManager = employeeManager;
    }

    public Organization getEmployeeOrg() {
        return employeeOrg;
    }

    public void setEmployeeOrg(Organization organization) {
        this.employeeOrg = organization;
    }

    public String getEmployeeImagePath() {
        return employeeImagePath;
    }

    public void setEmployeeImagePath(String employeeImagePath) {
        this.employeeImagePath = employeeImagePath;
    }

    public String getEmployeeBio() {
        return employeeBio;
    }

    public void setEmployeeBio(String employeeBio) {
        this.employeeBio = employeeBio;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeLast() {
        return employeeLast;
    }

    public void setEmployeeLast(String employeeLast) {
        this.employeeLast = employeeLast;
    }

    public String getEmployeeFirst() {
        return employeeFirst;
    }

    public void setEmployeeFirst(String employeeFirst) {
        this.employeeFirst = employeeFirst;
    }

    public Employee toEmployee(){
        Employee employee = new Employee();
        employee.setEmployeeFirst(employeeFirst);
        employee.setEmployeeLast(employeeLast);
        employee.setEmployeeBio(employeeBio);
        employee.setEmployeeEmail(employeeEmail);
        employee.setEmployeeImagePath(employeeImagePath);
        employee.setEmployeeManager(employeeManager);
        return employee;
    }

}
