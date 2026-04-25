package tr.com.huseyinaydin.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tr.com.huseyinaydin.application.dto.EntryCommentDto;
import tr.com.huseyinaydin.domain.models.EntryComment;

@Mapper(componentModel = "spring")
public interface EntryCommentMapper {
    @Mapping(target = "createdByUserName", source = "createdBy.username")
    @Mapping(target = "createdDate", source = "createDate")
    @Mapping(target = "favoritedCount", expression = "java(entryComment.getEntryCommentFavorites() != null ? entryComment.getEntryCommentFavorites().size() : 0)")
    @Mapping(target = "isFavorited", ignore = true)
    @Mapping(target = "voteType", ignore = true)
    EntryCommentDto toDto(EntryComment entryComment);
}
