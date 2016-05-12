package scheduler.core.models.entities;

import com.sun.istack.internal.Nullable;

/**
 * Created by c113554 on 05/11/2016.
 */
public class Employee {
    private Long employeeId;
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

    public void setEmployeeOrg(Organization employeeOrg) {
        this.employeeOrg = employeeOrg;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

}
