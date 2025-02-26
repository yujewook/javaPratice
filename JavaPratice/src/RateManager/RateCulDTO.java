package RateManager;

public class RateCulDTO {
	private String name;
	private String incomeDate;
	private String rateDate;
	private String income;
	private String date;
	private String rate;
	private String sumRateIncome;
	
	public String getSumRateIncome() {
		return sumRateIncome;
	}
	public void setSumRateIncome(String sumRateIncome) {
		this.sumRateIncome = sumRateIncome;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIncomeDate() {
		return incomeDate;
	}
	public void setIncomeDate(String incomeDate) {
		this.incomeDate = incomeDate;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getRateDate() {
		return rateDate;
	}
	public void setRateDate(String rateDate) {
		this.rateDate = rateDate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "RateCulDTO [name=" + name + ", incomeDate=" + incomeDate + ", rateDate=" + rateDate + ", income="
				+ income + ", date=" + date + ", rate=" + rate + ", sumRateIncome=" + sumRateIncome + "]";
	}
}
