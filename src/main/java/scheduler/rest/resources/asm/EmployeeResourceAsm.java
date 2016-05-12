package scheduler.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scheduler.core.models.entities.Employee;
import scheduler.rest.mvc.EmployeeController;
import scheduler.rest.mvc.OrganizationController;
import scheduler.rest.resources.EmployeeResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by c113554 on 05/11/2016.
 */
public class EmployeeResourceAsm extends ResourceAssemblerSupport<Employee,EmployeeResource>{

    public EmployeeResourceAsm() {
        super(EmployeeController.class, EmployeeResource.class);
    }

    @Override
    public EmployeeResource toResource(Employee employee) {
        EmployeeResource resource = new EmployeeResource();
        Long orgId = employee.getEmployeeOrg().getOrgId();
        resource.setEmployeeFirst(employee.getEmployeeFirst());
        resource.setEmployeeLast(employee.getEmployeeLast());
        resource.setEmployeeBio(employee.getEmployeeBio());
        resource.setEmployeeEmail(employee.getEmployeeEmail());
        resource.setEmployeeImagePath(employee.getEmployeeImagePath());
        resource.setEmployeeManager(employee.getEmployeeManager());

        Link self = linkTo(OrganizationController.class).slash(orgId).slash("employees").slash(employee.getEmployeeId()).withSelfRel();
        resource.add(self);
        if(employee.getEmployeeOrg() != null)
        {
            resource.add(linkTo(OrganizationController.class).slash(employee.getEmployeeOrg().getOrgId()).withRel("organization"));
        }
        return resource;
    }
}
