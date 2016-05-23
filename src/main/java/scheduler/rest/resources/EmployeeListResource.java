package scheduler.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import scheduler.core.services.util.EmployeeList;

import java.util.List;

/**
 * Created by c113554 on 05/11/2016.
 */
public class EmployeeListResource extends ResourceSupport {
    private List<EmployeeResource> employees;

    public List<EmployeeResource >getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResource> employees) {
        this.employees = employees;
    }

}
