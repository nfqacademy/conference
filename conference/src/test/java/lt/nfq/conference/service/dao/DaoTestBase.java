package lt.nfq.conference.service.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class DaoTestBase {

	private static SqlSessionFactory sqlSessionFactory;

	protected SqlSession sqlSession;
	
	@BeforeClass
	public static void setUp() throws Exception {
		createSqlSessionFactory();

		runScript("sql/schema.sql");
		runScript("sql/test-data.sql");
	}
	
	private static void createSqlSessionFactory() throws IOException {
		Reader reader = Resources.getResourceAsReader("sql/test-mybatis-config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
	}

	private static void runScript(String resource) throws IOException {
		SqlSession session = sqlSessionFactory.openSession();
		Connection conn = session.getConnection();
		Reader reader = Resources.getResourceAsReader(resource);
		ScriptRunner runner = new ScriptRunner(conn);
		runner.runScript(reader);
		reader.close();
		session.close();
	}

	@Before
	public void openSession() {
		sqlSession = sqlSessionFactory.openSession();
	}
	
	@After
	public void closeSession() {
		sqlSession.close();
	}

}