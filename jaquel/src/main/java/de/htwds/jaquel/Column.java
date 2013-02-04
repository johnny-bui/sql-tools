package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
public interface Column extends Table, CompleteSQL{
	TableConstraint constraint(String name);
	Column unique();
	Column notNull();
	Column autoIncrement();
}
