package com.alassaneniang.teamshandlingapi.service;

import com.alassaneniang.teamshandlingapi.entity.TeamEntity;
import com.alassaneniang.teamshandlingapi.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public List<TeamEntity> getAllTeams () {
        return teamRepository.findAll();
    }

    public TeamEntity getTeamById ( Long id ) {
        return teamRepository.findById( id ).orElse( null );
    }

    public TeamEntity createTeam ( TeamEntity team ) {
        return teamRepository.save( team );
    }

    public TeamEntity updateTeam ( TeamEntity team, Long id ) {
        return teamRepository.findById( id ).map( t -> {
                    t.setName( team.getName() );
                    t.setSlogan( team.getSlogan() );
                    return teamRepository.save( t );
                } )
                .orElseGet( () -> {
                    team.setId( id );
                    return teamRepository.save( team );
                } );
    }

    public void deleteTeam ( Long id ) {
        teamRepository.deleteById( id );
    }

}