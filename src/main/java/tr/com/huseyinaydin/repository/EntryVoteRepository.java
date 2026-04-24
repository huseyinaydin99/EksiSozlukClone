package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.huseyinaydin.domain.models.EntryVote;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EntryVoteRepository extends JpaRepository<EntryVote, UUID> {
    Optional<EntryVote> findByEntryIdAndCreatedById(UUID entryId, UUID createdById);
}
