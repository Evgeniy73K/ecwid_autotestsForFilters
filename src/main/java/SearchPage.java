import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    private By sortList = By.xpath("//div[@class=\"ec-filters__wrap\"]//div[text()=\"Сортировка\"]");
    private By priceSortByAsc = By.xpath("//input[@id=\"radio-sortBy-priceAsc\"]");
    private By priceSortByDesc = By.xpath("//input[@id=\"radio-sortBy-priceDesc\"]");
    private By productPriceList = By.xpath("//div[@class=\"grid-product__price-value ec-price-item\"]");
    private By saleLabel = By.xpath("//div[text()=\"Распродажа\"]");
    private By soldLabel = By.xpath("//div[text()=\"Распродано\"]");
    private By counterBreadCrumbs = By.xpath(" //a[contains(text(),'Поиск: нашлось 6')]");
    private By inStockCount = By.xpath("//label[@for=\"checkbox-in_stock\"]//div[@class=\"ec-filter__items-count ec-text-muted\"]");
    private By inSaleCount = By.xpath("//label[@for=\"checkbox-on_sale\"]//div[@class=\"ec-filter__items-count ec-text-muted\"]");


    public SearchPage clickInStockCheckBox() {
        driver.findElement(inStockCheckbox).click();
        return this;
    }
    public SearchPage clickOnSaleCheckBox() {
        driver.findElement(onSaleCheckbox).click();
        return this;
    }

    public SearchPage waitApFilter() {
        String value = getInstockAmount();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Поиск: нашлось " + value + "')]")));
        return this;
    }

    public SearchPage waitApFilter1() {
        int value = getOnSaleAmount();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Поиск: "+value+" совпадение')]")));
        return this;
    }

    public String getInstockAmount() {
        return driver.findElement(inStockCount).getText();
    }

    public int getOnSaleAmount() {
        return Integer.valueOf(driver.findElement(inSaleCount).getText());
    }

    public int getSoldLabels() {
        List<WebElement> soldLabels = driver.findElements(soldLabel);
        return soldLabels.size();
    }

    public int getSaleLabels() {
        List<WebElement> saleLabels = driver.findElements(saleLabel);
        return saleLabels.size();
    }

    public SearchPage clickSortList() {
        driver.findElement(sortList).click();
        return this;
    }

    public SearchPage clickSortPriceByAsc() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(priceSortByAsc).click();
        wait.until(ExpectedConditions.urlToBe("https://buy-in-10-seconds.company.site/search?sort=priceAsc"));
        return this;
    }

    public SearchPage clickSortPriceByDesc() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(priceSortByDesc).click();
        wait.until(ExpectedConditions.urlToBe("https://buy-in-10-seconds.company.site/search?sort=priceDesc"));
        return this;
    }



    public List<String> getProductPriceList() {
        List<String> currentPriceList = new ArrayList<>();
        List<WebElement> priceList = driver.findElements(productPriceList);
        for(int i = 0; i < priceList.size(); i++){
            currentPriceList.add(i,priceList.get(i).getText());
        }
        return currentPriceList;
    }

    public List<String> sortByPriceAscList() {
        List<String> sortedList = getProductPriceList().stream().sorted().collect(Collectors.toList());
        return sortedList;
    }

    public List<String> sortByPriceDescList() {
        List<String> sortedList = getProductPriceList().stream().sorted().collect(Collectors.toList());
        Collections.reverse(sortedList);
        return sortedList;
    }







}






