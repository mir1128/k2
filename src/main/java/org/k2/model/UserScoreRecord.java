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

    @Column(name = "max_score")
    private int  scoreMax;

    @Column(name = "max_score_created_at")
    private Date maxScoreCreatedAt;

    public UserScoreRecord() {
    }

    public UserScoreRecord(User user, int score) {
        this.user = user;
        this.score = score;
        this.updateAt = new Date();
        this.maxScoreCreatedAt = new Date();
        this.scoreMax = 0;
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

    public int getScoreMax() {
        return scoreMax;
    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

    public Date getMaxScoreCreatedAt() {
        return maxScoreCreatedAt;
    }

    public void setMaxScoreCreatedAt(Date maxScoreCreatedAt) {
        this.maxScoreCreatedAt = maxScoreCreatedAt;
    }

    public void updateMaxScore(int increase) {
        if (score + increase > scoreMax) {
            scoreMax = score + increase;
            maxScoreCreatedAt = new Date();
        }
    }
}
