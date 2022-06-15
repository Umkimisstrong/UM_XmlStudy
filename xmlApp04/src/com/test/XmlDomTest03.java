/*
    XmlDomTest03.java
    - XML DOM 활용 Local XML 읽어내기
    - breakfast_menu.xml 
*/
/*
	<food>
		<name>Belgian Waffles</name>
		<price>$5.95</price>
		<description>Two of our famous Belgian Waffles with plenty of real maple syrup</description>
		<calories>650</calories>
	</food>
*/
package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest03
{
	public static void main(String[] args)
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document xmlObj = null;
			String url = "breakfast_menu.xml";
			xmlObj = builder.parse(url);
			
			Element root = xmlObj.getDocumentElement();
			NodeList foodNodeList  = root.getElementsByTagName("food");
			
			
			for (int i = 0; i < foodNodeList.getLength(); i++)
			{
				Node foodNode	= foodNodeList.item(i);
				
				Element foodElement = (Element)foodNode;
				
				System.out.printf("■[%s] %s %s칼로리%n   - %s %n"
						          + "----------------------------------------------------------------------"
								, getText(foodElement, "name")
								, getText(foodElement, "price")
								, getText(foodElement, "calories")
								, getText(foodElement, "description"));
				
				System.out.println("");
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
