/*
 	XmlDomTest02
 	- memberList.xml
*/

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest02
{
	public static void main(String[] args)
	{
		// 김상기 010-5693-4223
		// 김민성 010-5222-1134
		try
		{
			 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			 DocumentBuilder builder = factory.newDocumentBuilder();
			 
			 Document xmlObj = null;
			 
			 String url = "memberList.xml";
			 xmlObj = builder.parse(url);
			 
			 
			// root 엘리먼트 접근 
			Element root = xmlObj.getDocumentElement();
			
			NodeList memberNodeList = root.getElementsByTagName("memberInfo");
			 
			// 테스트
			// System.out.println(memberNodeList.getLength());
			// 2 등록한 멤버 수
			
			
			
			for (int i = 0; i < memberNodeList.getLength(); i++)	// 2회 반복
			{
				Node memberNode = memberNodeList.item(i);
				// getElemenetsByTagName() 메소드가 이름을 통해 대상을 획득했다면..
				// item() 메소드는 위치(인덱스)를 통해 대상을 획득하게 된다.
				
				// 캐스팅
				Element memberElement = (Element)memberNode;
				// 엘리먼트가 노드의 하위 개념이기 때문에 가능한 구문
				
				System.out.printf("%s %s %n"
								, getText(memberElement, "name")
								, getText(memberElement, "tel"));
				
				// 커리큘럼에 대한 처리 추가
				NodeList curriculumnNodeList = memberElement.getElementsByTagName("curriculumn");
				
				// 없는 경우도 있음.. 있는 경우만 처리하게 하자..
				if (curriculumnNodeList.getLength() > 0)
				{
					Node curriculumnNode = curriculumnNodeList.item(0);
					Element curriculumnElement = (Element)curriculumnNode;
					
					/*
					// 방법1.
					NodeList subNodeList = curriculumnElement.getElementsByTagName("sub");
					for (int m = 0; m < subNodeList.getLength(); m++)
					{
						Node subNode = subNodeList.item(m);
						Element subElement = (Element)subNode;
						System.out.printf("%s %n", subElement.getTextContent()); 
						 
					}
					*/
					
					// 방법 2.
					/*
					-===========================================================================
					Node Type			Named Constant
					-===========================================================================
					   1                  ELEMENT_NODE
					   2                  ATTRIBUTE_NODE
					   3 				  TEXT_NODE
					   4				  CDATA_SECTION_NODE
					   5				  ENTITY_REFERENCE_NODE		  ?
					   6				  ENTITY_NODE
					   7				  PROCESSING_INSTRUCTION_NODE
					   8 				  COMMENT_NODE
					   9 				  DOCUMENT_NODE
		         	  10 				  DOCUMENT_TYPE_NODE
		         	  11 				  DOCUMENT_FRAGMENT_NODE
		         	  12 				  NOTATION_NODE
					-===========================================================================
					*/
					
					NodeList subNodeList = curriculumnElement.getChildNodes();		// check~!!
					for (int k = 0; k < subNodeList.getLength(); k++)
					{
						Node subNode = subNodeList.item(k);
						if (subNode.getNodeType() == 1)		// 얻어내고자하는 노드타입을 알면 노드타입인덱스로 뽑아낸다.
						{
							Element subElement = (Element)subNode;
							System.out.printf("%s %n", subElement.getTextContent());
						}
						
					} 
					System.out.println("");
				}
				
			}
			 
			// 실행 결과
			// 김상기 010-5693-4223 
			// 김민성 010-5222-1134 
			
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
