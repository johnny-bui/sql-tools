/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ddlgrammar;

import ddlgrammar.lexer.Lexer;
import ddlgrammar.lexer.LexerException;
import ddlgrammar.parser.Parser;
import ddlgrammar.parser.ParserException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;

/**
 *
 * @author hbui
 */
public class DDLGrammar {

	
	public static void main(String[] args) {
		try {
			InputStream resourceAsStream = DDLGrammar.class.getResourceAsStream("testfile.sql");
			Lexer l = new Lexer(new PushbackReader( new InputStreamReader(resourceAsStream) ));
			Parser p = new Parser(l);
			p.parse();
		} catch (ParserException ex) {
			System.err.println(ex.getMessage());
			System.err.println(ex.getToken().getClass().getName());
			System.err.println(ex.getToken().getText());
		} catch (LexerException ex) {
			System.err.println(ex.getMessage());
			System.err.println(ex.getToken().getClass().getName());
			System.err.println(ex.getToken().getText());
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
