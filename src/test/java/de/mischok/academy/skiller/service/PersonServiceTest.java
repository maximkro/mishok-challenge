package de.mischok.academy.skiller.service;

import de.mischok.academy.skiller.domain.Person;
import lombok.NonNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@Sql(scripts = {"classpath:person.sql"})
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @AfterEach
    private void tearDown() {
        personService.deleteAll();
    }

    @Test
    public void testGetAllPersons() {
        List<Person> allPersons = personService.getAllPersons();

        assertThat(allPersons, hasSize(1000));
        assertThat(allPersons.stream().map(Person::getId).collect(Collectors.toList()), is(LongStream.range(1, 1001).boxed().collect(Collectors.toList())));
    }

    @Test
    public void testGetAllPersonsWithComment() {
        List<Person> personsWithoutComment = personService.getAllPersonsWithComment();

        assertThat(personsWithoutComment, hasSize(172));

        List.of(18L, 20L, 34L, 40L, 41L, 43L, 47L, 49L, 53L, 56L, 58L, 64L, 69L, 70L, 72L, 73L, 83L, 84L, 90L, 95L, 106L, 112L, 115L, 116L, 118L, 119L, 126L, 132L, 134L, 136L, 143L, 153L, 158L, 161L, 162L, 163L, 185L, 188L, 189L, 191L, 193L, 222L, 230L, 240L, 243L, 244L, 247L, 250L, 252L, 256L, 262L, 273L, 289L, 296L, 304L, 305L, 308L, 310L, 311L, 313L, 323L, 326L, 329L, 342L, 343L, 350L, 353L, 362L, 364L, 369L, 372L, 374L, 375L, 388L, 392L, 397L, 400L, 405L, 409L, 430L, 434L, 442L, 444L, 451L, 461L, 475L, 476L, 480L, 484L, 499L, 503L, 514L, 525L, 528L, 532L, 542L, 547L, 563L, 565L, 570L, 579L, 583L, 586L, 594L, 597L, 602L, 610L, 614L, 615L, 616L, 619L, 625L, 630L, 633L, 642L, 647L, 664L, 681L, 682L, 683L, 684L, 695L, 697L, 701L, 713L, 719L, 728L, 732L, 739L, 740L, 741L, 742L, 761L, 763L, 779L, 787L, 789L, 792L, 800L, 805L, 822L, 824L, 828L, 833L, 842L, 861L, 867L, 876L, 884L, 887L, 895L, 897L, 907L, 921L, 928L, 936L, 940L, 942L, 943L, 945L, 950L, 953L, 956L, 961L, 962L, 967L, 970L, 979L, 982L, 990L, 995L, 996L)
                .forEach(id -> assertThat(ids(personsWithoutComment), hasItem(id)));
    }
    
    @Test
    public void testGetAllPersonsFromBrazilOrChina() {
        List<Person> personsFromBrazilAndChina = personService.getAllPersonsFromCountries("Brazil", "China");

        assertThat(personsFromBrazilAndChina, hasSize(19));

        List.of(19L, 126L, 141L, 175L, 192L, 260L, 353L, 415L, 472L, 568L, 575L, 740L, 749L, 761L, 769L, 777L, 861L, 868L, 985L)
                .forEach(id -> assertThat(ids(personsFromBrazilAndChina), hasItem(id)));
    }
    
    @Test
    public void testGetAllCountries() {
        List<String> countries = personService.getAllCountries();

        assertThat(countries, hasSize(38));

        List.of("Ukraine", "China", "Russia", "Indonesia", "New Zealand", "Lithuania", "Canada", "Japan", "South Korea", "Thailand", "Philippines", "Tunisia", "Cuba", "France", "Argentina", "Sweden", "Brazil", "United States", "Syria", "Finland", "Bangladesh", "Greece", "Iran", "Poland", "Jamaica", "Peru", "Italy", "Dominican Republic", "Dominica", "Czech Republic", "Tanzania", "Bolivia", "Serbia", "Portugal", "Iceland", "Niger", "Norway", "Malaysia")
                .forEach(country -> assertThat(countries, hasItem(country)));
    }
    
    @Test
    public void testGetAllPersonsBornInTheEighties() {
        List<Person> eightiesKids = personService.getAllPersonsBornInTheEighties();

        assertThat(eightiesKids, hasSize(217));

        List.of(10L, 12L, 22L, 23L, 24L, 30L, 31L, 32L, 41L, 56L, 59L, 63L, 71L, 73L, 99L, 102L, 107L, 110L, 120L, 122L, 123L, 126L, 132L, 136L, 140L, 141L, 142L, 146L, 153L, 175L, 176L, 179L, 181L, 182L, 185L, 193L, 194L, 197L, 211L, 215L, 218L, 224L, 225L, 235L, 246L, 250L, 254L, 257L, 259L, 260L, 266L, 272L, 274L, 279L, 281L, 288L, 295L, 300L, 304L, 305L, 319L, 322L, 331L, 334L, 335L, 336L, 339L, 348L, 354L, 361L, 366L, 367L, 372L, 375L, 378L, 379L, 382L, 387L, 391L, 392L, 400L, 401L, 404L, 405L, 406L, 416L, 417L, 420L, 423L, 425L, 430L, 440L, 443L, 444L, 451L, 456L, 458L, 464L, 467L, 473L, 477L, 480L, 483L, 485L, 507L, 509L, 511L, 513L, 527L, 534L, 538L, 546L, 547L, 548L, 558L, 560L, 568L, 571L, 575L, 576L, 582L, 586L, 592L, 594L, 607L, 611L, 617L, 622L, 637L, 638L, 645L, 646L, 650L, 652L, 658L, 661L, 665L, 668L, 670L, 672L, 674L, 675L, 682L, 690L, 693L, 695L, 700L, 702L, 705L, 708L, 709L, 710L, 714L, 716L, 722L, 724L, 729L, 733L, 750L, 759L, 764L, 769L, 776L, 778L, 782L, 783L, 787L, 795L, 804L, 805L, 806L, 809L, 810L, 813L, 818L, 822L, 823L, 825L, 827L, 834L, 836L, 840L, 844L, 846L, 851L, 856L, 862L, 863L, 865L, 867L, 884L, 886L, 898L, 900L, 909L, 910L, 912L, 924L, 927L, 934L, 937L, 939L, 940L, 946L, 958L, 959L, 963L, 964L, 967L, 972L, 976L, 981L, 986L, 989L, 992L, 996L, 999L)
                .forEach(id -> assertThat(ids(eightiesKids), hasItem(id)));
    }
    
    @Test
    public void testGetPersonCountByCountry() {
        Map<String, Long> personsCountByCountry = personService.getPersonCountByCountry();

        assertThat(personsCountByCountry.entrySet(), hasSize(38));

        Map.ofEntries(new AbstractMap.SimpleEntry<>("United States", 2L), new AbstractMap.SimpleEntry<>("Malaysia", 1L), new AbstractMap.SimpleEntry<>("Bolivia", 1L), new AbstractMap.SimpleEntry<>("Thailand", 2L), new AbstractMap.SimpleEntry<>("Portugal", 1L), new AbstractMap.SimpleEntry<>("Iceland", 1L), new AbstractMap.SimpleEntry<>("Syria", 1L), new AbstractMap.SimpleEntry<>("Russia", 5L), new AbstractMap.SimpleEntry<>("Greece", 2L), new AbstractMap.SimpleEntry<>("Sweden", 3L), new AbstractMap.SimpleEntry<>("South Korea", 2L), new AbstractMap.SimpleEntry<>("Iran", 1L), new AbstractMap.SimpleEntry<>("China", 17L), new AbstractMap.SimpleEntry<>("Poland", 7L), new AbstractMap.SimpleEntry<>("Brazil", 2L), new AbstractMap.SimpleEntry<>("Serbia", 1L), new AbstractMap.SimpleEntry<>("France", 3L), new AbstractMap.SimpleEntry<>("Lithuania", 1L), new AbstractMap.SimpleEntry<>("Tunisia", 2L), new AbstractMap.SimpleEntry<>("Argentina", 2L), new AbstractMap.SimpleEntry<>("Niger", 1L), new AbstractMap.SimpleEntry<>("Philippines", 2L), new AbstractMap.SimpleEntry<>("Japan", 3L), new AbstractMap.SimpleEntry<>("Tanzania", 1L), new AbstractMap.SimpleEntry<>("Ukraine", 2L), new AbstractMap.SimpleEntry<>("New Zealand", 1L), new AbstractMap.SimpleEntry<>("Cuba", 1L), new AbstractMap.SimpleEntry<>("Canada", 2L), new AbstractMap.SimpleEntry<>("Czech Republic", 2L), new AbstractMap.SimpleEntry<>("Bangladesh", 1L), new AbstractMap.SimpleEntry<>("Norway", 1L), new AbstractMap.SimpleEntry<>("Finland", 1L), new AbstractMap.SimpleEntry<>("Dominican Republic", 1L), new AbstractMap.SimpleEntry<>("Italy", 1L), new AbstractMap.SimpleEntry<>("Jamaica", 1L), new AbstractMap.SimpleEntry<>("Peru", 2L), new AbstractMap.SimpleEntry<>("Dominica", 1L), new AbstractMap.SimpleEntry<>("Indonesia", 11L))
                .forEach((key, value) -> assertThat(personsCountByCountry, hasEntry(key, value)));
    }

    private List<Long> ids(@NonNull List<Person> persons) {
        return persons.stream().map(Person::getId).collect(Collectors.toList());
    }
}
