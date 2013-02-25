package org.sablecc.sablecc;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import junit.framework.TestCase;
import org.junit.Test;
import org.sablecc.sablecc.lexer.Lexer;
import org.sablecc.sablecc.lexer.LexerException;
import org.sablecc.sablecc.node.TBlank;
import org.sablecc.sablecc.node.Token;

/**
 *
 * @author hbui
 */
public class SableCCLexerTest extends TestCase{

	@Test
	public void testBlank() throws LexerException, IOException{
		Lexer l = new Lexer(new PushbackReader(new StringReader("      \n\n\n")));
		Token next = l.next();
		assertTrue(next instanceof TBlank);
	}
	
}
