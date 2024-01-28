package com.ssafy.dancy.service;

import com.ssafy.dancy.entity.TesterEntity;
import com.ssafy.dancy.message.request.TestSaveRequest;
import com.ssafy.dancy.repository.test.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;


    public List<TesterEntity> getTestUsingName(String name){
        return testRepository.testFind(name);
    }

    public void saveTestEntity(TestSaveRequest request){
        TesterEntity entity = TesterEntity.builder()
                .name(request.name())
                .address(request.address())
                .build();

        TesterEntity save = testRepository.save(entity);
        System.out.println(save);
    }

    public List<TesterEntity> getTestUsingAddress(String address) {
        return testRepository.findAllByAddressContaining(address);
    }
}
