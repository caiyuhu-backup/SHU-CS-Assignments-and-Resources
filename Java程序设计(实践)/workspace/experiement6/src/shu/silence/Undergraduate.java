package shu.silence;

import java.text.SimpleDateFormat;
import java.util.Date;

import static shu.silence.Constants.*;

public class Undergraduate extends Student implements Manageable {
    double aver;


    @Override
    public boolean getPass() {
        setStatus(aver > 60);
        return aver > 60;
    }

    @Override
    public void printInfo() {
        System.out.println("__________________________________");
        System.out.println("基本信息如下：");
        System.out.println("姓名：" + getName());
        System.out.println("性别：" + getGender());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("生日：" + simpleDateFormat.format(getBirthday()));
        System.out.println("学号：" + getStuId());
        System.out.println("学生类别：" + getCategory());
        System.out.print("各科成绩为：");
        for (double v : score) {
            System.out.print(v + " ");
        }
        System.out.println();
        System.out.println("平均成绩：" + aver);
        System.out.println(getDegree());
    }

    @Override
    public double computeAverage() throws UndergraduateException {
        double[] score = getScore();
        double total = 0.0;
        for (double v : score) {
            if (v < 0 || v > 100) throw new UndergraduateException(v);
            else total += v;
        }
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
