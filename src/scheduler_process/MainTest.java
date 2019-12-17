/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler_process;

/**
 *
 * @author Ahmed
 */
public class MainTest {
     public static void main(String args[]) {
         Process P1=new Process("p1",2,5);
         Process p2=new Process("p2",3,4);
         Process p3=new Process("p3",5,1);
         FCFS fr=new FCFS();
         fr.setNoProcess(3);
         fr.addProcess(P1);   fr.addProcess(p2);   fr.addProcess(p3);
         fr.print();
     }
    
}
