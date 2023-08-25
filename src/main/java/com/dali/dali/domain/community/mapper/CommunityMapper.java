package com.dali.dali.domain.community.mapper;

import com.dali.dali.domain.community.dto.CommunityDto;
import com.dali.dali.domain.community.entity.Community;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommunityMapper {
    @Mapping(source = "id", target = "id")
    CommunityDto toDto(Community community);

    @Mapping(source = "id", target = "id")
    Community toEntity(CommunityDto communityDto);
}
