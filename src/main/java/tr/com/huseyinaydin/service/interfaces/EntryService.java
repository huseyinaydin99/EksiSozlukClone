package tr.com.huseyinaydin.service.interfaces;

import tr.com.huseyinaydin.domain.models.Entry;

import java.util.List;
import java.util.UUID;

public interface EntryService {
    Entry create(Entry entry);
    Entry update(Entry entry);
    void delete(UUID id);
    Entry getById(UUID id);
    List<Entry> getByUserId(UUID userId);
    List<Entry> searchBySubject(String subject);
    List<Entry> getAll();
}
