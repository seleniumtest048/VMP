package com.projectname.scripts;
import jxl.Sheet;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

import com.projectname.base.EmailReportUtil;
import com.projectname.base.Keywords;
import com.projectname.base.ReportUtil;
import com.projectname.utils.TestUtil;

public class DriverScript extends Keywords {
	
	public  String keyword;
	public  String stepDescription;
	public  String result;
	public  DriverScript dstest;
	public  WebDriver driver;
	 float totalTestCaseCount,runTestCaseCount=0,failedTestCases;

	public void driverScript(WebDriver driverThread, String threadName) throws Exception {
		driver=driverThread;
		initialize();
		controlscript(driver,threadName);
	}
	
	public  void controlscript(WebDriver driver,String threadName) throws Exception{
		controlshet=controllerwb.getSheet("Suite");
		totalTestCaseCount=controlshet.getRows()-1;
		System.out.println("am in control script");
		int colom=3,excelrow=1;
		System.out.println("Control Sheet rows--"+controlshet.getRows());
		for (int i = 1; i < controlshet.getRows(); i++) {
			log.info("Selecting Test Scenario From Controller File");
			String tsrunmode=controlshet.getCell(2,i).getContents();
			
			System.out.println("test secenario Runmode---"+tsrunmode);
			
			if (tsrunmode.equalsIgnoreCase("Y")) {
				runTestCaseCount++;
				log.info("Navigate To Test Scenario Sheet");
				String tcaseid=controlshet.getCell(0,i).getContents();
				Sheet tdsheet1=testdatawb.getSheet(tcaseid);
				System.out.println("Test Case ID --"+tdsheet1);
				//control sheet
				Sheet controlshet1=controllerwb.getSheet(tcaseid);
				String fileName=null;
				log.info("Loading Test Data Work Book");
					String testcaseid=tdsheet1.getCell(0,1).getContents();
					String testdesc=tdsheet1.getCell(1,1).getContents();
					int j;
					switch (threadName) {
					case "windowsGC":
							fileName = (testcaseid)+"_";
							stepDescription=testdesc;
							keyword=testcaseid;
							log.info("Passing Parameters Driver Script to ContolScript");
							j=5;
							result=controlScript(driver,threadName,j, colom, tcaseid,controlshet1,testcaseid,stepDescription,keyword,fileName);
							if (failcount>=1 || rptFailCnt>=1) {
								result="Fail";
								log.info("Test Scenario Result --"+ result);
								if (failcount==0) {
									failedTestCases+=rptFailCnt;
								}else{
									failedTestCases+=failcount;
								}
								rptFailCnt=0;
								failcount=0;
							}else{
								result="Pass";
								log.info("Test Scenario Result --"+ result);
							}
							
						break;
					case "windowsMF":
							fileName = (testcaseid)+"_";
							stepDescription=testdesc;
							keyword=testcaseid;
							log.info("Passing Parameters Driver Script to ContolScript");
							j=4;
							result=controlScript(driver,threadName,j, colom, tcaseid,controlshet1,testcaseid,stepDescription,keyword,fileName);
							if (failcount>=1 || rptFailCnt>=1) {
								result="Fail";
								log.info("Test Scenario Result --"+ result);
								if (failcount==0) {
									failedTestCases+=rptFailCnt;
								}else{
									failedTestCases+=failcount;
								}
								rptFailCnt=0;
								failcount=0;
							}else{
								result="Pass";
								log.info("Test Scenario Result --"+ result);
							}
							
						break;
					case "windowsIE":
							fileName = (testcaseid)+"_";
							stepDescription=testdesc;
							keyword=testcaseid;
							log.info("Passing Parameters Driver Script to ContolScript");
							j=6;
							result=controlScript(driver,threadName,j, colom, tcaseid,controlshet1,testcaseid,stepDescription,keyword,fileName);
							if (failcount>=1 || rptFailCnt>=1) {
								result="Fail";
								log.info("Test Scenario Result --"+ result);
								if (failcount==0) {
									failedTestCases+=rptFailCnt;
								}else{
									failedTestCases+=failcount;
								}
								rptFailCnt=0;
								failcount=0;
							}else{
								result="Pass";
								log.info("Test Scenario Result --"+ result);
							}
						break;
					default:
						break;
					}
			}
			controlshet=controllerwb.getSheet("Suite");
		}
		if (threadName.equals("windowsGC")) {
			closeBrowserWinGC(driver,threadName);
		}else if (threadName.equals("windowsMF")) {
			closeBrowserWinMF(driver,threadName);
		}else if (threadName.equals("windowsIE")) {
			closeBrowserWinIE(driver,threadName);
		}else if (threadName.equals("macMF")) {
			closeBrowserMacMF(driver,threadName);
		}else if (threadName.equals("macSafari")) {
			closeBrowserMacSafari(driver,threadName);
		}else if (threadName.equals("macSafari")) {
			closeBrowserMacGC(driver,threadName);
		}
		
	}
}