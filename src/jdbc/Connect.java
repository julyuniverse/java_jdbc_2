package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Connect {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc"; // DB 경로: jdbc:mysql://서버주소:포드/DB이름
        String user = "root";
        String password = "1234";
        Connection conn = null; // DB 연결
        PreparedStatement ps = null; // query 문에 매개 변수를 사용하고, 실행 전에 값을 지정할 수 있음.

        try {
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement("insert into USER (first_name, last_name, age) values (?, ?, ?);");
            ps.setString(1, "Kiyoon");
            ps.setString(2, "Jung");
            ps.setInt(3, 33);
            int resultCount = ps.executeUpdate();

            System.out.println(resultCount + "개의 행이 삽입되었습니다.");
        } catch (Exception e) {
            e.printStackTrace(); // e.printStackTrace(): 에러의 발생 근원지를 찾아서 단계별로 에러를 출력.
        } finally {
            // 연결되고 쿼리 해서 data를 가져오는 과정에서 메모리를 잡아먹기 때문에 쿼리가 끝나면 역순으로 해제한다.
            try {
                if (ps != null) ps.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
