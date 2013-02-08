/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwds.jaquel.mysql;

import de.htwds.jaquel.DDLComposer;
import de.htwds.jaquel.DMLComposer;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author phucluoi
 */
public class MySQLDMLComposerTest extends TestCase {
	
	public MySQLDMLComposerTest(String testName) {
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
		DMLComposer p = new MySQLDMLComposer();
		String sql = p.insertInto("aaaa", "x", "y", "z")
				.value("11111","222222", "33333")
				.value("11111","222222", "33333")
				.value("11111","222222", "33333")
				.getSQL();
		System.out.println(sql);
	}
}
