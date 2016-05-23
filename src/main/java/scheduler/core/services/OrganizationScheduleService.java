package scheduler.core.services;


import org.joda.time.DateTime;
import scheduler.core.models.entities.Organization;
import scheduler.core.models.entities.OrganizationSchedule;
import scheduler.core.services.util.OrganizationScheduleList;

import java.util.Date;


/**
 * Created by C113554 on 05/13/2016.
 */
public interface OrganizationScheduleService {
    public OrganizationSchedule findOrgSchedule(Long orgId, DateTime date);
    public OrganizationScheduleList findAllOrgSchedule(Long orgId);
    public OrganizationScheduleList findOrgSchedule(Long orgId, DateTime beginDate, DateTime endDate);
    public OrganizationSchedule updateOrgSchedule(Long orgId, OrganizationSchedule data);

    public OrganizationSchedule deleteOrgSchedule(Long orgId, DateTime date);
    public OrganizationScheduleList deleteOrgSchedule(Long orgId, DateTime beginDate, DateTime endDate);
}
