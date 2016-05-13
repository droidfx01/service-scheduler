package scheduler.mvc;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import scheduler.core.models.entities.Employee;
import scheduler.core.models.entities.Organization;
import scheduler.core.services.EmployeeService;
import scheduler.core.services.OrganizationService;
import scheduler.core.services.exceptions.EmployeeNotFoundException;
import scheduler.core.services.exceptions.OrganizationAlreadyExistsException;
import scheduler.core.services.exceptions.OrganizationNotFoundException;
import scheduler.core.services.util.OrganizationList;
import scheduler.rest.mvc.EmployeeController;
import scheduler.rest.mvc.OrganizationController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.endsWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by c113554 on 05/11/2016.
 */

public class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController controller;

    @Mock
    private EmployeeService service;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void deleteEmployee() throws Exception {
        Organization organization = new Organization();
        organization.setOrgId(1L);

        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeFirst("Hernando");
        employee.setEmployeeLast("Hoyos");
        employee.setEmployeeEmail("hfrog713@gmail.com");
        employee.setEmployeeBio("I am a computer technician and I work with everything from hardware to any type of software");
        employee.setEmployeeImagePath(null);
        employee.setEmployeeManager(null);
        employee.setEmployeeOrg(organization);


        when(service.deleteEmployee(eq(1L), eq(1L))).thenReturn(employee);
        mockMvc.perform(delete("/rest/organizations/1/employees/1"))
                .andDo(print())
                .andExpect(jsonPath("$.employeeFirst", is("Hernando")))
                .andExpect(jsonPath("$.employeeLast", is("Hoyos")))
                .andExpect(jsonPath("$.employeeEmail", is("hfrog713@gmail.com")))
                .andExpect(jsonPath("$.employeeBio", is("I am a computer technician and I work with everything from hardware to any type of software")));

    }

    @Test
    public void deleteNonExistingEmployee() throws Exception {
        when(service.deleteEmployee(eq(1L), eq(1L))).thenThrow(new EmployeeNotFoundException());
        mockMvc.perform(delete("/rest/organizations/1/employees/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteEmployeeNonExistingOrg() throws Exception {
        when(service.deleteEmployee(eq(1L), eq(1L))).thenThrow(new OrganizationNotFoundException());
        mockMvc.perform(delete("/rest/organizations/1/employees/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmployee() throws Exception {
        Organization organization = new Organization();
        organization.setOrgId(1L);

        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeFirst("Hernando");
        employee.setEmployeeLast("Hoyos");
        employee.setEmployeeEmail("hfrog713@gmail.com");
        employee.setEmployeeBio("Test Bio");
        employee.setEmployeeImagePath(null);
        employee.setEmployeeManager(null);
        employee.setEmployeeOrg(organization);

        when(service.updateEmployee(eq(1L), eq(1L), any(Employee.class))).thenReturn(employee);

        mockMvc.perform(put("/rest/organizations/1/employees/1")
                .content("{\"employeeFirst\":\"Hernando\", \"employeeLast\":\"Hoyos\", \"employeeEmail\":\"hfrog713@gmail.com\", \"employeeBio\":\"Test Bio\", \"employeeImagePath\":\"\", \"employeeManager\":{}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.employeeFirst", is("Hernando")))
                .andExpect(jsonPath("$.employeeLast", is("Hoyos")))
                .andExpect(jsonPath("$.employeeEmail", is("hfrog713@gmail.com")))
                .andExpect(jsonPath("$.employeeBio", is("Test Bio")))
                .andExpect(status().isOk());
    }

    @Test
    public void updateNonExistingEmployee() throws Exception {
        when(service.updateEmployee(eq(1L), eq(1L), any(Employee.class))).thenThrow(new EmployeeNotFoundException());
        mockMvc.perform(put("/rest/organizations/1/employees/1")
                .content("{\"employeeFirst\":\"Hernando\", \"employeeLast\":\"Hoyos\", \"employeeEmail\":\"hfrog713@gmail.com\", \"employeeBio\":\"Test Bio\", \"employeeImagePath\":\"\", \"employeeManager\":{}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmployeeNonExistingOrg() throws Exception {
        when(service.updateEmployee(eq(1L), eq(1L), any(Employee.class))).thenThrow(new OrganizationNotFoundException());
        mockMvc.perform(put("/rest/organizations/1/employees/1")
                .content("{\"employeeFirst\":\"Hernando\", \"employeeLast\":\"Hoyos\", \"employeeEmail\":\"hfrog713@gmail.com\", \"employeeBio\":\"Test Bio\", \"employeeImagePath\":\"\", \"employeeManager\":{}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void findEmployee() throws Exception {
        Organization organization = new Organization();
        organization.setOrgId(1L);

        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeFirst("Hernando");
        employee.setEmployeeLast("Hoyos");
        employee.setEmployeeEmail("hfrog713@gmail.com");
        employee.setEmployeeBio("Test Bio");
        employee.setEmployeeImagePath(null);
        employee.setEmployeeManager(null);
        employee.setEmployeeOrg(organization);

        when(service.findEmployee(eq(1L), eq(1L))).thenReturn(employee);

        mockMvc.perform(get("/rest/organizations/1/employees/1"))
                .andDo(print())
                .andExpect(jsonPath("$.employeeFirst", is("Hernando")))
                .andExpect(jsonPath("$.employeeLast", is("Hoyos")))
                .andExpect(jsonPath("$.employeeEmail", is("hfrog713@gmail.com")))
                .andExpect(jsonPath("$.employeeBio", is("Test Bio")))
                .andExpect(status().isOk());
    }

    @Test
    public void findNonExistingEmployee() throws Exception {
        when(service.findEmployee(eq(1L), eq(1L))).thenThrow(new EmployeeNotFoundException());
        mockMvc.perform(get("/rest/organizations/1/employees/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void findEmployeeNonExistingOrg() throws Exception {
        when(service.findEmployee(eq(1L), eq(1L))).thenThrow(new OrganizationNotFoundException());
        mockMvc.perform(get("/rest/organizations/1/employees/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
