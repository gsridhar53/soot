package soot.jimple.paddle.queue;

import soot.util.*;
import soot.jimple.paddle.bdddomains.*;
import soot.jimple.paddle.*;
import soot.jimple.toolkits.callgraph.*;
import soot.*;
import soot.util.queue.*;
import jedd.*;
import java.util.*;

public final class Qobj_varBDD extends Qobj_var {
    public Qobj_varBDD(String name) { super(name); }
    
    private LinkedList readers = new LinkedList();
    
    public void add(AllocNode _obj, VarNode _var) {
        add(new jedd.internal.RelationContainer(new Attribute[] { obj.v(), var.v() },
                                                new PhysicalDomain[] { H1.v(), V1.v() },
                                                ("add(jedd.internal.Jedd.v().literal(new java.lang.Object[...]" +
                                                 ", new jedd.Attribute[...], new jedd.PhysicalDomain[...])) at" +
                                                 " /home/olhotak/soot-trunk/src/soot/jimple/paddle/queue/Qobj_" +
                                                 "varBDD.jedd:34,8-11"),
                                                jedd.internal.Jedd.v().literal(new Object[] { _obj, _var },
                                                                               new Attribute[] { obj.v(), var.v() },
                                                                               new PhysicalDomain[] { H1.v(), V1.v() })));
    }
    
    public void add(final jedd.internal.RelationContainer in) {
        for (Iterator it = readers.iterator(); it.hasNext(); ) {
            Robj_varBDD reader = (Robj_varBDD) it.next();
            reader.add(new jedd.internal.RelationContainer(new Attribute[] { var.v(), obj.v() },
                                                           new PhysicalDomain[] { V1.v(), H1.v() },
                                                           ("reader.add(in) at /home/olhotak/soot-trunk/src/soot/jimple/p" +
                                                            "addle/queue/Qobj_varBDD.jedd:39,12-18"),
                                                           in));
        }
    }
    
    public Robj_var reader(String rname) {
        Robj_var ret = new Robj_varBDD(name + ":" + rname);
        readers.add(ret);
        return ret;
    }
}