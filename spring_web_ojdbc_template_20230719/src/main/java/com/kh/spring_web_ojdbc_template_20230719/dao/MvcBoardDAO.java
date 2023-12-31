package com.kh.spring_web_ojdbc_template_20230719.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import com.kh.spring_web_ojdbc_template_20230719.Constant;
import com.kh.spring_web_ojdbc_template_20230719.vo.MvcBoardVO;

public class MvcBoardDAO {
  private static final Logger logger = LoggerFactory.getLogger(MvcBoardDAO.class);

  // JdbcTemplate 설정
  private JdbcTemplate template;

  /*
   * DAO 클래스의 bean이 기본 생성자로 생성되는 순간 servlet-context.xml 파일에서 생성되서
   * 컨트롤러가 전달받아 Constant 클래스의 JdbcTemplate 클래스 타입의
   * static 객체에 저장한 bean으로 초기화시킨다
   */
  public MvcBoardDAO() {
    this.template = Constant.template;
  }

  // 밑에 있는DBCP 방식을 사용하는 객체를 초기화는 부분이므로 JdbcTemplate
  // 으로 코드 변환이 완려되면 모두 주석처리 한다.

  //private DataSource dataSource;
  //
  //public MvcboardDAO() {
  //    try {
  //        Context context = new InitialContext();
  //        dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/oracle");
  //    } catch (NamingException e) {
  //        e.printStackTrace();
  //    }
  //}

  /*
   * INSERT, DELETE, UPDATE SQL 명령을 실행하는 메소드의 인수로 넘어온 데이터가
   * 중간에 값이 변경되면 안 되기 때문에 JdbcTemplate 에서는 INSERT, DELETE, UPDATE
   * SQL 명령을 실행하는 메소드의 인수를 선언할 떄 final을 붙여서 넘어온
   * 데이터를 수정할 수 없도록 선언해야한다
   */
  public void insert(final MvcBoardVO mvcBoardVO) {
    logger.info("insert()");

    String sql = "";

    /*
     * 인터페이스 객체를 익명으로 구현하는 setValues() 추상 메소드가
     * 자동으로 override 되고 여기서 "?"를 채운다
     */
    template.update(sql, new PreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps) throws SQLException {
        ps.setString(1, mvcBoardVO.getName());
        ps.setString(2, mvcBoardVO.getSubject());
        ps.setString(3, mvcBoardVO.getContent());
      }
    });

    /*
     * update(): 테이블의 내용이 갱신되는 SQL 명령 (INSERT, DELETE, UPDATE)
     * query(): 테이블의 내용이 갱신되지 않는 SQL 명령 (SELECT) 결과가 여러 건일 경우 사용한다
     * queryForInt(): SELECT. 결과가 정수일 경우 사용한다
     * queryForObject(): SELECT. 실행 결과가 1건일 때 사용한다
     */
  }

  // 게시글의 총 개수
  public int selectCount() {
    logger.info("selectCount()");

    String sql = "SELECT COUNT(*) FROM mvcboard";
    // queryForObject(SQL명령, 리턴타입.class)
    return template.queryForObject(sql, Integer.class);
  }

  public ArrayList<MvcBoardVO> selectList(HashMap<String, Integer> hmap) {
    /*
     * JdbcTemplate을 사용할 경우 SELECT SQL 명령에만 "?"를 사용할 수 없다
     * "?" 자리에 데이터가 저장된 변수를 직접 사용해야한다
     */
    String sql = "SELECT * FROM (" + "SELECT ROWNUM rnum, AA.* FROM ("
        + "select * from mvcboard order by gup desc, seq asc" + "    ) AA where rownum <= "
        + hmap.get("endNo") + ") where rnum >= " + hmap.get("startNo");

    /* query(SQL 명령, new BeanPropertyRowMapper(리턴할 클래스.class)
     * SQL 명령이 실행 결과를 BeanPropertyRowMapper 클래스 생성자 인수로
     * MvcBoardBO 클래스를 넘겨서 SQL 명령 실행 결과를 저장시켜서 리턴한다
     * query() 메소드 실행 결과가 List 인터페이스로 저장된다
     * 그렇기 때문에 ArrayList 타입으로 형 변환해야 한다
     */
    return (ArrayList<MvcBoardVO>) this.template.query(sql,
        new BeanPropertyRowMapper(MvcBoardVO.class));
  }

  // 게시글 1개 가져오기
  public MvcBoardVO selectByIdx(int idx) {
    logger.info("selectByIdx()");

    // SQL Injection 주의
    String sql = "SELECT * FROM mvcboard WHERE idx = " + idx;
    return this.template.queryForObject(sql, new BeanPropertyRowMapper<>(MvcBoardVO.class));
  }

  public void update(final int idx) {
    logger.info("delete()");

    String sql = "DELETE FROM mvcboard WHERE idx = " + idx;
    this.template.update(sql);
  }


  public void increment(int idx) {
    String sql = "UPDATE mvcboard SET hit = hit + 1 WHERE idx = " + idx;
    this.template.update(sql);
  }

  // 실제 내용을 수정하는 메소드
  public void update(final int idx, final String subject, final String content) {
    logger.info("update(idx, subject, content)");

    // 서식 문자: %s 문자, %c 한 문자, %d 정수, %lf 실수
    String sql = String.format("UPDATE mvcboard SET subject = %s, content = %s WHERE idx = %d",
        subject, content, idx);
    this.template.update(sql);
  }
}
