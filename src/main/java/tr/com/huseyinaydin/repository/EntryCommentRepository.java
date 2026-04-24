package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.com.huseyinaydin.domain.models.EntryComment;

import java.util.List;
import java.util.UUID;

@Repository
public interface EntryCommentRepository extends JpaRepository<EntryComment, UUID> {
    @Query("SELECT ec FROM EntryComment ec WHERE ec.entry.id = :entryId")
    List<EntryComment> findByEntryId(@Param("entryId") UUID entryId);

    @Query("SELECT ec FROM EntryComment ec WHERE ec.createdBy.id = :userId")
    List<EntryComment> findByUserId(@Param("userId") UUID userId);
}
