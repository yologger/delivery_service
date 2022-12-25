package com.delivery.app.repository.delivery;

import com.delivery.app.repository.account.Account;
import com.delivery.app.repository.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private DeliveryStatus status;

    @Column(name = "ordered_date")
    @CreatedDate
    private LocalDateTime orderedDate;

    @ManyToOne
    private Account customer;

    @OneToMany(mappedBy = "delivery")
    private List<DeliveryDetail> deliveryDetails = new ArrayList<>();

    @ManyToOne
    private Store store;

    @Builder
    public Delivery(Account customer) {
        this.status = DeliveryStatus.PAYMENT_COMPLETE;
        setCustomer(customer);
    }

    private void setCustomer(Account customer) {
        if (this.customer != null) {
            this.customer.getDeliveries().remove(this);
        }
        this.customer = customer;
        customer.getDeliveries().add(this);
    }

    public void updateStatus(DeliveryStatus status) {
        this.status = status;
    }
}
