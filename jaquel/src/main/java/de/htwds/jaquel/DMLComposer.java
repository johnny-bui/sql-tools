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
	
	public SelectUncomplete select();
	public SelectTable select(String... cols);
	public SelectTable select(List<String> cols);
	// methods used in select statement
	public TableReference tab(String xxx);
}
