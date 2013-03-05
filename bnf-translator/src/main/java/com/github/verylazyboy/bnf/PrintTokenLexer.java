/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.lexer.Lexer;
import com.github.verylazyboy.bnf.lexer.LexerException;
import com.github.verylazyboy.bnf.node.Token;
import java.io.IOException;
import java.io.PushbackReader;

public class PrintTokenLexer extends Lexer {
	InfoPrinter printer;

	public PrintTokenLexer(PushbackReader pushbackReader) {
		super(pushbackReader);
		printer = new InfoPrinter() {

			@Override
			public void printTokenInfo(Token t) {
				System.out.println("Type: "
				+ token.getClass().getName()
				+ "[" + token.getLine() + "," + token.getPos() + "]"
				+ " Text:>>>|" + token.getText() + "|<<<");
			}

			@Override
			public void printStateInfo(State s) {
				System.out.println("Lexer's state:" + s.id());
			}

			
		};
	}
	
	
	public void setPrinter(InfoPrinter p){
		printer = p;
	}
	
	@Override
	protected void filter() throws LexerException, IOException {
		printer.printTokenInfo(token);
		printer.printStateInfo(state);
	}
}