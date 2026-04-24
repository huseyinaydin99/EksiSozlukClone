package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.huseyinaydin.domain.models.EntryCommentVote;

import java.util.UUID;

@Repository
public interface EntryCommentVoteRepository extends JpaRepository<EntryCommentVote, UUID> {
}
