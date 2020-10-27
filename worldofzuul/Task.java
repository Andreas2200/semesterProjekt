public class Task
{
    private int RewardPoints;
    private String[] steps;
    private String[] completedSteps;
    private int completedStepsCounter;
    private String taskDescription;
    private boolean isCompleted = false;
    private boolean rewardType = false;

    public Task(int RewardPoints, int steps, String taskDescription)
    {
        this.RewardPoints = RewardPoints;
        this.steps = new String[steps];
        this.taskDescription = taskDescription;
        this.completedSteps = new String[steps];
        completedStepsCounter = 0;
    }

    //Getters
    public int getRewardPoints()
    {
        return RewardPoints;
    }

    public String getTaskDescription()
    {
        return taskDescription;
    }

    public String[] getSteps()
    {
        return steps;
    }

    public String[] getCompletedSteps()
    {
        return completedSteps;
    }

    public boolean isRewardType()
    {
        return rewardType;
    }

    //Setters
    public void setStep(int stepNumber, String stepDescription)
    {
        steps[stepNumber] = stepDescription;
    }

    public void setRewardType(boolean rewardType)
    {
        this.rewardType = rewardType;
    }

    //Methods
    public void completedStep(int stepNumber)
    {
        completedSteps[completedStepsCounter] = steps[stepNumber];
        completedStepsCounter++;
    }
}
