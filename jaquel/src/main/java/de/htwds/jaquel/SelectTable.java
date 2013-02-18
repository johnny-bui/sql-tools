package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface SelectTable extends SelectUncomplete, CompleteSQL{
	public SelectClausel where(String something);
}
