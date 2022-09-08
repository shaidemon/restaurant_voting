package ru.daemon75.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vote")
@NoArgsConstructor
@Getter
@Setter
public class Vote extends BaseEntity {

    @Column(name = "date_vote", nullable = false)
    private LocalDate date_vote;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    public Vote(Integer id, LocalDate date_vote, Restaurant restaurant) {
        super(id);
        this.date_vote = date_vote;
        this.restaurant = restaurant;
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
