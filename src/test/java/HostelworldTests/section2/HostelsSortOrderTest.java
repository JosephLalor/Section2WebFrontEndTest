/* 
 * 
 * @author Joseph Lalor
 * 
 */

package HostelworldTests.section2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import edu.emory.mathcs.backport.java.util.Arrays;
import HostelworldFramework.pageObjects.*;

public class HostelsSortOrderTest 
{
	// Variables
	String programClassName = "HostelsAlphabeticalOrderTest";
	String searchText = "dublin";
	String nameOptionValue = "sortByName";
	String priceOptionValue = "sortByPrice";
	
	//URL variables
	String inputHomepageURL = "http://t.hostelworld.com";
	String homepageURL = "http://www.hostelworld.com";
	String listPageURL = "http://www.hostelworld.com/search?search_keywords=Dublin%2C+Ireland&country=Ireland&city=Dublin&date_from=2016-06-08&date_to=2016-06-11&number_of_guests=2";
	String pageTitle = "Hostels in Dublin | Book Dublin Hostel Online at Hostelworld.com";
	
	WebDriver driver;
	WebDriverWait wait;
	
	// Page object classes
	Homepage homepage;
	PropertyListPage propertyListPage;
	
	boolean instTitleFound;
	boolean orderNumberFound;
	
	
	
	@BeforeTest
	public void setup() 
	{
		System.out.println(programClassName+" starting");
		System.out.println(programClassName+": 'setup' running");
        driver = new FirefoxDriver();
        homepage = new Homepage(driver);
        propertyListPage = new PropertyListPage(driver);
        System.out.println("new Homepage");
        
        wait = new WebDriverWait(driver,10);

        //Maximize the browser
        driver.manage().window().maximize();
	}
	
	@Test
	public void goToHomepage()
	{
        System.out.println(programClassName+": Test Method 1 'goToHomepage' running");  
        driver.get(inputHomepageURL);
        wait.until(ExpectedConditions.urlContains(homepageURL));
        Assert.assertTrue(driver.getCurrentUrl().startsWith(homepageURL),"Incorrect page, test failed");
	}
	
	@Test(dependsOnMethods={"goToHomepage"})
	public void searchProperties()
	{
		System.out.println(programClassName+": Test Method 2 'searchProperties' running");
		homepage.propertySearch(searchText);
		wait.until(ExpectedConditions.titleIs(pageTitle));
		Assert.assertEquals(driver.getTitle(),pageTitle,"Incorrect page, test failed");
	}
	
	@Test(dependsOnMethods={"searchProperties"})
	public void useCase1SortByName()
	{
		System.out.println(programClassName+": Test Method 3 'sortByName' running");
		try
		{
			propertyListPage.selectSortOrder(nameOptionValue);
		}
		catch(Exception e)
		{
			propertyListPage.selectSortOrder(nameOptionValue);
		}
		Assert.assertTrue(propertyListPage.sortByName(),"Listings not in name order");
	}
	
	@Test(dependsOnMethods={"useCase1SortByName"})
	public void useCase2SortByPrice()
	{
		System.out.println(programClassName+": Test Method 4 'sortByPrice' running");
		try
		{
			propertyListPage.selectSortOrder(priceOptionValue);
		}
		catch(Exception e)
		{
			propertyListPage.selectSortOrder(priceOptionValue);
		}
		Assert.assertTrue(propertyListPage.sortByPrice(),"Listings not in price order");
	}
	
	@AfterTest
	public void shutdown()
	{
		System.out.println(programClassName+": 'shutdown' running");
		//driver.close();
		//driver.quit();
		System.out.println(programClassName+" finished");
    }
}