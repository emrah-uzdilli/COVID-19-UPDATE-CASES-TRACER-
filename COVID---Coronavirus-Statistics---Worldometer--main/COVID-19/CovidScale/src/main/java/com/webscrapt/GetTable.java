package com.webscrapt;

import java.io.*;
import org.apache.commons.lang3.ArrayUtils;
import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.text.SimpleDateFormat;


public class GetTable {


    // url from main website of corona world meter
    public String getTable() throws Exception {
        String[] data = null;
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());

        //---------------------------------------------------------//
        String result = "";
        String html = " https://www.worldometers.info/coronavirus/countries-where-coronavirus-has-spread/ ";
        try {
            File file = new File("GFGsheet_" + timeStamp + ".csv");


            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);


            String[] header = {"Country", "Recovery", "Death", "Region"};
            writer.writeNext(header);
            Document doc = Jsoup.connect(html).get();
            Elements tableElements =
                    doc.select
                            ("table");


            Elements tableRowElements =
                    tableElements.select
                            (":not(thead) tr");
            //result = "Country;Recovery;Death;Region";

            for (int i = 0; i < tableRowElements.size(); i++) {
                Element row1 = tableRowElements.get(i);
                System.out.println("row");
                Elements rowItems =
                        row1.select
                                ("td");

                for (int j = 0; j < rowItems.size(); j++) {

                    result = result + rowItems.get(j).text() + ";";
                    System.out.println(rowItems.get(j).text());
                    data = ArrayUtils.add(data, rowItems.get(j).text());
                }
                writer.writeNext(data);
                data = null;
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}