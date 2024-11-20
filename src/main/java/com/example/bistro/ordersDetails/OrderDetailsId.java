package com.example.bistro.ordersDetails;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class OrderDetailsId  implements Serializable {

    private Integer ordersId;
    private Integer menuId;

    public OrderDetailsId() {}

    public OrderDetailsId(Integer ordersId, Integer menuId) {
        super();
        this.ordersId = ordersId;
        this.menuId = menuId;
    }

    // hashCode 和 equals 方法，用於確保主鍵比較正確
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderDetailsId that = (OrderDetailsId) o;
            return Objects.equals(ordersId, that.ordersId) && Objects.equals(menuId, that.menuId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ordersId, menuId);
        }
}
