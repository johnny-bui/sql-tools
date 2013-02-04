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
interface TableConstraint {
	Primary primaryKey(List<String> cols);
	Foreign foreignKey(List<String> cols);
}
