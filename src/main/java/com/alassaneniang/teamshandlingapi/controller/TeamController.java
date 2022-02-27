package com.alassaneniang.teamshandlingapi.controller;

import com.alassaneniang.teamshandlingapi.entity.TeamDto;
import com.alassaneniang.teamshandlingapi.entity.TeamEntity;
import com.alassaneniang.teamshandlingapi.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/v1/teams" )
public class TeamController {

    private final TeamService teamService;
    private final ModelMapper modelMapper;

    @GetMapping( { "", "/" } )
    @ResponseBody
    public List<TeamDto> getTeams () {
        List<TeamEntity> allTeams = teamService.getAllTeams();
        return allTeams.stream()
                .map( this::convertToDto )
                .collect( Collectors.toList() );
    }

    @GetMapping( "/{id}" )
    @ResponseBody
    public TeamDto getTeamById ( @PathVariable Long id ) {
        return convertToDto( teamService.getTeamById( id ) );
    }

    @PostMapping( { "", "/" } )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public TeamDto createTeam ( @RequestBody TeamDto teamDto ) {
        TeamEntity teamEntity = convertToEntity( teamDto );
        TeamEntity createdTeam = teamService.createTeam( teamEntity );
        return convertToDto( createdTeam );
    }

    @PutMapping( "/{id}" )
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public TeamDto updateTeam ( @RequestBody TeamDto teamDto, @PathVariable Long id ) {
        if ( !Objects.equals( id, teamDto.getId() ) ) {
            throw new IllegalArgumentException( "IDs don't match" );
        }
        TeamEntity teamEntity = convertToEntity( teamDto );
        return convertToDto( teamService.updateTeam( teamEntity, id ) );
    }

    @DeleteMapping( "/{id}" )
    @ResponseStatus( HttpStatus.OK )
    public void deleteTeam ( @PathVariable Long id ) {
        teamService.deleteTeam( id );
    }

    private TeamDto convertToDto ( TeamEntity teamEntity ) {
        return modelMapper.map( teamEntity, TeamDto.class );
    }

    private TeamEntity convertToEntity ( TeamDto teamDto ) {
        TeamEntity teamEntity = modelMapper.map( teamDto, TeamEntity.class );
        if ( teamDto.getId() != null ) {
            TeamEntity oldTeamEntity = teamService.getTeamById( teamDto.getId() );
            teamEntity.setId( oldTeamEntity.getId() );
        }
        return teamEntity;
    }

}
