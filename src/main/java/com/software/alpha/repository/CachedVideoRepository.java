package com.software.alpha.repository;

import com.software.alpha.model.CachedVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CachedVideoRepository extends JpaRepository<CachedVideo, Long> {



}
