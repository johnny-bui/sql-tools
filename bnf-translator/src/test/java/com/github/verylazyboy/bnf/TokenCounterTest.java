package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.lexer.Lexer;
import com.github.verylazyboy.bnf.lexer.LexerException;
import com.github.verylazyboy.bnf.node.EOF;
import com.github.verylazyboy.bnf.node.Token;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import junit.framework.TestCase;
import org.junit.Test;
/**
 *
 * @author hbui
 * @version 28.02.2012
 */
public class TokenCounterTest extends TestCase{
	@Test
	public void testsql99() throws LexerException, IOException{
		//InputStream bnfTestStrean = 
		//		TokenCounterTest.class.getResourceAsStream("sql-99.bnf");
		
		DataInputStream s = new DataInputStream(
                new BufferedInputStream(
                TokenCounterTest.class.getResourceAsStream("sql-92.bnf")));
		
		PrintTokenLexer l = new PrintTokenLexer(
				new PushbackReader(
				new InputStreamReader(s)));
		try{
			Token t = l.next();
			while (! (t instanceof EOF)){
				t = l.next();
			}
		}catch(LexerException ex){
			fail(ex.getMessage());
		}
	}
}
