package com.group6.agile.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends AbstractEntity<String> implements Serializable {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<UserHasRole> users = new HashSet<>();
}
