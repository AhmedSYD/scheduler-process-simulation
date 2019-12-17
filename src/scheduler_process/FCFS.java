
package scheduler_process;
import java.util.*;


public class FCFS {
   private Vector<Process> ArrProcess=new Vector<>(100,2);
   private Vector<Process> sortedProcess=new Vector<>(100,2);
    private Vector<Float> TimeLine=new Vector<>(100,2);
   private List<Process> ArrList=new ArrayList<Process>();
    private List<Process> SortedList=new ArrayList<Process>();
   private int count;
   private int noProcess;

    public FCFS() {
      count=0;

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
    public Vector<Float> getTimeLine(){
         float start;
         start=sortedProcess.get(0).getArrival();
          TimeLine.addElement(start);
          for(int i=0;i<sortedProcess.size();i++){
              start+=sortedProcess.get(i).getBurst();
              TimeLine.addElement(start);
          }
         return TimeLine;
     }
    public Vector<Process> getSequenceProcess(){
        return sortedProcess;
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
        float finish=0;
        float TAT;
        float waiting=0;
        float avgWaiting;
        for(int i=0;i<sortedProcess.size();i++)
        {
            if(i==0) finish=sortedProcess.get(i).getBurst()+sortedProcess.get(i).getArrival();
            else finish+=sortedProcess.get(i).getBurst();
            TAT=finish-sortedProcess.get(i).getArrival();
            waiting+=TAT-sortedProcess.get(i).getBurst();    
        }
        avgWaiting=(float)waiting/sortedProcess.size();
        return avgWaiting;
    }
 
    
    
   
}
