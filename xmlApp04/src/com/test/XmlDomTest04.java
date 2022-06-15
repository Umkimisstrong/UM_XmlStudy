/*
	XmlDomTest04.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 로컬(local) XML 읽어내기
	  (VEHICLES.xml)
*/

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest04
{
	public static void main(String[] args)
	{
		/*
		--------------------------------------------------------------
		NO		MAKE		MODEL		YEAR		STYLE		PRICE
		--------------------------------------------------------------
		1		Dodge		Durango		1998     Sport Utility  18000
		Options ------------------------------------------------------
		     Power Locks : Yes
			 Power_Window : Yes
			 Stereo : Radio/Cassette/CD
			 Air_Conditioning : Yes
			 Automatic : Yes
			 Four_Wheel_Drive : Full/Partial
			 Very clean
		OWNER --------------------------------------------------------
			 NAME : Douglas Briggs
			 EMAIL : DBriggs@aDomain.Com
			 PHONE : 781 781 7811
		--------------------------------------------------------------	 
		*/
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document xmlObj = null;
			String url = "VEHICLES.xml";
			xmlObj = builder.parse(url);
			
			Element root = xmlObj.getDocumentElement();
			NodeList vehicleNodeList = root.getElementsByTagName("VEHICLE");
			
			for (int i = 0; i < vehicleNodeList.getLength(); i++)
			{
				//System.out.println(vehicleNodeList.getLength());
				
					Node vehicleNode = vehicleNodeList.item(i);
					Element vehicleElement = (Element)vehicleNode;
					
					System.out.printf("---------------------------------------------------------------------------------%n"
							+ "NO	MAKE		MODEL		YEAR		STYLE		PRICE%n"
							+ "---------------------------------------------------------------------------------%n"
							+ "%s\t%s\t\t%s\t%s\t\t%s\t\t%s\n"
							, getText(vehicleElement, "INVENTORY_NUMBER")
							, getText(vehicleElement, "MAKE")
							, getText(vehicleElement, "MODEL")
							, getText(vehicleElement, "YEAR")
							, getText(vehicleElement, "STYLE")
							, getText(vehicleElement, "PRICE"));
			}
			
			
		} catch (Exception e)
		{
			
			System.out.println(e.toString());
		}
		
		
		
		
		
		
	}
	
	public static String getText(Element parent, String tagName)
	{
		String result = "";
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		result = element.getChildNodes().item(0).getNodeValue();
		return result;
	}
}
