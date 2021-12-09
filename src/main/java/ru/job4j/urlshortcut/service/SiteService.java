package ru.job4j.urlshortcut.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repo.SiteRepo;

import java.util.List;

@Service
public class SiteService {
    private final SiteRepo repo;

    public SiteService(SiteRepo repo) {
        this.repo = repo;
    }

    public Site save(Site site) {
        return repo.save(site);
    }

    public void findById(int id) {
        repo.findById(id);
    }

    public Site findByLogin(String login) {
        return repo.findByLogin(login);
    }

    public List<Url> getStatistic() {
        return repo.getStatistic(findByLogin(getUsername()).getLink());
    }

    private String getUsername() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
