package de.htwds.jaquel;

import java.util.List;

/**
 *
 * @author hbui
 */
public interface SelectUncomplete {
	public SelectUncomplete all();
	public SelectUncomplete distinct();
	public SelectTable from(String... tabs);
	public SelectTable from(List<String> tabs);
	public SelectTable from(TableReference ref);
}
