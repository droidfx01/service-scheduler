package scheduler.rest.resources.asm;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scheduler.core.models.entities.Employee;
import scheduler.core.models.entities.Organization;
import scheduler.core.services.util.EmployeeList;
import scheduler.rest.mvc.EmployeeController;
import scheduler.rest.mvc.OrganizationController;
import scheduler.rest.resources.EmployeeListResource;
import scheduler.rest.resources.EmployeeResource;

import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by c113554 on 05/11/2016.
 */
public class EmployeeListResourceAsm extends ResourceAssemblerSupport<EmployeeList, EmployeeListResource> {


    public EmployeeListResourceAsm() {
        super(EmployeeController.class, EmployeeListResource.class);
    }

    @Override
    public EmployeeListResource toResource(EmployeeList list) {
        List<EmployeeResource> resources = new EmployeeResourceAsm().toResources(list.getEmployees());
        EmployeeListResource listResource = new EmployeeListResource();
        listResource.setEmployees(resources);
        listResource.add(linkTo(methodOn(OrganizationController.class).getAllEmployeesByOrg(list.getOrgId())).withSelfRel());
        //TODO: Add rel for employee bookings
        //TODO: Add rel for employee shifts
        return listResource;
    }
}
