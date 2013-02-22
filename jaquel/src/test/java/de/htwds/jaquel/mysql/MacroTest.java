/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwds.jaquel.mysql;

import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author hbui
 */
public class MacroTest extends TestCase{

	@Test
	public void testMSeclectClause(){
		// NOTE: should work as expected
		MSelect m = new MSelect();
		MFromClause from = m.newFromClause();
		from.newTableReferenceList().newSubTable("select ... ").newAsClause("q");
		from.newTableReferenceList().newTableCollection("xxxxx as x");
		from.newTableReferenceList().newTableCollection("yyyyy as y");
		System.out.println(m);
	}

	@Test
	public void testMSeclectClause2(){
		// NOTE: should work as expected
		MSelect m = new MSelect();
		MFromClause from = m.newFromClause();
		from.newTableReferenceList().newTableReference("xxx").newAsClause("x");
		from.newTableReferenceList().newSubTable("select ... ").newAsClause("q");
		System.out.println(m);
	}
	
	@Test
	public void testMTableList(){
		// NOTE: should make bug
		MTableReferenceList m = new MTableReferenceList();
		m.newSubTable("aaaa").newAsClause("A");
		m.newTableCollection("bbb as b");
		System.out.println(m);
	}
}
