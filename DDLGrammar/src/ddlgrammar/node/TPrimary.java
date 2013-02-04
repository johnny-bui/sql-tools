/* This file was generated by SableCC (http://www.sablecc.org/). */

package ddlgrammar.node;

import ddlgrammar.analysis.*;

@SuppressWarnings("nls")
public final class TPrimary extends Token
{
    public TPrimary()
    {
        super.setText("primary");
    }

    public TPrimary(int line, int pos)
    {
        super.setText("primary");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TPrimary(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTPrimary(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TPrimary text.");
    }
}
