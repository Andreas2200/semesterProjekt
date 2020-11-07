import java.util.Arrays;

public class TaskSystem
{
    private Task[] activeTasks;
    private int activeTaskCounter;
    private Task[] completedTask;
    private int completedTaskCounter;
    public Task testTask;
    public Task testTrack2;

    public TaskSystem(int amountOfQuests)
    {
        activeTasks = new Task[amountOfQuests];
        completedTask = new Task[amountOfQuests];
        activeTaskCounter = 0;
        completedTaskCounter = 0;
        createTasks();
    }

    private void createTasks()
    {
        testTask = new Task(100,2,"main","Test Task");
        testTask.setStep(0, "Go to the fishers market and speak with Kenneth");
        testTask.setStep(1,"Return to the harbor");

        testTrack2 = new Task(100,2, 1,"side","Test task 2");
        testTrack2.setStep(0,"Go to the beach");
        testTrack2.setStep(1,"Return to Bridge 1");
        testTrack2.setBadStep(0,"Test bad step");

    }

    public void moveCompletedTask(Task task)
    {
        for (int i = 0; i < activeTaskCounter; i++)
        {
            if(activeTasks[i] == task)
            {
                completedTask[completedTaskCounter] = activeTasks[i];
                System.out.println("assigned completed task");
                completedTaskCounter++;
                for (int j = activeTaskCounter<1 ? i + 1 : i; j < activeTaskCounter; j++)
                {
                    activeTasks[i] = activeTasks[j];
                    activeTasks[j] = null;
                }
                activeTaskCounter--;
            }
        }
    }

    public void addTask(Task task)
    {
        activeTasks[activeTaskCounter] = task;
        activeTaskCounter++;
        System.out.println(task.taskStart());
    }

    public void assignStepRoom(Task task, int stepNumber,Room room)//Kan på sigt ændres til assignStepNPC således man skal snakke med en NPC i stedet for, men da det ikke er lagt i spillet endnu bliver det rummet
    {
        task.setRoomSteps(stepNumber, room);
    }

    public void assignBadStepRoom(Task task, int stepNumber, Room room)
    {
        task.setRoomBadStep(stepNumber, room);
    }

    public String getCompletedTask()
    {
        String completedTasksPrint = "";

        for (int i = 0; i < completedTaskCounter; i++)
        {
            if(completedTask[i] != null)
            {
                completedTasksPrint += completedTask[i];
            }
        }
        return completedTasksPrint;
    }

    @Override
    public String toString()
    {
        String temp = "";
        if(activeTaskCounter != 0)
        {
            temp = "Active tasks: ";
            for(Task task: activeTasks)
            {
                if(task != null)
                {
                    temp += task + " ";
                }
            }
        }
        else
        {
            temp = "No active tasks right now";
        }
        return temp;
    }

    public Task getActiveTask()
    {
        return activeTasks[0];
    }

    public String getActiveTaskName()
    {
        String temp = "";

        return activeTasks[completedTaskCounter].getTaskName();
    }

    public Task getTask(String taskName)
    {
        Task temp = null;

        for (int i = 0; i < activeTaskCounter; i++)
        {
            if(activeTasks[i].getTaskName().equals(taskName))
            {
                temp = activeTasks[i];
                break;
            }
        }
        return temp;
    }

    public boolean isATask(String task)
    {
        for (int i = 0; i < activeTasks.length; i++)
        {
            if(activeTasks[i].getTaskName() == null)
            {
                return false;
            }
            else if (task.equals(activeTasks[i].getTaskName()))
            {
                return true;
            }
        }
        return false;
    }

    public String showTaskStep(Task task)
    {
        String temp = "";
        Task tempTask = null;
        for (int i = 0; i < activeTaskCounter ; i++)
        {
            if(activeTasks[i] == task)
            {
                tempTask = activeTasks[i];
                break;
            }
        }
        temp += tempTask.getStep();
        return temp;
    }

    public int getActiveTaskCounter()
    {
        return activeTaskCounter;
    }
}
