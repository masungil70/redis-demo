package kr.or.kosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosa.model.TestVo;
import kr.or.kosa.service.TestService;

@RestController
public class TestController {

    @Autowired
    TestService svc;

    /* 케시 어노테이션에 대한 설명 
       value = "TestVo" : 저장될 value로 API의 리턴 데이터인 TestVo 객체로 선언
			 key = "#id" : 이 API에서 id에 따라 응답값이 달라지므로 저장될 Key로 id 파라미터 값을 선언 
			 cacheManager = "cacheManager" : 위의 config에서 작성한 cacheManager 사용
			 unless = "#id == ''" : id가 "" 일때 캐시를 저장하지 않음
			 condition = "#id.length > 2" : id의 lengrh가 3 이상일 때만 캐시 저장
			 
			 아래 코드 실행 확인 
			 http://localhost:8080/getTest?id=masugnil
     */
    @Cacheable(value = "TestVo", key = "#id", cacheManager = "cacheManager", unless = "#id == ''", condition = "#id.length > 2")
    @GetMapping("/getTest")
    public TestVo getData(@RequestParam String id ){

        return svc.getTestSvc(id);
    }
}