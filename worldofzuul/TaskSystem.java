public class TaskSystem
{
    private Task[] activeTasks;
    private int activeTaskCounter;
    private Task[] completedTask;
    private int completedTaskCounter;
    public Task testTask;

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
        testTask = new Task(100,2,"This task is merely a simple test, to verify that the task system works.");
        testTask.setStep(0, "Go to the fishers market");
        testTask.setStep(1,"Return to the harbor");
        addTask(testTask);
    }

    private void addTask(Task task)
    {
        activeTasks[activeTaskCounter] = task;
        activeTaskCounter++;
    }
}
