package shu.silence;

import java.util.Date;

import static shu.silence.Constants.*;

public class Master extends Student {
    double aver;
    char thesisLevel;

    @Override
    public boolean getPass() {
        computeAverage();
        setStatus(aver >= 80 && (thesisLevel == 'A' || thesisLevel == 'B' || thesisLevel == 'C'));
        return aver >= 80 && (thesisLevel == 'A' || thesisLevel == 'B' || thesisLevel == 'C');
    }

    @Override
    public double computeAverage() {
        double[] score = getScore();
        double multiply = 1.0;
        for (double v : score)
            multiply *= v;
        aver = Math.pow(multiply, 1.0 / score.length);
        return aver;
    }

    @Override
    public String getDegree() {
        if (getPass()) {
            return ISSUE_MASTER_DEGREE;
        } else {
            return UNSATISFACTORY_ISSUE_MASTER_DEGREE;
        }
    }

    public double getAver() {
        return aver;
    }

    public void setAver(double aver) {
        this.aver = aver;
    }

    public char getThesisLevel() {
        return thesisLevel;
    }

    public void setThesisLevel(char thesisLevel) {
        this.thesisLevel = thesisLevel;
    }

    public Master(String name, char gender, Date birthday, String school, String category, String stuId, double[] score, boolean status, char thesisLevel) {
        super(name, gender, birthday, school, category, stuId, score, status);
        this.thesisLevel = thesisLevel;
    }

    public Master(char thesisLevel) {
        this.thesisLevel = thesisLevel;
    }
}
