package ru.vsu.cs.checklist.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "done")
    private boolean done;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "deleted")
    private boolean deleted;

}
