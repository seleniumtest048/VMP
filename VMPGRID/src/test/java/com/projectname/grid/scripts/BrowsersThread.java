package com.projectname.grid.scripts;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Platform;
import com.projectname.scripts.DriverScript;

class BrowsersThread extends Thread{
	public static int myCount = 0;
	public WebDriver windowsMF,macMF, windowsGC, windowsIE, macGC, macSafari;
   
 	public BrowsersThread(String name) {
	// TODO Auto-generated constructor stub
		super(name);
 	}

	public void run(){
    //	windows();
		try {
			
			windowsMF();
			windowsGC();
			windowsIE();
			windowsMFProfile();
			macMF();
			macGC();
			macSafari();
		} catch (Exception e) {
					e.printStackTrace();
		}
	}
	
	public void windowsMF() throws Exception{
		DesiredCapabilities capability= new DesiredCapabilities();
		if(getName().equalsIgnoreCase("windowsMF")){
			/*FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.proxy.type", 1);
			profile.setPreference("network.proxy.socks", "localhost");
			profile.setPreference("network.proxy.socks_port", 9853);
			capability.setCapability(FirefoxDriver.PROFILE, profile);*/
			capability.setPlatform(Platform.WINDOWS);
			capability = new DesiredCapabilities();
			capability.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
//			capability.setCapability(FirefoxDriver.PROFILE, profile);
			windowsMF= new RemoteWebDriver(new URL("http://localhost:".concat("4444").concat("/wd/hub")), capability);
    		DriverScript dst=new DriverScript();
    		windowsMF.get(RunThreadTest.URL);
    		windowsMF.manage().window().maximize();
			dst.driverScript(windowsMF,getName());
    	}
    	
	}
	public void windowsMFProfile() throws Exception{
		DesiredCapabilities capability= new DesiredCapabilities();
		if(getName().equalsIgnoreCase("windowsMFProfileBrowser")){
			System.out.println("firefox profile");
			capability.setPlatform(Platform.WINDOWS);
			FirefoxProfile profile = new ProfilesIni().getProfile("Selenium");
			capability = new DesiredCapabilities();
			capability.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			capability.setCapability(FirefoxDriver.PROFILE, profile);
			windowsMF= new RemoteWebDriver(new URL("http://localhost:".concat("4444").concat("/wd/hub")), capability);
    		DriverScript dst=new DriverScript();
    		windowsMF.get(RunThreadTest.URL);
    		windowsMF.manage().window().maximize();
			dst.driverScript(windowsMF,getName());
    	}
    	
	}
	public void windowsGC() throws Exception{
		DesiredCapabilities capability= new DesiredCapabilities();
    	if(getName().equalsIgnoreCase("windowsGC")){
    		System.out.println("Am in windows Chrome---");
    		capability.setPlatform(Platform.WINDOWS);
    		capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            windowsGC= new RemoteWebDriver(new URL("http://localhost:".concat("4444").concat("/wd/hub")), capability);
    		DriverScript dst=new DriverScript();
    		windowsGC.get(RunThreadTest.URL);
    		windowsGC.manage().window().maximize();
			dst.driverScript(windowsGC,getName());
    	}
    			  
	}
	
	public void windowsIE() throws Exception{
		DesiredCapabilities capability= new DesiredCapabilities();
    	if(getName().equalsIgnoreCase("windowsIE")){
    		System.out.println("Am in windows IE---");
    		capability.setPlatform(Platform.WINDOWS);
    		capability = DesiredCapabilities.internetExplorer();
    		capability.setCapability("ignoreZoomSetting", true);
            capability.setBrowserName("internetexplorer");
            windowsIE= new RemoteWebDriver(new URL("http://localhost:".concat("4444").concat("/wd/hub")), capability);
    		DriverScript dst=new DriverScript();
    		windowsIE.get(RunThreadTest.URL);
    		windowsIE.manage().window().maximize();
			dst.driverScript(windowsIE,getName());
    	}
    			  
	}
	
	public void macMF() throws Exception{
		DesiredCapabilities capability= new DesiredCapabilities();
    	if(getName().equalsIgnoreCase("macMF")){
    		
    		/*FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.proxy.type", 1);
			profile.setPreference("network.proxy.socks", "localhost");
			profile.setPreference("network.proxy.socks_port", 9853);
			capability.setCapability(FirefoxDriver.PROFILE, profile);
			capability.setPlatform(Platform.MAC);
			capability = new DesiredCapabilities();
			capability.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			capability.setCapability(FirefoxDriver.PROFILE, profile);
			macMF= new RemoteWebDriver(new URL("http://localhost:".concat("4444").concat("/wd/hub")), capability);
    		DriverScript dst=new DriverScript();
    		macMF.get(RunThread.URL);
    		macMF.manage().window().maximize();
			dst.driverScript(macMF,getName());*/
    		
    		FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.proxy.type", 1);
			profile.setPreference("network.proxy.socks", "localhost");
			profile.setPreference("network.proxy.socks_port", 9853);
			capability.setCapability(FirefoxDriver.PROFILE, profile);
			capability.setPlatform(Platform.WINDOWS);
			capability = new DesiredCapabilities();
			capability.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			capability.setCapability(FirefoxDriver.PROFILE, profile);
			macMF= new RemoteWebDriver(new URL("http://localhost:".concat("4444").concat("/wd/hub")), capability);
    		DriverScript dst=new DriverScript();
    		macMF.get(RunThreadTest.URL);
    		macMF.manage().window().maximize();
			dst.driverScript(macMF,getName());
    	}
    			  
	}
	
	public void macGC() throws Exception{
		DesiredCapabilities capability= new DesiredCapabilities();
    	if(getName().equalsIgnoreCase("macGC")){
    		/*System.out.println("Am in Mac Chrome---");
    		capability.setPlatform(Platform.MAC);
    		capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            macGC= new RemoteWebDriver(new URL("http://localhost:".concat("4444").concat("/wd/hub")), capability);
    		DriverScript dst=new DriverScript();
    		macGC.get(RunThread.URL);
    		macGC.manage().window().maximize();
			dst.driverScript(macGC,getName());*/
    		
    		System.out.println("Am in windows Chrome---");
    		capability.setPlatform(Platform.WINDOWS);
    		capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            macGC= new RemoteWebDriver(new URL("http://localhost:".concat("4444").concat("/wd/hub")), capability);
    		DriverScript dst=new DriverScript();
    		macGC.get(RunThreadTest.URL);
    		macGC.manage().window().maximize();
			dst.driverScript(macGC,getName());
    	}
    			  
	}
	
	public void macSafari() throws Exception{
		DesiredCapabilities capability= new DesiredCapabilities();
    	if(getName().equalsIgnoreCase("macSafari")){
    		System.out.println("Am in Mac Safari---");
    		capability.setPlatform(Platform.MAC);
    		capability = DesiredCapabilities.safari();
            capability.setBrowserName("safari");
            macGC= new RemoteWebDriver(new URL("http://localhost:".concat("4444").concat("/wd/hub")), capability);
    		DriverScript dst=new DriverScript();
    		macGC.get(RunThreadTest.URL);
    		macGC.manage().window().maximize();
			dst.driverScript(macGC,getName());
    	}
    			  
	}
}
