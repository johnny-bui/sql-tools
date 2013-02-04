/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwds.jaquel.mysql;

import de.htwds.jaquel.Composer;
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
	 * Test of createTable method, of class Composer.
	 */
	@Test
	public void testVerySimpleTable() {
		Composer p = new MySQLComposer();
		String sql = p.createTable("hellojaquel")
				.column("hallo", "varchar(40)")
				.column("jaquel", "varchar(100)")
				.getSQL();
		System.out.println(sql);
	}
	
	@Test
	public void testTableWithColumnConstraint(){
		Composer p = new MySQLComposer();
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

	public void testTableWithTableConstraint(){
		Composer p = new MySQLComposer();
		
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
	}		
}
