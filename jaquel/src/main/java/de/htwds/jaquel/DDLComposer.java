package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface DDLComposer {
	// DDL
	public CreateTable createTable(String tableName);	
	public DropTable dropTable(String tableName);
	public TruncateTable truncateTable(String tableName);
}
