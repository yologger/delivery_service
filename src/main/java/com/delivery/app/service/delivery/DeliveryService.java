package com.delivery.app.service.delivery;

import com.delivery.app.repository.account.Account;
import com.delivery.app.repository.account.AccountRepository;
import com.delivery.app.repository.delivery.Delivery;
import com.delivery.app.repository.delivery.DeliveryDetail;
import com.delivery.app.repository.delivery.DeliveryRepository;
import com.delivery.app.service.auth.AccountNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    final private AccountRepository accountRepository;

    final private DeliveryRepository deliveryRepository;

    @Transactional(rollbackFor = Exception.class)
    public FindDeliveriesBetweenResult findDeliveriesByPeriod(Long id, LocalDateTime startDate, LocalDateTime endDate) throws InvalidPeriodException, AccountNotExistException {

        if (startDate.plusDays(3).isBefore(endDate))
            throw new InvalidPeriodException("Period must be less than or equal to 3 days.");

        Account customer = accountRepository.findById(id).orElseThrow(() -> new AccountNotExistException("Account not exists"));

        List<Delivery> deliveryList = deliveryRepository.findDeliveriesByCustomerAndOrderedDateBetween(customer, startDate, endDate);
        List<DeliveryData> deliveryDataList = new ArrayList<>();

        for (Delivery delivery : deliveryList) {
            List<MenuData> menuDataList = new ArrayList<>();
            Long totalPrice = 0L;
            for (DeliveryDetail deliveryDetail : delivery.getDeliveryDetails()) {
                totalPrice = totalPrice + (deliveryDetail.getCount() * deliveryDetail.getMenu().getPrice());
                MenuData menuData = MenuData.builder()
                        .name(deliveryDetail.getMenu().getName())
                        .count(deliveryDetail.getCount())
                        .price(deliveryDetail.getMenu().getPrice())
                        .build();
                menuDataList.add(menuData);
            }
            DeliveryData deliveryData = DeliveryData.builder()
                    .deliveryId(delivery.getId())
                    .orderedAt(delivery.getOrderedDate())
                    .customerAddress(delivery.getCustomer().getAddress())
                    .storeName(delivery.getStore().getName())
                    .menus(menuDataList)
                    .totalPrice(totalPrice)
                    .build();
            deliveryDataList.add(deliveryData);
        }
        return FindDeliveriesBetweenResult.builder().deliveries(deliveryDataList).build();
    }
}
