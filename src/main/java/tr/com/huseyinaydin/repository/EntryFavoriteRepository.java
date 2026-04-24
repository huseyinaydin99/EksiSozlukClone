package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.huseyinaydin.domain.models.EntryFavorite;

import java.util.UUID;

@Repository
public interface EntryFavoriteRepository extends JpaRepository<EntryFavorite, UUID> {
}
