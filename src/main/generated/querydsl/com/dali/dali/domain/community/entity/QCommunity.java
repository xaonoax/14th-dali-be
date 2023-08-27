package com.dali.dali.domain.community.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunity is a Querydsl query type for Community
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunity extends EntityPathBase<Community> {

    private static final long serialVersionUID = 313766112L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunity community = new QCommunity("community");

    public final EnumPath<AMPM> ampm = createEnum("ampm", AMPM.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> currentCount = createNumber("currentCount", Integer.class);

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.dali.dali.domain.park.QPark park;

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final EnumPath<Time> time = createEnum("time", Time.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final com.dali.dali.domain.users.entity.QUser user;

    public final NumberPath<Integer> userCount = createNumber("userCount", Integer.class);

    public QCommunity(String variable) {
        this(Community.class, forVariable(variable), INITS);
    }

    public QCommunity(Path<? extends Community> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunity(PathMetadata metadata, PathInits inits) {
        this(Community.class, metadata, inits);
    }

    public QCommunity(Class<? extends Community> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.park = inits.isInitialized("park") ? new com.dali.dali.domain.park.QPark(forProperty("park")) : null;
        this.user = inits.isInitialized("user") ? new com.dali.dali.domain.users.entity.QUser(forProperty("user")) : null;
    }

}

