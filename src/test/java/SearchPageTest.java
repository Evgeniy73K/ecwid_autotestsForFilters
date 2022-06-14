import org.testng.Assert;
import org.testng.annotations.Test;



public class SearchPageTest extends Settings {


    @Test
    public void inStockFilterTest() {
        searchPage = new SearchPage(driver);
        searchPage.clickInStockCheckBox()
                .waitApFilter();
        Assert.assertEquals(searchPage.getSoldLabels(), 0, "Found sold products");

    }

    @Test
    public void onSaleFilterTest() {
        searchPage = new SearchPage(driver);
        searchPage.clickOnSaleCheckBox()
                .waitApFilter1();
        Assert.assertEquals(searchPage.getSaleLabels(), searchPage.getOnSaleAmount());
    }

    @Test
    public void sortPriceByAscTest() throws InterruptedException {
        searchPage = new SearchPage(driver);
        searchPage.clickSortList()
                .clickSortPriceByAsc();
        Thread.sleep(500);
        Assert.assertTrue(searchPage.getProductPriceList().equals(searchPage.sortByPriceAscList()), "Sorting failed");
    }

    @Test
    public void sortPriceByDescTest() throws  InterruptedException {
        searchPage = new SearchPage(driver);
        searchPage.clickSortList()
                .clickSortPriceByDesc();
        Thread.sleep(500);
        Assert.assertTrue(searchPage.getProductPriceList().equals(searchPage.sortByPriceDescList()), "Sorting failed");
    }
}
