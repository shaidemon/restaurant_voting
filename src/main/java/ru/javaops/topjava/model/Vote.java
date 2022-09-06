package ru.javaops.topjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "vote")
@NoArgsConstructor
@Getter
@Setter
public class Vote extends BaseEntity{

    @Column(name = "date_vote", nullable = false)
    private Date date_vote;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Vote(Integer id, Date date_vote) {
        super(id);
        this.date_vote = date_vote;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "Date:" + date_vote +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
