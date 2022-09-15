package ru.daemon75.voting.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Menu extends BaseEntity {

    @Column(name = "date_menu", nullable = false)
    private LocalDate date_menu;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Menu(Integer id, LocalDate date_menu, Restaurant restaurant) {
        super(id);
        this.date_menu = date_menu;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Menu:" + id + '[' + date_menu + ']';
    }
}
