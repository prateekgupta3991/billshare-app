package bitspilani.goa.letsPlay.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BillResponseDto {

    private String billName;

    private Double amount;

    private Long grpId;

    private Map<Long, ContributionDto> userContriPaid;

    private Map<Long, ContributionDto> userContriOwe;

    public BillResponseDto() {
    }

    public BillResponseDto(String billName, Double amount, Long grpId) {
        this.billName = billName;
        this.amount = amount;
        this.grpId = grpId;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    public Map<Long, ContributionDto> getUserContriPaid() {
        return userContriPaid;
    }

    public void setUserContriPaid(Map<Long, ContributionDto> userContriPaid) {
        this.userContriPaid = userContriPaid;
    }

    public Map<Long, ContributionDto> getUserContriOwe() {
        return userContriOwe;
    }

    public void setUserContriOwe(Map<Long, ContributionDto> userContriOwe) {
        this.userContriOwe = userContriOwe;
    }

    @Override
    public String toString() {
        return "BillResponseDto{" +
                "billName='" + billName + '\'' +
                ", amount=" + amount +
                ", grpId=" + grpId +
                ", userContriPaid=" + userContriPaid +
                ", userContriOwe=" + userContriOwe +
                '}';
    }
}
