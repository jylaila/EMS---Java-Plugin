package compiler.siel.auxComp;

import java.util.*;

public class SymbolTable {
    
    public SymbolTable() {
        table = new Hashtable<String, Object>();
    }
    
    public Object putInGlobal( String key, Object value ) {
       return table.put(key, value);
    }

    public Object getInGlobal( Object key ) {
       return table.get(key);
    }
    

    public void removeAll() {
           // remove all local identifiers from the table
         table.clear();
    }
    
    public Enumeration keys()   {
    	
    	return table.keys();
    }
    
      
        
    private Hashtable<String, Object> table;
}
            