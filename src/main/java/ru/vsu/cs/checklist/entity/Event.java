package ru.vsu.cs.checklist.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "type")
    private String type;

    @Column(name = "comment")
    private String comment;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "is_checklist")
    private boolean isChecklist;

    @Column(name = "new_value")
    private String newValue;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "created")
    @Generated(GenerationTime.INSERT)
    private ZonedDateTime created;

}
