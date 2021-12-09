package ru.job4j.urlshortcut.control;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.service.UrlService;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ru.job4j.urlshortcut.service.RandomStringService.genRandom;

@Controller
@RequestMapping("/url")
public class UrlControl {
    private final UrlService service;

    public UrlControl(UrlService service) {
        this.service = service;
    }

    /**
     * Конвертирует ссылку в код
     *
     * @param url ссылка на ресурс
     * @return код ассоциированный с ссылкой
     */
    @PostMapping("/convert")
    public ResponseEntity<Map<String, String>> convert(@Valid @RequestBody Url url) {
        return ResponseEntity.of(Optional.of(new HashMap<>() {{
            put("code", service.save(url.getUrl(), genRandom()));
        }}));
    }

    /**
     * Осуществляет переадресацию на ссылку ассоциированную с кодом
     *
     * @param code код ссылки
     * @return переадресованную страницу
     */
    @GetMapping("/redirect")
    public ResponseEntity<?> redirect(@RequestParam String code) {
        String url;
        try {
            url = (String) service.findByCode(code);
        } catch (Exception e) {
            return new ResponseEntity("Code is empty or not found", HttpStatus.NOT_FOUND);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(url));
        return new ResponseEntity<>(httpHeaders, HttpStatus.PERMANENT_REDIRECT);
    }
}
