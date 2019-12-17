
package scheduler_process;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.PriorityQueue;

/**
 *
 * @author Ahmed
 */
public class RoundRobin {
     private Vector<Process> ArrProcess=new Vector<>(100,2);
   private Vector<Process> sortedProcess=new Vector<>(100,2);
      private Vector<Float> TimeLine=new Vector<>(100,2);
         private List<Process> ArrList=new ArrayList<Process>();
    private List<Process> SortedList=new ArrayList<Process>();
   private int count;
   private int QuantumTime;
   private int noProcess;
       public RoundRobin() {
      count=0;
      QuantumTime=1;
    } 

    public int getQuantumTime() {
        return QuantumTime;
    }

    public void setQuantumTime(int QuantumTime) {
        this.QuantumTime = QuantumTime;
    }
    public void addProcess(Process p1)
    {

          count++;
        if(count<=noProcess){
           ArrProcess.addElement(p1);
           ArrList.add(p1);
        } 
    }
  public int getNoProcess() {
        return noProcess;
    }

    public void setNoProcess(int noProcess) {
        this.noProcess = noProcess;
    }
        public Vector<Process> sortingArrival(Vector<Process> arrP)//by bubble sort based on arrival time
    {
        
         boolean flag = true;
          Process temp; 
          while(flag)
          {
              flag = false;
              for(int i=0;  i<arrP.size()-1;  i++)
              {
                  if(arrP.get(i).getArrival()>arrP.get(i+1).getArrival())
                  {
                      temp=arrP.get(i);
                      arrP.set(i,arrP.get(i+1));
                      arrP.set(i+1,temp);
                      flag = true;
                  }
              }
                  
              
          }
          return arrP;

    }
  
  public Vector<Process> sorting(Vector<Process> arrP){
     Vector<Process>newArr=new Vector<>(100,2);
     
     int n=arrP.size(); int j=0; float start;
     float bur;
     start=arrP.get(0).getArrival();
     TimeLine.addElement(start);
    while(n>0){//continue loop until all process its burst time is 0 
       
        if(arrP.get(j).getTempBurst()>0 ){ 
            newArr.addElement(arrP.get(j));
              if(arrP.get(j).getTempBurst()>=QuantumTime){ // if time of burst bigeer than quantum 
                  bur=arrP.get(j).getTempBurst()-QuantumTime;
                  start+=QuantumTime;
                  if(bur==0) arrP.get(j).setEndTime(start);
                }
              else{// if time of burst lower than quantum should time of of execution=time of burst for process
                  bur=0;//as it must be equal 0 as it terminat in this step
                    start+=arrP.get(j).getTempBurst();
                    arrP.get(j).setEndTime(start);
                   }
              TimeLine.addElement(start);
               arrP.get(j).setTempBurst(bur);
          
        }
        else{
            n--; // that if one process its burst become 0 should decrease the value of n
        }
              if(arrP.get((j+1)%count).getArrival()<=start)
               {
                   j=(j+1)%count;
               }
    }
    return newArr;      
  }
      public Vector<Process> getSequenceProcess(){
        return sortedProcess;
    }
      public Vector<Float> getTimeLine(){
          return TimeLine;
      }
    public void print(){
        sortedProcess=sortingArrival(ArrProcess);
       
        TimeLine=getTimeLine();
        System.out.println(TimeLine.get(0));
        for(int i=0;i<sortedProcess.size();i++)
        {
            SortedList.add(sortedProcess.get(i));//add in list
        }
        for(int i=0;i<SortedList.size();i++)
        {
            System.out.println(SortedList.get(i).getName()+"   "+TimeLine.get(i+1));
        } 
    }
        public float avgWaitingTime()
       {

        float TAT;
        float waiting=0;
        float avgWaiting;
        for(int i=0;i<ArrProcess.size();i++)
        {
            
            TAT=ArrProcess.get(i).getEndTime()-ArrProcess.get(i).getArrival();
            waiting+=TAT-ArrProcess.get(i).getBurst();    
        }
        avgWaiting=(float)waiting/count;
        return avgWaiting;
       }
       
    
}
