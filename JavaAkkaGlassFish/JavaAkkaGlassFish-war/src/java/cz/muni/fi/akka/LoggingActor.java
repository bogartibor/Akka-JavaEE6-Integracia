/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.akka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author xbogar
 */

@Actor @Log
public class LoggingActor extends UntypedActor{
    
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private List<Integer> numberList = new ArrayList();

    public Collection<Integer> getNumberList() {
        return numberList;
    }
 
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof List) {
            Collection<Integer> summedListOfNumbers = (Collection<Integer>) message;
            log.info("Received List message: {}", summedListOfNumbers.toString());
            //System.out.println(summedListOfNumbers.toString());
            getContext().system().shutdown();
        } else {
            unhandled(message);
        }
    }
}