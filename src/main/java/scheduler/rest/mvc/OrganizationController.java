package scheduler.rest.mvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import scheduler.core.models.entities.Employee;
import scheduler.core.models.entities.Organization;
import scheduler.core.services.OrganizationService;
import scheduler.core.services.exceptions.EmployeeAlreadyExistsException;
import scheduler.core.services.exceptions.OrganizationAlreadyExistsException;
import scheduler.core.services.exceptions.OrganizationNotFoundException;
import scheduler.core.services.util.EmployeeList;
import scheduler.core.services.util.OrganizationList;
import scheduler.rest.exceptions.ConflictException;
import scheduler.rest.exceptions.NotFoundException;
import scheduler.rest.resources.EmployeeListResource;
import scheduler.rest.resources.EmployeeResource;
import scheduler.rest.resources.OrganizationListResource;
import scheduler.rest.resources.OrganizationResource;
import scheduler.rest.resources.asm.EmployeeListResourceAsm;
import scheduler.rest.resources.asm.EmployeeResourceAsm;
import scheduler.rest.resources.asm.OrganizationListResourceAsm;
import scheduler.rest.resources.asm.OrganizationResourceAsm;

import javax.xml.ws.Response;
import java.net.URI;

/**
 * Created by c113554 on 05/10/2016.
 */
@Controller
@RequestMapping("/rest/organizations")
public class OrganizationController {
    private OrganizationService service;

    public OrganizationController(OrganizationService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrganizationResource> createOrganization(@RequestBody OrganizationResource sentOrganization){
        try {
            Organization organization = service.createOrg((sentOrganization.toOrganization()));
            OrganizationResource resource = new OrganizationResourceAsm().toResource(organization);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<OrganizationResource>(resource, headers, HttpStatus.CREATED);
        } catch (OrganizationAlreadyExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<OrganizationListResource> getAllOrganizations(){
        OrganizationList organizationList = service.findAllOrgs();
        OrganizationListResource resource = new OrganizationListResourceAsm().toResource(organizationList);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(resource.getLink("self").getHref()));
        return new ResponseEntity<OrganizationListResource>(resource, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{orgId}", method = RequestMethod.GET)
    public ResponseEntity<OrganizationResource> getOrganizationById(@PathVariable Long orgId){
        Organization organization = service.findOrg(orgId);
        if(organization != null) {
            OrganizationResource resource = new OrganizationResourceAsm().toResource(organization);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<OrganizationResource>(resource, HttpStatus.OK);
        }else{
            return new ResponseEntity<OrganizationResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{orgId}", method = RequestMethod.PUT)
    public ResponseEntity<OrganizationResource> updateOrganization(@PathVariable Long orgId, @RequestBody OrganizationResource sentOrganization){
        Organization organization = service.updateOrg(orgId, sentOrganization.toOrganization());
        if(organization != null) {
            OrganizationResource resource = new OrganizationResourceAsm().toResource(organization);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<OrganizationResource>(resource, HttpStatus.OK);
        }else{
            return new ResponseEntity<OrganizationResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{orgId}", method = RequestMethod.DELETE)
    public ResponseEntity<OrganizationResource> deleteOrganization(@PathVariable Long orgId){
        Organization organization = service.deleteOrg(orgId);
        if(organization != null) {
            OrganizationResource resource = new OrganizationResourceAsm().toResource(organization);
            return new ResponseEntity<OrganizationResource>(resource, HttpStatus.OK);
        }else{
            return new ResponseEntity<OrganizationResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{orgId}/employees", method = RequestMethod.GET)
    public ResponseEntity<EmployeeListResource> getAllEmployeesByOrg(@PathVariable Long orgId){
        EmployeeList employeeList = service.findAllEmployeesByOrg(orgId);
        employeeList.setOrgId(orgId);
        EmployeeListResource resource = new EmployeeListResourceAsm().toResource(employeeList);
        return new ResponseEntity<EmployeeListResource>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{orgId}/employees", method = RequestMethod.POST)
    public ResponseEntity<EmployeeResource> createEmployee(@PathVariable Long orgId, @RequestBody EmployeeResource sentEmployee)
    {
        try {
            Employee employee = service.createEmployee(orgId, (sentEmployee.toEmployee()));
            EmployeeResource resource = new EmployeeResourceAsm().toResource(employee);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<EmployeeResource>(resource, headers, HttpStatus.CREATED);
        } catch (EmployeeAlreadyExistsException exception) {
            throw new ConflictException(exception);
        } catch (OrganizationNotFoundException exception){
            throw new NotFoundException(exception);
        }
    }
}
