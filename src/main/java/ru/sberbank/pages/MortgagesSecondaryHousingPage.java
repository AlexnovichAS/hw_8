package ru.sberbank.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MortgagesSecondaryHousingPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//iframe[contains(@sandbox,'allow-forms') and @title='Основной контент']")
    private WebElement iframeMainContent;

    @FindBy(xpath = "//div[contains(@class,'dc-input__input-container')]/label")
    private List<WebElement> boxFill;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//li[@data-e2e-id]")
    private List<WebElement> valueToCheck;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//li[@data-e2e-id]")
    private List<WebElement> valueToCheckRequiredIncome;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//span[text()='Ежемесячный платеж']/following-sibling::span")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[text()='Своя ставка']")
    private WebElement ownRate;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[contains(text(),'Скидка') and contains(text(),'при покупке недвижимости')]")
    private WebElement propertyPurchaseDiscount;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[text()='Страхование жизни и здоровья']")
    private WebElement lifeAndHealthInsurance;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[text()='Электронная регистрация сделки']")
    private WebElement electronicTransactionRegistration;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//span[text()='Процентная ставка']/following-sibling::span")
    private WebElement interestRate;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//span[text()='Сумма кредита']/following-sibling::span")
    private WebElement creditAmount;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//span[text()='Налоговый вычет']/following-sibling::span")
    private WebElement taxDeduction;

    @FindBy(xpath = "//div[contains(@class,'ppr-container--inline')]//span[text()='Необходимый доход']/following-sibling::span")
    private WebElement necessaryIncome;


    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
    @Step("Проверяем что открылась страница 'Ипотека на вторичное жильё от'")
    public MortgagesSecondaryHousingPage checkOpenInsurancePage(String namePage) {
        Assert.assertEquals("Заголовок: " + namePage + " отсутствует/не соответствует требуемому",
                namePage, title.getText());
        return this;
    }

    /**
     * Метод заполнения полей
     *
     * @param nameField - имя веб элемента, поля ввода
     * @param value     - значение вводимое в поле
     * @return MortgagesSecondaryHousingPage - т.е. остаемся на этой странице
     */
    @Step("Заполняем поле '{nameField}' значением '{value}'")
    public MortgagesSecondaryHousingPage fillField(String nameField, String value) {
        WebElement element = null;
        for (int i = 0; i < boxFill.size(); i++) {
            if (boxFill.get(i).findElement(By.xpath(".//label")).getText().equalsIgnoreCase(nameField)) {
                element = boxFill.get(i).findElement(By.xpath(".//input"));
                fillInputField(element, value);
                break;
            }
            Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице" + "'Ипотека на вторичное жильё от'");
        }
        wait.until(ExpectedConditions.attributeToBe(element, "value", value));
        if (element != null) {
            Assert.assertEquals("Поле: " + nameField + " было заполнено некорректно",
                    value, element.getText());
        }
        return this;
    }

    /**
     * Управление чекбоксами при расчете ипотеки
     *
     * @param nameCheckbox - имя веб элемента
     * @param nameCheckbox - имя веб элемента
     * @return MortgagesSecondaryHousingPage - т.е. остаемся на этой странице
     */
    @Step("Проставляем услугу '{nameField}' проверяем значение атрибута услуги '{value}'")
    public MortgagesSecondaryHousingPage processTicks(String nameCheckbox, String value) {
        WebElement element = null;
        switch (nameCheckbox) {
            case "Своя ставка":
                element = monthlyPayment;
                if (!monthlyPayment.getAttribute("aria-checked").equals(value)) {
                    element.click();
                }
                break;
            case "Скидка 0,3% при покупке недвижимости на Домклик":
                element = interestRate;
                if (!interestRate.getAttribute("aria-checked").equals(value)) {
                    element.click();
                }
                break;
            case "Страхование жизни и здоровья":
                element = creditAmount;
                if (!creditAmount.getAttribute("aria-checked").equals(value)) {
                    element.click();
                }
                break;
            case "Электронная регистрация сделки":
                element = taxDeduction;
                if (!taxDeduction.getAttribute("aria-checked").equals(value)) {
                    element.click();
                }
                break;
            default:
                Assert.fail("Чекбокс с наименованием '" + nameCheckbox + "' отсутствует на странице " +
                        "'Ипотека на вторичное жильё от'");

        }
        element = element.findElement(By.xpath("./../..//input"));
        Assert.assertEquals("Проверка ошибки у поля '" + nameCheckbox + "' была не пройдена",
                value, element.getAttribute("aria-checked"));
        return this;
    }

    /**
     * Проверка ошибки относящаяся к конкретному полю на форме
     *
     * @param nameField - имя веб элемента
     * @param value     - значение проверяемое в поле
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    @Step("Проверяем что в поле '{nameField}' отображается значение '{value}'")
    public MortgagesSecondaryHousingPage checkFieldValues(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Ежемесячный платеж":
                element = monthlyPayment;
                break;
            case "Процентная ставка":
                element = interestRate;
                break;
            case "Сумма кредита":
                element = creditAmount;
                break;
            case "Налоговый вычет":
                element = taxDeduction;
                break;
            case "Необходимый доход":
                element = necessaryIncome;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Ипотека на вторичное жильё от'");

        }
        Assert.assertEquals("Проверка ошибки у поля '" + nameField + "' была не пройдена",
                value, element.getText());
        return this;
    }
}
