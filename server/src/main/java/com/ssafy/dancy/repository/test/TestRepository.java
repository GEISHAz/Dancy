package com.ssafy.dancy.repository.test;

import com.ssafy.dancy.entity.TesterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TesterEntity, Long>, TestCustomRepository {

    List<TesterEntity> findAllByAddressContaining(String address);
}
