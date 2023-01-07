package de.mischok.academy.skiller.service;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SyncService {

    @Autowired
    private PlanningSystem planningSystem;

    public void makeChanges(final List<HrSystemVacation> vacations) {
        // query the saved absences from planning system
        List<PlanningSystem.Absence> absences = planningSystem.getAllPlanningSystemAbsences();

        // query the persons from planning system
        List<PlanningSystem.Person> persons = planningSystem.getAllPlanningSystemPersons();

        // TODO: update planning system with vacation data from HR-system

        List<String> absencesIDs = absences.stream().map(a -> a.id).collect(Collectors.toList());

        List<HrSystemVacation> relevantVacations = vacations
                .stream()
                .filter(vac -> persons.stream().anyMatch(p -> vac.employeeID.equals(p.id)))
                .collect(Collectors.toList());



        for(HrSystemVacation vacation : relevantVacations) {
            boolean match = false;

            for (PlanningSystem.Absence oAbsence : absences) {
                if(vacation.employeeID.equals(oAbsence.personId)) {
                    if (vacation.startDate.isEqual(oAbsence.startDate) && vacation.endDate.isEqual(oAbsence.endDate)) {
                        if (!vacation.status.equals(oAbsence.status)) {
                            if (vacation.status.equals("Abgelehnt")) {
                                planningSystem.deleteBooking(oAbsence.id);
                            }
                            else {
                                planningSystem.updateBooking(vacation, oAbsence.id);
                            }

                        }
                        absencesIDs.remove(oAbsence.id);
                        match = true;

                    } else if (vacation.startDate.isEqual(oAbsence.startDate) || vacation.endDate.isEqual(oAbsence.endDate)) {
                        planningSystem.deleteBooking(oAbsence.id);
                        planningSystem.createBooking(vacation);
                        absencesIDs.remove(oAbsence.id);
                        match = true;
                    }
                }
            }
            if (!match) {
                if(!vacation.status.equals("Abgelehnt"))
                    planningSystem.createBooking(vacation);
            }

        }

        for (String absenceID : absencesIDs) {
            planningSystem.deleteBooking(absenceID);
        }

    }

    @Builder
    @Data
    public static class HrSystemVacation {
        LocalDate startDate;
        LocalDate endDate;
        String employeeID;
        String status;
    }
}
