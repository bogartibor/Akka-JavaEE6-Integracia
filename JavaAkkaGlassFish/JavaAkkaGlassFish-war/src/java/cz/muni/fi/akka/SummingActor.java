/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.akka;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import java.util.ArrayList;
import java.util.Collection;
/**
 *
 * @author $xbogar
 */

public class SummingActor extends UntypedActor{
 
    private final ActorRef logger;
    
    public SummingActor( ActorRef logger) {
        this.logger = logger;
    }
    
    public Collection<Integer> sum(Collection<Integer> values, int number){
        Collection<Integer> result = new ArrayList();
        for(Integer i : values){
            result.add(i+number);
        }
        return result;
    }
    
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Work) {
            
            Work work = (Work) message;
            //SumSingleton sum = SumSingleton.getInstance();
            Collection<Integer> result = sum(work.getListHolder().getList(), work.getNumber());
            /*for(int i: work.getListHolder().getList()){
                result.add(sum.compute(i, work.getNumber()));
            }*/
            work.getListHolder().setList(result);
            getSender().tell(result, getSelf());
            logger.tell(result, getSelf());
        } else {
            unhandled(message);
        }
    }
    
    static class Work {

        private final ListHolder list;
        private final int number;

        public Work(ListHolder list, int numberToBeSumed) {
            this.list = list;
            this.number = numberToBeSumed;
        }

        public ListHolder getListHolder() {
            return list;
        }

        public int getNumber() {
            return number;
        }
        
    }

}
