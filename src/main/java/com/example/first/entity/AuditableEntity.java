package com.example.first.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@MappedSuperclass
public abstract class AuditableEntity {

    @Column(name = "CSTCL_EFCTV_DT")
    private LocalDate effectiveDate;

    @Column(name = "CSTCL_HOST_TS")
    private LocalDateTime hostTimestamp;

    @Column(name = "CSTCL_LOCAL_TS")
    private LocalDateTime localTimestamp;

    @Column(name = "CSTCL_ACPT_TS")
    private LocalDateTime acceptanceTimestamp;

    @Column(name = "CSTCL_ACPT_TS_UTC_OFST", length = 10)
    private String acceptanceTimestampUtcOffset;

    @Column(name = "CSTCL_USER_ID", length = 50)
    private String userId;

    @Column(name = "CSTCL_WS_ID", length = 50)
    private String workstationId;

    @Column(name = "CSTCL_PRGM_ID", length = 50)
    private String programId;

    @Column(name = "CSTCL_CRUD_VALUE", length = 1)
    private String crudValue;

    @Column(name = "CSTCL_UUID", updatable = false, length = 36)
    private String uuid;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.hostTimestamp = now;
        this.localTimestamp = now;
        this.acceptanceTimestamp = now;
        this.acceptanceTimestampUtcOffset = ZoneOffset.systemDefault().getRules().getOffset(now).toString();
        this.userId = "SYSTEM_USER";
        this.workstationId = "SERVER_NODE_1";
        this.programId = "CUSTOMER_MANAGEMENT_APP";
        this.crudValue = "C";
        this.uuid = UUID.randomUUID().toString();
        
        if (this.effectiveDate == null) {
            this.effectiveDate = LocalDate.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.hostTimestamp = now;
        this.localTimestamp = now;
        this.acceptanceTimestamp = now;
        this.acceptanceTimestampUtcOffset = ZoneOffset.systemDefault().getRules().getOffset(now).toString();
        this.crudValue = "U";
    }

    public LocalDate getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

    public LocalDateTime getHostTimestamp() { return hostTimestamp; }
    public void setHostTimestamp(LocalDateTime hostTimestamp) { this.hostTimestamp = hostTimestamp; }

    public LocalDateTime getLocalTimestamp() { return localTimestamp; }
    public void setLocalTimestamp(LocalDateTime localTimestamp) { this.localTimestamp = localTimestamp; }

    public LocalDateTime getAcceptanceTimestamp() { return acceptanceTimestamp; }
    public void setAcceptanceTimestamp(LocalDateTime acceptanceTimestamp) { this.acceptanceTimestamp = acceptanceTimestamp; }

    public String getAcceptanceTimestampUtcOffset() { return acceptanceTimestampUtcOffset; }
    public void setAcceptanceTimestampUtcOffset(String acceptanceTimestampUtcOffset) { this.acceptanceTimestampUtcOffset = acceptanceTimestampUtcOffset; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getWorkstationId() { return workstationId; }
    public void setWorkstationId(String workstationId) { this.workstationId = workstationId; }

    public String getProgramId() { return programId; }
    public void setProgramId(String programId) { this.programId = programId; }

    public String getCrudValue() { return crudValue; }
    public void setCrudValue(String crudValue) { this.crudValue = crudValue; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
}
