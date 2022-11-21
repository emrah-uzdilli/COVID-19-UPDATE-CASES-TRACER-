package com.webscrapt;


import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.Scanner;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebScraper implements Data {
    private String[] unFilteredDataArray;
    private String totalCoronaCases;
    private String totalDeaths;
    private String totalRecovered;
    private String lastUpdated;

    public WebScraper() {

    }

    public void getData() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter country name:");
        String c = scanner.nextLine();

        Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus/country/" + c + "/").timeout(60000)
                .get();

        Elements data = doc.select("div[class=content-inner]");
        String unFilteredData = data.text();

        unFilteredDataArray = unFilteredData.split(" ");

        totalCoronaCases = unFilteredDataArray[15];
        totalDeaths = unFilteredDataArray[17];
        totalRecovered = unFilteredDataArray[19];
        lastUpdated = unFilteredDataArray[7] + " " + unFilteredDataArray[8] + " "
                + unFilteredDataArray[9].substring(0, unFilteredDataArray[9].length() - 1);

    }
    public String getTotalCoronaCases() {
        return totalCoronaCases;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public String getTotalRecoveries() {
        return totalRecovered;
    }

    public String getLastUpdate() {
        return lastUpdated;
    }




}
