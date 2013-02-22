package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface FromSelect {
	public WhereClause from(String firstTab, String... tabs);// TODO
	public WhereClause from(TableReferenceWithAs tabs);// TODO
}
