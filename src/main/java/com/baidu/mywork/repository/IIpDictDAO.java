package com.baidu.mywork.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baidu.mywork.entity.IpDictDB;

@Repository
public interface IIpDictDAO extends PagingAndSortingRepository<IpDictDB, Long> {

    @Query("FROM IpDictDB t WHERE t.name LIKE :name ORDER BY t.name")
    Page<IpDictDB> search(@Param("name") String name, Pageable pageable);

}
