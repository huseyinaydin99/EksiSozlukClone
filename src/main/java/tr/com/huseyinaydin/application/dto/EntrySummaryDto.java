package tr.com.huseyinaydin.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntrySummaryDto {
    private UUID id;
    private String subject;
    private int commentCount;
}
