package com.github.verylazyboy.bnf;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Filter keywords or tokens of sql 92 in html file.
 * @author hbui
 */
public class Filter {

	public void filte() {
		DataInputStream s = new DataInputStream(
				new BufferedInputStream(
				Filter.class.getResourceAsStream("SQL-92.html")));
		
		String html = convertStreamToString(s);// Dummy trick 
		//System.out.print(html);
		Document doc = Jsoup.parse(html);

		
		Elements keywordRows = doc.select("table#sqlkeyword").select("tr:gt(0)");
		//Iterator<Element> i = keywordRows.iterator();
		//while(i.hasNext()){
		for(Element r:keywordRows){
			Elements e = r.select("td");
			if (e.size() >= 1){
				String t = e.get(0).text();//.trim();
				System.out.println(">>|"+t+"|<<");
			}
		}
		
		
		Elements rulesRows = doc.select("table#sqlrules").select("tr:gt(0)");
		for (Element r : rulesRows){
			Elements e = r.select("td");
			if (e.size() >= 1){
				String t = e.get(0).text().trim();
				System.out.println(">>|"+
						t.toLowerCase()
						.replace("<", "")
						.replace(">", "")
						.replace(" ", "_")
					+ "|<<");
			}
		}
	}

	public static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
}
