package com.github.verylazyboy.bnf;

import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author hbui
 */
public class HtmlFilterTest extends TestCase{
	@Test
	public void testFilter(){
		HtmlFilter f = new HtmlFilter();
		f.filter();
	}
}
