package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface QuantifierSelect extends FromSelect{
	public FromSelect all();
	public FromSelect all(ColumnRefercence cols);
	
	public FromSelect distinct();
	public FromSelect distinct(ColumnRefercence cols);

}
