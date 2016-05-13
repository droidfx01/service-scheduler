package scheduler.core.services;


import scheduler.core.models.entities.Organization;
import scheduler.core.models.entities.OrganizationSchedule;

import java.util.Date;


/**
 * Created by C113554 on 05/13/2016.
 */
public interface OrganizationScheduleService {
    public OrganizationSchedule findOrgSchedule(Long orgId, Date date);
    public OrganizationSchedule createOrgSchedule(Long orgId, Date date, OrganizationSchedule data);
    public OrganizationSchedule updateOrgSchedule(Long orgId, Date date, OrganizationSchedule data);
    public OrganizationSchedule deleteOrgSchedule(Long orgId, Date date);
}
