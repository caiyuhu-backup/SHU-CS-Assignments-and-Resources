package shu.silence;

import java.text.SimpleDateFormat;
import java.util.Date;

import static shu.silence.Constants.*;

public class Master extends Student implements Manageable {
    double aver;
    char thesisLevel;

    @Override
    public boolean getPass() {
        setStatus(aver >= 80 && (thesisLevel == 'A' || thesisLevel == 'B' || thesisLevel == 'C'));
        return aver >= 80 && (thesisLevel == 'A' || thesisLevel == 'B' || thesisLevel == 'C');
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
        System.out.println("学位论文成绩" + thesisLevel);
        System.out.println(getDegree());
    }

    @Override
    public double computeAverage() throws Exception {
        double[] score = getScore();
        double multiply = 1.0;
        for (double v : score) {
            if (v < 0 || v > 100)
                throw new UndergraduateException(v);
            else
                multiply *= v;
        }
        if (thesisLevel != 'A' && thesisLevel != 'B' && thesisLevel != 'C' && thesisLevel != 'D' && thesisLevel != 'E')
            throw new MasterException(thesisLevel);
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
