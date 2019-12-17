
package scheduler_process;
import java.util.*;

public class SJFPre {//not complete
   private Vector<Process> ArrProcess=new Vector<>(100,2);
   private Vector<Process> dubArrProcess=new Vector<>(100,2);
   private Vector<Process> sortedProcess=new Vector<>(100,2);
    private List<Process> ArrList=new ArrayList<Process>();
    private List<Process> SortedList=new ArrayList<Process>();
   private Vector<Float> TimeLine=new Vector<>(100,2);
   private int count;
    private int noProcess;

    public SJFPre() {
        count=0;
    }

   public void addProcess(Process p1)
    {
       
          count++;
        if(count<=noProcess){
           ArrProcess.addElement(p1);
           dubArrProcess.addElement(p1);
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
    {// need to modify as should use arrival time in it
         boolean flag = true;
        float start=0;
         start=arrP.get(0).getArrival();
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

             int j=0; float start;  float prevStart;    
          float bur;   int minIndex;  float highestArrival;
          highestArrival=arrP.get(arrP.size()-1).getArrival();
     start=arrP.get(0).getArrival();
        TimeLine.addElement(start);

        while(!arrP.isEmpty()){
         
             prevStart=start;
             minIndex=0;
             int k;
            for(k=0;k<arrP.size();k++) //job of the for loop is to find the minimum burst of process at certain arrival arrival
            {
                if(arrP.get(k).getArrival()<=start  && k!=0){
                    if(arrP.get(k).getTempBurst()<arrP.get(minIndex).getTempBurst()){ minIndex=k; }
                }
                else if(arrP.get(k).getArrival()>start){    break;}
            }

  

            if(start>=highestArrival)//as mean that all process come
            {
                    start+=arrP.get(minIndex).getTempBurst();         
            }
            else{
                start=arrP.get(k).getArrival();// arrival of second process
            }
            if(newArr.size()>0){
               int n=newArr.size()-1;
                if(!(newArr.get(n).getName().equals(arrP.get(minIndex).getName())) ){
                  newArr.addElement(arrP.get(minIndex));
                  TimeLine.addElement(prevStart);
               }
            }
            else{ newArr.addElement(arrP.get(minIndex));}

          
           bur=start-prevStart;
           bur=arrP.get(minIndex).getTempBurst()-bur;
           arrP.get(minIndex).setTempBurst(bur);
           if(bur<=0){arrP.get(minIndex).setEndTime(start);   arrP.remove(minIndex);}

        }
        TimeLine.addElement(start);
        return newArr;
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
        public Vector<Float> getTimeLine(){
          return TimeLine;
      }
        public float avgWaitingTime()
       {
        float TAT;
        float waiting=0;
        float avgWaiting;
        for(int i=0;i<dubArrProcess.size();i++)
        {
            TAT=dubArrProcess.get(i).getEndTime()-dubArrProcess.get(i).getArrival();
            waiting+=TAT-dubArrProcess.get(i).getBurst();    
        }
        avgWaiting=(float)waiting/count;
        return avgWaiting;
       }
}
 
