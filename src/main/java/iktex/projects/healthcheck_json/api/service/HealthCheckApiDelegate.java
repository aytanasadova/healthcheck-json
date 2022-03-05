package iktex.projects.healthcheck_json.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/**
 * @author Aytan Asadova
 * @time 05/03/2022
 **/

public interface HealthCheckApiDelegate {


    default ResponseEntity<?> getStatusForAllServices() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
