/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwds.jaquel.mysql;

import de.htwds.jaquel.DMLComposer;
import junit.framework.TestCase;
import static junit.framework.TestCase.fail;
import org.junit.Test;
import sqlgrammar.SQLGrammar;

/**
 *
 * @author Hong Phuc Bui
 * 
 */
public class MySQLDMLComposerSelectTest extends TestCase {
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testSimpleSelectAll(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select().all("xxx", "yyy")
					 .from("sometab")
					.getSQL();
		System.out.println( sql );
		SQLGrammar.checkSyntax(sql+";");
		/*
		select * from `sometab` ;
		 */
	}
	
	@Test
	public void testSimpleSelect(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select()
					 .from("sometab")
					.getSQL();
		System.out.println( sql );
		SQLGrammar.checkSyntax(sql+";");
		/*
		select * from `sometab` ;
		 */
	}
	
	@Test
	public void testSimpleSelectWithCols(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select(
						p.col("aaaa").as("a")
						.col("bbbbb").as("b")
					)
					 .from("sometab")
					.getSQL();
		System.out.println( sql );
		SQLGrammar.checkSyntax(sql+";");
		/*
		select * from `sometab` , `somewhere_else`;
		 */
		sql = p.select(
						p.col("aaaa").as("xxxx")
					)
					 .from("sometab")
					.getSQL();
		System.out.println( sql );
		SQLGrammar.checkSyntax(sql+";");

		sql = p.select(
						p.col("aaaa")
					)
					 .from("sometab")
					.getSQL();
		System.out.println( sql );
		SQLGrammar.checkSyntax(sql+";");
	}
	
	@Test
	public void testSimpleSelectWithTabRef(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select()
					 .from(
						p.tab("xxxx").as("x")
						.tab("yyyy").as("y")
					).getSQL();
		System.out.println( sql );
		SQLGrammar.checkSyntax(sql+";");
		/*
		select * from `sometab` , `somewhere_else`;
		 */
	}
	
	@Test
	public void testSimpleAllSelect(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select().all()
					 .from("sometab", "somewhere_else")
					.getSQL();
		System.out.println( sql );
		SQLGrammar.checkSyntax(sql+";");
		/*
		select all * from `sometab` , `somewhere_else`;
		 */
	}
	
	@Test
	public void testSimpleDistinctSelect(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select().distinct()
							.from("sometab", "somewhere_else")
						   .getSQL();
		System.out.println( sql);
		SQLGrammar.checkSyntax(sql + ";");
		/*
		select distinct * from `sometab` ;
		 */
	}
	
	@Test
	public void testSimpleColumnSelect(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select("aaa", "bbbb")
					 .from("sometab", "somewhere_else")
					.getSQL();
		System.out.println( sql);
		SQLGrammar.checkSyntax(sql + ";");
		/*
		select distinct `aaa`, `bbbb` from `sometab`, `somewhere_else` ;
		 */
		
	}
	
	@Test
	public void testSimpleColumnInDistinctSelect(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select().distinct("aaa", "bbbb")
					 .from("sometab", "somewhere_else").getSQL();
		System.out.println( sql );
		SQLGrammar.checkSyntax(sql + ";");
		/*
		select distinct `aaa`, `bbbb` from `sometab`, `somewhere_else` 
		 */
	}
	
	@Test
	public void testSelectSubTable(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select()
					  .from(p.query(p.select("a","b")
										 .from("mytable").where(
											 "a < b and b < 10"// or something like that
										 )
								 ).as("s")
					    )
					   .where("some thing here")
					   .getSQL();
		System.out.println(sql);
/* // generated code:
select * from 
 			(select(`a`, `b`)
				from `mytable` where  a < b and b < 10
			) as `s`
		where some thing here 
;
*/
		//fail("Insert assert");
	}
	
	@Test
	public void testSelectCollectTable(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select()
					  .from(p.tab("yyyyyyyyyyyyyy"))
					   .where("some thing here")
					   .getSQL();
		System.out.println(sql);
		SQLGrammar.checkSyntax(sql + ";");
/* // generated code:
select * from `yyyyyyyyyyyyyy`
where some thing here 
;
*/
	}
	
	@Test
	public void testSelectCombinedTable(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select()
						 .from(
								 p.tab("xxx")
								 .query(p.select("a","b")
										 .from("mytable").where(
											 "a < b and b < 10"// or something like that
										 )
								 ).as("s")
								.tab("tttt").as("t")
						 ).where("some thing here")
					 .getSQL();
		System.out.println(sql);
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
		fail("Insert assert");
	}
	
	@Test
	public void testTableWithAlias(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select().distinct()
					 .from(p.tab("xxx").as("x")
							.tab("yyyy").as("y"))
					 .getSQL();
		System.out.println(sql);
		/*
		select distinct aaaa as a, bbb as b from sometab;
		 */	
		SQLGrammar.checkSyntax(sql + ";");
	}
	
	@Test
	public void testColumnWithAlias(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select().distinct(
						 p.col("aaaa").as("a"). 
						 col("bbb").as("b").
						 col("c")
					 ).from("sometab")
					 .getSQL();
		System.out.println(sql);
		SQLGrammar.checkSyntax(sql + ";");
		/*
		select distinct `aaaa` as `a`, `bbb` as `b` from `sometab`;
		 */	
	}
	
	@Test
	public void testSelectDistinct(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select().distinct()
					 .from("sometab")
					 .getSQL();
		System.out.println(sql);
		SQLGrammar.checkSyntax(sql + ";");
		/*
		select distinct * from sometab ;
		 */
	}
	
	@Test
	public void testSelectAll(){
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.select().all()
					 .from("sometab")
					 .getSQL();
		System.out.println(sql);
		SQLGrammar.checkSyntax(sql + ";");
		/*
		select distinct * from sometab ;
		 */
	}
	
	@Test
	public void testMacroTableRefList(){
		MTableReferenceList macro = new MTableReferenceList();
		//macro.newTableReference("cccc").newAsClause("c");
		macro.newTableCollection("`aaa` as `a`, `bbb` as `b`");
		//macro.newSubTable("select * from `xxx`").newAsClause("x");
		System.out.println(macro.toString());
		//fail("temporary test");
	}
	
	@Test
	public void testSelectAllCols(){
		DMLComposer p = new MySQLDMLComposer();
		String sql;
		sql = p.select().all(
			p.col("aaaa").as("a"). 
			col("bbb").as("b").
			col("c")
		).from("sometab")
		.getSQL();
		System.out.println(sql);	
		SQLGrammar.checkSyntax(sql + ";");
/*
 select all aaaa as a, bbb as b, d from sometab ; 
*/		
		sql = p.select(
			p.col("aaaa").as("a"). 
			col("bbb").as("b").
			col("c")
		).from("sometab")
		.getSQL();
		System.out.println(sql);	
		SQLGrammar.checkSyntax(sql + ";");
/*
 select aaaa as a, bbb as b, c from sometab ;
*/		
	}
}
