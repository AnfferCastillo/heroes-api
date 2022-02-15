package com.anffercastillo.heroes.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MeasureAspect {

  Logger logger = LogManager.getLogger(MeasureAspect.class);

  @Around("@annotation(Measure)")
  public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    var start = System.currentTimeMillis();
    var proceed = joinPoint.proceed();
    var executionTime = System.currentTimeMillis() - start;
    var message =
        new StringBuilder()
            .append(joinPoint.getSignature())
            .append(" took ")
            .append(executionTime)
            .append("ms")
            .toString();
    logger.info(message);
    return proceed;
  }
}
