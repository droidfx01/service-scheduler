package scheduler.mvc;

import org.hamcrest.core.IsNull;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;
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
import scheduler.core.services.exceptions.DateDidNotParseException;
import scheduler.core.services.exceptions.OrganizationNotFoundException;
import scheduler.core.services.exceptions.ScheduleNotFoundException;
import scheduler.core.services.util.OrganizationList;
import scheduler.core.services.util.OrganizationScheduleList;
import scheduler.rest.mvc.OrganizationScheduleController;

import java.rmi.dgc.DGC;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static com.fasterxml.jackson.databind.util.ISO8601Utils.format;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static scheduler.core.models.entities.OrganizationSchedule.dateFormat;
import static scheduler.core.models.entities.OrganizationSchedule.timeFormat;

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
    public void findOrgScheduleDateRange() throws Exception
    {
        OrganizationSchedule schedule = new OrganizationSchedule();
        OrganizationSchedule schedule2 = new OrganizationSchedule();
        OrganizationScheduleList list = new OrganizationScheduleList();
        List<OrganizationSchedule> schedules = new ArrayList<>();

        Organization organization = new Organization();
        organization.setOrgId(1L);

        DateTime openTime = timeFormat.parseDateTime("07:00:00");
        DateTime closeTime = timeFormat.parseDateTime("19:00:00");
        DateTime date = dateFormat.parseDateTime("20160517");

        DateTime openTime2 = timeFormat.parseDateTime("07:00:00");
        DateTime closeTime2 = timeFormat.parseDateTime("19:00:00");
        DateTime date2 = dateFormat.parseDateTime("20160516");

        schedule.setScheduleDate(date);
        schedule.setScheduleOpen(openTime);
        schedule.setScheduleClose(closeTime);
        schedule.setOrganization(organization);

        schedule2.setScheduleDate(date2);
        schedule2.setScheduleOpen(openTime2);
        schedule2.setScheduleClose(closeTime2);
        schedule2.setOrganization(organization);

        schedules.add(schedule);
        schedules.add(schedule2);
        list.setSchedules(schedules);

        when(service.findOrgSchedule(eq(1L), any(DateTime.class), any(DateTime.class))).thenReturn(list);
        mockMvc.perform(get("/rest/organizations/1/schedules?startDate=20160516&endDate=20160517"))
                .andDo(print())
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/schedules"))))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/organizations/" + organization.getOrgId()))))
                .andExpect(status().isOk());
    }
    @Test
    public void findOrgScheduleSpecificDate() throws Exception
    {
        OrganizationSchedule schedule = new OrganizationSchedule();
        Organization organization = new Organization();
        organization.setOrgId(1L);
        DateTime openTime = timeFormat.parseDateTime("07:00:00");
        DateTime closeTime = timeFormat.parseDateTime("19:00:00");
        DateTime date = dateFormat.parseDateTime("20160517");
        schedule.setScheduleDate(date);
        schedule.setScheduleOpen(openTime);
        schedule.setScheduleClose(closeTime);
        schedule.setOrganization(organization);


        when(service.findOrgSchedule(eq(1L), eq(date))).thenReturn(schedule);
        mockMvc.perform(get("/rest/organizations/1/schedules?date=20160517"))
                .andDo(print())
                .andExpect(jsonPath("$.scheduleDate", is(schedule.getScheduleDateString())))
                .andExpect(jsonPath("$.scheduleOpen", is(schedule.getScheduleOpen())))
                .andExpect(jsonPath("$.scheduleClose", is(schedule.getScheduleClose())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/organizations/" + organization.getOrgId()))))
                .andExpect(status().isOk());

    }
    @Test
    public void findOrgScheduleNonExistingOrg() throws Exception
    {
        DateTime openTime = timeFormat.parseDateTime("07:00:00");
        DateTime closeTime = timeFormat.parseDateTime("19:00:00");
        DateTime date = dateFormat.parseDateTime("20160517");

        when(service.findOrgSchedule(eq(1L), eq(openTime), eq(closeTime))).thenThrow(new OrganizationNotFoundException());
        mockMvc.perform(get("/rest/organizations/1/schedules?startDate=20160517&endDate=20160517"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void findOrgScheduleInvalidDate() throws Exception
    {
        DateTime openTime = timeFormat.parseDateTime("07:00:00");
        DateTime closeTime = timeFormat.parseDateTime("19:00:00");
        DateTime date = dateFormat.parseDateTime("20160517");

        when(service.findOrgSchedule(eq(1L), eq(openTime), eq(closeTime))).thenThrow(new DateDidNotParseException()); //TODO: Populate Exception for Parsing)
        mockMvc.perform(get("/rest/organizations/1/schedules?startDate=20160517&endDate=invalid"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findOrgScheduleNoStartDate() throws Exception
    {
        OrganizationSchedule schedule = new OrganizationSchedule();
        OrganizationScheduleList list = new OrganizationScheduleList();
        List<OrganizationSchedule> schedules = new ArrayList<>();

        Organization organization = new Organization();
        organization.setOrgId(1L);
        DateTime openTime = timeFormat.parseDateTime("07:00:00");
        DateTime closeTime = timeFormat.parseDateTime("19:00:00");
        DateTime today = dateFormat.parseDateTime(dateFormat.print(new DateTime()));
        schedule.setScheduleDate(today);
        schedule.setScheduleOpen(openTime);
        schedule.setScheduleClose(closeTime);
        schedule.setOrganization(organization);

        schedules.add(schedule);
        list.setSchedules(schedules);

        when(service.findOrgSchedule(eq(1L),eq(today), eq(today))).thenReturn(list);
        mockMvc.perform(get("/rest/organizations/1/schedules?endDate=" + schedule.getScheduleDateString()))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void findOrgScheduleDateNotScheduled() throws Exception
    {
        DateTime openTime = timeFormat.parseDateTime("07:00:00");
        DateTime closeTime = timeFormat.parseDateTime("19:00:00");
        DateTime today = dateFormat.parseDateTime(dateFormat.print(new DateTime()));

        when(service.findOrgSchedule(eq(1L), eq(today))).thenThrow(new ScheduleNotFoundException());
        mockMvc.perform(get("/rest/organizations/1/schedules?date=" + dateFormat.print(today)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    public void findAllOrgSchedule() throws Exception
    {

        OrganizationSchedule schedule = new OrganizationSchedule();
        OrganizationSchedule schedule2 = new OrganizationSchedule();
        OrganizationScheduleList list = new OrganizationScheduleList();
        List<OrganizationSchedule> schedules = new ArrayList<>();

        Organization organization = new Organization();
        organization.setOrgId(1L);

        DateTime openTime = timeFormat.parseDateTime("07:00:00");
        DateTime closeTime = timeFormat.parseDateTime("19:00:00");
        DateTime date = dateFormat.parseDateTime("20160516");

        DateTime openTime2 = timeFormat.parseDateTime("07:00:00");
        DateTime closeTime2 = timeFormat.parseDateTime("19:00:00");
        DateTime date2 = dateFormat.parseDateTime("20160517");

        schedule.setScheduleDate(date);
        schedule.setScheduleOpen(openTime);
        schedule.setScheduleClose(closeTime);
        schedule.setOrganization(organization);

        schedule2.setScheduleDate(date2);
        schedule2.setScheduleOpen(openTime2);
        schedule2.setScheduleClose(closeTime2);
        schedule2.setOrganization(organization);

        schedules.add(schedule);
        schedules.add(schedule2);
        list.setSchedules(schedules);

        when(service.findAllOrgSchedule(organization.getOrgId())).thenReturn(list);
        mockMvc.perform(get("/rest/organizations/1/schedules"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void deleteOrgScheduleSpecificDate() throws Exception{
        OrganizationSchedule schedule = new OrganizationSchedule();
        Organization organization = new Organization();
        organization.setOrgId(1L);
        String today = dateFormat.print(new DateTime());
        DateTime date = dateFormat.parseDateTime(today);
        DateTime openTime = timeFormat.parseDateTime("07:00:00");
        DateTime closeTime = timeFormat.parseDateTime("19:00:00");

        schedule.setScheduleDate(date);
        schedule.setScheduleOpen(openTime);
        schedule.setScheduleClose(closeTime);
        schedule.setOrganization(organization);


        when(service.deleteOrgSchedule(eq(1L), eq(date))).thenReturn(schedule);
        mockMvc.perform(delete("/rest/organizations/1/schedules?date=" + today))
                .andDo(print())
//                .andExpect(jsonPath("$.scheduleDate", is(today)))
//                .andExpect(jsonPath("$.scheduleOpen", is("null")))
//                .andExpect(jsonPath("$.scheduleClose", is("null")))
//                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/organizations/" + organization.getOrgId()))))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteOrgScheduleNonExistingOrg(){}
    @Test
    public void deleteOrgScheduleDateNotScheduled(){}

    @Test
    public void bulkDeleteOrgSchedule(){}
    @Test
    public void bulkDeleteOrgScheduleInvalidData(){

    }
    @Test
    public void updateOrgSchedule(){}
    @Test
    public void updateOrgScheduleNonExistingOrg(){}
    @Test
    public void updateOrgScheduleDateNotScheduled(){}

}
