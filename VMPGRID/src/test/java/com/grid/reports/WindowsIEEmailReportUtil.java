package com.grid.reports;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.projectname.scripts.TestReport;
import com.projectname.utils.ConstantsThreads;
import com.projectname.utils.TestUtil;

public class WindowsIEEmailReportUtil {
	public static int scriptNumber = 1;
	public static String indexResultFilename;
	public static String currentDir;
	public static String currentSuiteName;
	public static int tcid;
	// public static String currentSuitePath;

	public static double passNumber;
	public static double failNumber;
	public static boolean newTest = true;
	public static ArrayList<String> description = new ArrayList<String>();;
	public static ArrayList<String> keyword = new ArrayList<String>();;
	public static ArrayList<String> teststatus = new ArrayList<String>();;

	public static void startTesting(String filename, String testStartTime,
			String env, String rel) {
		indexResultFilename = filename;
		currentDir = indexResultFilename.substring(0,
				indexResultFilename.lastIndexOf("//"));

		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			// Create file

			fstream = new FileWriter(filename);
			out = new BufferedWriter(fstream);

			String RUN_DATE = TestUtil.now("dd.MMMMM.yyyy").toString();

			String ENVIRONMENT = env;// SeleniumServerTest.ConfigurationMap.getProperty("environment");
			String RELEASE = rel;// SeleniumServerTest.ConfigurationMap.getProperty("release");

			out.newLine();

			out.write("<html>\n");
			out.write("<HEAD>\n");
			out.write(" <TITLE>Automation Test Results</TITLE>\n");
			out.write("</HEAD>\n");

			out.write("<body>\n");
			out.write("<h4 align=center><FONT COLOR=660066 FACE=AriaL SIZE=6><b><u> Automation Test Results</u></b></h4>\n");
			out.write("<table  border=1 cellspacing=1 cellpadding=1 >\n");
			out.write("<tr>\n");

			out.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> <u>Test Details :</u></h4>\n");
			out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run Date</b></td>\n");
			out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"
					+ RUN_DATE + "</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");

			out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run StartTime</b></td>\n");

