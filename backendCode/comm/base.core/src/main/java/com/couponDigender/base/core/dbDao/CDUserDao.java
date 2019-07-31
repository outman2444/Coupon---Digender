package com.couponDigender.base.core.dbDao;

import com.couponDigender.base.core.dbModal.CDUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDUserDao extends JpaRepository<CDUser, Long>{
}
