package com.proptechos.parser.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Slf4j
public class ClientResponseErrorHandler implements ResponseErrorHandler {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
        throws IOException {
        return (
            httpResponse.getStatusCode().series() == CLIENT_ERROR
                || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
        throws IOException {
        String errorMessage = extractMessage(httpResponse);
        log.error("Failed to obtain agent data. Status code [{}], Status message [{}]",
            httpResponse.getStatusCode(), errorMessage);
        if (httpResponse.getStatusCode()
            .series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new IllegalStateException(errorMessage);
            }
        }
        throw new IllegalStateException(errorMessage);
    }

    private String extractMessage(ClientHttpResponse httpResponse) {
        try (InputStream is = httpResponse.getBody()) {
            ErrorMessage msg = mapper.readValue(is, ErrorMessage.class);
            return msg.getMessage();
        } catch (IOException e) {
            log.error("Failed to read error message from response: ", e);
        }
        return "";
    }

    @Getter
    @Setter
    private static class ErrorMessage {

        private int status;
        private String error;
        private String message;
        private String timestamp;
        private String path;

    }

}
