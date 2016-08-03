

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateNewXML 
{       
    public CreateNewXML(Block[] tab, String name)
   {
   // Person person1 = new Person(12,"Kate","Muller","teacher");
   // Person person2 = new Person(10,"Bill","Yung","musician");
   // Person person3 = new Person(134,"Olaf","Roy","engineer");
    //Person person4 = new Person(2,"Iga","Suly","shopkeeper");
    //Person person5 = new Person(23,"Nancy","Caty","sailor");
    
    	//Block block1 = new Block("Node 1", "Content 1","Node1Exit1", "Node1Exit2","1");
    	//Block block2 = new Block("Node 2", "Content 1","Node1Exit1", "Node1Exit2","2");
    	//Block block3 = new Block("Node 3", "Content 1","Node1Exit1", "Node1Exit2","3");
    	//Block block4 = new Block("Node 4", "Content 1","Node1Exit1", "Node1Exit2","4");
    	//Block block5 = new Block("Node 5", "Content 1","Node1Exit1", "Node1Exit2","5");
    	  
    	
   // Person[] tab = new Person[] { person1, person2, person3, person4, person5 };
    /////Block[] tab = new Block[] { block1, block2, block3, block4, block5 };
    //Block(int id, String name, String content, String exit1,String exit2)
       try 
       { 
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document doc = builder.newDocument();
       doc.setXmlStandalone(true);
       Element rootElement = doc.createElement("block");
       doc.appendChild(rootElement);
       for(int i=0; i<tab.length;i++)
       {
           Element blockElement = doc.createElement("block");
           blockElement.setAttribute("name", ""+tab[i].getName()); //name
           rootElement.appendChild(blockElement);
           
           Element contentElement = doc.createElement("content");
           contentElement.setTextContent(tab[i].getContent()); //content
           blockElement.appendChild(contentElement);
           
           Element exit1Element = doc.createElement("exit1");
           exit1Element.setTextContent(tab[i].getExit1()); //Content
           blockElement.appendChild(exit1Element);
           
           Element exit2Element = doc.createElement("exit2");
           exit2Element.setTextContent(tab[i].getExit2());
           blockElement.appendChild(exit2Element);
           
           Element valueElement = doc.createElement("value");
           valueElement.setTextContent(tab[i].getValue());
           blockElement.appendChild(valueElement);
       }
       
       TransformerFactory transformerFactory = TransformerFactory.newInstance();
       Transformer transformer = transformerFactory.newTransformer();
       transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
       DOMSource dom = new DOMSource(doc);
       StreamResult result = new StreamResult(new File(name+".xml"));
       transformer.transform(dom, result);
       DOMSource source = new DOMSource(doc);
       StreamResult console = new StreamResult(System.out);
       System.out.println("File employee was created.");
       transformer.transform(source, console);
       }
       catch(Exception e ){ System.out.println(e.getMessage()); }
   }
}
