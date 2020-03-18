package todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAO implements AutoCloseable {

	private Connection connection = null;

	public DAO() {
	}

	/**
	 * データベースとの接続を取得する。
	 * もし取得していた場合には既存の接続を利用し、
	 * 取得していない場合は新たにコンテナから取得する。
	 *
	 * @return
	 * @throws Exception
	 */

	public Connection getConnection() throws Exception {

		//NamingException, SQLExceptionがスローされる

		try {
			if (connection == null || connection.isClosed()) {
				InitialContext initCtx = new InitialContext();
					DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/localDB");

					//データベース接続を取得する
					connection = ds.getConnection();
			}
		}
		catch (NamingException|SQLException e) {
			//もし絶族取得で例外が出た場合は connnection=null にし、
			//発生した例外はそのまま創出する。
			e.printStackTrace();
			connection = null;
			throw e;
		}

		return connection;

	}

	/**
	 * 接続を閉じる・確実に接続を開放するため finally で connection=null を行う。
	 */

	public void closeConnection() {
		try {
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection = null;
		}
	}

	/**
	 * PreparedStatementを返す。
	 *
	 * @param sql
	 * @return
	 * @throws Exception
	 */

	public PreparedStatement getPreparedStatement(String sql) throws Exception {
		return getConnection().prepareStatement(sql);

	}

	/**
	 * トランザクションのコミットを行う
	 * @throws SQLException
	 */

	public void commit() throws SQLException {
		connection.commit();

	}

	/**
	 * トランザクションのロールバックを行う
	 * @throws SQLException
	 */

	public void rollback() throws SQLException {
		connection.rollback();
	}

	/**
	 * 接続を閉じる
	 */
	public void close() {

		System.out.println("close connection ------------------------------------------------->");

		try {
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection = null;
		}

	}
}