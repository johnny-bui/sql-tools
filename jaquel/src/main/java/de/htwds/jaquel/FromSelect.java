package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface FromSelect extends CompleteSQL{
	public WhereClause from(String... tabs);// TODO
	public WhereClause from(TableReference tabs);// TODO
}
