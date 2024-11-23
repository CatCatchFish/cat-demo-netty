package cn.cat.netty.es.service;

import cn.cat.netty.es.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String> {

    Page<User> findByName(String name, Pageable pageable);

}
