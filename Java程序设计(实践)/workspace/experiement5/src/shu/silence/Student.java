package shu.silence;

import java.util.Arrays;
import java.util.Date;

public abstract class Student {
    String name;
    char gender;
    Date birthday;
    String school;
    String category;
    String stuId;
    double[] score;
    boolean status;

    public abstract boolean getPass();

    public abstract double computeAverage();

    public abstract String getDegree();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public double[] getScore() {
        return score;
    }

    public void setScore(double[] score) {
        this.score = score;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Student(String name, char gender, Date birthday, String school, String category, String stuId, double[] score, boolean status) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.school = school;
        this.category = category;
        this.stuId = stuId;
        this.score = score;
        this.status = status;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", school='" + school + '\'' +
                ", category='" + category + '\'' +
                ", stuId='" + stuId + '\'' +
                ", score=" + Arrays.toString(score) +
                ", status=" + status;
    }
}
