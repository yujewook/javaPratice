package JavaOfStandard;

import java.util.ArrayList;
import java.util.List;

class UserDTO {
    
    // �ʵ�
    private String name;
    private int age;
    private String email;

    // UserDTO ��ü���� ����Ʈ
    private List<UserDTO> list = new ArrayList<>();

    // �⺻ ������
    public UserDTO() {}

    // ��� �ʵ带 �����ϴ� ������
    public UserDTO(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getter�� Setter
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

    // toString �޼��� (������̳� �α��)
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
        // UserDTO ��ü ����
        UserDTO userDto = new UserDTO("John Doe", 30, "johndoe@example.com");

        // ����Ʈ�� UserDTO ��ü �߰�
        UserDTO innerUser = new UserDTO("Jane Doe", 25, "janedoe@example.com");
        userDto.getList().add(innerUser);

        // ����Ʈ�� ù ��° ��� ���
        System.out.println("ù ��° ����� ����: " + userDto.getList().get(0));

        // ù ��° ����� ����Ʈ�� �߰��� �ٸ� UserDTO ��ü�� �����Ƿ� �� ����Ʈ�� ��µ� ����
        System.out.println("ù ��° ������� ����Ʈ: " + userDto.getList().get(0).getList());
	}

}
