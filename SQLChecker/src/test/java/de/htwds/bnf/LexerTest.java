package de.htwds.bnf;

import de.htwds.bnf.lexer.Lexer;
import de.htwds.bnf.lexer.LexerException;
import de.htwds.bnf.node.TBlank;
import de.htwds.bnf.node.TIdentifier;
import de.htwds.bnf.node.TOnelineComment;
import de.htwds.bnf.node.Token;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertTrue;
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
				new PushbackReader(new StringReader("--xxxxxx\n -- yyyyyyy")));
		Token next = l.next();
		assertTrue(next instanceof TOnelineComment);
		
		next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<<");
		assertTrue(next instanceof TBlank);

		next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<<");
		assertTrue(next instanceof TOnelineComment);
	}

	@Test
	public void testIdentifier() throws LexerException, IOException
	{
		String testString = "-- comment\n <aaaa> <aa bb><aaa bb >";
		Lexer l = new Lexer(
				new PushbackReader(new StringReader(testString)));
		Token next = l.next();
		assertTrue(next instanceof TOnelineComment);
		
		next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<<");
		assertTrue(next instanceof TBlank);

		next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<<");
		assertTrue(next instanceof TIdentifier);
		
		next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<<");
		assertTrue(next instanceof TBlank);
		
		next = l.next();
		System.out.println(">>>>" + next.getClass().getName() + "<<<<");
		assertTrue(next instanceof TIdentifier);

		try{
			l.next();
			l.next();
			fail("expected here a LexerException");
		}catch (LexerException ex){
			System.out.println(ex);
		}
	}
}
