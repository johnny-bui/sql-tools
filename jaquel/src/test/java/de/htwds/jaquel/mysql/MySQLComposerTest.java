/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwds.jaquel.mysql;

import de.htwds.jaquel.DDLComposer;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author phucluoi
 */
public class MySQLComposerTest extends TestCase {
	
	public MySQLComposerTest(String testName) {
		super(testName);
	}
	
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
		DDLComposer p = new MySQLComposer();
		String sql = p.createTable("hellojaquel")
				.column("hallo", "varchar(40)")
				.column("jaquel", "varchar(100)")
				.getSQL();
		System.out.println(sql);
	}
	
	@Test
	public void testTableWithColumnConstraint(){
		DDLComposer p = new MySQLComposer();
		String constraintFirst = p.createTable("columnConstraint")
				.column("column", "bigint").unique().notNull()
				.column("dummy", "float")
				.getSQL();
		System.out.println(constraintFirst);
		
		String constraintSecond = p.createTable("columnConstraint2")
				.column("dummy", "float")
				.column("column", "integer").unique().notNull()
				.getSQL();
		System.out.println(constraintSecond);

		String constraintMiddle = p.createTable("thirdConstraint")
				.column("first", "text")
				.column("seconde", "varchar(1000)").unique().notNull()
				.column("third", "interger").autoIncrement()
				.getSQL();
		System.err.println(constraintMiddle);
	}
	
	@Test
	public void testTableWithTableConstraint(){
		DDLComposer p = new MySQLComposer();
		
		String simpleTableConstraint = p.createTable("constraintTab")
				.column("c1", "bigint")
				.column("bbb", "bigint").unique().notNull()
				.column("c2", "varchar(5000)")
				.constraint("rule_one").
					primaryKey("c1")
				.getSQL();
		System.out.println(simpleTableConstraint);
		
		String tableDualPrimaryKey = p.createTable("constraintTab")
				.column("c1", "bigint")
				.column("bbb", "bigint").unique().notNull()
				.column("c2", "varchar(5000)")
				.constraint("rule_one").
					primaryKey("c1", "bbb")
				.getSQL();
		System.out.println(tableDualPrimaryKey);
		
		String twoRulesTable = p.createTable("twoRule")
				.column("id", "bigint")
				.column("rul1", "integer")
				.column("rule2", "text")
				.constraint("r1").primaryKey("id")
				.constraint("r2").foreignKey("rul1")
						.references("constraintTab","c1")
				.getSQL();
		System.out.println(twoRulesTable);
		
		List<String> refCols = new ArrayList<String>();
		refCols.add("id");
		refCols.add("rul1");
		refCols.add("rul2");

		String twoRulesInListTable = p.createTable("twoRule")
				.column("id", "bigint")
				.column("rul1", "integer")
				.column("rule2", "text")
				.constraint("r1").primaryKey(refCols)
				.getSQL();
		System.out.println(twoRulesInListTable);
	}
	
	public void testTableWithTableConstraint2(){
		DDLComposer p = new MySQLComposer();
		
		String tableDualPrimaryKey = p.createTable("constraintTab")
				.column("c1", "bigint")
				.column("bbb", "bigint").unique().notNull()
				.column("c2", "varchar(5000)")
				.constraint("rule_one").
					primaryKey("c1", "bbb")
				.getSQL();
		System.out.println(tableDualPrimaryKey);
		
		String twoRulesTable = p.createTable("twoRule")
				.column("id", "bigint")
				.column("rul1", "integer")
				.column("rule2", "text")
				.constraint("r1").primaryKey("id","rul1","rul2")
				.constraint("r2").foreignKey("id","rul1","rul2")
						.references("constraintTab","c1","bbb","c2")
				.getSQL();
		System.out.println(twoRulesTable);

		List<String> refCols = new ArrayList<String>();
		refCols.add("id");
		refCols.add("rul1");
		refCols.add("rul2");

		String twoRulesInListTable = p.createTable("twoRule")
				.column("id", "bigint")
				.column("rul1", "integer")
				.column("rule2", "text")
				.constraint("r1").foreignKey(refCols)
					.references("xxxx", refCols)
				.getSQL();
		System.out.println(twoRulesInListTable);
		
		String threeRulesInListTable = p.createTable("twoRule")
				.column("id", "bigint")
				.column("rul1", "integer")
				.column("rule2", "text")
				.constraint("r1").foreignKey(refCols)
					.references("xxxx", refCols)
				.constraint("r3").primaryKey(refCols)
				.getSQL();
		System.out.println(threeRulesInListTable);
	}

	@Test
	public void testRefercencesNotMatch(){
		DDLComposer p = new MySQLComposer();
		List<String> refCols = new ArrayList<String>();
		refCols.add("id");
		refCols.add("rul1");
		refCols.add("rul2");
		try{
			String threeRulesInListTable = p.createTable("twoRule")
					.column("id", "bigint")
					.column("rul1", "integer")
					.column("rule2", "text")
					.constraint("r1").foreignKey(refCols)
						.references("xxxx", "a","b", "c", "d")
					.constraint("r3").primaryKey(refCols)
					.getSQL();
			System.out.println(threeRulesInListTable);
			fail("expected an runtime exception because the columns do not match");
		}catch (RuntimeException ex){
			// all OK
			System.out.println(">>>>>>> " +ex.getMessage());
		}
	}

	@Test
	public void testRefercencesNotMatch2(){
		DDLComposer p = new MySQLComposer();
		List<String> refCols = new ArrayList<String>();
		refCols.add("id");
		refCols.add("rul1");
		refCols.add("rul2");
		
		List<String> refCols2 = new ArrayList<String>();
		refCols2.add("id");
		refCols2.add("rul1");
		refCols2.add("rul2");
		refCols2.add("rul3");
		
		try{
			String threeRulesInListTable = p.createTable("twoRule")
					.column("id", "bigint")
					.column("rul1", "integer")
					.column("rule2", "text")
					.constraint("r1").foreignKey(refCols)
						.references("xxxx", refCols2)
					.constraint("r3").primaryKey(refCols)
					.getSQL();
			System.out.println(threeRulesInListTable);
			fail("expected an runtime exception because the columns do not match");
		}catch (RuntimeException ex){
			// all OK
			System.out.println(">>>>>>> " +ex.getMessage());
		}
	}
	

	@Test
	public void testRefercencesNotMatch3(){
		DDLComposer p = new MySQLComposer();
		List<String> refCols = new ArrayList<String>();
		refCols.add("id");
		refCols.add("rul1");
		refCols.add("rul2");
		
		try{
			String threeRulesInListTable = p.createTable("twoRule")
					.column("id", "bigint")
					.column("rul1", "integer")
					.column("rule2", "text")
					.constraint("r1").foreignKey(refCols)
						.references("xxxx", "yyyy")
					.constraint("r3").primaryKey(refCols)
					.getSQL();
			System.out.println(threeRulesInListTable);
			fail("expected an runtime exception because the columns do not match");
		}catch (RuntimeException ex){
			// all OK
			System.out.println(">>>>>>> " +ex.getMessage());
		}
	}

	@Test
	public void testDropTable(){
		DDLComposer p = new MySQLComposer();
		String sql = p.dropTable("xxxxxx").getSQL();
		System.out.println(sql);
	}
	
	@Test
	public void testTruncateTable(){
		DDLComposer p = new MySQLComposer();
		String sql = p.truncateTable("xxxxx").getSQL();
		System.out.println(sql);
	}

}
