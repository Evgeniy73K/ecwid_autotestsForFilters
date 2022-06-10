import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchPageTest extends Settings {


    @Test
    public void onSaleCheckBoxTest() {
        searchPage = new SearchPage(driver);
        searchPage.clickInStockCheckBox()
                .waitApFilter();
        Assert.assertTrue(searchPage.getSoldLabels() == 0, "Found sold products");









    }
}
