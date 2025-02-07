package com.unir.operador.facade;

import com.unir.operador.dto.ItemResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Component
@RequiredArgsConstructor
@Slf4j
public class BooksFacade {
    @Value("${getBook.url}")
    private String getBookUrl;

    private final RestTemplate restTemplate;

    public ItemResponseDTO getBook(String itemId) {
        try {
            String url = String.format(getBookUrl, itemId);
            log.info("Obteniendo libro con id: {}", itemId);
            return restTemplate.getForObject(url, ItemResponseDTO.class);
        } catch (HttpClientErrorException e) {
            log.error("Client Error al obtener el libro con id: {}", itemId);
            return null;
        } catch (HttpServerErrorException e) {
            log.error("Server Error al obtener el libro con id: {}", itemId);
            return null;
        } catch (Exception e) {
            log.error("Error al obtener el libro con id: {}", itemId);
            return null;
        }
    }
}
