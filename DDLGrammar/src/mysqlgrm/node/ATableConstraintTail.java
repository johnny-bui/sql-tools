/* This file was generated by SableCC (http://www.sablecc.org/). */

package mysqlgrm.node;

import mysqlgrm.analysis.*;

@SuppressWarnings("nls")
public final class ATableConstraintTail extends PTableConstraintTail
{
    private TComma _comma_;
    private PTableConstraint _tableConstraint_;

    public ATableConstraintTail()
    {
        // Constructor
    }

    public ATableConstraintTail(
        @SuppressWarnings("hiding") TComma _comma_,
        @SuppressWarnings("hiding") PTableConstraint _tableConstraint_)
    {
        // Constructor
        setComma(_comma_);

        setTableConstraint(_tableConstraint_);

    }

    @Override
    public Object clone()
    {
        return new ATableConstraintTail(
            cloneNode(this._comma_),
            cloneNode(this._tableConstraint_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATableConstraintTail(this);
    }

    public TComma getComma()
    {
        return this._comma_;
    }

    public void setComma(TComma node)
    {
        if(this._comma_ != null)
        {
            this._comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comma_ = node;
    }

    public PTableConstraint getTableConstraint()
    {
        return this._tableConstraint_;
    }

    public void setTableConstraint(PTableConstraint node)
    {
        if(this._tableConstraint_ != null)
        {
            this._tableConstraint_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._tableConstraint_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._comma_)
            + toString(this._tableConstraint_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._comma_ == child)
        {
            this._comma_ = null;
            return;
        }

        if(this._tableConstraint_ == child)
        {
            this._tableConstraint_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(this._tableConstraint_ == oldChild)
        {
            setTableConstraint((PTableConstraint) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}