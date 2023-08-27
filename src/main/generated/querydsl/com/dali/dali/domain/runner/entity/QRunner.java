package com.dali.dali.domain.runner.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRunner is a Querydsl query type for Runner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRunner extends EntityPathBase<Runner> {

    private static final long serialVersionUID = 1700676922L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRunner runner = new QRunner("runner");

    public final com.dali.dali.domain.community.entity.QCommunity community;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> participation = createNumber("participation", Integer.class);

    public final NumberPath<Long> user_id = createNumber("user_id", Long.class);

    public QRunner(String variable) {
        this(Runner.class, forVariable(variable), INITS);
    }

    public QRunner(Path<? extends Runner> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRunner(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRunner(PathMetadata metadata, PathInits inits) {
        this(Runner.class, metadata, inits);
    }

    public QRunner(Class<? extends Runner> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.community = inits.isInitialized("community") ? new com.dali.dali.domain.community.entity.QCommunity(forProperty("community"), inits.get("community")) : null;
    }

}

