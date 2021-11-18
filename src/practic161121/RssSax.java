package practic161121;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static practic161121.Currency.*;
import static practic161121.Currency.USD;

public class RssSax {
    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        final String adr = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=02/03/2002";

//        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern(
//                "dd/MM/yyyy"
//        )));

        RssCourseAnalizer analizer = new RssCourseAnalizer();
        //System.out.println(analizer.courseOnDate(LocalDate.now(), USD));
        System.out.println(analizer.courseValueOnDates(
                LocalDate.of(2003,01,13),
                LocalDate.of(2003,03,16),USD
        ));



    }
}
