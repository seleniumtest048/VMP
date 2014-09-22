
package com.grid.xmlfiles;

import java.io.File;  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerException;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
  
import org.testng.annotations.Test;
import org.w3c.dom.Attr;  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
  
public class CreateXml {  
	
	@Test
	public void creatXML(){
		CreateXml createXml = new CreateXml();  
		   createXml.createXML();  
	}
 public void createXML() {  
  try {  
  
   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
     .newInstance();  
   DocumentBuilder documentBuilder = documentFactory  
     .newDocumentBuilder();  
  
  //define windows firefox elements 
   
   Document document = documentBuilder.newDocument();  
   Element rootElement = document.createElement("WinMF");  
   document.appendChild(rootElement); 
   
   // define Firefox elements  
   Element school = document.createElement("TLID");  
   rootElement.appendChild(school);   
   
   // add attributes to school  
   Attr attribute = document.createAttribute("tlid");  
   attribute.setValue("1");  
   school.setAttributeNode(attribute);
   
// Test Case Description  elements  
   Element firstname = document.createElement("TestCaseDescription");  
   firstname.appendChild(document.createTextNode("Open the QA Home page"));  
   school.appendChild(firstname);  
  
   // Test Case Status elements  
   Element lastname = document.createElement("Status");  
   lastname.appendChild(document.createTextNode("Pass"));  
   school.appendChild(lastname); 
   
   
   // creating and writing to xml file  
   TransformerFactory transformerFactory = TransformerFactory  
     .newInstance();  
   Transformer transformer = transformerFactory.newTransformer();  
   DOMSource domSource = new DOMSource(document);  
   StreamResult streamResult = new StreamResult(new File(  
     "createdFiles/createFile.xml"));  
  
   transformer.transform(domSource, streamResult);  
  
   System.out.println("File saved to specified path!");  
  
   
   
   
   
   
   
   
   // define root elements  
 /*  Document document = documentBuilder.newDocument();  
   Element rootElement = document.createElement("school");  
   document.appendChild(rootElement);*/  
  
   // define school elements  
   /*Element school = document.createElement("Student");  
   rootElement.appendChild(school); */ 
  
   // add attributes to school  
   /*Attr attribute = document.createAttribute("id");  
   attribute.setValue("1");  
   school.setAttributeNode(attribute);  */
  
   // firstname elements  
  /* Element firstname = document.createElement("firstname");  
   firstname.appendChild(document.createTextNode("ankush"));  
   school.appendChild(firstname);  */
  
   // lastname elements  
  /* Element lastname = document.createElement("lastname");  
   lastname.appendChild(document.createTextNode("thakur"));  
   school.appendChild(lastname);  */
  
  /* // email elements  
   Element email = document.createElement("email");  
   email.appendChild(document.createTextNode("beingjavaguy@gmail.com"));  
   school.appendChild(email);  
  
   // phone elements  
   Element phone = document.createElement("phone");  
   phone.appendChild(document.createTextNode("8767675434"));  
   school.appendChild(phone);  */
  
  
  } catch (ParserConfigurationException pce) {  
   pce.printStackTrace();  
  } catch (TransformerException tfe) {  
   tfe.printStackTrace();  
  }  
 }  
}  