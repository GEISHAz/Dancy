package com.ssafy.dancy.repository.test;

import com.ssafy.dancy.entity.TesterEntity;

import java.util.List;

public interface TestCustomRepository {
    List<TesterEntity> testFind(String name);
}
