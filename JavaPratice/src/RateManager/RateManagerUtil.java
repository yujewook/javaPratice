package RateManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        // 금리 데이터를 날짜순으로 정렬
        rateInfo.sort(Comparator.comparing(FileDataRateInfoDTO::getRateDate));

        for (FileDataDTO income : incomeInfo) {
            String currentName = income.getName();
            String incomeDate = income.getIncomeDate();
            double currentAmount = Double.parseDouble(income.getIncome());

            double applicableRate = 0.0;
            String prevRateDate = incomeDate; // 시작 날짜 초기화

            // 입금일 이전의 금리 찾기
            applicableRate = getPreviousApplicableRate(rateInfo, incomeDate);

            // 금리 변경 사항 처리
            for (int i = 0; i < rateInfo.size(); i++) {
                FileDataRateInfoDTO rateData = rateInfo.get(i);
                String rateDate = rateData.getRateDate();

                // 입금일 이전의 금리는 건너뜀
                if (rateDate.compareTo(incomeDate) <= 0) {
                    continue;
                }

                // 금리 적용 기간 계산: 이전 금리 종료일은 다음 금리 시작일 전날
                String nextRateDate = getPreviousDate(rateDate);

                // 적용 기간이 입금일 이전이라면 건너뜀
                if (nextRateDate.compareTo(incomeDate) < 0) {
                    continue;
                }

                // 적용 일수 계산
                int days = calculateDaysBetween(prevRateDate, nextRateDate);

                // 이자 계산: (금액 * (1 + 금리% * (일수 / 365)))
                double sumRateIncome = currentAmount * (1 + (applicableRate / 100) * (days / 365.0));

                // DTO 저장
                RateCulDTO dto = new RateCulDTO();
                dto.setName(currentName);
                dto.setIncomeDate(prevRateDate); // 기간 시작일
                dto.setRateDate(nextRateDate);   // 기간 종료일
                dto.setIncome(String.format("%.2f", currentAmount));
                dto.setDate(String.valueOf(days));
                dto.setRate(String.format("%.2f", applicableRate));
                dto.setSumRateIncome(new BigDecimal(sumRateIncome).setScale(0, RoundingMode.HALF_UP).toString()); // 소수점 반올림
                outRateList.add(dto);

                // 금액 및 날짜 갱신
                currentAmount = sumRateIncome;
                prevRateDate = rateDate; // 다음 금리 시작일로 이동
                applicableRate = Double.parseDouble(rateData.getRate()); // 새 금리 적용
            }

            // 마지막 금리 적용 (최종 금리 이후 무한정 적용되는 경우)
            String finalEndDate = "99991231";
            int finalDays = calculateDaysBetween(prevRateDate, finalEndDate);
            double finalSumRateIncome = currentAmount * (1 + (applicableRate / 100) * (finalDays / 365.0));

            RateCulDTO finalDto = new RateCulDTO();
            finalDto.setName(currentName);
            finalDto.setIncomeDate(prevRateDate);
            finalDto.setRateDate(finalEndDate);
            finalDto.setIncome(String.format("%.2f", currentAmount));
            finalDto.setDate(String.valueOf(finalDays));
            finalDto.setRate(String.format("%.2f", applicableRate));
            finalDto.setSumRateIncome(String.format("%.2f", finalSumRateIncome));
            outRateList.add(finalDto);
        }

        return outRateList;
    }

    // 입금일 이전의 금리를 찾음
    private double getPreviousApplicableRate(List<FileDataRateInfoDTO> rateInfo, String incomeDate) {
        for (int i = rateInfo.size() - 1; i >= 0; i--) {
            FileDataRateInfoDTO rateData = rateInfo.get(i);
            if (rateData.getRateDate().compareTo(incomeDate) <= 0) {
                return Double.parseDouble(rateData.getRate());
            }
        }
        return 0.0; // 기본값
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
    }

    // 날짜 차이 계산
    private int calculateDaysBetween(String startDate, String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            long diff = end.getTime() - start.getTime();
            return (int) (diff / (1000 * 60 * 60 * 24)); // 시작일 포함
        } catch (ParseException e) {
            return 0;
        }
    }
}