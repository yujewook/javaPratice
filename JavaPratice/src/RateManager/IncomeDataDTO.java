package RateManager;

public class IncomeDataDTO {
	private String name;
	private String incomeDate;
	private String income;
	
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
	
	//확인용 메소드
	@Override
	public String toString() {
		return "FileDataDTO [name=" + name + ", incomeDate=" + incomeDate + ", income=" + income + "]";
	}
}
