package ArrayConvert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Converter {

    /**
     * 범용적인 List<String> → DTO 변환 메서드
     * @param dataList 문자열 리스트 (첫 번째 행은 헤더)
     * @param mapper 변환 로직을 정의하는 함수형 인터페이스
     * @param <T> 변환할 DTO 타입
     * @return 변환된 DTO 리스트
     */
    public <T> List<T> convertToDTOList(List<String[]> dataList, Function<String[], T> mapper) {
        List<T> resultList = new ArrayList<>();

        for (int i = 1; i < dataList.size(); i++) { // 헤더 제외
            T dto = mapper.apply(dataList.get(i)); // 변환 로직 적용
            if (dto != null) {
                resultList.add(dto);
            }
        }

        return resultList;
    }
}
