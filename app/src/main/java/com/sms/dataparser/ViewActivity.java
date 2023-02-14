package com.sms.dataparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ViewActivity extends AppCompatActivity {

    int mode;
    TextView xmlview,jsonview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        xmlview=findViewById(R.id.xmlview);
        jsonview=findViewById(R.id.jsonview);
        mode=getIntent().getIntExtra("mode",0);

        if(mode==1){
            parseXml();
        }
        if(mode==2){
            parseJson();
        }

        if(mode==3){
            parseXml();
            parseJson();
        }
    }

    public void parseXml() {
        try {
            InputStream inputStream = getAssets().open("xmlparser.xml");

            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document= documentBuilder.parse(inputStream);

            document.normalize();


            NodeList nList = document.getElementsByTagName("place");

            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    xmlview.setText(xmlview.getText()+"\nCity : "+ getValue("city", element2) +"\n");
                    Element element3 = (Element) node;
                    xmlview.setText(xmlview.getText()+"Temperature: "+ getValue("temperature", element3)+"\n");
                    Element element4 = (Element) node;
                    xmlview.setText(xmlview.getText()+"Longitude: "+ getValue("Longitude", element4)+"\n");
                    Element element5 = (Element) node;
                   xmlview.setText(xmlview.getText()+"Latitude: "+ getValue("Latitude", element5)+"\n");
                    Element element6 = (Element) node;
                   xmlview.setText(xmlview.getText()+"Humidity: "+ getValue("Humidity", element6)+"\n");
                    xmlview.setText(xmlview.getText()+"-----------------------");

                }
            }

        }catch (IOException e){
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    public void parseJson() {
        String stringdata=null;
       try {
           InputStream inputStream=getAssets().open("jsonparser.json");
           int size=inputStream.available();
           byte[] buffer= new byte[size];
           inputStream.read(buffer);
           stringdata=new String(buffer);
           Log.e("data", "parseJson: "+stringdata );
           JSONObject jsonObject=new JSONObject(stringdata);
           Log.e("data", "parseJson: "+jsonObject );
           Log.e("data", "parseJson: "+jsonObject.getClass().getName());
           JSONObject cityObject=jsonObject.getJSONObject("place");

           String city=cityObject.getString("city");
           Log.e("data", "parseJson: "+city );

           String Longitude=cityObject.getString("Longitude");
           String Latitude=cityObject.getString("Latitude");
           String Humidity=cityObject.getString("Humidity");
           String temperature=cityObject.getString("temperature");
           jsonview.setText("\n Place:");
           jsonview.append(city+"\n Longitude :");
           jsonview.append(Longitude+"\n Latitude :");
           jsonview.append(Latitude+"\n Humidity :");
           jsonview.append(Humidity+"\n Temperature :");
           jsonview.append(temperature+"\n ");


       } catch (IOException e) {
           e.printStackTrace();
       } catch (JSONException e) {
           e.printStackTrace();
       }
    }
}