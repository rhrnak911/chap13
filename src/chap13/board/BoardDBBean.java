package chap13.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BoardDBBean {

	private static BoardDBBean instance = new BoardDBBean();
	
	private BoardDBBean() {}
	
	public static BoardDBBean getInstance() {
		return instance;
	}
	public String doA() {
		return "연결";
	}
	public void insertArticle(BoardDataBean dataBean) {
		System.out.println(dataBean);
		Connection conn= null;	//DB 연결하는 객체
		PreparedStatement pstmt = null; // sql 연결하는데 insert select updata delete
		// jar 파일 관리하는 회사 maven or gradle
		
		// jar 파일 연결 되어져 있는지 확인       class.forName
		// connection 객체 연결			Drivermanager.getConnection();
		// prepareStatement 생성		conn.prepareStatement();
		// 실행
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.200:1433;database=young_20200611", "sa", "8765432!");
//			System.out.println("디비 연결 성공");
			pstmt = conn.prepareStatement("INSERT INTO [dbo].[board]" + 
					"           ([num]" + 
					"           ,[writer]" + 
					"           ,[email]" + 
					"           ,[subject]" + 
					"           ,[passwd]" + 
					"           ,[reg_date]" + 
					"           ,[readcount]" + 
					"           ,[ref]" + 
					"           ,[re_step]" + 
					"           ,[re_level]" + 
					"           ,[content]" + 
					"           ,[ip])" + 
					"     VALUES" + 
					"			((select max(num)+1 as num from board)" + 
					"           , ? "+		
					"			, ?" + 
					"			, ?" + 
					"			, ?" + 
					"			,getdate()" + 
					"			,0" + 
					"			,0" + 
					"			,0" + 
					"			,0" + 
					"			,?" + 
					"            ,'192.168.0.198')");
			pstmt.setString(1, dataBean.getWriter());
			pstmt.setString(2, dataBean.getEmail());
			pstmt.setString(3, dataBean.getSubject());
			pstmt.setString(4, dataBean.getPasswd());
			pstmt.setString(5, dataBean.getContent());
			
			pstmt.executeUpdate();
			System.out.println("insert 성공");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insertDetailArticle(String name,String subject, String email,
			String content, String password) {
		
		System.out.println("subject = "+subject);
		System.out.println("name = "+name);
		System.out.println("emaile = "+email);
		System.out.println("content = "+content);
		System.out.println("password = "+password);		
	}
}
