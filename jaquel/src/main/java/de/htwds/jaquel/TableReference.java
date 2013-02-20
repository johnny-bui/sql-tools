
package de.htwds.jaquel;

/**
 *
 * @author phucluoi
 * @version Feb 18, 2013
 */
public interface TableReference extends TableReferenceWithAs{
	TableReferenceWithAs as(String alias);
	// TODO: replace CompleteSQL with FromTable
	
}
