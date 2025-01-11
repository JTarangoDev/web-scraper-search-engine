package dev.jtarango.web_scrapper.repository;

import dev.jtarango.web_scrapper.models.WebPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WebPageRepository extends JpaRepository<WebPage, Integer> {

    @Query("SELECT w FROM WebPage w WHERE w.domain LIKE %:text% OR w.description LIKE %:text% OR w.title LIKE %:text% OR w.url LIKE %:text% ORDER BY ranking DESC")
    List<WebPage> findByText(@Param("text") String text);

    WebPage findByUrl(String url);
}
