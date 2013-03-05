package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.node.Node;
import com.github.verylazyboy.bnf.node.Token;

/**
 *
 * @author hbui
 * @version 18.03.2013
 */
public abstract class NodeOutputFormater {
	public abstract String format(Node node);
	public abstract String format(Token token);
	public abstract String indentUnit();
}
