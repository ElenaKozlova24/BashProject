package com.example.tests;

import com.example.otus.page.HomePage;
import com.example.otus.page.CalendarPage;
import com.example.otus.page.CoursePage;
import factory.WebDriverFactory;
import factory.impl.ChromeSettings;
import factory.impl.IChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.fail;

public class InfoAboutCoursesTest {
    private static final Logger logger = LogManager.getLogger(InfoAboutCoursesTest.class);
    private WebDriver driver;
    private HomePage homePage;
    private CoursePage coursePage;
    private CalendarPage calendarPage;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        String urlStand = System.getProperty("urlStand");
        String browserName = System.getProperty("browserName");
        String browserVersion = System.getProperty("browserVersion");
        String seleniumHubUrl = System.getProperty("selenium.hub.url");

        IChromeOptions chromeSettings = new ChromeSettings();

        driver = WebDriverFactory.createNewDriver(browserName, browserVersion, seleniumHubUrl, chromeSettings.settings());
        homePage = new HomePage(driver);
        coursePage = new CoursePage(driver);
        calendarPage = new CalendarPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testClickOnTestingElementAndCheckCourseCount() {
        logger.info("Начало теста");

        homePage.open(System.getProperty("urlStand"));

        homePage.clickOnTestingElement();

        int cardCount = homePage.getCourseCardCount();
        logger.info("Количество карточек: " + cardCount);

        if (cardCount > 0) {
            homePage.clickOnFirstCourseCard();
            coursePage.verifyCoursePage();
            coursePage.verifyCourseDuration();
            coursePage.verifyCourseFormat();

            calendarPage.navigateToCalendar();
            calendarPage.verifyEventCards();
        } else {
            fail("Нет карточек курсов");
        }

        logger.info("Тест завершен успешно");
    }
}
