package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface Composer {
	public CreateTable createTable(String tableName);	
	public DropTable dropTable(String tableName);
}
