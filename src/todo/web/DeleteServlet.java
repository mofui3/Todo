package todo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;

/**
 * 削除処理を行う
 */
@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータから選択しタスクidを取得する
		String paramId = request.getParameter("id");

		try (TodoDAO dao = new TodoDAO()) {
			//intへ変換 ※NumberFormExceptionが発生する可能性あり。チェック対象。
			int id = Integer.parseInt(paramId);

			//StringからIntへ変換し、daoで処理を行う。対象のタスクを1件削除し、成功すると1が返される、
			int result = dao.delete(id);
		}
		catch (Exception e) {
			throw new ServletException(e);
		}

		setMessage(request, "タスク[" + paramId + "]の削除処理が完了しました。");

		//画面を返す
		//完了画面を表示する
		RequestDispatcher rd = request.getRequestDispatcher("/todo/search");
		rd.forward(request, response);
	}

	/*
	 * JSPで表示するメッセージを設定する
	 * @param request サーブレットリクエスト
	 * @param massage メッセージ文字列
	 */

	protected void setMessage(HttpServletRequest request, String message) {
		request.setAttribute("message", message);
	}

}
