package com.secure.mandateapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CustomerRes {

    private int customerId;

    private String customerRef;

    private String customerName;

    private String status;
}
