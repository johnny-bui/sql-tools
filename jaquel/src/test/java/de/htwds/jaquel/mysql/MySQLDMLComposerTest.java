/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwds.jaquel.mysql;

import de.htwds.jaquel.DMLComposer;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;
import sqlgrammar.SQLGrammar;

/**
 *
 * @author Hong Phuc Bui
 * 
 */
public class MySQLDMLComposerTest extends TestCase {
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test of createTable method, of class DDLComposer.
	 */
	@Test
	public void testVerySimpleTable() {
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.insertInto("aaaa", "x", "y", "z")
				.value("11111","222222", "33333")
				.value("11111","222222", "33333")
				.value("11111","222222", "33333")
				.getSQL();
		System.out.println(sql);
		SQLGrammar.checkSyntax(sql);
	}

	/**
	 * Test of createTable method, of class DDLComposer.
	 */
	@Test
	public void testInsertIntoTableWithStringsValue() {
		DMLComposer p = new MySQLDMLComposer();
		List<String> values = new ArrayList<String>();
		values.add("1"); values.add("2"); values.add("3"); values.add("4");
		String sql = p.insertInto("aaaa", "x", "y", "z")
				.value(values)
				.value("11111","222222", "33333")
				.value(values)
				.getSQL();
		System.out.println(sql);
		SQLGrammar.checkSyntax(sql);
	}

	/**
	 * Test of createTable method, of class DDLComposer.
	 */
	@Test
	public void testInsertIntoTableWithListValue() {
		DMLComposer p = new MySQLDMLComposer();
		List<String> values = new ArrayList<String>();
		values.add("1"); values.add("2"); values.add("3"); values.add("4");
		List<String> cols = new ArrayList<String>();
		cols.add("x");
		cols.add("y");
		cols.add("z");
		String sql = p.insertInto("aaaa", cols)
				.value(values)
				.value("11111","222222", "33333")
				.value(values)
				.getSQL();
		System.out.println(sql);
		SQLGrammar.checkSyntax(sql);
	}

	@Test
	public void testDeleteTable(){
		DMLComposer p = new MySQLDMLComposer();
		String delete = p.delete("xxxx").getSQL();
		System.out.println(delete);
		SQLGrammar.checkSyntax(delete);
	}
	
	@Test
	public void testDeleteTwoTables(){
		DMLComposer p = new MySQLDMLComposer();
		String delete = p.delete("xxxx", "yyyyy").getSQL();
		System.out.println(delete);
		SQLGrammar.checkSyntax(delete);
	}

	@Test
	public void testSimpleSelect(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select().distinct()
					 .from("sometab")
					 .getSQL();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+sql);
		fail("TODO");
		/*
		select distinct * from sometab ;
		 */
	}
	
	
	@Test
	public void testSelect(){
		DMLComposer p = null;
		p.select()
			.from(
					p.tab("xxx").as("x")
					.tab("yyy").as("y")
					.query(p.select("a","b")
							.from("mytable").where(
								"a < b and b < 10"// or something like that
							)
					).as("s")
					.tab("c")
					.tab("d")
					.tab("xxxxx")
			).where("some thing here")
		.getSQL();
		
/* // generated code:
select * from 
			  `xxx` as `x`
 			, `yyy` as `y`
 			, (select(`a`, `b`)
				from `mytable` where <somthing here> 
			) as `s`
			, `c`
			, `d`
;
*/
		p.select().distinct(
			p.col("aaaa").as("a"). 
			col("bbb").as("b").
			col("c")
		).from("sometab")
		.getSQL();
/*
 select distinct aaaa as a, bbb as b from sometab; 
*/	
		p.select().distinct()
		.from("sometab")
		.getSQL();	
/*
 select distinct * from sometab ; 
*/
		
		p.select().all(
			p.col("aaaa").as("a"). 
			col("bbb").as("b").
			col("c")
		).from("sometab")
		.getSQL();
/*
 select all aaaa as a, bbb as b, d from sometab ; 
*/		
		p.select(
			p.col("aaaa").as("a"). 
			col("bbb").as("b").
			col("c")
		).from("sometab")
		.getSQL();
/*
 select aaaa as a, bbb as b, c from sometab ;
*/		
		p.select("1+2").getSQL();

		p.select("1+2","3+4", "6 > 5").getSQL();// actung: SQL Checker kann diese noch nich parsern
	}
}
