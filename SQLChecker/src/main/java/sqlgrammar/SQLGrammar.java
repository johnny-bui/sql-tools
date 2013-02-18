/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgrammar;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.StringReader;
import mysqlgrm.lexer.Lexer;
import mysqlgrm.lexer.LexerException;
import mysqlgrm.parser.Parser;
import mysqlgrm.parser.ParserException;

/**
 *
 * @author hbui
 */
public class SQLGrammar {

	
	public static void main(String[] args) {
		System.out.println("Running .....");
		try {
			InputStream resourceAsStream = SQLGrammar.class.getResourceAsStream("testfile.sql");
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
			ex.printStackTrace();
		}
		System.out.println("RUN SUCCESS!!!");
	}

	public static void checkSyntax(String sqlString) throws RuntimeException{
		try {
			Lexer l = new Lexer(new PushbackReader( new StringReader(sqlString) ));
			Parser p = new Parser(l);
			p.parse();
		} catch (ParserException ex) {
			throw new RuntimeException(ex.getMessage() + "\n" + sqlString, ex);
		} catch (LexerException ex) {
			throw new RuntimeException(ex.getMessage() + "\n" + sqlString , ex);
		} catch (IOException ex) {
			throw new RuntimeException(ex.getMessage() + "\n" + sqlString  , ex);
		}
	}
}
