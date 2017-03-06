package it.molis.baionetta.feed;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FxmlReader {

	//classe usata per leggere il file di backup in xml generato da blogger e creare il documento che viene richiamato a regime
	//quella classe non viene più chiamata e non dovrà più essere chiamata salvo imprevisti
	//per evitare strani problemi il file di backup del sito è stato eliminato

	File fXmlFile = new File("/home/fabio/workspace/BaioNews/src/blog-03-06-2017.xml");

	public void read(){

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("entry");

			System.out.println("----------------------------");
			PrintWriter writer = new PrintWriter("/home/fabio/workspace/BaioNews/src/BaioBackup.molis", "UTF-8");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					//System.out.println("Staff id : " + eElement.getAttribute("title"));
					//System.out.println("Id : " + eElement.getElementsByTagName("id").item(0).getTextContent());
					//writer.print(eElement.getElementsByTagName("id").item(0).getTextContent()+", ");

					LocalDate d = LocalDate.parse(eElement.getElementsByTagName("published").item(0).getTextContent(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
					System.out.println("Date : " +d);
					writer.print(d+"<> ");

					System.out.println("Category : " + ((Element) nNode.getChildNodes().item(4)).getAttribute("term")+""+((Element) nNode.getChildNodes().item(5)).getAttribute("term"));
					String str = ((Element) nNode.getChildNodes().item(3)).getAttribute("term");
					if(str.contains("schemas")){
						writer.print(((Element) nNode.getChildNodes().item(4)).getAttribute("term")+"<> ");
					}else
						writer.print(((Element) nNode.getChildNodes().item(3)).getAttribute("term")+"<> ");

					System.out.println("Title : " + ((Element) nNode.getChildNodes().item(nNode.getChildNodes().getLength()-3)).getAttribute("title") +""+ ((Element) nNode.getChildNodes().item(nNode.getChildNodes().getLength()-4)).getAttribute("title"));
					writer.print(((Element) nNode.getChildNodes().item(nNode.getChildNodes().getLength()-3)).getAttribute("title") +""+ ((Element) nNode.getChildNodes().item(nNode.getChildNodes().getLength()-4)).getAttribute("title")+"<> ");

					System.out.println("Link : " + ((Element) nNode.getChildNodes().item(nNode.getChildNodes().getLength()-3)).getAttribute("href") +""+ ((Element) nNode.getChildNodes().item(nNode.getChildNodes().getLength()-4)).getAttribute("href"));
					if(!((Element) nNode.getChildNodes().item(nNode.getChildNodes().getLength()-3)).getAttribute("href").isEmpty()){
						writer.print(((Element) nNode.getChildNodes().item(nNode.getChildNodes().getLength()-3)).getAttribute("href")+"<> ");
					}else
						writer.print(((Element) nNode.getChildNodes().item(nNode.getChildNodes().getLength()-4)).getAttribute("href")+"<> ");

					System.out.println("Autore : " + eElement.getElementsByTagName("name").item(0).getTextContent());
					writer.print(eElement.getElementsByTagName("name").item(0).getTextContent()+"\n");

				}
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
