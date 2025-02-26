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

        // �ݸ� �����͸� ��¥������ ����
        rateInfo.sort(Comparator.comparing(FileDataRateInfoDTO::getRateDate));

        for (FileDataDTO income : incomeInfo) {
            String currentName = income.getName();
            String incomeDate = income.getIncomeDate();
            double currentAmount = Double.parseDouble(income.getIncome());

            double applicableRate = 0.0;
            String prevRateDate = incomeDate; // ���� ��¥ �ʱ�ȭ

            // �Ա��� ������ �ݸ� ã��
            applicableRate = getPreviousApplicableRate(rateInfo, incomeDate);

            // �ݸ� ���� ���� ó��
            for (int i = 0; i < rateInfo.size(); i++) {
                FileDataRateInfoDTO rateData = rateInfo.get(i);
                String rateDate = rateData.getRateDate();

                // �Ա��� ������ �ݸ��� �ǳʶ�
                if (rateDate.compareTo(incomeDate) <= 0) {
                    continue;
                }

                // �ݸ� ���� �Ⱓ ���: ���� �ݸ� �������� ���� �ݸ� ������ ����
                String nextRateDate = getPreviousDate(rateDate);

                // ���� �Ⱓ�� �Ա��� �����̶�� �ǳʶ�
                if (nextRateDate.compareTo(incomeDate) < 0) {
                    continue;
                }

                // ���� �ϼ� ���
                int days = calculateDaysBetween(prevRateDate, nextRateDate);

                // ���� ���: (�ݾ� * (1 + �ݸ�% * (�ϼ� / 365)))
                double sumRateIncome = currentAmount * (1 + (applicableRate / 100) * (days / 365.0));

                // DTO ����
                RateCulDTO dto = new RateCulDTO();
                dto.setName(currentName);
                dto.setIncomeDate(prevRateDate); // �Ⱓ ������
                dto.setRateDate(nextRateDate);   // �Ⱓ ������
                dto.setIncome(String.format("%.2f", currentAmount));
                dto.setDate(String.valueOf(days));
                dto.setRate(String.format("%.2f", applicableRate));
                dto.setSumRateIncome(new BigDecimal(sumRateIncome).setScale(0, RoundingMode.HALF_UP).toString()); // �Ҽ��� �ݿø�
                outRateList.add(dto);

                // �ݾ� �� ��¥ ����
                currentAmount = sumRateIncome;
                prevRateDate = rateDate; // ���� �ݸ� �����Ϸ� �̵�
                applicableRate = Double.parseDouble(rateData.getRate()); // �� �ݸ� ����
            }

            // ������ �ݸ� ���� (���� �ݸ� ���� ������ ����Ǵ� ���)
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

    // �Ա��� ������ �ݸ��� ã��
    private double getPreviousApplicableRate(List<FileDataRateInfoDTO> rateInfo, String incomeDate) {
        for (int i = rateInfo.size() - 1; i >= 0; i--) {
            FileDataRateInfoDTO rateData = rateInfo.get(i);
            if (rateData.getRateDate().compareTo(incomeDate) <= 0) {
                return Double.parseDouble(rateData.getRate());
            }
        }
        return 0.0; // �⺻��
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
    }

    // ��¥ ���� ���
    private int calculateDaysBetween(String startDate, String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            long diff = end.getTime() - start.getTime();
            return (int) (diff / (1000 * 60 * 60 * 24)); // ������ ����
        } catch (ParseException e) {
            return 0;
        }
    }
}