package practic181121.dom;

import practic161121.Currency;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        RssCourseAnalyzer analyzer = new RssCourseAnalyzer();
        System.out.println(analyzer.courseOnDate(LocalDate.now(), Currency.USD));

    }
}
