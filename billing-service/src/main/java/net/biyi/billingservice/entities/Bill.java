package net.biyi.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.biyi.billingservice.model.Customer;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue
    private Long id;
    private Date billingDate;
    private Long customerId;
    @OneToMany(mappedBy = "bill")

    private List<ProductItem> productItems;
    @Transient
    private Customer customer;
}
