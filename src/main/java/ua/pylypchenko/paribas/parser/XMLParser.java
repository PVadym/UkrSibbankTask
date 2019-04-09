package ua.pylypchenko.paribas.parser;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Interface for parsing XML using {@link XMLStreamReader} implementation
 * @author pylypchenko
 */

public interface XMLParser {

    default void parse() throws XMLStreamException {
        XMLStreamReader reader = this.getReader();
        while (reader.hasNext()){
            int event = reader.next();
            if(event == XMLStreamConstants.START_ELEMENT){
                parseStartElement(reader.getLocalName());
            } else if (event == XMLStreamConstants.CHARACTERS){
                parseText(reader.getText());
            } else if (event == XMLStreamConstants.END_ELEMENT){
                parseEndElement(reader.getLocalName());
            }
        }
    }

    void parseStartElement(String nodeName);

    void parseEndElement(String nodeName);

    void parseText(String text);

    void setReader(XMLStreamReader reader);

    XMLStreamReader getReader();

    Object getParsedObject();
}
