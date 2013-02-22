package de.htwds.jaquel.mysql;

import de.htwds.jaquel.ColumnRefercence;
import de.htwds.jaquel.ColumnRefercenceWithAs;
import de.htwds.jaquel.DMLComposer;
import de.htwds.jaquel.DeleteTable;
import de.htwds.jaquel.FirstInsertTable;
import de.htwds.jaquel.FromSelect;
import de.htwds.jaquel.GroupBy;
import de.htwds.jaquel.InsertTable;
import de.htwds.jaquel.QuantifierSelect;
import de.htwds.jaquel.SQLSubClause;
import de.htwds.jaquel.TableReference;
import de.htwds.jaquel.TableReferenceWithAs;
import de.htwds.jaquel.TableReferenceWithoutAs;
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
// =========================== S E L E C T ================================== //
////////////////////////////////////////////////////////////////////////////////

	@Override
	public QuantifierSelect select() {
		return new MySQLQuantifierSelect(new MSelect());
	}

	@Override
	public FromSelect select(String firstCol, String... cols) {
		return new MySQLFromSelect(new MSelect(), firstCol, cols);
	}

	@Override
	public FromSelect select(List<String> cols) {
		return new MySQLFromSelect(new MSelect(), cols);
	}

	@Override
	public FromSelect select(ColumnRefercenceWithAs cols) {
		return new MySQLFromSelect(new MSelect(), cols);
	}

	@Override
	public TableReference tab(String tab) {
		return new MySQLTableReference(tab);
	}

	@Override
	public ColumnRefercence col(String col) {
		return new MySQLColumnReference(col);
	}

	@Override
	public TableReferenceWithoutAs query(SQLSubClause subclause) {
		return new MySQLTableReference(subclause);
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
	private final MFromClause macro;
	private MTableReference lastRefTable;
	private MSubTable lastSubTab;
	
	MySQLTableReference(String tab) {
		macro = new MFromClause();
		lastRefTable = macro.newTableReferenceList().newTableReference(tab);
		lastSubTab = null;
	}

	MySQLTableReference(SQLSubClause subclause) {
		macro = new MFromClause();
		lastSubTab = macro.newTableReferenceList().newSubTable(subclause.getSQLClause());
		lastRefTable = null;
	}

	
	
	@Override
	public TableReference as(String alias) {
		if (lastRefTable!=null){
			lastRefTable.newAsClause(alias);
		}else if (lastSubTab !=null){
			lastSubTab.newAsClause(alias);
		}
		return this;
	}

	@Override
	public TableReference tab(String tableName) {
		lastRefTable = macro.newTableReferenceList().newTableReference(tableName);
		lastSubTab = null;
		return this;
	}

	@Override
	public TableReferenceWithoutAs query(SQLSubClause select) {
		lastSubTab = macro.newTableReferenceList().newSubTable(select.getSQLClause());
		lastRefTable = null;
		return this;
	}

	@Override
	public String getSQL() {
		return macro.toString();
	}
}

/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
class MySQLWhereClause implements WhereClause{
	private final MSelect macro;
	private final MFromClause f;
	MySQLWhereClause(MSelect m, MFromClause from, String firstTab, String... tabs) {
		macro = m;
		f = from;
		f.newTableReferenceList().newTableReference(firstTab);
		for (String s:tabs){
			f.newTableReferenceList().newTableReference(s);
		}
	}

	MySQLWhereClause(MSelect m, MFromClause from, TableReferenceWithAs tabs) {
		macro = m;
		f = from;
		f.newTableReferenceList().newTableCollection(tabs.getSQL());
	}
	

	@Override
	public GroupBy where(String... condition) {
		return new MySQLGroupBy(macro, condition);
			
	}

	@Override
	public String getSQL() {
		return macro.toString();
	}
	
}


class MySQLGroupBy implements GroupBy{
	private final MSelect m;

	MySQLGroupBy(MSelect macro, String... condition) {
		m = macro;
		m.newWhereClause();
	}

	@Override
	public String getSQL() {
		return m.toString();
	}

	@Override
	public String getSQLClause() {
		return m.toString();
	}
	
}





class MySQLFromSelect implements FromSelect{
	private final MSelect macro;

	MySQLFromSelect(MSelect m) {
		macro = m;
		macro.newAsterisk();
	}

	MySQLFromSelect(MSelect m, ColumnRefercenceWithAs cols) {
		macro = m;
		macro.newSelectList().newColumnCollection(cols.getSQLClause());
	}
// ++++++
	MySQLFromSelect(MSelect m,String firstCol, String... cols) {
		macro = m;
		MSelectList col = macro.newSelectList();
		col.newColumnReference(firstCol);
		for (String c:cols){
			col.newColumnReference(c);
		}
	}
	
	MySQLFromSelect(MSelect m, List<String> cols) {
		macro = m;
		MSelectList col = macro.newSelectList();
		for (String c:cols){
			col.newColumnReference(c);
		}
	}
	
	
	@Override
	public WhereClause from(String firstTab, String... tabs) {
		return new MySQLWhereClause(macro, macro.newFromClause(), firstTab, tabs);
	}

	@Override
	public WhereClause from(TableReferenceWithAs tabs) {
		return new MySQLWhereClause(macro, macro.newFromClause(), tabs);
	}
}







class MySQLQuantifierSelect implements QuantifierSelect{
	private final MSelect macro;
	
	MySQLQuantifierSelect(MSelect m){
		macro = m;
	}
	
	@Override
	public FromSelect all() {
		macro.newAll();
		return new MySQLFromSelect(macro);
	}

	@Override
	public FromSelect all(ColumnRefercence cols) {
		macro.newAll();
		return new MySQLFromSelect(macro, cols);
	}

	@Override
	public FromSelect distinct() {
		macro.newDistinct();
		return new MySQLFromSelect(macro);
	}

	@Override
	public FromSelect distinct(ColumnRefercence cols) {
		macro.newDistinct();
		return new MySQLFromSelect(macro, cols);
	}
	
	@Override
	public FromSelect all(String firstCols, String... cols) {
		macro.newAll();
		return new MySQLFromSelect(macro, firstCols, cols);
	}

	@Override
	public FromSelect distinct(String firstCol, String... cols) {
		macro.newDistinct();
		return new MySQLFromSelect(macro, firstCol, cols);
	}
	
	@Override
	public WhereClause from(String firstTab, String... tabs) {
		macro.newAsterisk();
		return new MySQLWhereClause(macro, macro.newFromClause(), firstTab, tabs);
	}

	/// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@Override
	public WhereClause from(TableReferenceWithAs tabs) {
		macro.newAsterisk();
		return new MySQLWhereClause(macro, macro.newFromClause(), tabs);
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