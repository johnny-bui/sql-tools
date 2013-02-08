package de.htwds.jaquel.mysql;

import de.htwds.jaquel.Column;
import de.htwds.jaquel.DDLComposer;
import de.htwds.jaquel.Foreign;
import de.htwds.jaquel.Primary;
import de.htwds.jaquel.References;
import de.htwds.jaquel.CreateTable;
import de.htwds.jaquel.DropTable;
import de.htwds.jaquel.TableConstraint;
import de.htwds.jaquel.TruncateTable;
import java.util.List;




/**
 *
 * @author phucluoi
 * @version Feb 4, 2013
 */
public class MySQLComposer implements DDLComposer{

	@Override
	public CreateTable createTable(String tableName) {
		return new MySQLCreateTable(tableName);
	}

	@Override
	public DropTable dropTable(String tableName) {
		return new MySQLDropTable(tableName);
	}

	@Override
	public TruncateTable truncateTable(String tableName) {
		return new MySQLTruncateTable(tableName);
	}
}


class MySQLCreateTable implements CreateTable{
	MCreateTable tab;
	MySQLCreateTable(String tableName) {
		tab = new MCreateTable(tableName);
	}
	
	@Override
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

	@Override
	public TableConstraint constraint(String name) {
		return new MySQLTableConstraint(tab, name);
	}

	@Override
	public Column unique() {
		lastCol.newUnique();
		return this;
	}

	@Override
	public Column notNull() {
		lastCol.newNotNull();
		return this;
	}

	@Override
	public Column autoIncrement(){
		lastCol.newAutoIncrement();
		return this;
	}

	@Override
	public Column column(String name, String type) {
		lastCol = tab.newCreateColumn(name, type);
		return this;
	}

	@Override
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

	@Override
	public Primary primaryKey(List<String> cols) {
		return new MySQLPrimary(tab,lastConstraint,cols);
	}

	@Override
	public Primary primaryKey(String col) {
		return new MySQLPrimary(tab, lastConstraint, col);
	}
	
	@Override
	public Primary primaryKey(String... cols) {
		return new MySQLPrimary(tab, lastConstraint, cols);
	}
	
	@Override
	public Foreign foreignKey(List<String> cols) {
		return new MySQLForeign(tab, lastConstraint, cols);
	}

	@Override
	public Foreign foreignKey(String col) {
		return new MySQLForeign(tab, lastConstraint, col);
	}

	@Override
	public Foreign foreignKey(String... cols) {
		return new MySQLForeign(tab, lastConstraint, cols);
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

	@Override
	public String getSQL() {
		return tab.toString();
	}

	@Override
	public TableConstraint constraint(String name) {
		return new MySQLTableConstraint(tab, name);
	}
}



class MySQLForeign implements Foreign{
	private final MCreateTable tab;
	private final MConstraints constraint;
	private final MForeignKey f;
	private final int noc;
	
	MySQLForeign(MCreateTable tab, MConstraints lastConstraint, String col) {
		this.tab = tab;
		this.constraint = lastConstraint;
		f = constraint.newForeignKey();
		f.newCol(col);
		noc = 1;
	}

	MySQLForeign(MCreateTable tab, MConstraints lastConstraint, List<String> cols) {
		this.tab = tab;
		this.constraint = lastConstraint;
		f = constraint.newForeignKey();
		for (String c : cols){
			f.newCol(c);
		}
		noc = cols.size();
	}
	
	MySQLForeign(MCreateTable tab, MConstraints lastConstraint, String... cols) {
		this.tab = tab;
		this.constraint = lastConstraint;
		f = constraint.newForeignKey();
		for (String c : cols){
			f.newCol(c);
		}
		noc = cols.length;
	}
	
	@Override
	public References references(String constraintTab, String col) {
		if (noc != 1){
			throw new RuntimeException("Number of referenced column must match number of referencing column: " + noc + ":" + 1);
		}
		return new MySQLRefenrencesClause(tab, f,constraintTab,col);
	}

	@Override
	public References references(String constraintTab, List<String> cols) {
		if (noc != cols.size()){
			throw new RuntimeException("Number of referenced column must match number of referencing column: " + noc + ":" + cols.size());
		}
		return new MySQLRefenrencesClause(tab, f, constraintTab, cols);
	}

	@Override
	public References references(String constraintTab, String... cols) {
		if (noc != cols.length){
			throw new RuntimeException("Number of referenced column must match number of referencing column: " + noc + ":" + cols.length);
		}
		return new MySQLRefenrencesClause(tab, f, constraintTab, cols);
	}
}


class MySQLRefenrencesClause implements References{
	private final MCreateTable t;

	MySQLRefenrencesClause(
			MCreateTable tab, 
			MForeignKey f, 
			String constraintTab, 
			String col) {
		this.t = tab;
		MReferencesClausel r = f.newReferencesClausel(constraintTab);
		r.newCol(col);
	}

	MySQLRefenrencesClause(
			MCreateTable tab, 
			MForeignKey f, 
			String constraintTab, 
			List<String> cols) {
		t = tab;
		MReferencesClausel r = f.newReferencesClausel(constraintTab);
		for (String c:cols){
			r.newCol(c);
		}
	}

	MySQLRefenrencesClause(
			MCreateTable tab, 
			MForeignKey f, 
			String constraintTab, 
			String... cols) {
		t = tab;
		MReferencesClausel r = f.newReferencesClausel(constraintTab);
		for (String c:cols){
			r.newCol(c);
		}
	}

	@Override
	public String getSQL() {
		return t.toString();
	}

	@Override
	public TableConstraint constraint(String name) {
		return new MySQLTableConstraint(t, name);
	}

}


class MySQLDropTable implements DropTable {
	private final MDropTable tab;
	public MySQLDropTable(String tableName) {
		tab = new MDropTable(tableName);
	}

	@Override
	public String getSQL() {
		return tab.toString();
	}
}


class MySQLTruncateTable implements TruncateTable{
	private final MTruncateTable tab;

	MySQLTruncateTable(String tableName) {
		tab = new MTruncateTable(tableName);
	}

	@Override
	public String getSQL() {
		return tab.toString();
	}
	
}