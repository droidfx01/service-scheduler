package scheduler.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import scheduler.core.models.entities.Organization;
import scheduler.core.models.entities.OrganizationSchedule;
import scheduler.core.services.OrganizationScheduleService;
import scheduler.core.services.exceptions.OrganizationNotFoundException;
import scheduler.rest.mvc.OrganizationScheduleController;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by C113554 on 05/13/2016.
 */
public class OrganizationScheduleControllerTest {
    @InjectMocks
    private OrganizationScheduleController controller;

    @Mock
    private OrganizationScheduleService service;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void findOrgSchedule() throws Exception
    {
        OrganizationSchedule schedule = new OrganizationSchedule();
        Organization organization = new Organization();
        organization.setOrgId(1L);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        Time openTime = new Time(time.parse("07:00:00").getTime());
        Time closeTime = new Time(time.parse("19:00:00").getTime());
        Date date = df.parse("2016/05/16");
        schedule.setScheduleDate(date);
        schedule.setScheduleOpen(openTime);
        schedule.setScheduleClose(closeTime);
        schedule.setOrganization(organization);
        when(service.findOrgSchedule(eq(1L), Matchers.any(Date.class))).thenReturn(schedule);
        mockMvc.perform(get("/rest/organizations/1/schedules/20160516")).andExpect(status().isOk());


    }
    @Test
    public void findOrgScheduleNonExistingOrg() throws Exception
    {
        when(service.findOrgSchedule(eq(1L), any(Date.class))).thenThrow(new OrganizationNotFoundException());
        mockMvc.perform(get("/rest/organizations/1/schedules/20160516")).andDo(print()).andExpect(status().isNotFound());
    }
    @Test
    public void findOrgScheduleDateNotScheduled(){}
    @Test
    public void deleteOrgSchedule(){}
    @Test
    public void deleteOrgScheduleNonExistingOrg(){}
    @Test
    public void deleteOrgScheduleDateNotScheduled(){}
    @Test
    public void updateOrgSchedule(){}
    @Test
    public void updateOrgScheduleNonExistingOrg(){}
    @Test
    public void updateOrgScheduleDateNotScheduled(){}

}
