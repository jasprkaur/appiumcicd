package com.qa.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;

public class TestUtil {
    public  static final Duration wait = Duration.ofMillis(20);
    

    public HashMap<String,String> parseStringXML(InputStream file) throws Exception{
      HashMap<String,String> stringMap=new HashMap<>();
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document document=builder.parse(file);
        document.getDocumentElement().normalize();
        NodeList nodeList=document.getElementsByTagName("string");
            for (int i=0;i<nodeList.getLength();i++){
                    Node node=nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    // Store each element key value in map
                    stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
                }
            }
        return stringMap;




        //return ;
    }
}
