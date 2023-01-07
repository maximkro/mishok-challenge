package de.mischok.academy.skiller.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlanningSystem {

    List<Person> getAllPlanningSystemPersons() {
        Person res1 = Person.builder().id("111").firstName("f1").lastName("l1").build();
        Person res2 = Person.builder().id("222").firstName("f2").lastName("l2").build();
        Person res3 = Person.builder().id("333").firstName("f3").lastName("l3").build();
        Person res4 = Person.builder().id("444").firstName("f4").lastName("l4").build();
        Person res5 = Person.builder().id("555").firstName("f5").lastName("l5").build();
        Person res6 = Person.builder().id("666").firstName("f6").lastName("l6").build();
        Person res7 = Person.builder().id("777").firstName("f7").lastName("l7").build();
        Person res8 = Person.builder().id("888").firstName("f8").lastName("l8").build();
        Person res9 = Person.builder().id("999").firstName("f9").lastName("l9").build();
        Person res10 = Person.builder().id("101010").firstName("f10").lastName("l10").build();
        Person res11 = Person.builder().id("111111").firstName("f11").lastName("l11").build();
        Person res12 = Person.builder().id("121212").firstName("f12").lastName("l12").build();
        Person res14 = Person.builder().id("141414").firstName("f14").lastName("l14").build();
        Person res15 = Person.builder().id("151515").firstName("f15").lastName("l15").build();

        return List.of(res1, res2, res3, res4, res5, res6, res7, res8, res9, res10, res11, res12, res14, res15);
    }

    List<Absence> getAllPlanningSystemAbsences() {
        Absence b2_1 = Absence.builder().id("bok2_1").startDate(localDate(6)).endDate(localDate(10)).personId("222").status("Genehmigt").build();
        Absence b2_2 = Absence.builder().id("bok2_2").startDate(localDate(20)).endDate(localDate(24)).personId("222").status("Genehmigt").build();

        Absence b3_3 = Absence.builder().id("bok3_3").startDate(localDate(30)).endDate(localDate(40)).personId("333").status("Genehmigt").build();
        Absence b3_4 = Absence.builder().id("bok3_4").startDate(localDate(50)).endDate(localDate(60)).personId("333").status("Genehmigt").build();

        Absence b4_1 = Absence.builder().id("bok4_1").startDate(localDate(6)).endDate(localDate(10)).personId("444").status("Genehmigt").build();
        Absence b4_2 = Absence.builder().id("bok4_2").startDate(localDate(20)).endDate(localDate(24)).personId("444").status("Genehmigt").build();

        Absence b5_2 = Absence.builder().id("bok5_2").startDate(localDate(20)).endDate(localDate(24)).personId("555").status("Genehmigt").build();
        Absence b5_3 = Absence.builder().id("bok5_3").startDate(localDate(30)).endDate(localDate(40)).personId("555").status("Genehmigt").build();

        Absence b6_1 = Absence.builder().id("bok6_1").startDate(localDate(6)).endDate(localDate(10)).personId("666").status("Genehmigt").build();
        Absence b6_2 = Absence.builder().id("bok6_2").startDate(localDate(20)).endDate(localDate(24)).personId("666").status("Genehmigt").build();
        Absence b6_3 = Absence.builder().id("bok6_3").startDate(localDate(30)).endDate(localDate(40)).personId("666").status("Genehmigt").build();

        Absence b7_1 = Absence.builder().id("bok7_1").startDate(localDate(6)).endDate(localDate(10)).personId("777").status("Genehmigt").build();
        Absence b7_2 = Absence.builder().id("bok7_2").startDate(localDate(20)).endDate(localDate(24)).personId("777").status("Genehmigt").build();

        Absence b8_1 = Absence.builder().id("bok8_1").startDate(localDate(6)).endDate(localDate(10)).personId("888").status("Genehmigt").build();
        Absence b8_3 = Absence.builder().id("bok8_3").startDate(localDate(30)).endDate(localDate(40)).personId("888").status("Genehmigt").build();

        Absence b9_1 = Absence.builder().id("bok9_1").startDate(localDate(6)).endDate(localDate(10)).personId("999").status("Genehmigt").build();
        Absence b9_2 = Absence.builder().id("bok9_2").startDate(localDate(20)).endDate(localDate(24)).personId("999").status("Genehmigt").build();
        Absence b9_3 = Absence.builder().id("bok9_3").startDate(localDate(30)).endDate(localDate(40)).personId("999").status("Genehmigt").build();

        Absence b10_1 = Absence.builder().id("bok10_1").startDate(localDate(6)).endDate(localDate(10)).personId("101010").status("Genehmigt").build();
        Absence b10_2 = Absence.builder().id("bok10_2").startDate(localDate(20)).endDate(localDate(24)).personId("101010").status("Geplant").build();
        Absence b10_3 = Absence.builder().id("bok10_3").startDate(localDate(30)).endDate(localDate(40)).personId("101010").status("Geplant").build();

        Absence b11_1 = Absence.builder().id("bok11_1").startDate(localDate(6)).endDate(localDate(10)).personId("111111").status("Genehmigt").build();
        Absence b11_2 = Absence.builder().id("bok11_2").startDate(localDate(20)).endDate(localDate(24)).personId("111111").status("Genehmigt").build();

        Absence b12_1 = Absence.builder().id("bok12_1").startDate(localDate(-7)).endDate(localDate(-1)).personId("121212").status("Genehmigt").build();

        Absence b14_1 = Absence.builder().id("bok14_1").startDate(localDate(6)).endDate(localDate(10)).personId("141414").status("Geplant").build();

        return List.of(b2_1, b2_2, b3_3, b3_4, b4_1, b4_2, b5_2, b5_3, b6_1, b6_2, b6_3, b7_1, b7_2, b8_1, b8_3, b9_1, b9_2, b9_3, b10_1, b10_2, b10_3, b11_1, b11_2, b12_1, b14_1);
    }

    @SuppressWarnings("unused")
    void createBooking(SyncService.HrSystemVacation hrSystemVacation) {
        // NO-OP, real system would be called here via HTTP call
    }

    @SuppressWarnings("unused")
    void updateBooking(SyncService.HrSystemVacation hrSystemVacation, String planningSystemVacationId) {
        // NO-OP, real system would be called here via HTTP call
    }

    @SuppressWarnings("unused")
    void deleteBooking(String planningSystemVacationId) {
        // NO-OP, real system would be called here via HTTP call
    }

    private static LocalDate localDate(int offsetDays) {
        return LocalDate.now().plusDays(offsetDays);
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Absence {
        String id;
        LocalDate startDate;
        LocalDate endDate;
        String personId;
        String status;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person {
        String id;
        String firstName;
        String lastName;
    }
}
