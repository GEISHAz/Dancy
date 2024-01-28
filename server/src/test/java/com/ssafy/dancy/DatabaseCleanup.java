package com.ssafy.dancy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DatabaseCleanup {

    @PersistenceContext
    private EntityManager entityManager;

    private final Set<String> tables = new HashSet<>();

    public void init() {
        List<Object[]> tableInfos = entityManager.createNativeQuery("SHOW TABLES").getResultList();
        for (Object[] tableInfo : tableInfos) {
            String tableName = (String) tableInfo[0];
            tables.add(tableName);
        }
    }

    @Transactional
    public void truncateAllTables(){
        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for(String tableName : tables){
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName + " RESTART IDENTITY").executeUpdate();
        }

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}