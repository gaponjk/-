package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @BeforeEach
    void setUp() throws MalformedURLException {
        Main.companies.clear();
        Main.companies.add(new Company("Компания А", "Ко А", LocalDate.of(2024, 10, 1), "Москва, ул. Пушкина",
                LocalDate.of(1995, 3, 20), 0, "Аудитор 1", "+7 123 456 7890", "info@companya.com", "ИТ",
                "Разработка ПО", new URL("https://companya.com")));
        Main.companies.add(new Company("Компания Б", "Ко Б", LocalDate.of(2024, 10, 1), "Санкт-Петербург, ул. Ленина",
                LocalDate.of(2000, 5, 15), 150, "Аудитор 2", "+7 098 765 4321", "info@companyb.com", "Финансы",
                "Консалтинг", new URL("https://companyb.com")));
    }
    @Test
    void findCompanyByShortNameTest() {
        Company result = Main.findCompanyByShortName("Ко А");
        assertNotNull(result);
        assertEquals("Компания А", result.getFullName());

        result = Main.findCompanyByShortName("undefined");
        assertNull(result);
    }
    @Test
    void findCompanyByBranchTest() {
        ArrayList<Company> result = Main.findCompanyByBranch("фИнанСы");
        assertEquals(1, result.size());
        assertEquals("Компания Б", result.get(0).getFullName());

        result = Main.findCompanyByBranch("undefined");
        assertEquals(0, result.size());
    }
    @Test
    void findCompanyByDateOfFoundationTest() {
        ArrayList<Company> result = Main.finCompanyByDateOfFoundation(LocalDate.of(1994, 1, 1), LocalDate.of(2001, 1, 1));
        assertEquals(2, result.size());
        assertEquals("Компания А", result.get(0).getFullName());
        assertEquals("Компания Б", result.get(1).getFullName());
        result = Main.finCompanyByDateOfFoundation(LocalDate.of(2010, 1, 1), LocalDate.of(2015, 1, 1));
        assertEquals(0, result.size());
    }
    @Test
    void testFindCompanyByNumberOfEmployees() {
        ArrayList<Company> result = Main.findCompanyByNumberOfEmployees(50, 150);
        assertEquals(1, result.size());
        assertEquals("Компания Б", result.get(0).getFullName());

        result = Main.findCompanyByNumberOfEmployees(0, 20);
        assertEquals(1, result.size());
        assertEquals("Компания А", result.get(0).getFullName());
        result = Main.findCompanyByNumberOfEmployees(151, 200);
        assertEquals(0, result.size());
    }
}