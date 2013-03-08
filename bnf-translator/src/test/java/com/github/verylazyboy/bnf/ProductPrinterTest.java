package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.lexer.Lexer;
import com.github.verylazyboy.bnf.node.Start;
import com.github.verylazyboy.bnf.parser.Parser;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import junit.framework.TestCase;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 *
 * @author hbui
 * @version 04.03.2012
 */
public class ProductPrinterTest extends TestCase{
	@Test
	public void testPrint() throws Exception{
		DataInputStream s = new DataInputStream(
                new BufferedInputStream(
                ProductPrinterTest.class.getResourceAsStream("sql-03.bnf")));
		Lexer l = new Lexer(
				new PushbackReader(
				new InputStreamReader(s)));
		try{
			Parser p =
				new Parser(l);
			Start tree = p.parse();
			ProductionPrinter printer = new ProductionPrinter();
			tree.apply(printer);
		}catch(Exception ex){
			System.out.println();
			System.out.println("=========================================");
			System.out.println(ex.getMessage());
			System.out.println("=========================================");
			fail();
		}
	}
}
