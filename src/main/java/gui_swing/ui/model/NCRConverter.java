package gui_swing.ui.model;

import org.jsoup.Jsoup;

public class  NCRConverter {

    static final String [] ncrs = {
            "&#260;","&#261;","&#262;","&#263;","&#280;","&#281;",
            "&#321;","&#322;","&#323;","&#324;","&#211;","&#243;",
            "&#346;","&#347;","&#377;","&#378;","&#379;","&#380;",
            "&#8222;","&#8211;","&#8221;","&#8212;","&#160;","&#174;"};
   static final String [] normalText = {
           "Ą","ą","Ć","ć","Ę","ę",
           "Ł","ł","Ń","ń","Ó","ó",
           "Ś","ś","Ź","ź","Ż","ż",
           "„","–","”","—"," ","®"};

    public static String convertNcrToText(String text){
        for (int i = 0; i < normalText.length ; i++) {
            text = text.replaceAll(ncrs[i],normalText[i]);
        }
        return text;
    }
    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}
