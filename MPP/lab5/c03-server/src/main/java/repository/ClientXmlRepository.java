package repository;


import common.domain.Client;
import common.domain.validators.RentalException;
import common.domain.validators.Validator;
import common.domain.validators.ValidatorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by paul on 3/12/2017.
 */
public class ClientXmlRepository extends InMemoryRepository<Long, Client> {

    public ClientXmlRepository(Validator<Client> v) {
        super(v);
        findAll();
    }
    @Override
    public Iterable<Client> findAll() {
        List<Client> clients;
        try{
            clients = loadClients();
            clients.forEach((m)->{
                try {
                    super.save(m);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            throw new RentalException(e);
        }
        return clients;
    }


    @Override
    public Optional<Client> delete(Long id){
        try {
            this.removeClient(id);
        }catch (ParserConfigurationException|IOException|SAXException|TransformerException e){
            throw new RentalException("Could not delete client from xml",e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client entity){
        try {
            this.delete(entity.getId());
            this.saveClients(entity);
        }catch (IOException | SAXException | ParserConfigurationException | TransformerException e){
            throw new RentalException("Could not update xml client", e);
        }
        return Optional.empty();
    }

    public Optional<Client> removeClient(Long id) throws ParserConfigurationException, IOException, SAXException,TransformerException{
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("./data/client1.xml");
        Element root = document.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        Stream<Node> nodeStream = IntStream.range(0, nodes.getLength()).mapToObj(nodes::item);
        nodeStream.forEach((Node node)->{
            if (node instanceof Element) {
                Client client = createClientFromNode(node);
                //search for the id
                if (client.getId() == id){
                    //if found , delete the node
                    node.getParentNode().removeChild(node);
                }
            }
        });
        //save in file
        Transformer transformer= TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document),new StreamResult(new File("./data/client1.xml")));

        return Optional.empty();

    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        Optional<Client> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        try{
            saveClients(entity);
        }catch (IOException | SAXException | ParserConfigurationException | TransformerException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private List<Client> loadClients() throws ParserConfigurationException, IOException, SAXException {
        List<Client> clients = new ArrayList<>();

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("./data/client1.xml");
        Element root = document.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        Stream<Node> nodeStream = IntStream.range(0, nodes.getLength()).mapToObj(nodes::item);
        nodeStream.forEach((Node node)->{
            if (node instanceof Element) {
                Client client = createClientFromNode(node);
                clients.add(client);
            }
        });

        return clients;
    }

    private Client createClientFromNode(Node node) {
        Long clientid = Long.parseLong (((Element) node).getAttribute("clientid"));


        String name = getTextContentByTagName((Element) node, "name");
        int age = Integer.parseInt(getTextContentByTagName((Element) node, "age"));
        Client client = new Client(name,age);
        client.setId(clientid);
        return client;
    }

    private String getTextContentByTagName(Element node, String tagName) {
        NodeList nodes = node.getElementsByTagName(tagName);
        Node nodeWithTextContent = nodes.item(0);
        String textContent = nodeWithTextContent.getTextContent();
        return textContent;
    }

    private void saveClients(Client client) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("./data/client1.xml");
        Element root = document.getDocumentElement();

        Node bookNode = createClientNode(document, root, client);
        root.appendChild(bookNode);

        //save in file
        Transformer transformer= TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document),new StreamResult(new File("./data/client1.xml")));
    }

    private Node createClientNode(Document document, Element root, Client client) {
        Node clientNode = document.createElement("client");

        ((Element) clientNode).setAttribute("clientid", client.getId().toString());
        appendChildNodeWithTextContent(document, clientNode, "name", client.getName());
        appendChildNodeWithTextContent(document, clientNode, "age", String.valueOf(client.getAge()));

        return clientNode;
    }

    private void appendChildNodeWithTextContent(Document document, Node parent, String tagName, String text) {
        Node node = document.createElement(tagName);
        node.setTextContent(text);
        parent.appendChild(node);
    }
}
