package tr.com.huseyinaydin.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private List<EntryDetailDto> entries;
}
