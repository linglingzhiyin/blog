package com.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    private String available;

    private String permission;

    @ManyToMany(mappedBy = "roles")
    private Set<SysUser> sysUsers = new HashSet<SysUser>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "role_permission"
            , joinColumns = @JoinColumn(name = "role_id"
            , referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "permission_id"
            , referencedColumnName = "id"))
    private Set<SysPermission> permissions = new HashSet<>();

}
