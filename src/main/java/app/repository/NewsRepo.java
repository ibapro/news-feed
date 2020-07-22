package app.repository;

import app.entity.ArticlesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NewsRepo extends JpaRepository<ArticlesEntity, Integer> {
    Optional<ArticlesEntity> findByUrl(String url);
}
