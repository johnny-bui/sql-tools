package de.htwds.jaquel;

import java.util.List;

/**
 *
 * @author hbui
 */
public interface FirstInsertTable {
	public InsertTable value(List<String> values);	
	public InsertTable value(String... values);
}
