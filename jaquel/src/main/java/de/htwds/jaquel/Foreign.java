package de.htwds.jaquel;

import java.util.List;

/**
 *
 * @author hbui
 */
public interface Foreign {
	public References references(String constraintTab, String col);
	public References references(String constraintTab, List<String> cols);
}
