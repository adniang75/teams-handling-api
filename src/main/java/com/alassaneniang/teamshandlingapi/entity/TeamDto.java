package com.alassaneniang.teamshandlingapi.entity;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeamDto {

    private Long id;
    private String name;
    private String slogan;

    public TeamDto ( String name, String slogan ) {
        this.name = name;
        this.slogan = slogan;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        TeamDto that = (TeamDto) o;

        return Objects.equals( id, that.id );
    }

    @Override
    public int hashCode () {
        return id != null ? id.hashCode() : 0;
    }

}
