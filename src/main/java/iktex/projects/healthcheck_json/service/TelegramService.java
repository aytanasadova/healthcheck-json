package iktex.projects.healthcheck_json.service;


import iktex.projects.healthcheck_json.dto.TelegramMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${telegram.url}")
    private String telegramUrl;

    public void sendMessage(String text) {

        String elem = text;

        TelegramMessage telegramMessage = new TelegramMessage();
      telegramMessage.setChat_id("917313009");
//        telegramMessage.setChat_id("971268601"); //"first_name": "Anar", "last_name": "Mammadov",
        telegramMessage.setText(elem);
        telegramMessage.setParse_mode("Markdown");
        try {
            prepareMessageParams(telegramMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void prepareMessageParams(TelegramMessage telegramMessage) {

        HttpHeaders headers = new HttpHeaders();

        HttpEntity entity = new HttpEntity(headers);

        restTemplate.exchange(telegramUrl, HttpMethod.POST, entity, String.class,
                telegramMessage.getChat_id(),
                telegramMessage.getText(),
                telegramMessage.getParse_mode());
    }

}
