package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.parser.Parser;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author hbui
 */
public class ParserTest extends TestCase{
	@Test
	public void testParser(){
		DataInputStream s = new DataInputStream(
                new BufferedInputStream(
                TokenCounterTest.class.getResourceAsStream("sql-92.bnf")));
		PrintTokenLexer l = new PrintTokenLexer(
				new PushbackReader(
				new InputStreamReader(s)));
		try{
			Parser p =
				new Parser(l);
			p.parse();
		}catch(Exception ex){
			System.out.println();
			System.out.println("=========================================");
			System.out.println(ex.getMessage());
			System.out.println("=========================================");
			fail();
		}
	}
}
