package com.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class SysPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String url;

    private String percode;

    private Long parentid;

    private String parentids;

    private String sortstring;

    private String available;


    @ManyToMany(mappedBy = "permissions")
    private Set<SysRole> publishers = new HashSet<>();

}