package com.dali.dali.domain.community.mapper;

import com.dali.dali.domain.community.dto.CommunityDto;
import com.dali.dali.domain.community.dto.CommunityDto.CommunityDtoBuilder;
import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.entity.Community.CommunityBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-25T17:41:59+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class CommunityMapperImpl implements CommunityMapper {

    @Override
    public CommunityDto toDto(Community community) {
        if ( community == null ) {
            return null;
        }

        CommunityDtoBuilder communityDto = CommunityDto.builder();

        communityDto.id( community.getId() );
        communityDto.title( community.getTitle() );
        communityDto.content( community.getContent() );
        communityDto.gender( community.getGender() );
        communityDto.ampm( community.getAmpm() );
        communityDto.time( community.getTime() );
        communityDto.userCount( community.getUserCount() );
        communityDto.currentCount( community.getCurrentCount() );
        communityDto.regDate( community.getRegDate() );
        communityDto.updateDate( community.getUpdateDate() );

        return communityDto.build();
    }

    @Override
    public Community toEntity(CommunityDto communityDto) {
        if ( communityDto == null ) {
            return null;
        }

        CommunityBuilder community = Community.builder();

        community.id( communityDto.getId() );
        community.title( communityDto.getTitle() );
        community.content( communityDto.getContent() );
        community.regDate( communityDto.getRegDate() );
        community.updateDate( communityDto.getUpdateDate() );
        community.gender( communityDto.getGender() );
        community.ampm( communityDto.getAmpm() );
        community.time( communityDto.getTime() );
        community.userCount( communityDto.getUserCount() );
        community.currentCount( communityDto.getCurrentCount() );

        return community.build();
    }
}
