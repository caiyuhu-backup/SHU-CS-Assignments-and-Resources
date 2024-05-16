package shu.silence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Silence
 * @date: 2022/2/15 13:13
 * @description:
 */
public class RegexChk {
    private String reg;

    public boolean checkInteger(String str) {
        reg = "^-?[1-9]\\d*|0$";
        return check(str);
    }

    public boolean checkPhoneNumber(String str) {
        reg = "^1\\d{10}$";
        return check(str);
    }

    public boolean checkEmail(String str) {
        reg = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        return check(str);
    }

    public boolean checkPostAddress(String str) {
        reg = "^\\d{6}$";
        return check(str);
    }

    public boolean checkIdCode(String str) {
        reg = "^\\d{17}([0-9]|X)$";
        return check(str);
    }

    public boolean check(String str) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        boolean result = matcher.matches();
        reg = null;
        return result;
    }

}
