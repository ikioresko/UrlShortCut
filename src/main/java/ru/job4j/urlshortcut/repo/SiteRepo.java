package ru.job4j.urlshortcut.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;

import java.util.List;

@Repository
public interface SiteRepo extends CrudRepository<Site, Integer> {

    @Query("select s from Site s where s.login = ?1")
    Site findByLogin(String login);

    @Query("select u from Site s join Url u on u.url like concat(?1, '%')")
    List<Url> getStatistic(String link);
}
