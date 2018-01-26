
package com.bilgeadam.edu.vkfmobil.common.Base;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * by @Umut
 * SAMPLES
 **/

/**SAMPLES **/
/*
 * String rootXML="<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\"><s:Body><root>[{\"ErrorCode\":\"20.06.2014 12\",\"ErrorDescription\":\"20.06.2014 12\"}]</root></s:Body></s:Envelope>";
 *
 * ORnek
 * String val = XMLParseHelper.getNodeValue(rootXML, "root");
 *


String testXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<menu><item><id>1</id><name>Margherita</name><cost>155</cost><description>Single cheese topping</description></item><item><id>2</id><name>Double Cheese Margherita</name><cost>225</cost><description>Loaded with Extra Cheese</description></item><item><id>3</id><name>Fresh Veggie</name><cost>110</cost><description>Oninon and Crisp capsicum</description></item><item><id>4</id><name>Peppy Paneer</name><cost>155</cost><description>Paneer, Crisp capsicum and Red pepper</description></item><item><id>5</id><name>Mexican Green Wave</name><cost>445</cost><description>Onion, Crip capsicum, Tomato with mexican herb</description></item></menu>";
 *
 *
 * ORnek
 * XMLParseHelper xmlHelper =new XMLParseHelper(testXML);
			 String KEY_ID = "id";
			   String KEY_NAME = "name";
			   String KEY_COST = "cost";
			    String KEY_DESC = "description";
			String root = "item";
			ArrayList<String> keys =  new ArrayList<String>();
			keys.add(KEY_ID);
			keys.add(KEY_NAME);
			keys.add(KEY_COST);
			keys.add(KEY_DESC);

			ArrayList<HashMap<String, String>> response = xmlHelper.getChildMapValuesByRootItem(root, keys);

			for(HashMap<String, String> map: response)
			{

				String val= map.get(KEY_NAME);
				String val1= map.get(KEY_COST);
			}
 *
 */

public class XMLParseHelper 
{	
	
	 ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
	HashMap<String, String> map = new HashMap<String, String>();
	Document document ;
	
	List<String> xmlKeys;
	
	public XMLParseHelper(String xml)
	{
		if(xml!=null)
		{
			document = getDomElement(xml);
			if(document!=null)
			{
				
				xmlKeys = new ArrayList<String>();
			}
		}
		
	}
	
	
	
	
	public List<String> getChildValuesByRootItem(String ROOT_KEY, String ITEM_TAGNAME) 
	{
		List<String>  list = new ArrayList<String>();
		NodeList nodeList = getElementsByTagName(ROOT_KEY);
		
		for (int i = 0; i <nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element e = (Element) node;
            String val = this.getValueByElement(e, ITEM_TAGNAME);
            list.add(val);
			}
        }

		return list;
	}

	public ArrayList<HashMap<String, String>> getChildMapValuesByRootItem(String ROOT_KEY, List<String> CHILD_KEYS) 
	{
		Items.clear();
		
		
		NodeList nodeList = getElementsByTagName(ROOT_KEY);
		
		for (int i = 0; i <nodeList.getLength(); ) {
			
			Node node =  nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				
				Element element =(Element)node;
			HashMap<String, String> map = new HashMap<String, String>();
			
			
				for(String key: CHILD_KEYS)
				{
					map.put(key,this.getValueByElement(element, key));
				}
            Items.add(map);
            i++;
			}
        }

		return Items;
	}
	
	
	
	 
	public  Document getDomElement(String xml){
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
 
            DocumentBuilder db = dbf.newDocumentBuilder();
 
            InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xml));
                doc = db.parse(is); 
 
            } catch (ParserConfigurationException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (SAXException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            }
                
            return doc;
    }
	
	/**
	 * Xml'den tek bir node icinde ki value almak icin kullanabilir
	 * @param TAG_NAME
	 * @return
	 */
	public static String getNodeValue(String xml, String TAG_NAME)
	{
		String value = null;
		Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
 
            DocumentBuilder db = dbf.newDocumentBuilder();
 
            InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xml));
                doc = db.parse(is); 
 
            } catch (ParserConfigurationException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (SAXException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            }
                
           if(doc!=null)
           {
        	   NodeList nodeList = doc.getElementsByTagName(TAG_NAME);
        	   
        	   if(nodeList!=null && nodeList.getLength()>0)
        	   {
        		   Node rootNode = nodeList.item(0);
        		   
        		   value = XMLParseHelper.getElementValue(rootNode);
        	   }
           }
		
		
		return value;
		
	}
	
	
	
	public String getValueByElement(Element e, String tagName)
	{
		return e.getElementsByTagName(tagName).item(0).getTextContent();
	}
	public NodeList getElementsByTagName(String tagName)
	{
		NodeList list = null;
		list = document.getElementsByTagName(tagName);
		return list;
		
	}

	
	public static final String getElementValue( Node elem ) {
        Node child;
        if( elem != null){
            if (elem.hasChildNodes()){
                for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return null;
 }
	
	
	/*
	 *  NodeList nl = doc.getElementsByTagName(KEY_ITEM);
        // looping through all item nodes <item>
        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            map.put(KEY_ID, parser.getValue(e, KEY_ID));
            map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
            map.put(KEY_COST, "Rs." + parser.getValue(e, KEY_COST));
            map.put(KEY_DESC, parser.getValue(e, KEY_DESC));
 
            // adding HashList to ArrayList
            menuItems.add(map);
        }
	 */
	
	
	

}
