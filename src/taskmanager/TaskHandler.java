/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taskmanager;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import static taskmanager.MainUI.textArea_logs;
import static taskmanager.MainUI.textArea_highPriorityTasks;
import static taskmanager.MainUI.textArea_lowPriorityTasks;

public class TaskHandler {
    private static Queue<String> highPriorityTasks = new LinkedList<>();
    private static Queue<String> lowPriorityTasks = new LinkedList<>();
    private static LinkedList<String> taskLogs = new LinkedList<>();
    
    public static void addTask(boolean isHighPriority, String task){
        if(isHighPriority){
            //Add high priority task
            highPriorityTasks.add(task);
            addLog(true, true, task);
        }else{
            //Add low priority task
            lowPriorityTasks.add(task);
            addLog(true, false, task);
        }
    }
    
    public static void processTask(boolean isHighPriority){
        String task;
                
        if(isHighPriority){
            //Check if queue is empty
            if(highPriorityTasks.isEmpty()){
                return;
            }

            task = highPriorityTasks.peek();    //Get the head value before removing

            highPriorityTasks.remove();         //Remove head
            addLog(false, true, task);          //Add log and pass the saved value of head
        }else{
            //Check if queue is empty
            if(lowPriorityTasks.isEmpty()){
                return;
            }

            task = lowPriorityTasks.peek();     //Get the head value before removing
            
            lowPriorityTasks.remove();          //Remove head
            addLog(false, false, task);         //Add log and pass the saved value of head
        }
    }
    
    private static void addLog(boolean isAdd, boolean isHighPriority, String task){
        taskLogs.add((isAdd ? "Added " : "Removed ")            //Check if mode if it is add or remove
            + (isHighPriority ? "high " : "low ")               //Check priority type
            + "priority task: " + task
        );
        
        //Clear text area first
        textArea_logs.setText("");
        
        //Loop through the logs to display it on the text area
        for(String log : taskLogs) {
            textArea_logs.append(log + "\n");                   //Append task
        }
        
        refreshPriorityTaskTextArea();                          //Refresh priority text areas            
    }
    
    private static void refreshPriorityTaskTextArea(){
        //Clear text areas
        textArea_highPriorityTasks.setText("");
        textArea_lowPriorityTasks.setText("");
        
        
        int highPrioIndex = 1;                                                      //Declare index high priority tasks
        
        //Loop through the logs to display it on the text area
        for(String log : highPriorityTasks) {
            textArea_highPriorityTasks.append(highPrioIndex + ". " + log + "\n");   //Append task
            highPrioIndex++;                                                        //Increment index
        }
        
        
        int lowPrioIndex = 1;                                                       //Declare index for low priority tasks
        
        //Loop through the logs to display it on the text area
        for(String log : lowPriorityTasks) {                                        
            textArea_lowPriorityTasks.append(lowPrioIndex + ". " + log + "\n");     //Append task
            lowPrioIndex++;                                                         //Increment index
        }
    }
}
