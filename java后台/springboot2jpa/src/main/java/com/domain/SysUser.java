package com.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class SysUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String username;

    private String password;

    private String locked;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role"
            , joinColumns = @JoinColumn(name = "user_id"
            , referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "role_id"
            , referencedColumnName = "id"))
    private Set<SysRole> roles = new HashSet<>();

}
