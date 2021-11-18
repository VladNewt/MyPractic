package practic181121;

import practic161121.Currency;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
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
        //
        String adr = baseAdr + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        try {
            XMLStreamReader reader = XMLInputFactory.newFactory().createXMLStreamReader(
                    URI.create(adr).toURL().openStream()
            );


            boolean rightCurrency = false;
            int event = reader.getEventType();

            while (true) {
                switch (event) {
                    case START_ELEMENT:
                        if (reader.getName().getLocalPart().equals("CharCode")) {
                            event = reader.next();
                            if (reader.isWhiteSpace()) continue;
                            if (reader.getText().equals(Currency.USD.name()))
                                rightCurrency = true;

                        } else if (rightCurrency && reader.getName().getLocalPart().equals("Value")) {
                            event = reader.next();
                            if (reader.isWhiteSpace()) continue;
                            res = Double.parseDouble(reader.getText().replace(',', '.'));
                            rightCurrency = false;
                        }

                        break;

                }
                //Проверка на наличие следующего элемента
                if (!reader.hasNext())
                    break;
                event = reader.next();


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return res;

    }
}
