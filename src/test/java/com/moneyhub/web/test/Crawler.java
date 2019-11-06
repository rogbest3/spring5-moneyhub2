package com.moneyhub.web.test;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	public static void main(String[] args) {
/*		String url = "https://kr.fxexchangerate.com/currency-exchange-rates.html";
		try {
			Connection.Response response = Jsoup.connect(url)
												.method(Connection.Method.GET)
												.execute();
			Document document = response.parse();
		//	String html = document.html();
			String text = document.text();
		//	System.out.println(html);
			System.out.println(text);
		} catch (Exception e2) {
			e2.printStackTrace();
		}		*/		
/*		try {
			Document rawData = Jsoup.connect("https://music.bugs.co.kr/chart").timeout(10*1000).get();
			  Elements artist = rawData.select("p[class=artist]"); 
			  Elements title = rawData.select("p[class=title]"); 
			  List<String> artist2 = new ArrayList<>();
			  List<String> title2 = new ArrayList<>();
			  for(Element e : artist) {
				  artist2.add(e.text());
			  }
			  for(Element e : title) {
				  title2.add(e.text());
			  }
			  System.out.println(artist2); 
			  System.out.println("---------------");
			  System.out.println(title2); 

		} catch (Exception e2) {
			e2.printStackTrace();
		}*/
		try {
			Document rawData = Jsoup.connect("https://kr.fxexchangerate.com/currency-exchange-rates.html")
									.timeout(10*1000)
									.get();
			  Elements country1 = rawData.select("table[class=pure-table pure-table-bordered fx-table-usd dis-table-list] th"); 
		      Elements country2 = rawData.select("table[class=pure-table pure-table-bordered fx-table-usd dis-table-list] td"); 
//			  Elements title = rawData.select("p[class=title]"); 
			  List<String> artist1 = new ArrayList<>();
			  List<String> artist2 = new ArrayList<>();
			  for(Element e : country1) {
				  artist1.add(e.text());
			  }

			  System.out.println(artist1); 
			  System.out.println("---------------");
			  for(Element e : country2) {
				  artist2.add(e.text());
			  }
			  System.out.println(artist2); 
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
