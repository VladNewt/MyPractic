package practic181121.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import practic161121.Currency;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class RssCourseAnalyzer {
    //https://www.cbr-xml-daily.ru
    //16/11/2021
    final String baseAdr = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    public TreeMap<LocalDate, Double> courseValueOnDates(
            LocalDate start, LocalDate end, Currency currency) {
        TreeMap<LocalDate, Double> res = new TreeMap<>();
        LocalDate current = LocalDate.from(start);
        do {
            res.put(current, courseOnDate(current, currency));
            current = current.plusDays(1);
        }
        while (current.isBefore(end) || current.isEqual(end));
        return res;
    }

    public double courseOnDate(LocalDate date, Currency currency) {
        Double res = 0.0;
        String adr = baseAdr + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        boolean rightCurrency = false;

        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(adr);
            doc.normalizeDocument();

            Node root = doc.getDocumentElement();

            NodeList childNodes = root.getChildNodes();

            Node node;

            //1
//            for (int i = 0; i < childNodes.getLength(); i++) {
//                node = childNodes.item(i);
//                for(int j = 0; j < node.getChildNodes().getLength();j++) {
//                    NodeList data = node.getChildNodes();
//                    if (node.getNodeName().equals("CharCode") && data.item(j).getTextContent().equals(currency.name())) {
//                        rightCurrency=true;
//
//                    } else if (rightCurrency && node.getNodeName().equals("Value")) {
//                        res = Double.parseDouble(node.getTextContent().replace(',','.'));
//                        rightCurrency = false;
//                        i = childNodes.getLength();
//                        break;
//                    }
//                }
//            }


            //2
            Node valute = root.getFirstChild();
            Node data;

            while (valute!=null) {
                data = valute.getFirstChild();
                while (data!=null && !rightCurrency) {

                    if(data.getNodeName().equals("CharCode") && 
                            data.getTextContent().equals(currency.name())) {
                        rightCurrency=true;
                    } else if (rightCurrency && data.getNodeName().equals("Value")) {
                        res = Double.parseDouble(data.getTextContent()
                            .replace(',','.'));
                        break;
                    }


                    data=data.getNextSibling();
                }


                valute = valute.getNextSibling();
            }







        } catch (Exception e) {
            e.printStackTrace();
        }


        return res;

    }
}
