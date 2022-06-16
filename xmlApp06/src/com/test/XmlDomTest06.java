/*
	XmlDomTest06.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 원격(remote) XML 읽어내기
	  (http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=105)
	  -- 기상청날씨누리
*/
/*
stnId=108	전국	
stnId=109	서울,경기
stnId=105	강원
stnId=131	충북
stnId=133	충남
stnId=146	전북
stnId=143	경북
stnId=156	전남	
stnId=184	제주특별자치도
stnId=159	경남
*/

package com.test;

import java.net.URL;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlDomTest06
{
	public static void main(String[] args)
	{
		
		Scanner sc = new Scanner(System.in);
		String [] stnId = {"108", "109", "105", "131", "133", "146", "143", "156", "184", "159"};		
		
		do
		{
			try
			{
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document xmlObj = null;
				System.out.println("■■■ 기상청 육상 중기 예보 ■■■");
				System.out.println("1. 전국	      ");
				System.out.println("2. 서울,경기     ");
				System.out.println("3. 강원        ");
				System.out.println("4. 충북        ");
				System.out.println("5. 충남        ");
				System.out.println("6. 전북        ");
				System.out.println("7. 경북        ");
				System.out.println("8. 전남	      ");
				System.out.println("9. 제주특별자치도   ");
				System.out.println("10. 경남        ");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("--------------------------------------------");
				System.out.println(">>> 번호 입력(-1 종료) : ");
				String m = sc.next();
				
				if (m.equals("-1"))
					break;
				
				String str = String.format("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=%s", stnId[Integer.parseInt(m)-1]);
				System.out.println(str);
				URL url = new URL(str);
				InputSource is = new InputSource(url.openStream());
				xmlObj = builder.parse(is);
				Element root = xmlObj.getDocumentElement();
				Node itemNode = root.getElementsByTagName("item").item(0);
				Element itemElement = (Element)itemNode;
				System.out.printf("%n%s%n%n", XMLDOM.getText(itemElement, "title"));
				Node wfNode = root.getElementsByTagName("wf").item(0);
				Element wfElement = (Element)wfNode;
				System.out.println("[기상 전망] ----------------------------------------------------");
				System.out.printf("%s%n%n", wfElement.getTextContent().replaceAll("<br />", "\n"));
				System.out.println("[육상 날씨] ----------------------------------------------------");
				NodeList locationNodeList = root.getElementsByTagName("location");
				
				for (int i = 0; i < locationNodeList.getLength(); i++)
				{
					Node locationNode = locationNodeList.item(i);
					Element locationElement = (Element)locationNode;
					
					System.out.printf("도시 : %s%n"
									, XMLDOM.getText(locationElement, "city"));
					
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
				}
			}
				
				
			
			catch (Exception e)
			{
				System.out.println(e.toString());
			}
		} while (false);
		
	}
}
