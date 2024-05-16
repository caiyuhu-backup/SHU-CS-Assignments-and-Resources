package shu.silence;

import org.junit.Test;

/**
 * @author: Silence
 * @date: 2022/2/15 13:29
 * @description:
 */
public class RegexChkTest {
    private final RegexChk regexChk = new RegexChk();

    @Test
    public void checkIntegerTest() {
        String str1 = "0";
        String str2 = "-210";
        String str3 = "9990";
        String str4 = "89.2";
        System.out.println("整数测试开始");
        System.out.println("Str为" + str1 + ", 校验结果为 " + regexChk.checkInteger(str1));
        System.out.println("Str为" + str2 + ", 校验结果为 " + regexChk.checkInteger(str2));
        System.out.println("Str为" + str3 + ", 校验结果为 " + regexChk.checkInteger(str3));
        System.out.println("Str为" + str4 + ", 校验结果为 " + regexChk.checkInteger(str4));
    }

    //
    @Test
    public void checkPhoneNumber() {
        String str1 = "13834162141";
        String str2 = "23834162141";
        String str3 = "1383416214";
        String str4 = "89.2";
        System.out.println("手机号测试开始");
        System.out.println("Str为" + str1 + ", 校验结果为 " + regexChk.checkPhoneNumber(str1));
        System.out.println("Str为" + str2 + ", 校验结果为 " + regexChk.checkPhoneNumber(str2));
        System.out.println("Str为" + str3 + ", 校验结果为 " + regexChk.checkPhoneNumber(str3));
        System.out.println("Str为" + str4 + ", 校验结果为 " + regexChk.checkPhoneNumber(str4));
    }

    @Test
    public void checkEmail() {
        String str1 = "hcy1142111516@gmail.com";
        String str2 = "1142111516@qq.com";
        String str3 = "1142111516@qq.com.com";
        System.out.println("邮箱测试开始");
        System.out.println("Str为" + str1 + ", 校验结果为 " + regexChk.checkEmail(str1));
        System.out.println("Str为" + str2 + ", 校验结果为 " + regexChk.checkEmail(str2));
        System.out.println("Str为" + str3 + ", 校验结果为 " + regexChk.checkEmail(str3));
    }

    @Test
    public void checkPostAddress() {
        String str1 = "123331";
        String str2 = "030021";
        String str3 = "0300021";
        System.out.println("邮政编码测试开始");
        System.out.println("Str为" + str1 + ", 校验结果为 " + regexChk.checkPostAddress(str1));
        System.out.println("Str为" + str2 + ", 校验结果为 " + regexChk.checkPostAddress(str2));
        System.out.println("Str为" + str3 + ", 校验结果为 " + regexChk.checkPostAddress(str3));
    }

    @Test
    public void checkIdCode() {
        String str1 = "140107200202271712";
        String str2 = "14010720020227171";
        String str3 = "34010720020227171X";
        System.out.println("身份证号码测试开始");
        System.out.println("Str为" + str1 + ", 校验结果为 " + regexChk.checkIdCode(str1));
        System.out.println("Str为" + str2 + ", 校验结果为 " + regexChk.checkIdCode(str2));
        System.out.println("Str为" + str3 + ", 校验结果为 " + regexChk.checkIdCode(str3));

    }

}