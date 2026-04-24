package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.huseyinaydin.domain.models.EntryComment;
import tr.com.huseyinaydin.infrastructure.exceptions.NotFoundException;
import tr.com.huseyinaydin.repository.EntryCommentRepository;
import tr.com.huseyinaydin.service.interfaces.EntryCommentService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EntryCommentServiceImpl implements EntryCommentService {

    private final EntryCommentRepository entryCommentRepository;

    @Override
    @Transactional
    public EntryComment create(EntryComment entryComment) {
        return entryCommentRepository.save(entryComment);
    }

    @Override
    @Transactional
    public EntryComment update(EntryComment entryComment) {
        if (!entryCommentRepository.existsById(entryComment.getId())) {
            throw new NotFoundException("Entry comment not found.");
        }
        return entryCommentRepository.save(entryComment);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!entryCommentRepository.existsById(id)) {
            throw new NotFoundException("Entry comment not found.");
        }
        entryCommentRepository.deleteById(id);
    }

    @Override
    public EntryComment getById(UUID id) {
        return entryCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entry comment not found with id: " + id));
    }

    @Override
    public List<EntryComment> getByEntryId(UUID entryId) {
        return entryCommentRepository.findByEntryId(entryId);
    }

    @Override
    public List<EntryComment> getByUserId(UUID userId) {
        return entryCommentRepository.findByUserId(userId);
    }
}
