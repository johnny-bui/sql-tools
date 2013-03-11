package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.lexer.Lexer;
import com.github.verylazyboy.bnf.node.AGrammar;
import com.github.verylazyboy.bnf.node.Start;
import com.github.verylazyboy.bnf.parser.Parser;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 *
 * @author hbui
 * @version 08.03.2013
 */
public class KeyStatementFilterTest extends TestCase{
	@Test
	public void testGetDependProd(){
		HtmlFilter htlmFilter = new HtmlFilter();
		htlmFilter.filter();
		Set<String> keySttm = htlmFilter.getKeyStatements();
		//Set<String> keySttm = new HashSet<String>();
		//keySttm.add("<identifier>");
		DataInputStream s = new DataInputStream(
				new BufferedInputStream(
					TestParseAll.class.getResourceAsStream("sql-03.bnf")));
		Lexer l = new Lexer(
				new PushbackReader(
				new InputStreamReader(s)));
		try{
			Parser p = new Parser(l);
				
			Start tree = p.parse();
			KeyStatementFilter sttmFilter = new KeyStatementFilter();
			Set<String> dependProd = sttmFilter.getDependProd((AGrammar) tree.getPGrammar(), keySttm);
			System.out.println(dependProd.size());
		}catch(Exception ex){
			System.out.println();
			System.out.println("=========================================");
			System.out.println(ex.getMessage());
			System.out.println("=========================================");
			fail();
		}
	}
}
