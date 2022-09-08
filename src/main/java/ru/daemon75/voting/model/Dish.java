package ru.daemon75.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dish")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Dish extends NamedEntity {

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "date_menu")
    @JsonIgnore
    private Menu menu;

    public Dish(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
//        this.restaurant = restaurant;
//        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish:" + id + '[' + name + ", price=" + price + ",Menu date:" + getMenu().getDate_menu() + ']';
    }
}