			out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"
					+ testStartTime + "</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");
			// out.newLine();
			out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Run EndTime</b></td>\n");
			out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>END_TIME</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");
			// out.newLine();

			out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Environment</b></td>\n");
			out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"
					+ ENVIRONMENT + "</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");

			out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Release</b></td>\n");
			out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"
					+ RELEASE + "</b></td>\n");
			out.write("</tr>\n");

			out.write("</table>\n");
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} finally {

			fstream = null;
			out = null;
		}
	}

	public static void startSuite(String suiteName) {

		FileWriter fstream = null;
		BufferedWriter out = null;
		currentSuiteName = suiteName.replaceAll(" ", "_");
		tcid = 1;
		try {
			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);

			out.write("<h4> <FONT COLOR=660000 FACE= Arial  SIZE=4.5> <u>"
					+ suiteName + " Report :</u></h4>\n");
			out.write("<table  border=1 cellspacing=1 cellpadding=1 width=30%>\n");
			out.write("<tr>\n");
			// out.write("<td width=10%  align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Script#</b></td>\n");

			out.write("<td width=5% align= center  	bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>TL ID</b></td>\n");
			out.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Case Description</b></td>\n");
			out.write("<td width=3% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Windows 7 IE Version 10</b></td>\n");
			out.write("</tr>\n");
			out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		} finally {

			fstream = null;
			out = null;
		}
	}
	public static void endSuite() {
		FileWriter fstream = null;
		BufferedWriter out = null;

		try {
			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);
			out.write("</table>\n");
			out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		} finally {

			fstream = null;
			out = null;
		}

	}

	@SuppressWarnings("unused")
	public static void addTestCaseSteps(String testCaseID, String TestLinkID,
			String testCaseDescription,
			String windowsIEResults, 
			 String windowIETestLinkID) {
		newTest = true;
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			newTest = true;
			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);
			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);
			if (!testCaseID.isEmpty()) {
				out.write("<td width=5% align= left bgcolor=#FF8141><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"
						+ testCaseID+"</b></td>\n");
				out.write("<td width=10% align= left bgcolor=#FF8141 ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
						+ testCaseDescription + "</b></td>\n");
			}else if ( (windowsIEResults!=null && windowsIEResults.equals("desc"))) {
				out.write("<td width=5% align= left bgcolor=#d3d3d3><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"
						+ testCaseID+"</b></td>\n");
				out.write("<td width=10% align= left bgcolor=#d3d3d3 ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
						+ testCaseDescription + "</b></td>\n");
			}else{
				out.write("<td width=5% align= left><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"
						+ testCaseID+"  "+TestLinkID + "</b></td>\n");
				out.write("<td width=10% align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
						+ testCaseDescription + "</b></td>\n");
			}
			
			// Windows Results IE:
			if (windowsIEResults == null) {
				out.write("<td width=3% align= left  bgcolor=#CCCCCC><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>Not Run</b></td>\n");
			}else if (windowsIEResults.equals("desc")) {
				out.write("<td align=left bgcolor=#FF8141 width=3%><FONT COLOR=#153E7E FACE=Arial SIZE=3><b></b></td>");
			}else if (windowsIEResults.equalsIgnoreCase("Fail")) {
				if (ConstantsThreads.screenShotPathWindowsIE
						.get(windowIETestLinkID) != null)
				out.write("<td align=center width=3%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b><a href="
						+ "windowsIE"
						+ ConstantsThreads.screenShotPathWindowsIE.get(windowIETestLinkID)
						+ " target=_blank>Fail</a></b></td>");
			} else if (windowsIEResults.equalsIgnoreCase("Pass")) {
				out.write("<td width=3% align= left  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"
						+ windowsIEResults + "</b></td>\n");
			} else if (windowsIEResults.equalsIgnoreCase("Skip")) {
				out.write("<td width=3% align= left  bgcolor=Yellow><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"
						+ windowsIEResults + "</b></td>\n");
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
		description = new ArrayList<String>();
		keyword = new ArrayList<String>();
		teststatus = new ArrayList<String>();
		// screenShotPath = new ArrayList<String>();
		newTest = false;
	}

	public static void addKeyword(String threadName, String desc, String key,
			String stat, String path, String testLinkID) {
		description.add(desc);
		keyword.add(key);
		teststatus.add(stat);
		if (threadName.equalsIgnoreCase("windowsMF")) {
			// ConstantsThreads.screenShotPathWindowsMF.add(path);
			ConstantsThreads.screenShotPathWindowsMF.put(testLinkID, path);
		} else if (threadName.equalsIgnoreCase("windowsIE")) {
//			ConstantsThreads.screenShotPathWindowsIE.add(path);
			ConstantsThreads.screenShotPathWindowsIE.put(testLinkID, path);
		} else if (threadName.equalsIgnoreCase("windowsGC")) {
//			ConstantsThreads.screenShotPathWindowsGC.add(path);
			ConstantsThreads.screenShotPathWindowsGC.put(testLinkID, path);
		} else if (threadName.equalsIgnoreCase("macMF")) {
//			ConstantsThreads.screenShotPathMacMF.add(path);
			ConstantsThreads.screenShotPathMacMF.put(testLinkID, path);
		} else if (threadName.equalsIgnoreCase("macSafari")) {
//			ConstantsThreads.screenShotPathMacSafari.add(path);
			ConstantsThreads.screenShotPathMacSafari.put(testLinkID, path);
		} else if (threadName.equalsIgnoreCase("macGC")) {
//			ConstantsThreads.screenShotPathMacGC.add(path);
			ConstantsThreads.screenShotPathMacGC.put(testLinkID, path);
		}

	}

	public static void updateEndTime(String endTime) {
		StringBuffer buf = new StringBuffer();
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(indexResultFilename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if (strLine.indexOf("END_TIME") != -1) {
					strLine = strLine.replace("END_TIME", endTime);
				}
				buf.append(strLine);
			}
			// Close the input stream
			in.close();
			System.out.println(buf);
			FileOutputStream fos = new FileOutputStream(indexResultFilename);
			DataOutputStream output = new DataOutputStream(fos);
			output.writeBytes(buf.toString());
			fos.close();

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}