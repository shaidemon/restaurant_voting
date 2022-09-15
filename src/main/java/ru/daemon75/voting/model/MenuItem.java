package ru.daemon75.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "menu_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class MenuItem extends NamedEntity {

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    private LocalDate dateMenu;

    public MenuItem(Integer id, String name, Integer price, LocalDate dateMenu) {
        super(id, name);
        this.price = price;
//        this.restaurant = restaurant;
        this.dateMenu = dateMenu;
    }

    @Override
    public String toString() {
        return "MenuItem:" + id + '[' + name + ", price=" + price + ']';
    }
}
