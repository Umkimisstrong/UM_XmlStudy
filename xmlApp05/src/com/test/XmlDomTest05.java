/*
	XmlDomTest04.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 로컬(local) XML 읽어내기
	  (rss.xml)
	  -- 기상청날씨누리
*/

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest05
{
	public static void main(String[] args)
	{
		
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			String url = "rss.xml";
			Document xmlObj = builder.parse(url);
			
			Element root = xmlObj.getDocumentElement();
			
			// 타이틀 추출
			Node itemNode = root.getElementsByTagName("item").item(0);
			Element itemElement = (Element)itemNode;
			
			System.out.printf("%s %n%n", XMLDOM.getText(itemElement, "title"));
			
			// 기상전망 추출
			Node wfNode = root.getElementsByTagName("wf").item(0);
			Element wfElement = (Element)wfNode;
			
			System.out.println("[기상전망] -----------------------------------------------------");
			System.out.printf("%s%n%n", wfElement.getTextContent().replaceAll("<br />", "\n"));
			
			// 도시별 기상 예보 추출하기
			System.out.println("[기상전망] ----------------------------------------------------- ]");
			NodeList locationNodeList = root.getElementsByTagName("location");
			
			for (int i = 0; i < locationNodeList.getLength() ; i++)
			{
				Node locationNode = locationNodeList.item(i);
				Element locationElement = (Element)locationNode;
				
				System.out.printf("도시 : %s%n", XMLDOM.getText(locationElement, "city"));
				System.out.println("-----------------------------------------------------");
				NodeList dataNodeList = locationElement.getElementsByTagName("data");
				
				for (int j = 0; j < dataNodeList.getLength(); j++)
				{
					Node dataNode = dataNodeList.item(j);
					Element dataElement = (Element)dataNode;
					System.out.printf("    %s / %s / %s ~ %s / %s%n"
									, XMLDOM.getText(dataElement, "tmEf")
									, XMLDOM.getText(dataElement, "wf")
									, XMLDOM.getText(dataElement, "tmn")
									, XMLDOM.getText(dataElement, "tmx")
									, XMLDOM.getText(dataElement, "rnSt"));
				}
				System.out.println("-----------------------------------------------------");

			}
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
	}
}
