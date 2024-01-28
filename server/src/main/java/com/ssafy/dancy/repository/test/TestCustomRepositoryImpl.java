package com.ssafy.dancy.repository.test;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.dancy.entity.TesterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.dancy.entity.QTesterEntity.*;

@Repository
@RequiredArgsConstructor
public class TestCustomRepositoryImpl implements TestCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<TesterEntity> testFind(String name) {
        return jpaQueryFactory.selectFrom(testerEntity)
                .where(testerEntity.name.eq(name))
                .fetch();
    }
}
