package softwareArchitecture.termProject.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

@Aspect
@Slf4j
public class LogConfig {
    @Around("execution(* softwareArchitecture.termProject..*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        log.info("LogAspect Root:: "+pjp.getSignature().getDeclaringTypeName());
        log.info("LogAspect Method:: "+pjp.getSignature().getName());
        return result;
    }
}


