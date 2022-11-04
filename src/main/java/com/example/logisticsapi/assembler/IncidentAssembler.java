package com.example.logisticsapi.assembler;

import com.example.logisticsapi.dtos.response.IncidentResponse;
import com.example.logisticsapi.model.Incident;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class IncidentAssembler {

    private ModelMapper modelMapper;

    public IncidentResponse toResponse(Incident incident){
        return modelMapper.map(incident, IncidentResponse.class);
    }

    public List<IncidentResponse> toIncidentList(List<Incident> incidentList){
        return incidentList.stream().map(this::toResponse).toList();
    }
}
