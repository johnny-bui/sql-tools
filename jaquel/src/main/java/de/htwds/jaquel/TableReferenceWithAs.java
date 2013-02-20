package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface TableReferenceWithAs extends CompleteSQL{
	TableReference tab(String tableName);
	TableReference query(CompleteSQL select);
}
