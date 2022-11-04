package com.example.logisticsapi.model;

import com.example.logisticsapi.enums.DeliveryStatus;
import com.example.logisticsapi.exceptions.BusinessException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID deliveryId;

    @ManyToOne
    private User user;

    @Embedded
    private Addressee addressee;

    private BigDecimal deliveryFee;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Incident> incidents = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private OffsetDateTime orderDate;

    private OffsetDateTime completionDate;

    public Incident addIncident(String description) {
        Incident incident = new Incident();
        incident.setDescription(description);
        incident.setRegistrationDate(OffsetDateTime.now());
        incident.setDelivery(this);
        this.getIncidents().add(incident);

        return incident;
    }

    public void complete() {
        if (cannotBeCompleted()){
            throw new BusinessException("Delivery could not be completed");
        }
        setDeliveryStatus(DeliveryStatus.COMPLETED);
        setCompletionDate(OffsetDateTime.now());
    }

    public boolean canBeCompleted(){
        return DeliveryStatus.PENDING.equals(getDeliveryStatus());
    }

    public boolean cannotBeCompleted(){
        return !canBeCompleted();
    }
}
