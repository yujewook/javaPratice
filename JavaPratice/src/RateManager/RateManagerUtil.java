package RateManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import FileManager.FileDataDTO;
import FileManager.FileDataRateInfoDTO;

public class RateManagerUtil {
	public List<RateCulDTO> calculateInterest(List<FileDataDTO> incomeInfo, List<FileDataRateInfoDTO> rateInfo) {
		List<RateCulDTO> outRateList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    // 금리 데이터를 날짜순 정렬
    rateInfo.sort(Comparator.comparing(FileDataRateInfoDTO::getRateDate));

    for (FileDataDTO income : incomeInfo) {
        String currentName = income.getName();
        String incomeDate = income.getIncomeDate();
        double currentAmount = Double.parseDouble(income.getIncome());

        // 입금일 이후 가장 가까운 금리를 찾음
        double applicableRate = getApplicableRate(rateInfo, incomeDate);

        // 입금일 이후 모든 이자율 변경 사항을 처리
        for (int i = 0; i < rateInfo.size(); i++) {
            FileDataRateInfoDTO rateData = rateInfo.get(i);
            String rateDate = rateData.getRateDate();
            double rate = Double.parseDouble(rateData.getRate());

            // 입금일 이전의 금리는 무시
            if (rateDate.compareTo(incomeDate) < 0) {
                continue;
            }

            // 다음 금리 변경 전날까지 적용
            String nextRateDate = (i + 1 < rateInfo.size()) ? getPreviousDate(rateInfo.get(i + 1).getRateDate()) : "99991231";

            // 적용 기간 계산
            int days = calculateDaysBetween(incomeDate, nextRateDate);
            double sumRateIncome = currentAmount * (1 + (applicableRate / 100) * (days / 365.0));

            // DTO 저장
            RateCulDTO dto = new RateCulDTO();
            dto.setName(currentName);
            dto.setIncomeDate(incomeDate);
            dto.setRateDate(nextRateDate);
            dto.setIncome(String.format("%.2f", currentAmount));
            dto.setDate(String.valueOf(days));
            dto.setRate(String.format("%.2f", applicableRate));
            dto.setSumRateIncome(String.format("%.2f", sumRateIncome));

            outRateList.add(dto);

            // 금액 갱신 및 날짜 이동
            currentAmount = sumRateIncome;
            incomeDate = nextRateDate;
            applicableRate = rate; // 새 금리 적용
        }
    }
    return outRateList;
}

// 입금일 이후 적용할 금리를 찾음
private double getApplicableRate(List<FileDataRateInfoDTO> rateInfo, String incomeDate) {
    for (FileDataRateInfoDTO rateData : rateInfo) {
        if (rateData.getRateDate().compareTo(incomeDate) >= 0) {
            return Double.parseDouble(rateData.getRate());
        }
    }
    return 0.0; // 기본값
}

// 날짜 차이 계산
private int calculateDaysBetween(String startDate, String endDate) {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        long diff = end.getTime() - start.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    } catch (ParseException e) {
        return 0;
    }
}

// 하루 전 날짜 구하기
private String getPreviousDate(String date) {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(date));
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return sdf.format(cal.getTime());
    } catch (ParseException e) {
        return date;
    }
}}
