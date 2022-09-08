package ru.javaops.topjava.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Menu extends BaseEntity {

    @Column(name = "date_menu", nullable = false, unique = true)
    private LocalDate date_menu;

    @OneToMany(mappedBy = "menu")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Dish> dishes;

    public Menu(Integer id, LocalDate date_menu) {
        super(id);
        this.date_menu = date_menu;
    }

    @Override
    public String toString() {
        return "Menu:" + id + '[' + date_menu + ']';
    }
}
