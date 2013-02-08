package de.htwds.jaquel;

import java.util.List;

/**
 *
 * @author hbui
 */
public interface DMLComposer {
	public InsertTable insertInto(String tableName, List<String> tupel);
	public InsertTable insertInto(String tableName, String... tupel);
}
