package tr.com.huseyinaydin.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntryDetailDto {
    private UUID id;
    private String subject;
    private String content;
    private LocalDateTime createdDate;
    private String createdByUserName;
    private boolean isFavorited;
    private int favoritedCount;
    private int voteType; // 0: None, 1: Down, 2: Up
}
