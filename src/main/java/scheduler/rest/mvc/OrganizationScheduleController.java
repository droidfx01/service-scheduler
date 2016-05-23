package scheduler.rest.mvc;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.xml.internal.ws.developer.ValidationErrorHandler;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import scheduler.core.models.entities.OrganizationSchedule;
import scheduler.core.services.OrganizationScheduleService;
import scheduler.core.services.exceptions.DateDidNotParseException;
import scheduler.core.services.exceptions.OrganizationNotFoundException;
import scheduler.core.services.exceptions.ScheduleNotFoundException;
import scheduler.core.services.util.OrganizationScheduleList;
import scheduler.rest.exceptions.BadRequestException;
import scheduler.rest.exceptions.ConflictException;
import scheduler.rest.exceptions.NotFoundException;
import scheduler.rest.resources.OrganizationScheduleListResource;
import scheduler.rest.resources.OrganizationScheduleResource;
import scheduler.rest.resources.asm.OrganizationScheduleListResourceAsm;
import scheduler.rest.resources.asm.OrganizationScheduleResourceAsm;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static javafx.scene.input.KeyCode.R;
import static scheduler.core.models.entities.OrganizationSchedule.dateFormat;

/**
 * Created by C113554 on 05/13/2016.
 */
@Controller
public class OrganizationScheduleController {
    private OrganizationScheduleService service;


    @RequestMapping(value = "/rest/organizations/{orgId}/schedules", method = RequestMethod.GET, params = "date")
    public ResponseEntity<OrganizationScheduleResource> findOrgSchedule(
            @PathVariable Long orgId,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyyMMdd") DateTime date) {

        try {
            OrganizationSchedule schedule = service.findOrgSchedule(orgId, date);
            OrganizationScheduleResource resource = new OrganizationScheduleResourceAsm().toResource(schedule);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<OrganizationScheduleResource>(resource, headers, HttpStatus.OK);
        } catch (ScheduleNotFoundException exception) {
            throw new NotFoundException(exception);
        }catch (DateDidNotParseException exception){
            throw new BadRequestException(exception);
        }
    }

    @RequestMapping(value = "/rest/organizations/{orgId}/schedules",method = RequestMethod.GET, params = {"endDate"})
    public ResponseEntity<OrganizationScheduleListResource> findOrgSchedule(
            @PathVariable Long orgId,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") DateTime startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyyMMdd") DateTime endDate) {

        try {
            OrganizationScheduleList scheduleList;
            if (startDate == null) {
                String dt = dateFormat.print(new DateTime());
                startDate = dateFormat.parseDateTime(dt);
            }
            scheduleList = service.findOrgSchedule(orgId, startDate, endDate);
            OrganizationScheduleListResource resource = new OrganizationScheduleListResourceAsm().toResource(scheduleList);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<OrganizationScheduleListResource>(resource, headers, HttpStatus.OK);
        } catch (ScheduleNotFoundException exception) {
            throw new NotFoundException(exception);
        } catch(OrganizationNotFoundException exception){
            throw new NotFoundException(exception);
        }catch (DateDidNotParseException exception){
            throw new BadRequestException(exception);
        }
    }

    @RequestMapping(value = "/rest/organizations/{orgId}/schedules", method = RequestMethod.GET)
    public ResponseEntity<OrganizationScheduleListResource> findAllOrgSchedule(
            @PathVariable Long orgId) {

        try {
            OrganizationScheduleList scheduleList = service.findAllOrgSchedule(orgId);
            OrganizationScheduleListResource resource = new OrganizationScheduleListResourceAsm().toResource(scheduleList);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<OrganizationScheduleListResource>(resource, headers, HttpStatus.OK);
        } catch(OrganizationNotFoundException exception){
            throw new NotFoundException(exception);
        }
    }

    @RequestMapping(value = "/rest/organizations/{orgId}/schedule", method = RequestMethod.DELETE)
    public ResponseEntity<OrganizationScheduleListResource> bulkDeleteOrgSchedule(
            @PathVariable Long orgId,
            @RequestParam(value = "startDate", required = false) DateTime startDate,
            @RequestParam(value = "endDate") DateTime endDate) {


        if (startDate == null) {
            String stringStartDate = dateFormat.print(new DateTime());
            startDate = dateFormat.parseDateTime(stringStartDate);
        } else {
            String stringStartDate = dateFormat.print(startDate);
            startDate = dateFormat.parseDateTime(stringStartDate);
        }
        try {
            String stringEndDate = dateFormat.print(endDate);
            endDate = dateFormat.parseDateTime(stringEndDate);
            OrganizationScheduleList schedule = service.deleteOrgSchedule(orgId, startDate, endDate);
            OrganizationScheduleListResource resource = new OrganizationScheduleListResourceAsm().toResource(schedule);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<OrganizationScheduleListResource>(resource, headers, HttpStatus.OK);
        } catch (ScheduleNotFoundException exception) {
            throw new NotFoundException(exception);
        }catch (DateDidNotParseException exception){
            throw new BadRequestException(exception);
        }
    }

    @RequestMapping(value = "/rest/organizations/{orgId}/schedules", method = RequestMethod.DELETE)
    public ResponseEntity<OrganizationScheduleResource> deleteOrgSchedule(
            @PathVariable Long orgId,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyyMMdd")  DateTime date ) {
        try {
            String stringDate = dateFormat.print(date);
            date = dateFormat.parseDateTime(stringDate);
            OrganizationSchedule schedule = service.deleteOrgSchedule(orgId, date);
            OrganizationScheduleResource resource = new OrganizationScheduleResourceAsm().toResource(schedule);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<OrganizationScheduleResource>(resource, headers, HttpStatus.OK);
        } catch (ScheduleNotFoundException exception) {
            throw new NotFoundException(exception);
        }catch (DateDidNotParseException exception){
            throw new BadRequestException(exception);
        }
    }

    @RequestMapping(value = "/rest/organizations/{orgId}/schedules", method = RequestMethod.PUT)
    public ResponseEntity<OrganizationScheduleResource> updateOrgSchedule(
            @PathVariable Long orgId,
            @RequestBody OrganizationScheduleResource sentSchedule) {
        try {
            OrganizationSchedule schedule = service.updateOrgSchedule(orgId, sentSchedule.toSchedule());
            OrganizationScheduleResource resource = new OrganizationScheduleResourceAsm().toResource(schedule);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<OrganizationScheduleResource>(resource, headers, HttpStatus.OK);
        } catch (ScheduleNotFoundException exception) {
            throw new NotFoundException(exception);
        }catch (DateDidNotParseException exception){
            throw new BadRequestException(exception);
        }
    }
}
