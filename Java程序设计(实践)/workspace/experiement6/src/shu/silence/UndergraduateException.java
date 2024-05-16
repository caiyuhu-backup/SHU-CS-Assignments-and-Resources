package shu.silence;

public class UndergraduateException extends Exception {
    static final long serialVersionUID = -3387516993123574848L;

    String message;

    public UndergraduateException(double score) {
        message = "此大学生的某门成绩" + score + "不在0-100 范围内";
    }

    public String warnMess() {
        return message;
    }
}
