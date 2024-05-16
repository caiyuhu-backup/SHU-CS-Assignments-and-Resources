package shu.silence;

public class MasterException extends Exception {
    static final long serialVersionUID = -3387516993198774848L;

    String message;

    public MasterException(char thesisLevel) {
        message = "此硕士生研究生学位论文等级" + thesisLevel + "不在A-E范围内";
    }

    public String warnMess() {
        return message;
    }
}
