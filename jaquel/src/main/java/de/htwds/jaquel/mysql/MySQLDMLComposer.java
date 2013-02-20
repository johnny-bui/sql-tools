package de.htwds.jaquel.mysql;

import de.htwds.jaquel.ColumnRefercence;
import de.htwds.jaquel.ColumnRefercenceWithAs;
import de.htwds.jaquel.CompleteSQL;
import de.htwds.jaquel.DMLComposer;
import de.htwds.jaquel.DeleteTable;
import de.htwds.jaquel.FirstInsertTable;
import de.htwds.jaquel.FromSelect;
import de.htwds.jaquel.GroupBy;
import de.htwds.jaquel.InsertTable;
import de.htwds.jaquel.QuantifierSelect;
import de.htwds.jaquel.TableReference;
import de.htwds.jaquel.TableReferenceWithAs;
import de.htwds.jaquel.WhereClause;
import java.util.List;






/**
 *
 * @author hbui
 */
public class MySQLDMLComposer implements DMLComposer{

	@Override
	public FirstInsertTable insertInto(String tableName, List<String> tupel) {
		MInsertInto tab = new MInsertInto(tableName);
		return new MySQLInsertTable(tab,tupel);
	}

	@Override
	public FirstInsertTable insertInto(String tableName, String... tupel) {
		MInsertInto tab = new MInsertInto(tableName);
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

////////////////////////////////////////////////////////////////////////////////
//============================ S E L E C T =====================================
////////////////////////////////////////////////////////////////////////////////

	@Override
	public QuantifierSelect select() {
		return new MySQLQuantifierSelect();
	}

	@Override
	public FromSelect select(String... cols) {
		MSelect macro = new MSelect();
		MSelectList c = macro.newSelectList();
		for (String s : cols){
			c.newColumnReference(s);
		}
		return new MySQLFromSelect(macro);
	}

	@Override
	public FromSelect select(List<String> cols) {
		MSelect macro = new MSelect();
		MSelectList c = macro.newSelectList();
		for (String s : cols){
			c.newColumnReference(s);
		}
		return new MySQLFromSelect(macro);
	}

	@Override
	public FromSelect select(ColumnRefercence cols) {
		MSelect macro = new MSelect();
		macro.newSelectList().newColumnCollection(cols.getSQLClause());
		return new MySQLFromSelect(macro);
	}

	@Override
	public TableReference tab(String tab) {
		return new MySQLTableReference(tab);
	}

	@Override
	public ColumnRefercence col(String col) {
		return new MySQLColumnReference(col);
	}

}










class MySQLColumnReference implements ColumnRefercence{
	private final MColumnReferenceList m;
	private MColumnReference lastCol;

	MySQLColumnReference(String col) {
		m = new MColumnReferenceList();
		lastCol = m.newColumnReference(col);
	}

	@Override
	public ColumnRefercenceWithAs as(String alias) {
		lastCol.newAsClause(alias);
		return this;
	}

	@Override
	public ColumnRefercence col(String colName) {
		lastCol = m.newColumnReference(colName);
		return this;
	}

	@Override
	public String getSQLClause() {
		return m.toString();
	}
	
}



class MySQLTableReference implements TableReference{
	private final MTableReferenceList macro;
	private MTableReference lastTable;

	MySQLTableReference(String tab) {
		macro = new MTableReferenceList();
		lastTable = macro.newTableReference(tab);
	}

	@Override
	public TableReference as(String alias) {
		// ERROR, this method can be called more than once on a table
		lastTable.newAsClause(alias);
		return this;
	}

	@Override
	public TableReference tab(String tableName) {
		lastTable = macro.newTableReference(tableName);
		return this;
	}

	@Override
	public TableReference query(CompleteSQL select) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String getSQL() {
		return macro.toString();
	}
}


class MySQLWhereClause implements WhereClause{
	private final MSelect macro;

	MySQLWhereClause(MSelect m) {
		macro = m;
	}
	

	@Override
	public GroupBy where(String... condition) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String getSQL() {
		return macro.toString();
	}
	
}

class MySQLFromSelect implements FromSelect{
	private final MSelect m;

	MySQLFromSelect(MSelect macro) {
		m = macro;
	}
	
	
	@Override
	public WhereClause from(String... tabs) {
		MFromClause from = m.newFromClause();
		MTableReferenceList tabList = from.newTableReferenceList();
		for (String s:tabs){
			tabList.newTableReference(s);
		}
		return new MySQLWhereClause(m);
	}

	@Override
	public WhereClause from(TableReferenceWithAs tabs) {
		MFromClause from = m.newFromClause();
		from.newTableReferenceList().newTableCollection(tabs.getSQL());
		return new MySQLWhereClause(m);
	}

	@Override
	public String getSQL() {
		return m.toString();
	}
	
}




class MySQLQuantifierSelect implements QuantifierSelect{
	private final MSelect macro;
	
	MySQLQuantifierSelect(){
		macro = new MSelect();
	}
	
	@Override
	public FromSelect all() {
		macro.newAll();
		macro.newAsterisk();// QUESTION? where is better to call this method
		// here or in the Constructor MySQLFromSelect(MSelect)
		MySQLFromSelect f = new MySQLFromSelect(macro);
		return f;
	}

	@Override
	public FromSelect all(ColumnRefercence cols) {
		macro.newAll();
		macro.newSelectList().newColumnReference(cols.getSQLClause());
		MySQLFromSelect f = new MySQLFromSelect(macro);
		return f;
	}

	@Override
	public FromSelect distinct() {
		macro.newDistinct();
		macro.newAsterisk();
		MySQLFromSelect f = new MySQLFromSelect(macro);
		return f;
	}

	@Override
	public FromSelect distinct(ColumnRefercence cols) {
		macro.newDistinct();
		macro.newSelectList().newColumnCollection(cols.getSQLClause());
		MySQLFromSelect f = new MySQLFromSelect(macro);
		return f;
	}
	
	@Override
	public FromSelect all(String... cols) {
		macro.newAll();
		MSelectList col = macro.newSelectList();
		for (String c:cols){
			col.newColumnReference(c);
		}
		return new MySQLFromSelect(macro);
	}

	@Override
	public FromSelect distinct(String... cols) {
		macro.newDistinct();
		MSelectList col = macro.newSelectList();
		for (String c:cols){
			col.newColumnReference(c);
		}
		return new MySQLFromSelect(macro);
	}
	
	@Override
	public WhereClause from(String... tabs) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public WhereClause from(TableReferenceWithAs tabs) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String getSQL() {
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