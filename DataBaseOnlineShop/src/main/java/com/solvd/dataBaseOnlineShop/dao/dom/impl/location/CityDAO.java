package com.solvd.dataBaseOnlineShop.dao.dom.impl.location;

import com.solvd.dataBaseOnlineShop.dao.interfaces.location.ICityDAO;
import com.solvd.dataBaseOnlineShop.models.location.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CityDAO implements ICityDAO {
    private static final Logger logger = LogManager.getLogger(CityDAO.class);
    private final String FILE = "src/main/resources/xml/cities.xml";

    public String getFILE() {
        return FILE;
    }

    @Override
    public void create(City city) {
        //Open needed resources
        Document doc = createDocument(FILE);

        Node parentNode = doc.getDocumentElement();
        Element childNode = doc.createElement("city");

        childNode.setAttribute("id",String.valueOf(city.getId()));
        parentNode.appendChild(childNode);

        Element name = doc.createElement("name");
        name.setTextContent(city.getName());
        childNode.appendChild(name);

        Element state = doc.createElement("States_id");
        state.setTextContent(String.valueOf(city.getState()));
        childNode.appendChild(state);

        saveChanges(doc, FILE);
    }

    @Override
    public City getByID(int id) {
        Document doc = createDocument(FILE);
        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("city");

        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())==id)
                .collect(Collectors.toList());
        City city = new City();

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);

            city.setId(Integer.parseInt(node.getAttributes().item(0).getTextContent()));

            city.setName(node.getElementsByTagName("name").item(0).getTextContent());
            city.setStateId(
                    Integer.parseInt(node.getElementsByTagName("States_id").item(0).getTextContent()));
        }
        return city;
    }

    @Override
    public void update(City city) {
        Document doc = createDocument(FILE);

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("city");
        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())== city.getId())
                .collect(Collectors.toList());

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);

            node.getElementsByTagName("name").item(0)
                    .setTextContent(city.getName());
            node.getElementsByTagName("States_id").item(0)
                    .setTextContent(String.valueOf(city.getState()));
        }
        saveChanges(doc, FILE);
    }

    @Override
    public void delete(int id) {
        Document doc = createDocument(FILE);

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("city");
        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())== id)
                .collect(Collectors.toList());

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);
            root.removeChild(node);
        }
        saveChanges(doc, FILE);
    }

    public static synchronized Document createDocument(String file) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder dBuilder;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error(e);
        }
        return doc;
    }

    public static synchronized void saveChanges(Document doc, String file){
        Transformer transformer;
        StreamResult result;
        doc.normalize();
        DOMSource source = new DOMSource(doc);

        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setAttribute("indent-number", 2);
        try {
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            result = new StreamResult(new FileOutputStream(file));
            transformer.transform(source, result);
        } catch (TransformerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
