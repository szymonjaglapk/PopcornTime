package com.popcorntime.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer type_id;

    @Column(name = "type_name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "types")
    private Set<User> users = new HashSet<>();
}
