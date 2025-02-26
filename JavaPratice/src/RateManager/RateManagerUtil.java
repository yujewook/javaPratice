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

    // �ݸ� �����͸� ��¥�� ����
    rateInfo.sort(Comparator.comparing(FileDataRateInfoDTO::getRateDate));

    for (FileDataDTO income : incomeInfo) {
        String currentName = income.getName();
        String incomeDate = income.getIncomeDate();
        double currentAmount = Double.parseDouble(income.getIncome());

        // �Ա��� ���� ���� ����� �ݸ��� ã��
        double applicableRate = getApplicableRate(rateInfo, incomeDate);

        // �Ա��� ���� ��� ������ ���� ������ ó��
        for (int i = 0; i < rateInfo.size(); i++) {
            FileDataRateInfoDTO rateData = rateInfo.get(i);
            String rateDate = rateData.getRateDate();
            double rate = Double.parseDouble(rateData.getRate());

            // �Ա��� ������ �ݸ��� ����
            if (rateDate.compareTo(incomeDate) < 0) {
                continue;
            }

            // ���� �ݸ� ���� �������� ����
            String nextRateDate = (i + 1 < rateInfo.size()) ? getPreviousDate(rateInfo.get(i + 1).getRateDate()) : "99991231";

            // ���� �Ⱓ ���
            int days = calculateDaysBetween(incomeDate, nextRateDate);
            double sumRateIncome = currentAmount * (1 + (applicableRate / 100) * (days / 365.0));

            // DTO ����
            RateCulDTO dto = new RateCulDTO();
            dto.setName(currentName);
            dto.setIncomeDate(incomeDate);
            dto.setRateDate(nextRateDate);
            dto.setIncome(String.format("%.2f", currentAmount));
            dto.setDate(String.valueOf(days));
            dto.setRate(String.format("%.2f", applicableRate));
            dto.setSumRateIncome(String.format("%.2f", sumRateIncome));

            outRateList.add(dto);

            // �ݾ� ���� �� ��¥ �̵�
            currentAmount = sumRateIncome;
            incomeDate = nextRateDate;
            applicableRate = rate; // �� �ݸ� ����
        }
    }
    return outRateList;
}

// �Ա��� ���� ������ �ݸ��� ã��
private double getApplicableRate(List<FileDataRateInfoDTO> rateInfo, String incomeDate) {
    for (FileDataRateInfoDTO rateData : rateInfo) {
        if (rateData.getRateDate().compareTo(incomeDate) >= 0) {
            return Double.parseDouble(rateData.getRate());
        }
    }
    return 0.0; // �⺻��
}

// ��¥ ���� ���
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

// �Ϸ� �� ��¥ ���ϱ�
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
