package de.mischok.academy.skiller.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SyncServiceTest {

    @SpyBean
    private PlanningSystem planningSystem;

    @Autowired
    private SyncService syncService;

    private List<SyncService.HrSystemVacation> vacations;

    @SuppressWarnings("FieldCanBeLocal")
    private SyncService.HrSystemVacation v1_1, v1_2, v3_1, v3_2, v4_1, v4_2, v4_3, v5_1, v5_2, v5_3, v6_2, v6_3, v7_1, v7_3, v8_1, v8_2, v8_3, v9_1, v9_3, v10_1, v10_3, v11_1, v11_2, v12_1, v13_1, v14_1, v15_1;

    @BeforeEach
    public void setUp() {
        v1_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(10)).status("Genehmigt").employeeID("111").build();
        v1_2 = SyncService.HrSystemVacation.builder().startDate(localDate(20)).endDate(localDate(24)).status("Genehmigt").employeeID("111").build();

        v3_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(10)).status("Genehmigt").employeeID("333").build();
        v3_2 = SyncService.HrSystemVacation.builder().startDate(localDate(20)).endDate(localDate(24)).status("Genehmigt").employeeID("333").build();

        v4_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(10)).status("Genehmigt").employeeID("444").build();
        v4_2 = SyncService.HrSystemVacation.builder().startDate(localDate(20)).endDate(localDate(24)).status("Genehmigt").employeeID("444").build();
        v4_3 = SyncService.HrSystemVacation.builder().startDate(localDate(30)).endDate(localDate(40)).status("Genehmigt").employeeID("444").build();

        v5_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(10)).status("Genehmigt").employeeID("555").build();
        v5_2 = SyncService.HrSystemVacation.builder().startDate(localDate(20)).endDate(localDate(24)).status("Genehmigt").employeeID("555").build();
        v5_3 = SyncService.HrSystemVacation.builder().startDate(localDate(30)).endDate(localDate(40)).status("Genehmigt").employeeID("555").build();

        v6_2 = SyncService.HrSystemVacation.builder().startDate(localDate(20)).endDate(localDate(24)).status("Genehmigt").employeeID("666").build();
        v6_3 = SyncService.HrSystemVacation.builder().startDate(localDate(30)).endDate(localDate(40)).status("Genehmigt").employeeID("666").build();

        v7_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(10)).status("Genehmigt").employeeID("777").build();
        v7_3 = SyncService.HrSystemVacation.builder().startDate(localDate(30)).endDate(localDate(40)).status("Genehmigt").employeeID("777").build();

        v8_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(10)).status("Genehmigt").employeeID("888").build();
        v8_2 = SyncService.HrSystemVacation.builder().startDate(localDate(20)).endDate(localDate(24)).status("Genehmigt").employeeID("888").build();
        v8_3 = SyncService.HrSystemVacation.builder().startDate(localDate(30)).endDate(localDate(40)).status("Genehmigt").employeeID("888").build();

        v9_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(10)).status("Genehmigt").employeeID("999").build();
        v9_3 = SyncService.HrSystemVacation.builder().startDate(localDate(30)).endDate(localDate(40)).status("Genehmigt").employeeID("999").build();

        v10_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(10)).status("Genehmigt").employeeID("101010").build();
        v10_3 = SyncService.HrSystemVacation.builder().startDate(localDate(30)).endDate(localDate(40)).status("Genehmigt").employeeID("101010").build();

        v11_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(11)).status("Genehmigt").employeeID("111111").build();
        v11_2 = SyncService.HrSystemVacation.builder().startDate(localDate(19)).endDate(localDate(24)).status("Genehmigt").employeeID("111111").build();

        v12_1 = SyncService.HrSystemVacation.builder().startDate(localDate(-7)).endDate(localDate(-1)).status("Genommen").employeeID("121212").build();

        v13_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(10)).status("Geplant").employeeID("131313").build();

        v14_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(10)).status("Abgelehnt").employeeID("141414").build();

        v15_1 = SyncService.HrSystemVacation.builder().startDate(localDate(6)).endDate(localDate(10)).status("Abgelehnt").employeeID("151515").build();

        vacations = new ArrayList<>(List.of(v1_1, v1_2, v3_1, v3_2, v4_1, v4_2, v4_3, v5_1, v5_2, v5_3, v6_2, v6_3, v7_1, v7_3, v8_1, v8_2, v8_3, v9_1, v9_3, v10_1, v10_3, v11_1, v11_2, v12_1, v13_1, v14_1, v15_1));
    }

    private LocalDate localDate(int offsetDays) {
        return LocalDate.now().plusDays(offsetDays);
    }

    @Test
    public void testMakeChangesResource1() {
        syncService.makeChanges(vacations);

        verify(planningSystem, times(1)).createBooking(eq(v1_1));
        verify(planningSystem, times(1)).createBooking(eq(v1_2));
    }

    @Test
    public void testMakeChangesResource2() {
        syncService.makeChanges(vacations);

        verify(planningSystem, times(1)).deleteBooking(eq("bok2_1"));
        verify(planningSystem, times(1)).deleteBooking(eq("bok2_2"));
    }

    @Test
    public void testMakeChangesResource3() {
        syncService.makeChanges(vacations);

        verify(planningSystem, times(1)).createBooking(eq(v3_1));
        verify(planningSystem, times(1)).createBooking(eq(v3_2));

        verify(planningSystem, times(1)).deleteBooking(eq("bok3_3"));
        verify(planningSystem, times(1)).deleteBooking(eq("bok3_4"));
    }

    @Test
    public void testMakeChangesResource4() {
        syncService.makeChanges(vacations);

        verify(planningSystem, never()).deleteBooking(eq("bok4_1"));
        verify(planningSystem, never()).deleteBooking(eq("bok4_2"));

        verify(planningSystem, never()).updateBooking(eq(v4_1), eq("bok4_1"));
        verify(planningSystem, never()).updateBooking(eq(v4_2), eq("bok4_2"));

        verify(planningSystem, times(1)).createBooking(eq(v4_3));
    }

    @Test
    public void testMakeChangesResource5() {
        syncService.makeChanges(vacations);

        verify(planningSystem, times(1)).createBooking(eq(v5_1));
        verify(planningSystem, never()).createBooking(eq(v5_2));
        verify(planningSystem, never()).createBooking(eq(v5_3));

        verify(planningSystem, never()).updateBooking(eq(v5_2), eq("bok5_2"));
        verify(planningSystem, never()).updateBooking(eq(v5_3), eq("bok5_3"));

        verify(planningSystem, never()).deleteBooking(eq("bok5_2"));
        verify(planningSystem, never()).deleteBooking(eq("bok5_3"));
    }

    @Test
    public void testMakeChangesResource6() {
        syncService.makeChanges(vacations);

        verify(planningSystem, never()).createBooking(eq(v6_2));
        verify(planningSystem, never()).createBooking(eq(v6_3));

        verify(planningSystem, never()).updateBooking(eq(v6_2), eq("bok6_2"));
        verify(planningSystem, never()).updateBooking(eq(v6_3), eq("bok6_3"));

        verify(planningSystem, times(1)).deleteBooking(eq("bok6_1"));
    }

    @Test
    public void testMakeChangesResource7() {
        syncService.makeChanges(vacations);

        verify(planningSystem, never()).deleteBooking(eq("bok7_1"));
        verify(planningSystem, never()).createBooking(eq(v7_1));
        verify(planningSystem, never()).updateBooking(eq(v7_1), eq("bok7_1"));

        verify(planningSystem, times(1)).deleteBooking(eq("bok7_2"));

        verify(planningSystem, times(1)).createBooking(eq(v7_3));
    }

    @Test
    public void testMakeChangesResource8() {
        syncService.makeChanges(vacations);

        verify(planningSystem, times(1)).createBooking(eq(v8_2));

        verify(planningSystem, never()).deleteBooking(eq("bok8_1"));

        verify(planningSystem, never()).deleteBooking(eq("bok8_3"));
    }

    @Test
    public void testMakeChangesResource9() {
        syncService.makeChanges(vacations);

        verify(planningSystem, never()).createBooking(eq(v9_1));
        verify(planningSystem, never()).createBooking(eq(v9_3));

        verify(planningSystem, never()).deleteBooking(eq("bok9_1"));
        verify(planningSystem, never()).deleteBooking(eq("bok9_3"));
        verify(planningSystem, times(1)).deleteBooking(eq("bok9_2"));
    }

    @Test
    public void testMakeChangesResource10() {
        syncService.makeChanges(vacations);

        verify(planningSystem, never()).createBooking(eq(v10_1));
        verify(planningSystem, never()).deleteBooking(eq("bok10_1"));
        verify(planningSystem, never()).updateBooking(eq(v10_1), eq("bok10_1"));

        verify(planningSystem, times(1)).deleteBooking(eq("bok10_2"));

        verify(planningSystem, never()).createBooking(eq(v10_3));
        verify(planningSystem, never()).deleteBooking(eq("bok10_3"));
        verify(planningSystem, times(1)).updateBooking(eq(v10_3), eq("bok10_3"));
    }

    @Test
    public void testMakeChangesResource11() {
        syncService.makeChanges(vacations);

        verify(planningSystem, times(1)).deleteBooking(eq("bok11_1"));
        verify(planningSystem, times(1)).createBooking(eq(v11_1));
        verify(planningSystem, never()).updateBooking(eq(v11_1), eq("bok11_1"));

        verify(planningSystem, times(1)).deleteBooking(eq("bok11_2"));
        verify(planningSystem, times(1)).createBooking(eq(v11_2));
        verify(planningSystem, never()).updateBooking(eq(v11_2), eq("bok11_2"));
    }

    @Test
    public void testMakeChangesResource12() {
        syncService.makeChanges(vacations);

        verify(planningSystem, never()).deleteBooking(eq("bok12_1"));
        verify(planningSystem, never()).createBooking(eq(v12_1));
        verify(planningSystem, times(1)).updateBooking(eq(v12_1), eq("bok12_1"));
    }

    @Test
    public void testMakeChangesResource13() {
        syncService.makeChanges(vacations);

        verify(planningSystem, never()).deleteBooking(eq("bok13_1"));
        verify(planningSystem, never()).createBooking(eq(v13_1));
        verify(planningSystem, never()).updateBooking(eq(v13_1), eq("bok13_1"));
    }

    @Test
    public void testMakeChangesResource14() {
        syncService.makeChanges(vacations);

        verify(planningSystem, times(1)).deleteBooking(eq("bok14_1"));
        verify(planningSystem, never()).createBooking(eq(v14_1));
        verify(planningSystem, never()).updateBooking(eq(v14_1), eq("bok14_1"));
    }

    @Test
    public void testMakeChangesResource15() {
        syncService.makeChanges(vacations);

        verify(planningSystem, never()).createBooking(eq(v15_1));
    }
}
