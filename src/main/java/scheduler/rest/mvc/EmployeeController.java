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
import scheduler.core.services.exceptions.OrganizationAlreadyExistsException;
import scheduler.rest.exceptions.ConflictException;
import scheduler.rest.resources.EmployeeResource;
import scheduler.rest.resources.OrganizationResource;
import scheduler.rest.resources.asm.EmployeeResourceAsm;
import scheduler.rest.resources.asm.OrganizationResourceAsm;

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

}
