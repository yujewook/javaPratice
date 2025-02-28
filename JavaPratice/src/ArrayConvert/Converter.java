package ArrayConvert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Converter {

    /**
     * �������� List<String> �� DTO ��ȯ �޼���
     * @param dataList ���ڿ� ����Ʈ (ù ��° ���� ���)
     * @param mapper ��ȯ ������ �����ϴ� �Լ��� �������̽�
     * @param <T> ��ȯ�� DTO Ÿ��
     * @return ��ȯ�� DTO ����Ʈ
     */
    public <T> List<T> convertToDTOList(List<String[]> dataList, Function<String[], T> mapper) {
        List<T> resultList = new ArrayList<>();

        for (int i = 1; i < dataList.size(); i++) { // ��� ����
            T dto = mapper.apply(dataList.get(i)); // ��ȯ ���� ����
            if (dto != null) {
                resultList.add(dto);
            }
        }

        return resultList;
    }
}
