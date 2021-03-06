package com.alassaneniang.teamshandlingapi.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity( name = "team" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeamEntity {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    private Long id;
    private String name;
    private String slogan;

    public TeamEntity ( String name, String slogan ) {
        this.name = name;
        this.slogan = slogan;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        TeamEntity that = (TeamEntity) o;

        return Objects.equals( id, that.id );
    }

    @Override
    public int hashCode () {
        return id != null ? id.hashCode() : 0;
    }
}
