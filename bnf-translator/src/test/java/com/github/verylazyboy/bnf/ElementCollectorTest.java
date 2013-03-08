package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.lexer.Lexer;
import com.github.verylazyboy.bnf.node.AGrammar;
import com.github.verylazyboy.bnf.node.Start;
import com.github.verylazyboy.bnf.parser.Parser;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.util.Set;
import junit.framework.TestCase;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 *
 * @author hbui
 * @version 04.03.2012
 */
public class ElementCollectorTest extends TestCase{
	@Test
	public void testPrint() throws Exception{
		DataInputStream s = new DataInputStream(
				new BufferedInputStream(
					ElementCollectorTest.class.getResourceAsStream("sql-03.bnf")));
		Lexer l = new Lexer(
				new PushbackReader(
				new InputStreamReader(s)));
		try{
			Parser p = new Parser(l);
				
			Start tree = p.parse();
			ElementCollector c = new ElementCollector();
			tree.apply(c);
			
			Set<String> idElement = c.getIdElement();
//			for (String e : idElement){
//				System.out.println(e);
//			}
			Set<String> kw = c.getKeyword();
//			for (String k : kw){
//				System.out.println(k);
//			}
			TokenFilter f = new TokenFilter();
			f.filter(idElement, (AGrammar) tree.getPGrammar());
			Set<String> tokens = f.getTokens();
//			for (String t: tokens){
//				System.out.println(t);
//			}
		}catch(Exception ex){
			System.out.println();
			System.out.println("=========================================");
			System.out.println(ex.getMessage());
			System.out.println("=========================================");
			fail();
		}
	}
}
