package cn.com.lgs.exceptionhandler;

import cn.com.lgs.utils.CommonResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author 10545
 * @date 2021/10/23 18:55
 */
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return CommonResult.failed("全局异常处理：" + e.getMessage());
    }
}
