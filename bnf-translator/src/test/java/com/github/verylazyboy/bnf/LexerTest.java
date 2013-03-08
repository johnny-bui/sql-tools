package com.github.verylazyboy.bnf;


import com.github.verylazyboy.bnf.lexer.Lexer;
import com.github.verylazyboy.bnf.lexer.LexerException;
import com.github.verylazyboy.bnf.node.TBlank;
import com.github.verylazyboy.bnf.node.TIdentifier;
import com.github.verylazyboy.bnf.node.TOnelineComment;
import com.github.verylazyboy.bnf.node.TString;
import com.github.verylazyboy.bnf.node.Token;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 *
 * @author hbui
 */
public class LexerTest extends TestCase{

	@Test
	public void testOnlineComment() throws LexerException, IOException
	{
		Lexer l = new Lexer(
			new PushbackReader(new StringReader("// s\n//\n")));
		
		Token next = l.next();
		assertTrue(next instanceof TOnelineComment);
		

		next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<<");
		assertTrue(next instanceof TOnelineComment);
	}
	
	@Test
	public void testIdentifier() throws LexerException, IOException
	{
		String testString = "// comment\n <aaaa> <aa bb><aaa bb ><x>";
		Lexer l = new Lexer(
				new PushbackReader(new StringReader(testString)));
		Token next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<< |"+ next.getText() + "|");
		assertTrue(next instanceof TOnelineComment);
		
		next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<< |" + next.getText() + "|");
		assertTrue(next instanceof TBlank);

		next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<< |" + next.getText() +"|");
		assertTrue(next instanceof TIdentifier);
		
		next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<< |" + next.getText() + "|");
		assertTrue(next instanceof TBlank);
		
		next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<< |" + next.getText() + "|");
		assertTrue(next instanceof TIdentifier);

		try{
			next = l.next();
			next = l.next();
			fail("expected here an Exception but got token: " + next.getClass().getName());
		}catch (LexerException ex){
			System.out.println(ex);
		}catch(IOException ex){
			System.out.println(ex);
		}
	}

	@Test
	public void testIdentifier2() throws LexerException, IOException
	{
		String testString = "<x>";
		Lexer l = new Lexer(
				new PushbackReader(new StringReader(testString)));
		Token next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<< |"+ next.getText() + "|");
		assertTrue(next instanceof TIdentifier);
	}
	
	@Test
	public void testIdentifier3() 
	{
		String testString = "<aaa ab >";
		Lexer l = new Lexer(
				new PushbackReader(new StringReader(testString)));
		Token next;
		try {
			next = l.next();
			fail("Expected a lexer exception but get Token: " + next.getClass().getName());
		} catch (LexerException ex) {
			System.out.println(">>>>" + ex.getToken().getClass().getName() + "<<<< |"+ ex.getToken().getText() + "|");
		}catch(IOException ex){
			System.out.println(">>>>" + ex.toString() + "<<<< |");
		}
	}
	
	
	@Test
	public void testIdentifier4() throws IOException, LexerException
	{
		String testString = "<SQL object identifier>";
		Lexer l = new Lexer(
				new PushbackReader(new StringReader(testString)));
		Token next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<< |"+ next.getText() + "|");
		assertTrue(next instanceof TIdentifier);
	}
	
	@Test
	public void testTransition() throws LexerException, IOException
	{
		String testString = "<y>::=<x>|<z>";
		PrintStateLexer l = 
				new PrintStateLexer(new PushbackReader(new StringReader(testString)));
		Token next;
		for(int i=0; i < 5; ++i){
// identifier -> assign -> identifier -> bar -> identifier
			next = l.next();
			System.out.println(next.getClass().getName());
			System.out.println(l.getState().id());
			System.out.println();
		}
	}
	
	@Test
	public void testTransition2() throws LexerException, IOException
	{
		String testString = "<y>::=\"|\"\n";
		PrintStateLexer l = 
				new PrintStateLexer(new PushbackReader(new StringReader(testString)));
		Token next;
		for(int i=0; i < 3; ++i){
// identifier -> assign -> string
			next = l.next();
			System.out.println(next.getClass().getName());
			System.out.println(l.getState().id());
			System.out.println();
		}
	}

	@Test
	public void testTransition3() throws LexerException, IOException
	{
		String testString = "<y>::=\"<|>\" <z>::=<aaaa>|<bbbb> | ";
		PrintStateLexer l = 
				new PrintStateLexer(new PushbackReader(new StringReader(testString)));
		Token next;
		for(int i=0; i < 12; ++i){
// identifier -> assign -> string
			next = l.next();
			System.out.println(next.getClass().getName());
			System.out.println("***"+next.getText()+"***");
			System.out.println(l.getState().id());
			System.out.println();
		}
	}

	@Test
	public void testTransition4() throws LexerException, IOException
	{
		String testString = "<y>::={{{{ <z>::=<aaaa>|<bbbb>";
		PrintStateLexer l = 
				new PrintStateLexer(new PushbackReader(new StringReader(testString)));
		Token next;
		for(int i=0; i < 9; ++i){
// identifier -> assign -> string
			next = l.next();
			System.out.println(next.getClass().getName());
			System.out.println("***"+next.getText()+"***");
			System.out.println(l.getState().id());
			System.out.println();
		}
	}

	@Test
	public void testTransition5() throws LexerException, IOException
	{
		String testString = "<y>::=[ <a> ]";
		PrintStateLexer l = 
				new PrintStateLexer(new PushbackReader(new StringReader(testString)));
		Token next;
		for(int i=0; i < 7; ++i){
// identifier -> assign -> string
			next = l.next();
			System.out.println(next.getClass().getName());
			System.out.println("***"+next.getText()+"***");
			System.out.println(l.getState().id());
			System.out.println();
		}
	}

	@Test
	public void testString() throws LexerException, IOException
	{
		String testString = "\"\\\"\"";
		PrintStateLexer l = 
				new PrintStateLexer(new PushbackReader(new StringReader(testString)));
		Token next = l.next();
		assertTrue(next instanceof TString);
	}
}
