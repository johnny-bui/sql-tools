package de.htwds.jaquel.mysql;

import de.htwds.jaquel.Column;
import de.htwds.jaquel.Composer;
import de.htwds.jaquel.Foreign;
import de.htwds.jaquel.Primary;
import de.htwds.jaquel.Table;
import de.htwds.jaquel.TableConstraint;
import java.util.List;

/**
 *
 * @author phucluoi
 * @version Feb 4, 2013
 */
public class MySQLComposer implements Composer{

	public Table createTable(String tableName) {
		return new MySQLTable(tableName);
	}
}

class MySQLTable implements Table{
	MCreateTable tab;
	MySQLTable(String tableName) {
		tab = new MCreateTable(tableName);
	}
	
	public Column column(String name, String type) {
		return new MySQLColumn(tab,name,type);
	}
}

class MySQLColumn implements Column{
	private final MCreateTable tab;
	private MCreateColumn lastCol;

	MySQLColumn(MCreateTable tab, String name, String type) {
		this.tab = tab;
		lastCol = tab.newCreateColumn(name, type);
	}

	public TableConstraint constraint(String name) {
		return new MySQLTableConstraint(tab, name);
	}

	public Column unique() {
		lastCol.newUnique();
		return this;
	}

	public Column notNull() {
		lastCol.newNotNull();
		return this;
	}

	public Column autoIncrement(){
		lastCol.newAutoIncrement();
		return this;
	}

	public Column column(String name, String type) {
		lastCol = tab.newCreateColumn(name, type);
		return this;
	}

	public String getSQL() {
		return tab.toString();
	}
}



class MySQLTableConstraint implements TableConstraint{
	private final MCreateTable tab;
	private final MConstraints lastConstraint;

	MySQLTableConstraint(MCreateTable tab, String name) {
		this.tab = tab;
		lastConstraint = tab.newConstraints(name);
	}

	public Primary primaryKey(List<String> cols) {
		return new MySQLPrimary(tab,lastConstraint,cols);
	}

	public Primary primaryKey(String col) {
		return new MySQLPrimary(tab, lastConstraint, col);
	}
	
	public Primary primaryKey(String... cols) {
		return new MySQLPrimary(tab, lastConstraint, cols);
	}
	
	public Foreign foreignKey(List<String> cols) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Foreign foreignKey(String cols) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	

	public Foreign foreignKey(String... cols) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

class MySQLPrimary implements Primary{
	private final MCreateTable tab;

	MySQLPrimary(MCreateTable tab, MConstraints lastConstraint, List<String> cols) {
		this.tab = tab;
		MPrimaryKey p = lastConstraint.newPrimaryKey();
		for(String s:cols){
			p.newCol(s);
		}
	}
	
	MySQLPrimary(MCreateTable tab, MConstraints lastConstraint, String... cols) {
		this.tab = tab;
		MPrimaryKey p = lastConstraint.newPrimaryKey();
		for(String s:cols){
			p.newCol(s);
		}
	}

	MySQLPrimary(MCreateTable tab, MConstraints lastConstraint, String col) {
		this.tab = tab;
		MPrimaryKey p = lastConstraint.newPrimaryKey();
		p.newCol(col);
	}

	public String getSQL() {
		return tab.toString();
	}

	public TableConstraint constraint(String name) {
		return new MySQLTableConstraint(tab, name);
	}
	
}
