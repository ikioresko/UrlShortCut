package ru.job4j.urlshortcut.control;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.SiteService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ru.job4j.urlshortcut.service.RandomStringService.genRandom;

@Controller
@RequestMapping("/site")
public class SiteControl {
    private final SiteService service;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SiteControl(SiteService service, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.service = service;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    /**
     * @param site объект с обязательно заполненным полем link
     * @return сгенерированный логин:пароль, при успешной регистрации
     */
    @PostMapping("/registration")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody Site site) {
        String login = genRandom();
        String password = genRandom();
        try {
            site.setLogin(login);
            site.setPassword(bCryptPasswordEncoder.encode(password));
            service.save(site);
        } catch (Exception e) {
            return ResponseEntity.of(Optional.of(new HashMap<>() {{
                put("registration", false);
            }}));
        }
        return ResponseEntity.of(Optional.of(new HashMap<>() {{
            put("registration", true);
            put("login", login);
            put("password ", password);
        }}));
    }

    /**
     * Отображает статистику использования сайтов отображая количество вызовов каждой ссылки
     *
     * @return Отображение key:val, где key это ссылка, а val количество вызова ссылки
     */
    @GetMapping("/statistic")
    public ResponseEntity<Map<String, String>> statistic() {
        return ResponseEntity.of(Optional.of(new HashMap<>() {{
            service.getStatistic()
                    .forEach(x -> put("url : " + x.getUrl(),
                            "total : " + x.getCall()));
        }}));
    }
}
