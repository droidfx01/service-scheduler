package scheduler.core.services;


import scheduler.core.models.entities.Organization;
import scheduler.core.models.entities.OrganizationSchedule;

import java.util.Date;


/**
 * Created by C113554 on 05/13/2016.
 */
public interface OrganizationScheduleService {
    public OrganizationSchedule findOrgSchedule(Long orgId, Date beginDate, Date endDate);
    public OrganizationSchedule updateOrgSchedule(Long orgId, OrganizationSchedule data);
    public OrganizationSchedule deleteOrgSchedule(Long orgId, Date date);
}
