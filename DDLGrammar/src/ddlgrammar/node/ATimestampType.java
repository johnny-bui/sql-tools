/* This file was generated by SableCC (http://www.sablecc.org/). */

package ddlgrammar.node;

import ddlgrammar.analysis.*;

@SuppressWarnings("nls")
public final class ATimestampType extends PType
{
    private TTimestamp _timestamp_;

    public ATimestampType()
    {
        // Constructor
    }

    public ATimestampType(
        @SuppressWarnings("hiding") TTimestamp _timestamp_)
    {
        // Constructor
        setTimestamp(_timestamp_);

    }

    @Override
    public Object clone()
    {
        return new ATimestampType(
            cloneNode(this._timestamp_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATimestampType(this);
    }

    public TTimestamp getTimestamp()
    {
        return this._timestamp_;
    }

    public void setTimestamp(TTimestamp node)
    {
        if(this._timestamp_ != null)
        {
            this._timestamp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._timestamp_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._timestamp_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._timestamp_ == child)
        {
            this._timestamp_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._timestamp_ == oldChild)
        {
            setTimestamp((TTimestamp) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
