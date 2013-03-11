package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.analysis.DepthFirstAdapter;
import com.github.verylazyboy.bnf.node.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author hbui
 * @version 06.03
 */
public class TokenFilter extends DepthFirstAdapter{

	private Set<String> tokens;
	private Set<String> helpers;
	private Set<String> ref;
	public TokenFilter() {
		tokens = new HashSet<String>();
		helpers = new HashSet<String>();
	}

	public void filter(final Set<String> ref, AGrammar grammar){
		this.ref = ref;
		grammar.apply(this);
	}

	
	@Override
	public void caseAGrammar(AGrammar node) {
		if(node.getTokens() != null)
        {
            node.getTokens().apply(this);
        }
	}

	@Override
	public void caseATokenDef(ATokenDef node) {
		TIdentifier token = node.getIdentifier();
		String name = token.getText();
		if (ref.contains(name)){
			tokens.add(name);
		}else{
			helpers.add(name);
		}
		super.caseATokenDef(node);
	}

	
	
	public Set<String> getTokens() {
		return tokens;
	}

	public Set<String> getHelpers() {
		return helpers;
	}

	
}
