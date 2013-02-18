
package de.htwds.jaquel;

/**
 *
 * @author phucluoi
 * @version Feb 18, 2013
 */
public interface TableReference extends CompleteSQL{
	TableReference as(String alias);
	TableReference tab(String tableName);

	public TableReference query(SelectClausel select);
}
