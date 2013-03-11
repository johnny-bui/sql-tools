package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.analysis.DepthFirstAdapter;
import com.github.verylazyboy.bnf.node.Node;
import com.github.verylazyboy.bnf.node.Token;

/**
 *
 * @author hbui
 * @version 04.03.2013
 */
public class ProductionPrinter extends DepthFirstAdapter{

	private int indent = 0;
	NodeOutputFormater f;

	public ProductionPrinter(){
		f = new NodeOutputFormater() {

			@Override
			public String format(Node node) {
				return node.getClass().getSimpleName();
			}

			@Override
			public String format(Token token) {
				return token.getClass().getSimpleName() + " " + token.getText() 
						+ " [" + token.getLine() + "," + token.getPos() + "]";
			}

			@Override
			public String indentUnit() {
				return "    ";
			}
		};
	}
	
	public void setFormater(NodeOutputFormater formater){
		f = formater;
	}
	
	@Override
	public void defaultIn(Node node) {
		indent +=1;
		for (int i = 0; i < indent; ++i){
			System.out.print(f.indentUnit());
		}
		System.out.print(indent + " ");
		System.out.println(f.format(node));
	}

	@Override
	public void defaultOut(Node node) {
		indent -= 1;
	}
	
	@Override
	public void defaultCase(Node node) {
		for (int i = 0; i < indent + 1; ++i){
			System.out.print(f.indentUnit());
		}
		try{
			System.out.println(f.format((Token)node));
		}catch (ClassCastException ex){
			System.out.println(f.format(node));
		}
	}
	
	
}
