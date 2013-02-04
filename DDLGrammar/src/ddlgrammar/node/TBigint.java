/* This file was generated by SableCC (http://www.sablecc.org/). */

package ddlgrammar.node;

import ddlgrammar.analysis.*;

@SuppressWarnings("nls")
public final class TBigint extends Token
{
    public TBigint()
    {
        super.setText("bigint");
    }

    public TBigint(int line, int pos)
    {
        super.setText("bigint");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TBigint(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTBigint(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TBigint text.");
    }
}
