package de.htwds.jaquel.mysql;

import de.htwds.jaquel.DMLComposer;
import de.htwds.jaquel.InsertTable;
import de.htwds.jaquel.mysql.MInsertInto;
import de.htwds.jaquel.mysql.MTupel;
import java.util.List;



/**
 *
 * @author hbui
 */
public class MySQLDMLComposer implements DMLComposer{
	private MInsertInto tab;

	@Override
	public InsertTable insertInto(String tableName, List<String> tupel) {
		tab = new MInsertInto(tableName);
		return new MySQLInsertTable(tab,tupel);
	}

	@Override
	public InsertTable insertInto(String tableName, String... tupel) {
		tab = new MInsertInto(tableName);
		return new MySQLInsertTable(tab,tupel);
	}
	
}
class MySQLInsertTable implements InsertTable{
	private final MInsertInto tab;

	MySQLInsertTable(MInsertInto tab, List<String> tupel) {
		this.tab = tab;
		for (String c: tupel){
			tab.newCol(c);
		}
	}

	MySQLInsertTable(MInsertInto tab, String[] tupel) {
		this.tab = tab;
		for (String c: tupel){
			tab.newCol(c);
		}
	}

	@Override
	public InsertTable value(List<String> values) {
		MTupel m = tab.newTupel();
		for(String s:values){
			m.newValues(s);
		}
		return this;
	}

	@Override
	public String getSQL() {
		return tab.toString();
	}

	@Override
	public InsertTable value(String... values) {
		MTupel m = tab.newTupel();
		for(String s:values){
			m.newValues(s);
		}
		return this;
	}
}