package practic161121;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RssCourseAnalizer {

    //  02/03/2002
    final String baseAdr = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    public TreeMap<LocalDate, Double> courseValueOnDates(LocalDate start, LocalDate end, Currency currency) throws ParserConfigurationException, SAXException {
        TreeMap<LocalDate, Double> res = new TreeMap<>();

        LocalDate current = start;
         do {
             res.put(current, courseOnDate(current, currency));
             current=current.plusDays(1);

         } while (current.isBefore(end) || current.equals(end));

        return res;
    }

    public double courseOnDate(LocalDate date, Currency currency) throws ParserConfigurationException, SAXException {


        StringBuilder res = new StringBuilder();


        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();

        DefaultHandler handler = new DefaultHandler() {
            boolean findCurrency;
            boolean rightCurrency;
            boolean targetValue;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equals("CharCode")) {
                    findCurrency = true;
                }

                if (qName.equals("Value") && rightCurrency) {
                    rightCurrency = false;
                    targetValue = true;
                }
            }


            @Override
            public void characters(char[] ch, int start, int length) {
                if (findCurrency &&
                        new String(ch, start, length).equalsIgnoreCase(currency.name())
                ) {
                    findCurrency = false;
                    rightCurrency = true;
                }
                if (targetValue) {
                    res.append(new String(ch, start, length));
                    targetValue = false;
                }

            }
        };


        return Double.parseDouble(res.toString().replace(',','.'));

    }
}
