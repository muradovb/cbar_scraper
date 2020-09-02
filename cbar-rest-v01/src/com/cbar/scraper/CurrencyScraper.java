package com.cbar.scraper;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME; 

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cbar.entity.Currency;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CurrencyScraper {
	
	private WebDriver webDriver;
	
	private static int TIMEOUT=30;
	
	private static CurrencyScraper cInstance = null;
	
	public CurrencyScraper() {
		//setting up chrome driver
		WebDriverManager.getInstance(CHROME).setup();
		ChromeOptions options = new ChromeOptions();
	    webDriver = new ChromeDriver(options);
	}
	
	//methods
	public ArrayList<Currency> getCurrencyData(){
		//arraylist to keep all the currency data
		ArrayList<Currency> currencyData = new ArrayList<>();
		
		try {
			
			//go to the specified address
			webDriver.get("https://www.cbar.az/currency/rates");
			//wait until the page fully loads
			WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT);
			
			//get page source
            String pageSource = webDriver.getPageSource();
            
            //parse the page source
            Document doc = Jsoup.parse(pageSource);
            Elements currencies = doc.select("div.table_items > div");
           
            for(Element el:currencies) {
            	String code = el.getElementsByClass("kod").text();
            	String name = el.getElementsByClass("valuta").text();
            	String value = el.getElementsByClass("kurs").text();
            	
            	Currency temp= new Currency(code, name, value);
            	
            	currencyData.add(temp);
            }
			
		}
		catch(TimeoutException e){
			System.out.println("timed out");
			System.exit(0);
		}
		
		return currencyData;
	}
	
	//singleton
	public static CurrencyScraper getInstance() {
		if(cInstance==null) {
			cInstance=new CurrencyScraper();
		}
		return cInstance;
	}
	

}
