package iktex.projects.healthcheck_json.dto;


import lombok.Data;

@Data
public class HealthDto {

    private Integer id;
    private String name;
    private String apiUrl;
    private String note;
    private Integer stateId;
}
