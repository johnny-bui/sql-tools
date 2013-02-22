package de.htwds.jaquel;

import java.util.List;

/**
 *
 * @author hbui
 */
public interface DMLComposer {
	public FirstInsertTable insertInto(String tableName, List<String> tupel);
	public FirstInsertTable insertInto(String tableName, String... tupel);
	public DeleteTable delete(List<String> tableName);
	public DeleteTable delete(String... tableName);
	
	public QuantifierSelect select();
	public FromSelect select(String firstCol, String... cols);
	public FromSelect select(List<String> cols);
	public FromSelect select(ColumnRefercenceWithAs cols);
	// methods used in select statement
	public TableReference tab(String xxx);
	public TableReferenceWithoutAs query(SQLSubClause subclause);
	
	public ColumnRefercence col(String aaaa);
}
