package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class MainPage {
    private WebDriver driver; // Инициализация драйвера

    public static String baseUrl = "https://qa-scooter.praktikum-services.ru/"; // Базовая ссылка на сервис

    private By OrderHeaderButton = By.cssSelector(".Header_Nav__AGCXC>.Button_Button__ra12g"); // Кнопка заказа в хедере
    private By OrderBodyButton = By.cssSelector(".Home_FinishButton__1_cWm>.Button_Button__ra12g"); // кнопка заказа в теле

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkUrl() {
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }

    public void moveToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String revelImportantQuestionsAndGetAnswerText(By locator) {
        moveToElement(locator);
        WebElement element = driver.findElement(locator);
        element.click();

        WebElement grandParent = element.findElement(By.xpath("../.."));
        WebElement targetChild = grandParent.findElement(By.xpath(".//div[@class='accordion__panel']"));

        return targetChild.getText();
    }

    public void OrderFromHeader(){
        driver.findElement(OrderHeaderButton).click();
    }

    public void OrderFromBody(){
        moveToElement(OrderBodyButton);
        driver.findElement(OrderBodyButton).click();
    }

}
