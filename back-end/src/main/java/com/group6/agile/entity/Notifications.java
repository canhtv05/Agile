package com.group6.agile.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notifications extends AbstractEntity<String> implements Serializable {

    private String title;

    private String content;

    private Boolean isRead = false;

    private Boolean isGlobal = false;

    private String userId;
}
