package com.springboot.mybatis.controller;


import com.springboot.common.ResultCodeType;
import com.springboot.common.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常控制
 */
@ControllerAdvice
public class MyControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultMap errorHandler(Exception ex) {
        ex.printStackTrace();
        ResultMap resultMap = new ResultMap();
        resultMap.setResultCode(ResultCodeType.BIZ_ERROR);
        logger.error("errorHandler----> 全局异常 ex = " + ex);
        return resultMap;
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public ResultMap myErrorHandler(NullPointerException ex) {
        ResultMap resultMap = new ResultMap();
        resultMap.setResultCode(ResultCodeType.BIZ_ERROR);
        resultMap.setMessage("空指针异常");
        logger.error("myErrorHandler----> 全局异常 ex = " + ex);
        return resultMap;
    }
}
