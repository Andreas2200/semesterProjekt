package PointSystem;

import javafx.scene.control.ProgressBar;

public class PointSystem {
public ProgressBar progressBar;

    private double point = 10;

    public double addPoint(int value)
    {
        point += value;
        progressBar.setProgress(progressBar.getProgress()-getPoint());
        return point;
    }

    @Override
    public String toString()
    {
        return "Your current points: " + point;
    }

    public double getPoint() { return point; }

    public void setPoint(int point) { this.point = point; }


}
