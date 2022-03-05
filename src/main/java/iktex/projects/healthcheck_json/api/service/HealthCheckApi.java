package iktex.projects.healthcheck_json.api.service;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Aytan Asadova
 * @time 05/03/2022
 **/

@CrossOrigin(origins = "*")
public interface HealthCheckApi {
    default HealthCheckApiDelegate getDelegate() {
        return new HealthCheckApiDelegate() {
        };
    }


    @GetMapping()
    default ResponseEntity<?> getStatusForAllServices() {

        return getDelegate().getStatusForAllServices();
    }

}
