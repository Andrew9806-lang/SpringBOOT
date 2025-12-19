package org.example.carsharing.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "promo_codes")
public class PromoCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", nullable = false, length = 64)
    // code promocoda
    private String code;
    @Enumerated(EnumType.STRING)
    // eto libo procent libo summa
    private DiscountType discountType;
    @Column(nullable = false, precision = 10, scale = 2)
    //summa skidki
    private BigDecimal iscountvalue;
    //???
    @Column(nullable = false, length = 3)
    //valuta
    private String currency;
    @Column(name = "used_count", nullable = false)
    //kolichestvo ispolzovaniya kupona
    private Integer usedCount = 0;
    @Column(name = "max_usage_count", nullable = false)
    private Integer maxUsageCount;
    @Column(name = "start_at", nullable = false)
    private Instant startAt;
    @Column(name = "end_at", nullable = false)
    private Instant endAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PromoCodeStatus status;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @PrePersist
    public void onCreate() {
        if (createdAt == null) {

            createdAt = Instant.now();
        }

    }

    public Integer getMaxUsageCount() {
        return maxUsageCount;
    }

    public void setMaxUsageCount(Integer maxUsageCount) {
        this.maxUsageCount = maxUsageCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getIscountvalue() {
        return iscountvalue;
    }

    public void setIscountvalue(BigDecimal iscountvalue) {
        this.iscountvalue = iscountvalue;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
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

    public PromoCodeStatus getStatus() {
        return status;
    }

    public void setStatus(PromoCodeStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
