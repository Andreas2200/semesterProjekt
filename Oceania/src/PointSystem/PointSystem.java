package PointSystem;

import javafx.scene.control.ProgressBar;

public class PointSystem {
public ProgressBar progressBar;

    private int point = 0;

    public int addPoint(int value)
    {
        point += value;
        progressBar.setProgress(0);
        return point;
    }

    @Override
    public String toString()
    {
        return "Your current points: " + point;
    }

    public int getPoint() { return point; }

    public void setPoint(int point) { this.point = point; }


}
