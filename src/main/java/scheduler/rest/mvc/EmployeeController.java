package scheduler.rest.mvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scheduler.core.models.entities.Employee;
import scheduler.core.models.entities.Organization;
import scheduler.core.services.EmployeeService;
import scheduler.core.services.exceptions.EmployeeAlreadyExistsException;
import scheduler.core.services.exceptions.EmployeeNotFoundException;
import scheduler.core.services.exceptions.OrganizationAlreadyExistsException;
import scheduler.core.services.exceptions.OrganizationNotFoundException;
import scheduler.rest.exceptions.ConflictException;
import scheduler.rest.exceptions.NotFoundException;
import scheduler.rest.resources.EmployeeResource;
import scheduler.rest.resources.OrganizationResource;
import scheduler.rest.resources.asm.EmployeeResourceAsm;
import scheduler.rest.resources.asm.OrganizationResourceAsm;

import javax.xml.ws.Response;
import java.lang.reflect.Method;
import java.net.URI;

/**
 * Created by c113554 on 05/11/2016.
 */
@Controller
@RequestMapping(value = "/rest/organizations/{orgId}/employees")
public class EmployeeController {
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{empId}")
    public ResponseEntity<EmployeeResource> deleteEmployee(@PathVariable Long empId) {
        try {
            Employee employee = service.deleteEmployee(empId);
            EmployeeResource resource = new EmployeeResourceAsm().toResource(employee);
            return new ResponseEntity<EmployeeResource>(resource, HttpStatus.OK);
        } catch (EmployeeNotFoundException exception) {
            throw new NotFoundException(exception);
        }catch(OrganizationNotFoundException exception){
            throw new NotFoundException(exception);
        }

    }

    @RequestMapping(value = "/{empId}")
    public ResponseEntity<EmployeeResource> findEmployeeById(@PathVariable Long empId) {
        try {
            Employee employee = service.findEmployee(empId);
            EmployeeResource resource = new EmployeeResourceAsm().toResource(employee);
            return new ResponseEntity<EmployeeResource>(resource, HttpStatus.OK);
        } catch (EmployeeNotFoundException exception) {
            throw new NotFoundException(exception);
        }catch(OrganizationNotFoundException exception){
            throw new NotFoundException(exception);
        }
    }

    @RequestMapping(value = "/{empId}")
    public ResponseEntity<EmployeeResource> updateEmployee(@PathVariable Long empId, @RequestBody EmployeeResource sentEmployee) {
        Employee employee = service.updateEmployee(empId, sentEmployee);
    }
}
