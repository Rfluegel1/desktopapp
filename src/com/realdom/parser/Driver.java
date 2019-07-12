package com.realdom.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Driver {
	public static String FILE_NAME = "/Users/reidfluegel/Documents/Personal/Untitled1.musicxml";

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError, TransformerException {
		String xmlRaw = (new MusicSheetParser()).processXMLFile(new File(FILE_NAME)).toString();
		System.out.println(xmlTransform(4,xmlRaw));
		//System.out.println((new MusicSheetParser()).processXMLFile(new File(FILE_NAME)).toString());
	}
	
	public static String xmlTransform(int indentation, String xmlRaw) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setAttribute("indent-number", indentation);
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult streamResult = new StreamResult(new StringWriter());
		transformer.transform(new StreamSource(new StringReader(xmlRaw)), streamResult);
		return streamResult.getWriter().toString();
	}

}
