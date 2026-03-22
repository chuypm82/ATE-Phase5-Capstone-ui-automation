package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import pages.SignupPage;
import pages.HomePage;
import pages.CartPage;

public class TestCases extends BaseTest {
	
	String NewEmail="user.tester105@test.com";
	String ExistingEmail="user.tester101@test.com";
	String Pass="Tester101";

    @Test(priority = 5, description = "Validate the SignUp as User scenario")
    public void validateSignup() throws InterruptedException {
        SignupPage signup = new SignupPage(driver);
        signup.signupPage1("John", "Doe", NewEmail, "1234567101", Pass, "Tester101");
        signup.signupPage2("1001 11 st N app 101", "ext 101", "Dallas", "10001", "TX", "United States");
        String successMessage = signup.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("Welcome!"), "Signup failed!");
    }

   @Test(priority = 1, description = "Validate login with correct credentials")
    public void validateLoginSuccess() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.login(ExistingEmail, Pass);
        // You can verify login success by checking homepage title or element
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Medicare - Home"), "Login failed with valid credentials!");
    }
   
    @Test(priority = 4, description = "Validate login with incorrect credentials")
    public void validateLoginFailure() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.login("john@example.com", "WrongPass");
        String errorMsg = login.getErrorMessage();
        Assert.assertTrue(errorMsg.toLowerCase().contains("invalid"),"Error message not displayed for invalid login!");
    }
    

    @Test(priority = 2, description = "Validate adding one item to the cart and checkout")
    public void validateAddOneItemToCart() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.login(ExistingEmail, Pass);
        HomePage home = new HomePage(driver);
        home.addOneItemAndGoToCart();
        CartPage cart = new CartPage(driver);        
        Assert.assertEquals(cart.getCartItemCount(), 1, "Cart does not have 1 item!");
        cart.clickCheckout();
        String orderMsg = cart.getOrderSuccessMessage();
        Assert.assertTrue(orderMsg.contains("Your Order is Confirmed!!"), "Checkout failed for 1 item!");
    }
    
    @Test(priority = 3, description = "Validate adding two items to the cart and checkout")
    public void validateAddTwoItemsToCart() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.login(ExistingEmail, Pass);
        HomePage home = new HomePage(driver);
        home.addOneItemAndGoToCart();
        CartPage cart = new CartPage(driver);
        cart.continueShoping();
        home.addSecondProductToCart();
        Assert.assertEquals(cart.getCartItemCount(), 2, "Cart does not have 2 items!");
        cart.clickCheckout();
        String orderMsg = cart.getOrderSuccessMessage();
        Assert.assertTrue(orderMsg.contains("Your Order is Confirmed!!"), "Checkout failed for 2 items!");
    }
}