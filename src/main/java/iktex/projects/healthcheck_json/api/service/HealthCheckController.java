package iktex.projects.healthcheck_json.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Aytan Asadova
 * @time 05/03/2022
 **/

@RestController
@RequestMapping("/healthCheck")
public class HealthCheckController implements HealthCheckApi {
    private final HealthCheckApiDelegate delegate;

    public HealthCheckController(@Autowired(required = false) HealthCheckApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new HealthCheckApiDelegate() {});
    }

    @Override
    public HealthCheckApiDelegate getDelegate() {
        return delegate;
    }

}
