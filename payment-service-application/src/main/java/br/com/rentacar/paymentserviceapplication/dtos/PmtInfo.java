package br.com.rentacar.paymentserviceapplication.dtos;

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
