package JavaOfStandard;

import java.util.ArrayList;
import java.util.List;

class UserDTO {
    
    // 필드
    private String name;
    private int age;
    private String email;

    // UserDTO 객체들의 리스트
    private List<UserDTO> list = new ArrayList<>();

    // 기본 생성자
    public UserDTO() {}

    // 모든 필드를 포함하는 생성자
    public UserDTO(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getter와 Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserDTO> getList() {
        return list;
    }

    public void setList(List<UserDTO> list) {
        this.list = list;
    }

    // toString 메서드 (디버깅이나 로깅용)
    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}



public class DTO_pratice {

	public static void main(String[] args) {
        // UserDTO 객체 생성
        UserDTO userDto = new UserDTO("John Doe", 30, "johndoe@example.com");

        // 리스트에 UserDTO 객체 추가
        UserDTO innerUser = new UserDTO("Jane Doe", 25, "janedoe@example.com");
        userDto.getList().add(innerUser);

        // 리스트의 첫 번째 요소 출력
        System.out.println("첫 번째 사용자 정보: " + userDto.getList().get(0));

        // 첫 번째 요소의 리스트에 추가된 다른 UserDTO 객체가 없으므로 빈 리스트가 출력될 것임
        System.out.println("첫 번째 사용자의 리스트: " + userDto.getList().get(0).getList());
	}

}
