package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.lexer.Lexer;
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
}
