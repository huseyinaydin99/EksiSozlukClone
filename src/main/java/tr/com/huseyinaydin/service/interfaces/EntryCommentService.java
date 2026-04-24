package tr.com.huseyinaydin.service.interfaces;

import tr.com.huseyinaydin.domain.models.EntryComment;

import java.util.List;
import java.util.UUID;

public interface EntryCommentService {
    EntryComment create(EntryComment entryComment);
    EntryComment update(EntryComment entryComment);
    void delete(UUID id);
    EntryComment getById(UUID id);
    List<EntryComment> getByEntryId(UUID entryId);
    List<EntryComment> getByUserId(UUID userId);
}
