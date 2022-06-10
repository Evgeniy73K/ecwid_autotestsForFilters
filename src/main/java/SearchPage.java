import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    public static String url = "https://buy-in-10-seconds.company.site/search";


    private WebDriver driver;
    private WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    private By inputKeyword = By.xpath("//input[@name=\"keyword\"]");
    private By inputPriceFrom = By.xpath("//div[@class=\"form-control form-control--empty form-control--small ec-filter__price-from\"]");
    private By inputPriceTo = By.xpath("form-control form-control--empty form-control--small ec-filter__price-to");
    private By inStockCheckbox = By.xpath("//input[@id=\"checkbox-in_stock\"]");
    private By onSaleCheckbox = By.xpath("//input[@id=\"checkbox-on_sale\"]");
    private By SortList = By.xpath("//div[@class=\"ec-filters__wrap\"]//div[text()=\"Сортировка\"]");
    private By priceSortByAsc = By.xpath("//input[@id=\"radio-sortBy-priceAsc\"]");
    private By priceSortByDesc = By.xpath("//input[@id=\"radio-sortBy-priceDesc\"]");
    private By productPriceList = By.xpath("//div[@class=\"grid-product__price-value ec-price-item\"]");
    private By saleLabel = By.xpath("//div[text()=\"Распродажа\"]");
    private By soldLabel = By.xpath("//div[text()=\"Распродано\"]");
    private By counterBreadCrumbs = By.xpath(" //a[contains(text(),'Поиск: нашлось 6')]");
    private By inStockCount = By.xpath("//label[@for=\"checkbox-in_stock\"]//div[@class=\"ec-filter__items-count ec-text-muted\"]");


    public SearchPage clickInStockCheckBox() {
        driver.findElement(inStockCheckbox).click();
        return this;
    }

    public SearchPage waitApFilter() {
        String value = getInstockAmount();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Поиск: нашлось " + value + "')]")));
        return this;
    }

    public String getInstockAmount() {
        return driver.findElement(inStockCount).getText();
    }

    public int getSoldLabels() {
        List<WebElement> soldLabels = driver.findElements(soldLabel);
        return soldLabels.size();
    }


}






