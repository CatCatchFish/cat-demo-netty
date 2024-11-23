package cn.cat.netty.es.service;

import cn.cat.netty.es.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IUserService {
    void save(User user);

    void deleteById(String id);

    User queryUserById(String id);

    Iterable<User> queryAll();

    Page<User> findByName(String name, PageRequest request);
}
