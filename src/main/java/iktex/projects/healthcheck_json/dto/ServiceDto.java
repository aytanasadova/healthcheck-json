package iktex.projects.healthcheck_json.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


@Data
public class ServiceDto {


    private int id;
    private String apiName;
    @Value("http://127.0.0.1/8080/")
    private String apiUrl;
    private String note;
    private Integer healtyStatusCode;
    @Value("healthCheck")
    private String methodName;

}
