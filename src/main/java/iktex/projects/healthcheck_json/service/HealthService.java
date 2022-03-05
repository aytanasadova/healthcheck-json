package iktex.projects.healthcheck_json.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import iktex.projects.healthcheck_json.api.service.HealthCheckApiDelegate;
import iktex.projects.healthcheck_json.dto.HealthDto;
import iktex.projects.healthcheck_json.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class HealthService implements HealthCheckApiDelegate {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    TelegramService telegramService;

    @Override
    public ResponseEntity<?> getStatusForAllServices() {
        List<ServiceDto> serviceDtoList = new ArrayList<>();
        List<HealthDto> healthDtos = new ArrayList<>();

        serviceDtoList = ( List<ServiceDto>)readFromJson();

        System.out.println("6666 "+serviceDtoList);

        for (ServiceDto elem : serviceDtoList) {
            if (elem != null) {
                HealthDto healthDto = new HealthDto();
                healthDto = getStatusByApi(elem);
                healthDtos.add(healthDto);
            }
        }
        return ResponseEntity.ok(healthDtos);
    }


    private HealthDto getStatusByApi(ServiceDto service) {
        HealthDto healthDto = new HealthDto();
        healthDto.setId(service.getId());
        healthDto.setApiUrl(service.getApiUrl());
        healthDto.setNote(service.getNote());
        healthDto.setName(service.getApiName());
        String url = service.getApiUrl() + service.getMethodName();
        try {
            String result = restTemplate.getForObject(url, String.class);
            healthDto.setStateId(1);
        } catch (Exception e) {
            healthDto.setStateId(-1);
            telegramService.sendMessage(url + " service is down");

        }

        return healthDto;

    }


    public List<ServiceDto> readFromJson()  {

        List<ServiceDto> serviceDtoList=new ArrayList<>();

        String filePath = null;

        filePath = "services.json";

        final ObjectMapper mapper = new ObjectMapper();
        final Resource resource = (Resource) new ClassPathResource(filePath);
        try {
            final InputStream input = resource.getInputStream();
            final File file = resource.getFile();
            serviceDtoList = (List<ServiceDto>) mapper.readValue(file, new TypeReference<List<ServiceDto> >() {
            });

        } catch (Exception e) {
            return null;
        }

        return serviceDtoList;
    }

}

