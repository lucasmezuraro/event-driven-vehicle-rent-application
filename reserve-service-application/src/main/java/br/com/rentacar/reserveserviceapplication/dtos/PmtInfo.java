package br.com.rentacar.reserveserviceapplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PmtInfo {
    String name;
    String cpf;
    String number;
    String yearValid;
}
