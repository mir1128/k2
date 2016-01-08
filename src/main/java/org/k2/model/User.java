package org.k2.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name")
    String name;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.REMOVE})
    private UserBoardStatus userBoardStatus;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.REMOVE})
    private UserScoreRecord userScoreRecord;

    public User() {
    }

    public User(String name, Date createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserBoardStatus getUserBoardStatus() {
        return userBoardStatus;
    }

    public void setUserBoardStatus(UserBoardStatus userBoardStatus) {
        this.userBoardStatus = userBoardStatus;
    }

    public UserScoreRecord getUserScoreRecord() {
        return userScoreRecord;
    }

    public void setUserScoreRecord(UserScoreRecord userScoreRecord) {
        this.userScoreRecord = userScoreRecord;
    }
}
