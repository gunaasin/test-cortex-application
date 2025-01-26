package com.clone.amazon.delivery;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DeliveryInfo {

    LocalDate tomorrow = LocalDate.now().plusDays(1);
    LocalDate fourDaysAfter = LocalDate.now().plusDays(4);
    LocalDate sevenDaysAfter = LocalDate.now().plusDays(7);

    final int deliveryChargeTomorrow = 249;
    final int deliveryChargeFourDaysAfter = 99;
    final int deliveryChargeSevenDaysAfter= 0;

    public int getDeliveryCharge(int deliveryOptionId){
        if(deliveryOptionId==3){
            return deliveryChargeTomorrow;
        }else if (deliveryOptionId==2){
            return deliveryChargeFourDaysAfter;
        }else{
            return deliveryChargeSevenDaysAfter;
        }
    }
}
