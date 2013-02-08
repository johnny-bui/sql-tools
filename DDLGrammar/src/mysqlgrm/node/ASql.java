/* This file was generated by SableCC (http://www.sablecc.org/). */

package mysqlgrm.node;

import java.util.*;
import mysqlgrm.analysis.*;

@SuppressWarnings("nls")
public final class ASql extends PSql
{
    private final LinkedList<PSttm> _sttm_ = new LinkedList<PSttm>();

    public ASql()
    {
        // Constructor
    }

    public ASql(
        @SuppressWarnings("hiding") List<?> _sttm_)
    {
        // Constructor
        setSttm(_sttm_);

    }

    @Override
    public Object clone()
    {
        return new ASql(
            cloneList(this._sttm_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASql(this);
    }

    public LinkedList<PSttm> getSttm()
    {
        return this._sttm_;
    }

    public void setSttm(List<?> list)
    {
        for(PSttm e : this._sttm_)
        {
            e.parent(null);
        }
        this._sttm_.clear();

        for(Object obj_e : list)
        {
            PSttm e = (PSttm) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._sttm_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._sttm_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._sttm_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        for(ListIterator<PSttm> i = this._sttm_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PSttm) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}