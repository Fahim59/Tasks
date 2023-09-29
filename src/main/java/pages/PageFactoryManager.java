package pages;

import org.openqa.selenium.WebDriver;

public class PageFactoryManager {

    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static CustomerPage customerPage;
    private static ProductPage productPage;
    private static SalesPage salesPage;

    public static LoginPage getloginPage(WebDriver driver){
        return loginPage == null ? new LoginPage(driver) : loginPage;
    }

    public static DashboardPage getDashboardPage(WebDriver driver){
        return dashboardPage == null ? new DashboardPage(driver) : dashboardPage;
    }

    public static CustomerPage getCustomerPage(WebDriver driver){
        return customerPage == null ? new CustomerPage(driver) : customerPage;
    }

    public static ProductPage getProductPage(WebDriver driver){
        return productPage == null ? new ProductPage(driver) : productPage;
    }

    public static SalesPage getSalesPage(WebDriver driver){
        return salesPage == null ? new SalesPage(driver) : salesPage;
    }
}
