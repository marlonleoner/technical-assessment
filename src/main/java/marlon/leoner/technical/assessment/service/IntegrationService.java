package marlon.leoner.technical.assessment.service;

import marlon.leoner.technical.assessment.domain.response.CPFValidationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class IntegrationService {

    @Value("${cpf.validator.token}")
    private String token;

    @Value("${cpf.validator.type}")
    private String type;

    public Boolean isCPFValid(String cpf) {
        CPFValidationResponse validate = validateCpf(cpf);
        if (Objects.isNull(validate)) {
            return false;
        }

        return validate.getValid();
    }

    private CPFValidationResponse validateCpf(String cpf) {
        UriComponents url = UriComponentsBuilder
                .fromUriString("https://api.invertexto.com/v1/validator")
                .queryParam("token", token)
                .queryParam("value", cpf)
                .queryParam("type", type)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CPFValidationResponse> result = restTemplate.getForEntity(url.toString(), CPFValidationResponse.class);
        if (result.getStatusCode() == HttpStatus.OK) {
            return result.getBody();
        }

        return null;
    }

}
