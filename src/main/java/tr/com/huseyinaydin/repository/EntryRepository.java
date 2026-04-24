package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.com.huseyinaydin.domain.models.Entry;

import java.util.List;
import java.util.UUID;

@Repository
public interface EntryRepository extends JpaRepository<Entry, UUID> {
    @Query("SELECT e FROM Entry e WHERE e.createdBy.id = :userId")
    List<Entry> findByUserId(@Param("userId") UUID userId);

    @Query("SELECT e FROM Entry e WHERE LOWER(e.subject) LIKE LOWER(CONCAT('%', :subject, '%'))")
    List<Entry> findBySubjectContaining(@Param("subject") String subject);
}
