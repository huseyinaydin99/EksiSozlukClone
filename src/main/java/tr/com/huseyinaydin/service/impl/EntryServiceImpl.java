package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.huseyinaydin.domain.models.Entry;
import tr.com.huseyinaydin.infrastructure.exceptions.NotFoundException;
import tr.com.huseyinaydin.repository.EntryRepository;
import tr.com.huseyinaydin.service.interfaces.EntryService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;

    @Override
    @Transactional
    public Entry create(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    @Transactional
    public Entry update(Entry entry) {
        if (!entryRepository.existsById(entry.getId())) {
            throw new NotFoundException("Entry not found.");
        }
        return entryRepository.save(entry);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!entryRepository.existsById(id)) {
            throw new NotFoundException("Entry not found.");
        }
        entryRepository.deleteById(id);
    }

    @Override
    public Entry getById(UUID id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entry not found with id: " + id));
    }

    @Override
    public List<Entry> getByUserId(UUID userId) {
        return entryRepository.findByUserId(userId);
    }

    @Override
    public List<Entry> searchBySubject(String subject) {
        return entryRepository.findBySubjectContaining(subject);
    }

    @Override
    public List<Entry> getAll() {
        return entryRepository.findAll();
    }
}
