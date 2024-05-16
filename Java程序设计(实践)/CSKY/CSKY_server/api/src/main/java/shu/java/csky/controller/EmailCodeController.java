package shu.java.csky.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shu.java.csky.EmailCodeService;
import shu.java.csky.vo.ResultVO;

import javax.annotation.Resource;

/**
 * @author 20121706
 */
@RestController
@RequestMapping("/ec")
public class EmailCodeController {
    @Resource
    private EmailCodeService emailCodeService;

    @GetMapping("/send/{email}")
    public ResultVO sendEmailCode(@PathVariable String email) {
        return emailCodeService.sendCode(email);
    }

    @GetMapping("/check/{code}/{eid}")
    public ResultVO checkCode(@PathVariable("code") String code, @PathVariable("eid") String eid) {
        return emailCodeService.checkCode(code, eid);
    }
}
