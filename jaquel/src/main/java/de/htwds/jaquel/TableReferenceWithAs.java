package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface TableReferenceWithAs extends CompleteSQL{
	TableReference tab(String tableName);
	TableReferenceWithoutAs query(SQLSubClause select);
}
