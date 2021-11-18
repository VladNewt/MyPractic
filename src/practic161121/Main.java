package practic161121;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(Arrays.asList(
           new User("a",1, "user a"),
           new User("b",2),
           new User("c", 3)
        ));

        try {
            ArrayList<User> userRes = new ArrayList<>();


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            //
            doc.setXmlVersion("1.0");
            doc.setXmlStandalone(false);
            //
            Element root = doc.createElement("users");
            doc.appendChild(root);
            //
            Element element;
            Element text;
            for (User user : users) {
                element = doc.createElement("user");
                element.setAttribute("name", user.getName());
                element.setAttribute("age", String.valueOf(user.getAge()));
                text = doc.createElement("resume");
                text.setTextContent(user.getResume());
                element.appendChild(text);
                root.appendChild(element);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(
                    new FileOutputStream("users.xml")
            ));

            //SAXParser
            try {
                SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
                DefaultHandler handler = new DefaultHandler() {
                    User user;
                    boolean name = false;
                    boolean age = false;
                    boolean resume = false;

                    @Override
                    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                        //super.startElement(uri, localName, qName, attributes);
                        //аттрибуты
                        if (qName.equals("user") /*&& attributes.getLength()>0*/) {
                            user = new User();
                            user.setName(attributes.getValue("name"));
                            user.setAge(Integer.parseInt(attributes.getValue("age")));

                            System.out.println(attributes.getValue("name"));
                            System.out.println(attributes.getValue("age"));
                            name = true; age = true;
                        }

                        //узлы
                        if (qName.equals("resume")) {
                            resume=true;
                        }




                    }

                    @Override
                    public void characters(char[] ch, int start, int length) {
                        if (resume) {
                            user.setResume(new String(ch,start,length));
                            //System.out.println(new String(ch,start,length));
                            resume=false;
                        }
                    }

                    @Override
                    public void endElement(String uri, String localName, String qName) throws SAXException {
                        if(qName.equals("user")) {
                            userRes.add(user);
                            user=null;
                        }
                    }
                };

                saxParser.parse("users.xml", handler);
                userRes.forEach(System.out::println);

            } catch (Exception e) {
                e.printStackTrace();
            }




        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
