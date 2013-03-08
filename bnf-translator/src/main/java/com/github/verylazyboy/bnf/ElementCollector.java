package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.analysis.DepthFirstAdapter;
import com.github.verylazyboy.bnf.node.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Collects all identifiers and key words in productions of a BNF grammar in two sets
 * @author hbui
 */
public class ElementCollector extends DepthFirstAdapter{

	// set of identifier
	private Set<String> idElement;
	// set of key words
	private Set<String> keyword;
	
	// help set to count how many strings are in productions (and not in tokens) 
	// of a grammar. 
	private int stringCounter = 0;
	
	public Set<String> getIdElement() {
		return idElement;
	}

	public Set<String> getKeyword() {
		return keyword;
	}
	
	public ElementCollector() {
		idElement = new HashSet<String>();
		keyword = new HashSet<String>();
		stringCounter = 0;
	}
/*
	@Override
	public void caseAGrammar(AGrammar node) {
		if(node.getProductions() != null)
        {
            node.getProductions().apply(this);
        }
		System.out.println("There are " + stringCounter + " Strings in the grammar.");
	}
	
	@Override
	public void caseAStrElem(AStrElem node) {
		TString str = node.getString();
		System.out.println("============================================");
		System.out.println(str.getText() + " [" + str.getLine() 
				+ "," + str.getPos() + "]");
		System.out.println("============================================");
		++stringCounter;
		super.caseAStrElem(node); 
	}
	
	@Override
	public void caseAIdElem(AIdElem node) {
		TIdentifier identifier = node.getIdentifier();
		idElement.add(identifier.getText());
	}

	@Override
	public void caseATokenElem(ATokenElem node) {
		TTokenName tokenName = node.getTokenName();
		keyword.add(tokenName.getText());
	}
*/
}
