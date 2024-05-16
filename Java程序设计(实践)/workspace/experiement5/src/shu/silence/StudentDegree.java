package shu.silence;

import java.text.SimpleDateFormat;

public class StudentDegree {
    public void printStudentInfo(Student student) {
        System.out.println("__________________________________");
        System.out.println("基本信息如下：");
        System.out.println("姓名：" + student.getName());
        System.out.println("性别：" + student.getGender());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("生日：" + simpleDateFormat.format(student.getBirthday()));
        System.out.println("学号：" + student.getStuId());
        System.out.println("学生类别：" + student.getCategory());
        double aver = student.computeAverage();
        System.out.println("平均成绩：" + aver);
    }

    public void issueDegree(Student student) {
        System.out.println(student.getDegree());
    }
}
