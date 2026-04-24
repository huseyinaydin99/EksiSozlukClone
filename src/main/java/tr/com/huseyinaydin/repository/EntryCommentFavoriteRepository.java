package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.huseyinaydin.domain.models.EntryCommentFavorite;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EntryCommentFavoriteRepository extends JpaRepository<EntryCommentFavorite, UUID> {
    Optional<EntryCommentFavorite> findByEntryCommentIdAndCreatedUserId(UUID entryCommentId, UUID createdUserId);
}
