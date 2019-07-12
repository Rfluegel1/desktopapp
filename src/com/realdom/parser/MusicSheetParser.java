package com.realdom.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class MusicSheetParser {
	public StringBuffer processXMLFile(File xmlFile) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {
		XMLEvent xmlEvent = null;
		Characters characters = null;
		StringBuffer xmlRaw = new StringBuffer();
		XMLEventReader xmlReader = XMLInputFactory.newInstance().createXMLEventReader(new FileInputStream(xmlFile));
		while(xmlReader.hasNext()) {
			xmlEvent = xmlReader.nextEvent();
			switch(xmlEvent.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				xmlRaw.append("<"+(((StartElement)xmlEvent).getName()).getLocalPart()+">");
				break;
			case XMLStreamConstants.CHARACTERS:
				characters = (Characters)xmlEvent;
				if(!(characters.isWhiteSpace() || characters.isIgnorableWhiteSpace())) {
					xmlRaw.append(characters.getData());
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				xmlRaw.append("</"+(((EndElement)xmlEvent).getName()).getLocalPart()+">");
				break;
			}
		}
		return xmlRaw;
	}
}
