package com.alassaneniang.teamshandlingapi.repository;

import com.alassaneniang.teamshandlingapi.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}
