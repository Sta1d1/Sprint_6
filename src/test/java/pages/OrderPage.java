package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderPage {
    private WebDriver driver; // Инициализация драйвера

    public static String baseOrderUrl = MainPage.baseUrl + "order"; // Базовая ссылка на страницу оформления заказа

    // Куда заказываем
    private By inputName = By.cssSelector(".Order_Form__17u6u input[placeholder='* Имя']");
    private By inputLastName = By.cssSelector(".Order_Form__17u6u input[placeholder='* Фамилия']");
    private By inputAdress = By.cssSelector(".Order_Form__17u6u input[placeholder='* Адрес: куда привезти заказ']");
    private By inputMetro = By.cssSelector(".Order_Form__17u6u input[placeholder='* Станция метро']");
    private By inputPhone = By.cssSelector(".Order_Form__17u6u input[placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']"); // Кнопка "Далее"

    // Какой самокат и когда заказываем
    private By inputOrderDate = By.cssSelector(".Order_Form__17u6u input[placeholder='* Когда привезти самокат']");
    private By inputRentalPeriod = By.cssSelector(".Dropdown-arrow");
    private By inputColorScooterBlack = By.xpath("//div[@class='Order_Checkboxes__3lWSI']//*[text()='чёрный жемчуг']");
    private By inputColorScooterGray = By.xpath("//div[@class='Order_Checkboxes__3lWSI']//*[text()='серая безысходность']");
    private By inputCommentForCourier = By.cssSelector(".Order_Form__17u6u input[placeholder='Комментарий для курьера']");
    private By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");

    // Модалка подтверждения заказа
    private By confirmOrderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Да']");

    // Модалка что заказа создан
    private By orderCreatedModal = By.className("Order_Modal__YZ-d3");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkUrl() {
        assertEquals(driver.getCurrentUrl(), baseOrderUrl);
    }

    public void setName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    public void setLastName(String lastName) {
        driver.findElement(inputLastName).sendKeys(lastName);
    }

    public void setAddress(String address) {
        driver.findElement(inputAdress).sendKeys(address);
    }

    public void setMetro(String metro) {
        WebElement element = driver.findElement(inputMetro);
        element.click();
        element.sendKeys(metro);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement targetChild = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(".//*[@class='select-search__select']//*[text()='" + metro + "']/..")
        ));
        targetChild.click();
    }

    public void setPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
    }

    public void addOrderInformationAndClickNext(String name, String lastName, String address, String metro, String phone) {
        checkUrl();
        setName(name);
        setLastName(lastName);
        setAddress(address);
        setMetro(metro);
        setPhone(phone);
        driver.findElement(nextButton).click();
    }

    public void setOrderDate(String orderDate) {
        driver.findElement(inputOrderDate).sendKeys(orderDate);
    }

    public void setRentalPeriod(String rentalPeriod) {
        WebElement element = driver.findElement(inputRentalPeriod);
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement targetChild = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(".//*[@class='Dropdown-menu']//*[text()='" + rentalPeriod + "']/..")
        ));
        targetChild.click();
    }

    public void setColorScooter(String colorScooter) {
        if (colorScooter.equals("black")) {
            driver.findElement(inputColorScooterBlack).click();
        } else if (colorScooter.equals("gray")) {
            driver.findElement(inputColorScooterGray).click();
        } else {
            throw new IllegalArgumentException("Неверный цвет самоката");
        }
    }

    public void setCommentForCourier(String commentForCourier) {
        driver.findElement(inputCommentForCourier).sendKeys(commentForCourier);
    }

    public void setConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }

    public void addOrderScoterInformationAndClickOrderButton(String orderDate, String rentalPeriod, String colorScooter, String commentForCourier) {
        setOrderDate(orderDate);
        setRentalPeriod(rentalPeriod);
        setColorScooter(colorScooter);
        setCommentForCourier(commentForCourier);
        driver.findElement(orderButton).click();
    }

    public void checkOrderCreatedModal() {
        setConfirmOrderButton();
        driver.findElement(orderCreatedModal).isDisplayed();
    }


}
