package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.lexer.Lexer;
import com.github.verylazyboy.bnf.node.Token;

/**
 *
 * @author hbui
 */
public abstract class InfoPrinter {
	public abstract void printTokenInfo(Token t);
	public abstract void printStateInfo(Lexer.State s);
}
