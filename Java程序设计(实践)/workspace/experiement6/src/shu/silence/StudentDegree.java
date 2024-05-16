package shu.silence;

import java.text.SimpleDateFormat;

public class StudentDegree {

    public void issueDegree(Manageable manage) {
        try {
            manage.computeAverage();
        } catch (Exception e) {
            System.out.println("***********************************");
            if (e instanceof MasterException)
                System.out.println(((MasterException) e).warnMess());
            if (e instanceof UndergraduateException)
                System.out.println(((UndergraduateException) e).warnMess());
            System.out.println("***********************************");

        }
        manage.printInfo();

    }
}
