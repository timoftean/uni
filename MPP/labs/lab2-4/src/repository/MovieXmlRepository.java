package repository;

import domain.Movie;
import domain.validators.RentalException;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;


/**
 * Created by Nicu on 3/11/2017.
 */

public class MovieXmlRepository extends InMemoryRepository<Long,Movie> {

    public MovieXmlRepository(Validator<Movie> v) {
        super(v);

        findAll();
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        Optional<Movie> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        try{
            saveMovies(entity);
        }catch (IOException | SAXException | ParserConfigurationException | TransformerException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Movie> findAll() {
        List<Movie> movies;
        try{
            movies = loadMovies();
            movies.forEach((m)->{
                try {
                    super.save(m);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            throw new RentalException(e);
        }
        return movies;
    }


    @Override
    public Optional<Movie> delete(Long id){
        try {
            this.removeMovie(id);
        }catch (ParserConfigurationException|IOException|SAXException|TransformerException e){
            throw new RentalException("Could not delete movie from xml",e);
        }
        return Optional.empty();
    }
    @Override
    public Optional<Movie> update(Movie entity){
        try {
            this.delete(entity.getId());
            this.saveMovies(entity);
        }catch (IOException | SAXException | ParserConfigurationException | TransformerException e){
            throw new RentalException("Could not update xml movie", e);
        }
        return Optional.empty();
    }

    public Optional<Movie> removeMovie(Long id) throws ParserConfigurationException, IOException, SAXException,TransformerException{
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("./data/movie1.xml");
        Element root = document.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        Stream<Node> nodeStream = IntStream.range(0, nodes.getLength()).mapToObj(nodes::item);
        nodeStream.forEach((Node node)->{
            if (node instanceof Element) {
                Movie movie = createMovieFromNode(node);
                if (movie.getId() == id){
                    node.getParentNode().removeChild(node);
                }
            }
        });
        //save in file
        Transformer transformer= TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document),new StreamResult(new File("./data/movie1.xml")));

        System.out.println("Done");


        return Optional.empty();

    }
    private List<Movie> loadMovies() throws ParserConfigurationException, IOException, SAXException {
        List<Movie> movies = new ArrayList<>();

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("./data/movie1.xml");
        Element root = document.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        Stream<Node> nodeStream = IntStream.range(0, nodes.getLength()).mapToObj(nodes::item);
        nodeStream.forEach((Node node)->{
            if (node instanceof Element) {
                Movie movie = createMovieFromNode(node);
                movies.add(movie);
            }
        });

        return movies;
    }

    private Movie createMovieFromNode(Node node) {
        Long movieid = Long.parseLong (((Element) node).getAttribute("movieid"));


        String name = getTextContentByTagName((Element) node, "name");
        String director = getTextContentByTagName((Element) node, "director");
        String genre = getTextContentByTagName((Element) node, "genre");
        int availableCopies = Integer.parseInt(getTextContentByTagName((Element) node, "availablecopies"));

        Movie movie = new Movie(name,director,genre,availableCopies);
        movie.setId(movieid);
        return movie;
    }

    private String getTextContentByTagName(Element node, String tagName) {
        NodeList nodes = node.getElementsByTagName(tagName);
        Node nodeWithTextContent = nodes.item(0);
        String textContent = nodeWithTextContent.getTextContent();
        return textContent;
    }

    private void saveMovies(Movie movie) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("./data/movie1.xml");
        Element root = document.getDocumentElement();

        Node bookNode = createMovieNode(document, root, movie);
        root.appendChild(bookNode);

        //save in file
        Transformer transformer= TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document),new StreamResult(new File("./data/movie1.xml")));
    }

    private Node createMovieNode(Document document, Element root, Movie movie) {
        Node movieNode = document.createElement("movie");

        ((Element) movieNode).setAttribute("movieid", movie.getId().toString());
        appendChildNodeWithTextContent(document, movieNode, "name", movie.getName());
        appendChildNodeWithTextContent(document, movieNode, "director", String.valueOf(movie.getDirector()));
        appendChildNodeWithTextContent(document, movieNode, "genre", String.valueOf(movie.getGenre()));
        appendChildNodeWithTextContent(document, movieNode, "availablecopies", String.valueOf(movie.getAvailableCopies()));


        return movieNode;
    }

    private void appendChildNodeWithTextContent(Document document, Node parent, String tagName, String text) {
        Node node = document.createElement(tagName);
        node.setTextContent(text);

        parent.appendChild(node);
    }

}