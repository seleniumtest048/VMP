package com.grid.htmlmerge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.projectname.utils.ConstantsThreads;
import com.projectname.utils.TestUtil;



public class Copy_2_of_HtmlMerge {

	HashMap<String, HtmlReportValues> report=new HashMap<String, HtmlReportValues>();
	public static boolean newTest = true;
	public static String indexResultFilename;
	@Test
	public void htmlMergeFiles() throws Exception{
		htmlReport("WindowsFirefox");
		htmlReport("WindowsGC");
		
//		htmlReport("WindowsIE");
		System.out.println("total Report Count======>>>"+report.size());
		htmlMergeReport();
		Set<String> keySet = report.keySet();
		Iterator<String> iterator =  keySet.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String winGCStatus=report.get(key).winGCStatus;
			String winMFStatus=report.get(key).winMFStatus;
//			String winIEStatus=report.get(key).winIEStatus;
			System.out.println("====================================*****************************================================");
			System.out.println("test link ID============>>>"+report.get(key).testLinkID);
			System.out.println("windows firefox status=============>>>"+winMFStatus);
			System.out.println("windows chrome status===========>>>>>"+winGCStatus);
//			System.out.println("windows IE status=============>>>>>>"+winIEStatus);
			System.out.println("====================================*****************************================================");
			htmlMERREPROT(report.get(key).testLinkID,report.get(key).desc,winGCStatus,winMFStatus);
		}
		
	}

	private void htmlReport(String platform) throws Exception{
		Thread.sleep(3000);
		WebDriver driver =new FirefoxDriver();
		driver.get("E:\\SeleniumFrameWorkSpace\\GridFrameWrokJar\\EmailReport\\"+platform+"index.html");
		Thread.sleep(3000);
		List<WebElement> wb=driver.findElements(By.xpath("//html/body/table[2]/tbody/tr"));

		int tabSize=wb.size();
		System.out.println("Table Count=====>>"+tabSize);
		for (int i = 1; i <= tabSize; i++) {
			
			try{	
				String testLinkId = driver.findElement(By.xpath("//html/body/table[2]/tbody/tr["+i+"]/td[1]")).getText();
				String result=driver.findElement(By.xpath("//html/body/table[2]/tbody/tr["+i+"]/td[3]")).getText();
				if(!report.containsKey(testLinkId)){
					HtmlReportValues htmlReportValues = new HtmlReportValues();
					htmlReportValues.testLinkID = testLinkId;
					htmlReportValues.desc=driver.findElement(By.xpath("//html/body/table[2]/tbody/tr["+i+"]/td[2]")).getText();
					
					System.out.println("**********************************************");
					System.out.println("**********************************************"+platform);
					System.out.println("Test Link ID====>>"+htmlReportValues.testLinkID);
					System.out.println("Test Desc====>>"+htmlReportValues.desc);
					System.out.println("Test Result====>>"+result);
					System.out.println("**********************************************");

					recordResult(htmlReportValues, result, platform);
					report.put(htmlReportValues.testLinkID,htmlReportValues );
				}
				else{
					HtmlReportValues reportValues = report.get(testLinkId);
					reportValues = recordResult(reportValues, result, platform);
					report.put(testLinkId, reportValues);
				}
//				report.put(htmlReportValues.testLinkID,htmlReportValues );	
			}catch(Exception ei){}
		}
		driver.quit();
	}

	private HtmlReportValues recordResult(HtmlReportValues reportValues, String result, String platform){

		switch (platform) {
		case "WindowsGC":
			reportValues.winGCStatus = result;
			break;
		case "WindowsFirefox":
			reportValues.winMFStatus = result;
			break;
		case "WindowsIE":
			reportValues.winIEStatus = result;
			break;
		default:
			break;
		}

		return reportValues;
	}
	
	
	private void htmlMergeReport() throws Exception{
		System.out.println("In Final Report=========>>>>>>>>>>>>>>");
		FileWriter fstream = null;
		BufferedWriter out = null;
		indexResultFilename = "E:\\SeleniumFrameWorkSpace\\GridFrameWrokJar\\EmailReport\\finalindex.html";
			// Create file
		try {
			fstream = new FileWriter(indexResultFilename);
			out = new BufferedWriter(fstream);

			String RUN_DATE = TestUtil.now("dd.MMMMM.yyyy").toString();

			out.newLine();

			out.write("<html>\n");
			out.write("<HEAD>\n");
			out.write(" <TITLE>Automation Test Results</TITLE>\n");
			out.write("</HEAD>\n");

			out.write("<body>\n");
			out.write("<h4 align=center><FONT COLOR=660066 FACE=AriaL SIZE=6><b><u> Automation Test Results</u></b></h4>\n");
			out.write("<table  border=1 cellspacing=1 cellpadding=1 >\n");
			
			out.write("<tr>\n");
			out.write("<td width=3% align= center 	bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>TLID</b></td>\n");
			out.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Description</b></td>\n");
			out.write("<td width=5% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>WindowsGC</b></td>\n");
			out.write("<td width=5% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>WindowsMF </b></td>\n");
			out.write("</tr>\n");
			
			
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} finally {

			fstream = null;
			out = null;
		}
			
	}
	private void htmlMERREPROT(String tlid,String desc,String winGC,String winMF) throws Exception{
		FileWriter fstream = null;
		BufferedWriter out = null;
		newTest = true;
	
		try {
			newTest = true;
			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);
			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);
			
			out.write("<tr>\n");
			
			if (winGC==null|| winMF==null) {
				out.write("<td width=3% align= left bgcolor=#CCCCCC><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+tlid+"</b></td>\n");
				out.write("<td width=10% align= left bgcolor=#CCCCCC><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+desc+"</b></td>\n");
				out.write("<td width=3% align= left  bgcolor=#CCCCCC><FONT COLOR=#153E7E FACE=Arial SIZE=2><b></b></td>\n");
				out.write("<td width=3% align= left  bgcolor=#CCCCCC><FONT COLOR=#153E7E FACE=Arial SIZE=2><b></b></td>\n");
			}else if (winGC.equalsIgnoreCase("fail")|| winMF.equalsIgnoreCase("fail")) {
				out.write("<td width=3% align= left><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+tlid+"</b></td>\n");
				out.write("<td width=10% align= left><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+desc+"</b></td>\n");
				out.write("<td width=3% align= left  bgcolor=red><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+winGC+" </b></td>\n");
				out.write("<td width=3% align= left  bgcolor=red><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+winMF+" </b></td>\n");
			}else if (winGC.equalsIgnoreCase("pass")||winMF.equalsIgnoreCase("pass")) {
				out.write("<td width=3% align= left><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+tlid+"</b></td>\n");
				out.write("<td width=10% align= left><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+desc+"</b></td>\n");
				out.write("<td width=3% align= left  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+winGC+" </b></td>\n");
				out.write("<td width=3% align= left  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+winMF+" </b></td>\n");
			}
			
			
			out.write("</tr>\n");
			
			
		
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// screenShotPath = new ArrayList<String>();
		newTest = false;
	}
}















