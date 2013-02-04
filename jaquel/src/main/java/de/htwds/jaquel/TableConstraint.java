/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwds.jaquel;

import java.util.List;

/**
 *
 * @author hbui
 */
public interface TableConstraint {
	Primary primaryKey(List<String> cols);
	Primary primaryKey(String... cols);// liberal
	Primary primaryKey(String col);

	Foreign foreignKey(List<String> cols);
	Foreign foreignKey(String... cols);// liberal
	Foreign foreignKey(String cols);
}
