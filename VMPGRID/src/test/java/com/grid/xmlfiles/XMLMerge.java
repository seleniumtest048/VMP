package com.grid.xmlfiles;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.projectname.utils.TestUtil;



public class XMLMerge {
	
	LinkedHashMap<String, XmlReportValues> report=new LinkedHashMap<String, XmlReportValues>();
	public static boolean newTest = true;
	public static String indexResultFilename;
	public String currentpath,path;
	@Test
	public synchronized void xmlMergeFiles() throws Exception{
		try{
			currentpath = new java.io.File(".").getCanonicalPath();
			path = currentpath.replace("\\", "\\\\");
			xmlReader("WinGC");
			xmlReader("WinIE");
			xmlReader("WinMf");
			xmlMergeReport();
			Set<String> keySet = report.keySet();
			Iterator<String> iterator =  keySet.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				String winGCStatus=report.get(key).winGCStatus;
				String winMFStatus=report.get(key).winMFStatus;
				String winIEStatus=report.get(key).winIEStatus;
				htmlMERREPROT(report.get(key).testLinkID,report.get(key).desc,winGCStatus,winMFStatus,winIEStatus);
			}
		}catch(Exception e){
			
		}
	}	
	private void xmlReader(String platform) {
		try {
			File stocks = new File(path+"\\XmlReports\\"+platform+".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stocks);
			doc.getDocumentElement().normalize();
			System.out.println("root of xml file" + doc.getDocumentElement().getNodeName());
			NodeList nodes = doc.getElementsByTagName("TLID");
			System.out.println("Nodes Length========="+platform+"================"+nodes.getLength());
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String testLinkId=element.getAttribute("tlid");
					String result=getValue("Status", element);
					System.out.println("results=====>>"+platform+"========"+getValue("Status", element));
					if(!report.containsKey(element.getAttribute("tlid"))){	
						XmlReportValues xmlReports=new XmlReportValues();
						xmlReports.testLinkID=element.getAttribute("tlid");
						xmlReports.desc=getValue("TestCaseDescription", element);
						recordResult(xmlReports, result, platform);
						report.put(xmlReports.testLinkID,xmlReports );
					}else {
						XmlReportValues reportValues=report.get(testLinkId);
						reportValues = recordResult(reportValues, result, platform);
						report.put(testLinkId, reportValues);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private XmlReportValues recordResult(XmlReportValues reportValues, String result, String platform){
		switch (platform) {
		case "WinGC":
			reportValues.winGCStatus = result;
			break;
		case "Winmf":
			reportValues.winMFStatus = result;
			break;
		case "WinIE":
			reportValues.winIEStatus = result;
			break;
		default:
			break;
		}
		return reportValues;
	}
	private void xmlMergeReport() throws Exception{
		FileWriter fstream = null;
		BufferedWriter out = null;
		indexResultFilename = path+"\\FinalReport\\FinalReport.html";
		try {
			fstream = new FileWriter(indexResultFilename);
			out = new BufferedWriter(fstream);
			out.newLine();
			out.write("<html>\n");
			out.write("<HEAD>\n");
			out.write(" <TITLE>Automation Test Results</TITLE>\n");
			out.write("</HEAD>\n");
			out.write("<body>\n");
			out.write("<h4 align=center><FONT COLOR=660066 FACE=AriaL SIZE=6><b><u> Automation Test Results</u></b></h4>\n");
			out.write("<table  border=1 cellspacing=1 cellpadding=1 >\n");
			out.write("<tr>\n");
			out.write(td("TLID"));
			out.write(td("Description"));
			out.write(td("WindowsGC"));
			out.write(td("WindowsMF"));
			out.write(td("WindowsIE"));
			out.write("</tr>\n");
			out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		} finally {

			fstream = null;
			out = null;
		}
			
	}
	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
		}
	private String td(String heading){
		String td="<td width=3% align= center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>"+heading+"</b></td>\n";
		return td;
	}
	private void htmlMERREPROT(String tlid,String desc,String winGC,String winMF,String winIE) throws Exception{
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
			out.write("<td width=3% align= left bgcolor="+getHeadingColor(tlid)+"><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+tlid+"</b></td>\n");
			out.write("<td width=10% align= left bgcolor="+getHeadingColor(tlid)+"><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+desc+"</b></td>\n");
			
			out.write("<td width=3% align= left  bgcolor="+getColor(winGC)+"><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+getStatus(winGC)+" </b></td>\n");
			out.write("<td width=3% align= left  bgcolor="+getColor(winMF)+"><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+getStatus(winMF)+" </b></td>\n");
			out.write("<td width=3% align= left  bgcolor="+getColor(winIE)+"><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+getStatus(winIE)+" </b></td>\n");
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
		newTest = false;
	}
	private String getStatus(String status){
		if (status != null) {
			switch (status) {
			case "Pass":
				return status;
			case "Fail":
				return status;
			default:
				break;
			}
		}else{
			return "NotRun";
		}
		return "";
		
	}
	private String getColor(String status){

		if (status != null) {
			switch (status) {
			case "Pass":
				return "#BCE954";
			case "Fail":
				return "red";

			default:
				break;
			}
			
		}
		return "#CCCCCC";
		}
	private String getHeadingColor(String status){

			if (status != null) {
				
				if (!status.contains("WHOIS-") && !status.contains("TL_TC_") && !status.contains("WHOIS") && !status.contains("WHOSIS")&& !status.contains("VMP_")) {
					return "#CCCCCC";
				}
			}
			return "#FFFFFF";
			}
//		Read more: http://javarevisited.blogspot.com/2011/12/parse-xml-file-in-java-example-tutorial.html#ixzz3De8ARq4V
}















