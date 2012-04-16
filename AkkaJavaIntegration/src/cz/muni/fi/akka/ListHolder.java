/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.akka;

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;

/**
 *
 * @author $xbogar
 */
@Stateless
public class ListHolder {
    private Collection<Integer> list = new ArrayList<>();
    
    public ListHolder(){
        list.add(1);
        list.add(2);
        list.add(3);
    }
    
    public Collection<Integer> getList() {
        return list;
    }

    public void setList(Collection<Integer> list) {
        this.list = list;
    }
    
}
