package tr.com.huseyinaydin.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tr.com.huseyinaydin.application.dto.EntryDetailDto;
import tr.com.huseyinaydin.application.dto.EntrySummaryDto;
import tr.com.huseyinaydin.domain.models.Entry;

@Mapper(componentModel = "spring")
public interface EntryMapper {
    @Mapping(target = "createdByUserName", source = "createdBy.username")
    @Mapping(target = "createdDate", source = "createDate")
    @Mapping(target = "favoritedCount", expression = "java(entry.getEntryFavorites() != null ? entry.getEntryFavorites().size() : 0)")
    @Mapping(target = "isFavorited", ignore = true)
    @Mapping(target = "voteType", ignore = true)
    EntryDetailDto toDetailDto(Entry entry);

    @Mapping(target = "commentCount", expression = "java(entry.getEntryComments() != null ? entry.getEntryComments().size() : 0)")
    EntrySummaryDto toSummaryDto(Entry entry);
}
