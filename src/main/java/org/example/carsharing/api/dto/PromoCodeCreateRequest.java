package org.example.carsharing.api.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;

/*
Vhodnoi DTO sozdaniya promoCoda .
anotacii provaryayut obyazatelnie polya,AssertTrue -inavarianto intervala i procenta
 */
public class PromoCodeCreateRequest {
    @NotBlank
    //proverka chtob ot a / do y ot 0 - 9  _ \ - {3- eto min ,64-max}
    @Pattern(regexp = "[A-Z0-9_\\-]{3,64}")
    private String code;
    @NotNull
    private String discountType;
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal discountValue;
    @NotNull
    private String currency;
    //tolko polozhitelnie
    @Positive
    private Integer maxUsageCount;
    @NotNull
    @Future
    private Instant startAt;
    @NotNull
    //validaciya na vremya chtob proshloe ne napisal
    @Future
    private Instant endAt;

    @AssertTrue(message = "startAt must be before enadAt")
    public boolean isTimeRangeValid() {
        return startAt != null && endAt != null && startAt.isBefore(endAt);
    }

    @AssertTrue(message = "Percent value musst be between 1 and 100 for PERCENT")
    public boolean isPercentValid() {
        if (discountType == null || discountValue == null) return true;
        // tak kak on enum me sdelali ego v string i poluchaestxya equals
        if ("PERCENT".equals(discountType)) {
            //compare to sravnivaet zadannie parametri s nastoyashimi discount sravnivaet
            //tut mi govorim skidka ne menshe 1 i ne menshe i ne bilshe chem 100
            return discountValue.compareTo(new BigDecimal("1")) >= 0 &&
                    discountValue.compareTo(new BigDecimal("100")) <= 0;
        }

        return true;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public Integer getMaxUsageCount() {
        return maxUsageCount;
    }

    public void setMaxUsageCount(Integer maxUsageCount) {
        this.maxUsageCount = maxUsageCount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Instant getStartAt() {
        return startAt;
    }

    public void setStartAt(Instant startAt) {
        this.startAt = startAt;
    }

    public Instant getEndAt() {
        return endAt;
    }

    public void setEndAt(Instant endAt) {
        this.endAt = endAt;
    }
}
