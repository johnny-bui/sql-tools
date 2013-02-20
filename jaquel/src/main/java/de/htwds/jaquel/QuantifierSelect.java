package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface QuantifierSelect extends FromSelect{
	/**
	 * <code>SELECT ALL *</code>
	 */
	public FromSelect all();
	/**
	 * <code>SELECT ALL col1, cols2 as alias2, col3 ...</code>
	 */
	public FromSelect all(ColumnRefercence cols);
	
	/**
	 * <code>SELECT ALL `cols1`, `cols2`, `cosl3` ...</code>
	 */
	public FromSelect all(String... cols);
	
	/**
	 * <code>SELECT DISTINCT *</code>
	 */
	public FromSelect distinct();
	/**
	 * <code>SELECT DOSTINCT col1, cols2 as alias2, col3, ...</code>
	 */
	public FromSelect distinct(ColumnRefercence cols);
	
	/**
	 * <code>SELECT DISTINCT `cols1`, `cols2`, `cosl3` ...</code>
	 */
	public FromSelect distinct(String... cols);

}
