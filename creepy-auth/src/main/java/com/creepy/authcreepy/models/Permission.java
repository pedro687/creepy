package com.creepy.authcreepy.models;

import com.creepy.authcreepy.models.enums.PermissionsEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
@Data
@EqualsAndHashCode
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionsEnum description;
}