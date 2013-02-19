package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface ColumnRefercence extends SQLSubClause{
	public ColumnRefercence as(String alias);
	public ColumnRefercence col(String colName);
}
