package com.delivery.app.repository.delivery;

import com.delivery.app.repository.menu.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class DeliveryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int count;

    @ManyToOne
    private Delivery delivery;

    @OneToOne
    private Menu menu;

    public DeliveryDetail(int count, Delivery delivery, Menu menu) {
        this.count = count;
        this.delivery = delivery;
        this.menu = menu;
    }

    public void setDelivery(Delivery delivery) {
        if (this.delivery != null) {
            this.delivery.getDeliveryDetails().remove(this);
        }
        this.delivery = delivery;
        delivery.getDeliveryDetails().add(this);
    }
}

