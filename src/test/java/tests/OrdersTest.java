package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

public class OrdersTest {
    private WebDriver driver; // Инициализация драйвера


    @BeforeEach
    void browserSetup() {
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver); // Создаём объект

        driver.manage().window().maximize();
        driver.get(MainPage.baseUrl);
        mainPage.checkUrl(); // Проверяем что находимся на странице
    }

    @Test
    void testOrderFromHeader() {
        MainPage mainPage = new MainPage(driver); // Создаем объект
        mainPage.OrderFromHeader(); // Клик по кнопке заказать
        OrderPage orderPage = new OrderPage(driver); // Создаем объект
        orderPage.addOrderInformationAndClickNext("Иван", "Иванов", "г. Москва, ул. Ленина, д. 1", "Марьино", "+79991234567");
        orderPage.addOrderScoterInformationAndClickOrderButton("25.07.2026", "двое суток", "black","Тестовый комментарий");
        orderPage.checkOrderCreatedModal();
    }

    @Test
    void testOrderFromBody() {
        MainPage mainPage = new MainPage(driver); // Создаем объект
        mainPage.OrderFromBody(); // Клик по кнопке заказать
        OrderPage orderPage = new OrderPage(driver); // Создаем объект
        orderPage.addOrderInformationAndClickNext("Антон", "Антонов", "г. Москва, ул. Есенина, д. 5", "Черкизовская", "+71231265531");
        orderPage.addOrderScoterInformationAndClickOrderButton("21.04.2027", "пятеро суток", "gray","Комментарий для теста");
        orderPage.checkOrderCreatedModal();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
