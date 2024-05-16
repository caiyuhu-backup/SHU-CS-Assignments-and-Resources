package shu.java.csky.interceptor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import shu.java.csky.vo.ResultVO;

/**
 * @author 20121706
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常处理 返回json
     *
     * @param e 通过 @ExceptionHandler 标注方法处理特定异常，这里以用户未登录异常为例，通过全局异常进
     *          行统一处理
     *          在用户修改接口中抛出未登录异常为例进行测试
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO exceptionHandler(Exception e) {
        e.printStackTrace();
        ResultVO ResultVO = new ResultVO();
        ResultVO.setCode(400);
        ResultVO.setMsg(e.getMessage());
        return ResultVO;
    }
}
