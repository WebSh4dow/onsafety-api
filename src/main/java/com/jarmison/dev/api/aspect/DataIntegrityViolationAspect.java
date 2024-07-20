package com.jarmison.dev.api.aspect;

import com.jarmison.dev.api.core.exception.notification.NotificationViolationException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataIntegrityViolationAspect {

    @AfterThrowing(pointcut = "within(@org.springframework.web.bind.annotation.RestController *)", throwing = "ex")
    public void handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        throw new NotificationViolationException("Ocorreu um erro de violacao de dados na api. Verificar campos cpf, e email devem ser unicos");
    }
}
