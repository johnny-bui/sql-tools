package com.github.verylazyboy.bnf;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Filter keywords or tokens of sql-xx.html file.
 * @author hbui
 */
public class HtmlFilter {

	private Set<String> specificationRules;
	private Set<String> keyStatements;
	private Set<String> specificationKeywords;

	
	

	public HtmlFilter() {
		specificationKeywords = new HashSet<String>();
		specificationRules = new HashSet<String>();
		keyStatements = new HashSet<String>();
	}

	
	
	public void filter() {
		
		DataInputStream s = new DataInputStream(
				new BufferedInputStream(
				HtmlFilter.class.getResourceAsStream("sql-03-org.html")));
		
		String html = convertStreamToString(s);// Dummy trick 
		if (html.length() == 0){
			throw new RuntimeException("html String is empty");
		}
		Document doc = Jsoup.parse(html);
		// Filter and Collect specificated Key SQL Statement
		Element keyStatementList = doc.select("ul").first();
		Elements items = keyStatementList.select("li");
		for (Element e : items){
			String a = e.select("a").first().text();
			keyStatements.add(a);
			//System.out.println(a);
		}
		// Filter and Collect specificated KW
		Element kwTable = doc.select("a[name=xref-keywords] ~ table").first();
		Elements keywordRows = kwTable.select("tr:gt(0)");
		for(Element r:keywordRows){
			Elements e = r.select("td");
			if (e.size() >= 1){
				String t = e.get(0).text();//.trim();
				specificationKeywords.add(t);
				//System.out.println(">>|"+t+"|<<");
			}
		}
		
		// Filter and Collect specificated rules
		Element ruleTable = doc.select("a[name=xref-rules] ~ table").first();
		Elements rulesRows = ruleTable.select("tr:gt(0)");
		for (Element r : rulesRows){
			Elements e = r.select("td");
			if (e.size() >= 1){
				String t = "<" + e.get(0).text() + ">"; 
				specificationRules.add(t);
				System.out.println(">>|"+ t + "|<<");
			}
		}

		printStatistic(System.out);
	}

	public static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	private void printStatistic(PrintStream out) {
		out.println("===========================================================");
		out.println("There are " + specificationKeywords.size() + " spec. KWs.");
		out.println("          " + specificationRules.size() + " spec. Rules.");
		out.println("          " + keyStatements.size() + " key Statements.");
		out.println("===========================================================");
	}
	
	
	public Set<String> getSpecificationKeywords() {
		return specificationKeywords;
	}

	public Set<String> getSpecificationRules() {
		return specificationRules;
	}

	public Set<String> getKeyStatements() {
		return keyStatements;
	}
}
