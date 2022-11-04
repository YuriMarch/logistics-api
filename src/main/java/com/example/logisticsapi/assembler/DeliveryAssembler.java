package com.example.logisticsapi.assembler;

import com.example.logisticsapi.dtos.input.DeliveryInput;
import com.example.logisticsapi.dtos.response.DeliveryResponse;
import com.example.logisticsapi.model.Delivery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class DeliveryAssembler {

    private ModelMapper modelMapper;
    public DeliveryResponse toResponse(Delivery delivery){
        return modelMapper.map(delivery, DeliveryResponse.class);
    }

    public List<DeliveryResponse> toResponseList(List<Delivery> deliveryList){
        return deliveryList.stream().map(this::toResponse).toList();
    }

    public Delivery toEntity(DeliveryInput deliveryInput){
        return modelMapper.map(deliveryInput, Delivery.class);
    }
}
