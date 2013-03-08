package com.github.verylazyboy.bnf;

import com.github.verylazyboy.bnf.analysis.DepthFirstAdapter;
import com.github.verylazyboy.bnf.node.*;
import java.util.HashSet;
import java.util.Set;
import org.sablecc.sablecc.analysis.AnalysisAdapter;

/**
 * gets a list of key productions and picks up all productions, on which the key 
 * productions depend.
 * 
 * @author hbui
 */
public class KeyStatementFilter extends DepthFirstAdapter{
	private Set<String> dependProduct;
	private Set<String> newAddedProduct;
	//private String currentParent;

	public KeyStatementFilter() {
		dependProduct = new HashSet<String>();
		newAddedProduct = new HashSet<String>();
	}
	
	public Set<String> getDependProd(AGrammar g, Set<String> keyProd){
		dependProduct.addAll(keyProd);
		int beginSize = 0;
		int endSize = 0;
		int i = 0;
		do{
			++i;
			beginSize = dependProduct.size();
			newAddedProduct.clear();
			g.getProductions().apply(this);
			endSize = dependProduct.size();
			System.out.println("Iteration " + i + " beginSize:" + beginSize + " endSize:" + endSize + " newAddedProductions:" + newAddedProduct);
		}while(endSize > beginSize);
		System.out.println("*********************************************");
		System.out.println("depProd:" + dependProduct);
		System.out.println("*********************************************");
		return dependProduct;
	}

	@Override
	public void caseAProd(AProd node) {
		String prodName = node.getIdentifier().getText();
		if (dependProduct.contains(prodName)){
			//currentParent = prodName;
			super.caseAProd(node); 
		}
		// else nothing to do
	}
/*
	@Override
	public void caseAIdElem(AIdElem node) {
		String dependProductionName = node.getIdentifier().getText();
		newAddedProduct.add(dependProductionName);
		dependProduct.add(dependProductionName);
		super.caseAIdElem(node); 
	}
*/ 
}


