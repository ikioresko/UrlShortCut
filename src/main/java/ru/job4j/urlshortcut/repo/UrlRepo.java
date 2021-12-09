package ru.job4j.urlshortcut.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.Url;

import java.util.Optional;

public interface UrlRepo extends CrudRepository<Url, Integer> {

    @Query(value = "insert into Url (url, code) values (?1, ?2) "
            + "on conflict (url) do update set url = ?1 returning code",
            nativeQuery = true)
    String save(String url, String code);

    @Query("select u.url from Url u where u.code = ?1")
    Optional<?> findByCode(String code);

    @Transactional
    @Modifying
    @Query("update Url u set u.call = u.call + 1 where u.code = ?1")
    void countUpdate(String code);
}
