package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.lexer.Lexer;
import com.github.verylazyboy.bnf.lexer.LexerException;
import java.io.IOException;
import java.io.PushbackReader;

/**
 *
 * @author hbui
 */
public class PrintStateLexer extends Lexer{
	public PrintStateLexer(PushbackReader p){
		super(p);
	}
	public State getState(){
		return this.state;
	}

	@Override
	protected void filter() throws LexerException, IOException {
		super.filter(); //To change body of generated methods, choose Tools | Templates.
	}
	
}
