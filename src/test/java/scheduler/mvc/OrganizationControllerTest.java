package scheduler.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import scheduler.core.models.entities.Employee;
import scheduler.core.models.entities.Organization;
import scheduler.core.services.OrganizationService;
import scheduler.core.services.exceptions.EmployeeAlreadyExistsException;
import scheduler.core.services.exceptions.OrganizationAlreadyExistsException;
import scheduler.core.services.exceptions.OrganizationNotFoundException;
import scheduler.core.services.util.EmployeeList;
import scheduler.core.services.util.OrganizationList;
import scheduler.rest.mvc.OrganizationController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by c113554 on 05/10/2016.
 */
public class OrganizationControllerTest {
    @InjectMocks
    private OrganizationController controller;

    @Mock
    private OrganizationService service;

    private MockMvc mockMvc;



    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void createOrganization() throws Exception
    {
        Organization organization = new Organization();
        organization.setOrgId(1L);
        organization.setOrgName("Mason's Computer Solutions");
        organization.setOrgDescription("A computer repair business.");
        organization.setOrgAddress1("225 East Ave Apt 3C");
        organization.setOrgAddress2("");
        organization.setOrgCity("Syracuse");
        organization.setOrgZip(13224);
        organization.setOrgState("NY");
        organization.setOrgCountry("US");

        when(service.createOrg(any(Organization.class))).thenReturn(organization);

        mockMvc.perform(post("/rest/organizations")
                .content("{\"orgName\":\"Mason's Computer Solutions\", \"orgDesc\":\"A computer repair business.\", \"orgAddress1\":\"225 East Ave Apt 3C\", \"orgAddress2\":\"\", \"orgCity\":\"Syracuse\", \"orgState\":\"NY\", \"orgZip\" : 13224, \"orgCountry\":\"Mason's Computer Solutions\"} ")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(header().string("Location", org.hamcrest.Matchers.endsWith("/rest/organizations/1")))
                .andExpect(jsonPath("$.orgName", is(organization.getOrgName())))
                .andExpect(jsonPath("$.orgDescription", is(organization.getOrgDescription())))
                .andExpect(jsonPath("$.orgAddress1", is(organization.getOrgAddress1())))
                .andExpect(jsonPath("$.orgAddress2", is(organization.getOrgAddress2())))
                .andExpect(jsonPath("$.orgCity", is(organization.getOrgCity())))
                .andExpect(jsonPath("$.orgState", is(organization.getOrgState())))
                .andExpect(jsonPath("$.orgZip", is(organization.getOrgZip())))
                .andExpect(jsonPath("$.orgCountry", is(organization.getOrgCountry())))
                .andExpect(status().isCreated());
    }

    @Test
    public void createOrganizationAlreadyExists() throws Exception
    {
        Organization organization = new Organization();
        organization.setOrgId(1L);
        organization.setOrgName("Mason's Computer Solutions");
        organization.setOrgDescription("A computer repair business.");
        organization.setOrgAddress1("225 East Ave Apt 3C");
        organization.setOrgAddress2("");
        organization.setOrgCity("Syracuse");
        organization.setOrgZip(13224);
        organization.setOrgState("NY");
        organization.setOrgCountry("US");

        when(service.createOrg(any(Organization.class))).thenThrow(new OrganizationAlreadyExistsException());

        mockMvc.perform(post("/rest/organizations")
                .content("{\"orgName\":\"Mason's Computer Solutions\", \"orgDesc\":\"A computer repair business.\", \"orgAddress1\":\"225 East Ave Apt 3C\", \"orgAddress2\":\"\", \"orgCity\":\"Syracuse\", \"orgState\":\"NY\", \"orgZip\" : 13224, \"orgCountry\":\"Mason's Computer Solutions\"} ")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void updateOrganization() throws Exception
    {
        Organization organization = new Organization();
        organization.setOrgId(1L);
        organization.setOrgName("Mason's Computer Solutions");
        organization.setOrgDescription("A computer repair business.");
        organization.setOrgAddress1("225 East Ave Apt 3C");
        organization.setOrgAddress2("");
        organization.setOrgCity("Syracuse");
        organization.setOrgZip(13224);
        organization.setOrgState("NY");
        organization.setOrgCountry("US");

        when(service.updateOrg(eq(1L), any(Organization.class))).thenReturn(organization);
        mockMvc.perform(put("/rest/organizations/1")
                .content("{\"orgName\":\"Mason's Fresh Clams\", \"orgDesc\":\"A computer repair business.\", \"orgAddress1\":\"225 East Ave Apt 3C\", \"orgAddress2\":\"\", \"orgCity\":\"Syracuse\", \"orgState\":\"NY\", \"orgZip\" : 13224, \"orgCountry\":\"Mason's Computer Solutions\"} ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orgName", is(organization.getOrgName())))
                .andExpect(jsonPath("$.orgDescription", is(organization.getOrgDescription())))
                .andExpect(jsonPath("$.orgAddress1", is(organization.getOrgAddress1())))
                .andExpect(jsonPath("$.orgAddress2", is(organization.getOrgAddress2())))
                .andExpect(jsonPath("$.orgCity", is(organization.getOrgCity())))
                .andExpect(jsonPath("$.orgState", is(organization.getOrgState())))
                .andExpect(jsonPath("$.orgZip", is(organization.getOrgZip())))
                .andExpect(jsonPath("$.orgCountry", is(organization.getOrgCountry())))
                .andExpect(status().isOk());
    }

    @Test
    public void updateNonExistingOrganization()throws Exception
    {
        when(service.updateOrg(eq(1L), any(Organization.class))).thenReturn(null);
        mockMvc.perform(put("/rest/organizations/1")
                .content("{\"orgName\":\"Mason's Fresh Clams\", \"orgDesc\":\"A computer repair business.\", \"orgAddress1\":\"225 East Ave Apt 3C\", \"orgAddress2\":\"\", \"orgCity\":\"Syracuse\", \"orgState\":\"NY\", \"orgZip\" : 13224, \"orgCountry\":\"Mason's Computer Solutions\"} ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getExistingOrganization() throws Exception
    {
        Organization organization = new Organization();
        organization.setOrgId(1L);
        organization.setOrgName("Mason's Computer Solutions");
        organization.setOrgDescription("A computer repair business.");
        organization.setOrgAddress1("225 East Ave Apt 3C");
        organization.setOrgAddress2("");
        organization.setOrgCity("Syracuse");
        organization.setOrgZip(13224);
        organization.setOrgState("NY");
        organization.setOrgCountry("US");

        when(service.findOrg(1L)).thenReturn(organization);
        mockMvc.perform(get("/rest/organizations/1"))
                .andDo(print())
                //.andExpect(header().string("Location", org.hamcrest.Matchers.endsWith("/rest/organizations/1")))
                .andExpect(jsonPath("$.orgName", is(organization.getOrgName())))
                .andExpect(jsonPath("$.orgDescription", is(organization.getOrgDescription())))
                .andExpect(jsonPath("$.orgAddress1", is(organization.getOrgAddress1())))
                .andExpect(jsonPath("$.orgAddress2", is(organization.getOrgAddress2())))
                .andExpect(jsonPath("$.orgCity", is(organization.getOrgCity())))
                .andExpect(jsonPath("$.orgState", is(organization.getOrgState())))
                .andExpect(jsonPath("$.orgZip", is(organization.getOrgZip())))
                .andExpect(jsonPath("$.orgCountry", is(organization.getOrgCountry())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/organizations/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void getNonExistingOrganization() throws Exception
    {
        when(service.findOrg(1L)).thenReturn(null);
        mockMvc.perform(get("/rest/organizations/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllOrganizations() throws Exception
    {
        List<Organization> list  = new ArrayList<>();

        Organization organizationA = new Organization();
        organizationA.setOrgId(1L);
        organizationA.setOrgName("Mason's Computer Solutions");
        organizationA.setOrgDescription("A computer repair business.");
        organizationA.setOrgAddress1("225 East Ave Apt 3C");
        organizationA.setOrgAddress2("");
        organizationA.setOrgCity("Syracuse");
        organizationA.setOrgZip(13224);
        organizationA.setOrgState("NY");
        organizationA.setOrgCountry("US");

        Organization organizationB = new Organization();
        organizationB.setOrgId(1L);
        organizationB.setOrgName("Mason's Tasty Clams");
        organizationB.setOrgDescription("Clam Shop");
        organizationB.setOrgAddress1("208 Union Ave");
        organizationB.setOrgAddress2("Apt 4");
        organizationB.setOrgCity("New Rochelle");
        organizationB.setOrgZip(10801);
        organizationB.setOrgState("NY");
        organizationB.setOrgCountry("US");

        list.add(organizationA);
        list.add(organizationB);

        OrganizationList allOrgs = new OrganizationList();
        allOrgs.setOrganizations(list);

        when(service.findAllOrgs()).thenReturn(allOrgs);
        mockMvc.perform(get("/rest/organizations"))
                .andDo(print())
                .andExpect(jsonPath("$.organizations[*].orgName", hasItems(endsWith("Mason's Tasty Clams"), endsWith("Mason's Computer Solutions"))))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteOrganization() throws Exception
    {
        Organization organization = new Organization();
        organization.setOrgId(1L);
        organization.setOrgName("Mason's Computer Solutions");
        organization.setOrgDescription("A computer repair business.");
        organization.setOrgAddress1("225 East Ave Apt 3C");
        organization.setOrgAddress2("");
        organization.setOrgCity("Syracuse");
        organization.setOrgZip(13224);
        organization.setOrgState("NY");
        organization.setOrgCountry("US");

        when(service.deleteOrg(1L)).thenReturn(organization);
        mockMvc.perform(delete("/rest/organizations/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orgName", is(organization.getOrgName())))
                .andExpect(jsonPath("$.orgDescription", is(organization.getOrgDescription())))
                .andExpect(jsonPath("$.orgAddress1", is(organization.getOrgAddress1())))
                .andExpect(jsonPath("$.orgAddress2", is(organization.getOrgAddress2())))
                .andExpect(jsonPath("$.orgCity", is(organization.getOrgCity())))
                .andExpect(jsonPath("$.orgState", is(organization.getOrgState())))
                .andExpect(jsonPath("$.orgZip", is(organization.getOrgZip())))
                .andExpect(jsonPath("$.orgCountry", is(organization.getOrgCountry())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/organizations/1"))));
    }

    @Test
    public void deleteNonExistingOrganization() throws Exception
    {
        when(service.deleteOrg(1L)).thenReturn(null);
        mockMvc.perform(delete("/rest/organizations/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createEmployee() throws Exception
    {
        Organization organization = new Organization();
        organization.setOrgId(1L);
        organization.setOrgName("Mason's Computer Solutions");
        organization.setOrgDescription("A computer repair business.");
        organization.setOrgAddress1("225 East Ave Apt 3C");
        organization.setOrgAddress2("");
        organization.setOrgCity("Syracuse");
        organization.setOrgZip(13224);
        organization.setOrgState("NY");
        organization.setOrgCountry("US");

        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeFirst("Hernando");
        employee.setEmployeeLast("Hoyos");
        employee.setEmployeeEmail("hfrog713@gmail.com");
        employee.setEmployeeBio("I am a computer technician and I work with everything from hardware to any type of software");
        employee.setEmployeeImagePath(null);
        employee.setEmployeeManager(null);
        employee.setEmployeeOrg(organization);

        when(service.createEmployee(eq(1L), any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/rest/organizations/1/employees")
                .content("{\"employeeFirst\":\"Hernando\", \"employeeLast\":\"Hoyos\", \"employeeEmail\":\"hfrog713@gmail.com\", \"employeeBio\":\"Test Bio\", \"employeeImagePath\":\"\", \"employeeManager\":{}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.employeeFirst", is("Hernando")))
                .andExpect(jsonPath("$.employeeLast", is("Hoyos")))
                .andExpect(jsonPath("$.employeeEmail", is("hfrog713@gmail.com")))
                .andExpect(jsonPath("$.employeeBio", is("I am a computer technician and I work with everything from hardware to any type of software")));
    }

    @Test
    public void createEmployeeAlreadyExists() throws Exception
    {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeFirst("Hernando");
        employee.setEmployeeLast("Hoyos");
        employee.setEmployeeEmail("hfrog713@gmail.com");
        employee.setEmployeeBio("I am a computer technician and I work with everything from hardware to any type of software");
        employee.setEmployeeImagePath(null);
        employee.setEmployeeManager(null);


        when(service.createEmployee(eq(1L), any(Employee.class))).thenThrow(new EmployeeAlreadyExistsException());

        mockMvc.perform(post("/rest/organizations/1/employees")
                .content("{\"employeeFirst\":\"Hernando\", \"employeeLast\":\"Hoyos\", \"employeeEmail\":\"hfrog713@gmail.com\", \"employeeBio\":\"Test Bio\", \"employeeImagePath\":\"\", \"employeeManager\":{}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void createEmployeeNonExistingOrganization() throws Exception{
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeFirst("Hernando");
        employee.setEmployeeLast("Hoyos");
        employee.setEmployeeEmail("hfrog713@gmail.com");
        employee.setEmployeeBio("I am a computer technician and I work with everything from hardware to any type of software");
        employee.setEmployeeImagePath(null);
        employee.setEmployeeManager(null);


        when(service.createEmployee(eq(1L), any(Employee.class))).thenThrow(new OrganizationNotFoundException());

        mockMvc.perform(post("/rest/organizations/1/employees")
                .content("{\"employeeFirst\":\"Hernando\", \"employeeLast\":\"Hoyos\", \"employeeEmail\":\"hfrog713@gmail.com\", \"employeeBio\":\"Test Bio\", \"employeeImagePath\":\"\", \"employeeManager\":{}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllEmployeesByOrganization() throws Exception{
        Organization organization = new Organization();
        organization.setOrgId(1L);
        organization.setOrgName("Mason's Computer Solutions");
        organization.setOrgDescription("A computer repair business.");
        organization.setOrgAddress1("225 East Ave Apt 3C");
        organization.setOrgAddress2("");
        organization.setOrgCity("Syracuse");
        organization.setOrgZip(13224);
        organization.setOrgState("NY");
        organization.setOrgCountry("US");

        List<Employee> list = new ArrayList<>();

        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeFirst("Hernando");
        employee.setEmployeeLast("Hoyos");
        employee.setEmployeeEmail("hfrog713@gmail.com");
        employee.setEmployeeBio("I am a computer technician and I work with everything from hardware to any type of software");
        employee.setEmployeeImagePath(null);
        employee.setEmployeeManager(null);
        employee.setEmployeeOrg(organization);
        list.add(employee);

        Employee employee2 = new Employee();
        employee2.setEmployeeId(2L);
        employee2.setEmployeeFirst("Carlos");
        employee2.setEmployeeLast("Hoyos");
        employee2.setEmployeeEmail("carloshoyos45@gmail.com");
        employee2.setEmployeeBio("I am a currently in China");
        employee2.setEmployeeImagePath(null);
        employee2.setEmployeeManager(employee);
        employee2.setEmployeeOrg(organization);
        list.add(employee2);

        EmployeeList employees = new EmployeeList();
        employees.setEmployees(list);

        when(service.findAllEmployeesByOrg((eq(1L)))).thenReturn(employees);
        mockMvc.perform(get("/rest/organizations/1/employees"))
                .andDo(print())
                .andExpect(jsonPath("$.employees[*].employeeLast", hasItem(is("Hoyos"))))
                .andExpect(jsonPath("$.employees[*].employeeFirst", hasItems(is("Hernando"), is("Carlos"))))
                .andExpect(jsonPath("$.employees[*].links[*].href", hasItems(endsWith("/rest/organizations/1/employees/1"), endsWith("/rest/organizations/1/employees/2"))))
                .andExpect(status().isOk());

    }
    public void getAllEmployeesByNonExistingOrganization() throws Exception{

        when(service.findAllEmployeesByOrg(eq(1L))).thenThrow(new OrganizationNotFoundException());

        mockMvc.perform(get("/rest/organizations/1/employees"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
