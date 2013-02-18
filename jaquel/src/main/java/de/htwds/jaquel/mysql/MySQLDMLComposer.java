package de.htwds.jaquel.mysql;

import de.htwds.jaquel.DMLComposer;
import de.htwds.jaquel.DeleteTable;
import de.htwds.jaquel.FirstInsertTable;
import de.htwds.jaquel.InsertTable;
import de.htwds.jaquel.SelectTable;
import de.htwds.jaquel.SelectUncomplete;
import java.util.List;








/**
 *
 * @author hbui
 */
public class MySQLDMLComposer implements DMLComposer{
	private MInsertInto tab;

	@Override
	public FirstInsertTable insertInto(String tableName, List<String> tupel) {
		tab = new MInsertInto(tableName);
		return new MySQLInsertTable(tab,tupel);
	}

	@Override
	public FirstInsertTable insertInto(String tableName, String... tupel) {
		tab = new MInsertInto(tableName);
		return new MySQLInsertTable(tab,tupel);
	}

	@Override
	public DeleteTable delete(String... tableName) {
		return new MySQLDeleteTable(tableName);
	}

	@Override
	public DeleteTable delete(List<String> tableName) {
		return new MySQLDeleteTable(tableName);
	}

	@Override
	public SelectUncomplete select() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public SelectTable select(String... cols) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public SelectTable select(List<String> cols) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
class MySQLDeleteTable implements DeleteTable{
	private final MDeleteTable tab;

	MySQLDeleteTable(String... tableName) {
		tab = new MDeleteTable();
		for (String s : tableName){
			tab.newCol(s);
		}
	}

	MySQLDeleteTable(List<String> tableName) {
		tab = new MDeleteTable();
		for (String s : tableName){
			tab.newCol(s);
		}
	}

	@Override
	public String getSQL() {
		return tab.toString();
	}
	
}

class MySQLFirstInsertTable implements FirstInsertTable{
	private final MInsertInto tab;
	
	MySQLFirstInsertTable(MInsertInto tab, List<String> tupel) {
		this.tab = tab;
		for (String c: tupel){
			tab.newCol(c);
		}
	}

	MySQLFirstInsertTable(MInsertInto tab, String[] tupel) {
		this.tab = tab;
		for (String c: tupel){
			tab.newCol(c);
		}
	}
	
	@Override
	public InsertTable value(List<String> values) {
		return new MySQLInsertTable(tab, values);
	}

	@Override
	public InsertTable value(String... values) {
		return new MySQLInsertTable(tab, values);
	}
}

class MySQLInsertTable implements InsertTable{
	private final MInsertInto tab;

	MySQLInsertTable(MInsertInto tab, List<String> tupel) {
		this.tab = tab;
		for (String c: tupel){
			tab.newCol(c);
		}
	}

	MySQLInsertTable(MInsertInto tab, String[] tupel) {
		this.tab = tab;
		for (String c: tupel){
			tab.newCol(c);
		}
	}

	@Override
	public InsertTable value(List<String> values) {
		MTupel m = tab.newTupel();
		for(String s:values){
			m.newValues(s);
		}
		return this;
	}

	@Override
	public String getSQL() {
		return tab.toString();
	}

	@Override
	public InsertTable value(String... values) {
		MTupel m = tab.newTupel();
		for(String s:values){
			m.newValues(s);
		}
		return this;
	}
}