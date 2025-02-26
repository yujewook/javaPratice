package FileManager;

public class FileDataRateInfoDTO {
	private String rateDate;
	private String rate;
	
	public String getRateDate() {
		return rateDate;
	}
	public void setRateDate(String income) {
		this.rateDate = income;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "FileDataRateInfoDTO [rateDate=" + rateDate + ", rate=" + rate + "]";
	}
}
