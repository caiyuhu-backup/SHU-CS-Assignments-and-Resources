package shu.silence;

import java.util.Date;
import static shu.silence.Constants.*;

public class Undergraduate extends Student {
    double aver;


    @Override
    public boolean getPass() {
        computeAverage();
        setStatus(aver > 60);
        return aver > 60;
    }

    @Override
    public double computeAverage() {
        double[] score = getScore();
        double total = 0.0;
        for (double v : score) total += v;
        aver = total / score.length;
        return aver;
    }

    @Override
    public String getDegree() {
        if (getPass()) {
            return ISSUE_UNDERGRADUATE_DEGREE;
        } else {
            return UNSATISFACTORY_ISSUE_UNDERGRADUATE_DEGREE;
        }
    }

    public double getAver() {
        return aver;
    }

    public void setAver(double aver) {
        this.aver = aver;
    }

    public Undergraduate(String name, char gender, Date birthday, String school, String category, String stuId, double[] score, boolean status) {
        super(name, gender, birthday, school, category, stuId, score, status);
    }

    public Undergraduate() {
    }
}
