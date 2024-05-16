package shu.silence;

import java.util.Date;

import static shu.silence.Constants.MASTER;
import static shu.silence.Constants.UNDERGRADUATE;

public class StudentApplication {
    public static void main(String[] args) {
        Student student;
        Undergraduate undergraduate = new Undergraduate("王小二", '男', new Date("2001/6/1"), "上海大学", UNDERGRADUATE, "11128981", new double[]{1000.5, 82, 87, 73}, false);

        Master master = new Master("李燕", '女', new Date("1999/6/12"), "上海大学", MASTER, "10306", new double[]{70, 52.5, 95, 88, 89, 91}, false, 'B');

        StudentDegree studentDegree = new StudentDegree();

        studentDegree.issueDegree(undergraduate);

        studentDegree.issueDegree(master);

    }
}
