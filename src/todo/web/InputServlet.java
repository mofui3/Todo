package todo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dto.Todo;
/**
 * Servlet implementation class InputServlet
 */
@WebServlet("/todo/input")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//voの作成
		Todo dto = new Todo();

		//新規登録であることを判別するためにid=0としている。
		dto.setId(0);

		//タスク1件のDTOをリクエスト属性へバインド
		request.setAttribute("dto", dto);

		//詳細画面を表示する
		RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
		rd.forward(request, response);
	}
}
