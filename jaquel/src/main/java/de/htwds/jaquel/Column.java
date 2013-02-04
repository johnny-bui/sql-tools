package de.htwds.jaquel;

/**
 *
 * @author hbui
 */
interface Column extends TableDefinition, CompleteSQL{
	TableConstraint constraint(String name);
	Column unique();
	Column notNull();
}
