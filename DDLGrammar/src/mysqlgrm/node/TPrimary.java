/* This file was generated by SableCC (http://www.sablecc.org/). */

package mysqlgrm.node;

import mysqlgrm.analysis.*;

@SuppressWarnings("nls")
public final class TPrimary extends Token
{
    public TPrimary(String text)
    {
        setText(text);
    }

    public TPrimary(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TPrimary(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTPrimary(this);
    }
}