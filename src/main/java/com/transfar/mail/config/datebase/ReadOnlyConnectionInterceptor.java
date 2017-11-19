package com.transfar.mail.config.datebase;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/18 20:40
 * @version:1.0.0
 */
@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered{
    private static Logger LOGGER = LoggerFactory.getLogger(ReadOnlyConnectionInterceptor.class);

    @Around("@annotation(readOnlyConnection)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint,ReadOnlyConnection readOnlyConnection) throws Throwable {
        try{
            LOGGER.info("-------------set DataBaseType=ReadOnly---------------------");
            DataBaseContextHolder.setDataBaseType(DataBaseContextHolder.DataBaseType.SLAVE);
            Object result = proceedingJoinPoint.proceed();
            return result;
        }finally {
            DataBaseContextHolder.clearDataBaseType();
            LOGGER.info("-------------clear DataBase---------------------");
        }

    }


    @Override
    public int getOrder() {
        return 0;
    }
}
