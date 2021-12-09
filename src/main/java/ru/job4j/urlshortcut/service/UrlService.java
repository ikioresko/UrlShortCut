package ru.job4j.urlshortcut.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.urlshortcut.repo.UrlRepo;

@Service
public class UrlService {
    private final UrlRepo repo;

    public UrlService(UrlRepo repo) {
        this.repo = repo;
    }

    public String save(String url, String code) {
        return repo.save(url, code);
    }

    public Object findByCode(String code) {
        var url = repo.findByCode(code).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Account is not found. Please, check requisites."
        ));
        repo.countUpdate(code);
        return url;
    }
}
