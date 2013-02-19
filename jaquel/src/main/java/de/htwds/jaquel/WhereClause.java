package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface WhereClause extends CompleteSQL{
	public GroupBy where(String... condition);
}
