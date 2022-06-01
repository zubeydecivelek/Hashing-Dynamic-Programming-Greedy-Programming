import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {
    /**
     * TODO: Parse the input XML file and return a dictionary as described in the assignment insturctions
     *
     * @param filename the input XML file
     * @return a dictionary as described in the assignment insturctions
     */
    public static Map<String, Malware> parse(String filename) {
        // TODO: YOUR CODE HERE

        Map<String, Malware> malwareMap = new HashMap<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try{
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            Document document = documentBuilder.parse(new File(filename));

            document.getDocumentElement().normalize();

            /*System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
            System.out.println("------");*/

            NodeList nodeList = document.getElementsByTagName("row");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String hash = element.getElementsByTagName("hash").item(0).getTextContent();
                    String level = element.getElementsByTagName("level").item(0).getTextContent();

                    Malware malware = new Malware(title,Integer.parseInt(level),hash);

                    malwareMap.put(hash,malware);
//                    System.out.println(malware);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return malwareMap;
    }
}
