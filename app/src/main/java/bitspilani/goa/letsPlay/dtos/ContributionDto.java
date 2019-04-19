package bitspilani.goa.letsPlay.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContributionDto {

    private Double shareAmount;

    private Double sharePercentage;

    public ContributionDto() {
    }

    public ContributionDto(Double shareAmount, Double sharePercentage) {
        this.shareAmount = shareAmount;
        this.sharePercentage = sharePercentage;
    }

    public Double getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(Double shareAmount) {
        this.shareAmount = shareAmount;
    }

    public Double getSharePercentage() {
        return sharePercentage;
    }

    public void setSharePercentage(Double sharePercentage) {
        this.sharePercentage = sharePercentage;
    }

    @Override
    public String toString() {
        return "ContributionDto{" +
                "shareAmount=" + shareAmount +
                ", sharePercentage=" + sharePercentage +
                '}';
    }
}
