package org.k2.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_score_record")
public class UserScoreRecord {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "score")
    private int  score;

    @Column(name = "update_at")
    private Date updateAt;

    public UserScoreRecord() {
    }

    public UserScoreRecord(User user, int score) {
        this.user = user;
        this.score = score;
        this.updateAt = new Date();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
