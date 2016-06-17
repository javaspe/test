package com.khbclass.memo.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.khbclass.memo.model.MemoDAO;
import com.khbclass.memo.model.MemoVO;

/**
 * Servlet implementation class Memo
 */
@WebServlet("/Memo/*") // " /* " �� ����ϸ� �ڿ� ���� �ּҿ� ������� ��� �����Ѵ�
public class Memo extends HttpServlet {

	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Memo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String commands = uri.substring(ctxPath.length()+1);
		// /ValueTransfer        /        Memo/new/safas/111/afsaf/sdfa22/faf
		                       //case 1   Memo
		                       //case 2   Memo/��ɾ�
		
		String cmd[] = commands.split("/");
		if(cmd.length == 1){
			
			MemoDAO dao = new MemoDAO();
			ArrayList<MemoVO> list = dao.getList();
			
			request.setAttribute("list", list);
			
			String url="/memo/list.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else if(cmd.length > 1){
			if("new".equals(cmd[1])){
				String url="/memo/write.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		// -------- �� ���������� form ���� �Ѿ�� ��
		String content = request.getParameter("content");
		String user_name = request.getParameter("user_name");
		String file = request.getParameter("file");
		//------------------------------------------
		
		// -------- �Ѿ�� ���� Table �� �÷����·� ����
		MemoVO vo = new MemoVO();
		vo.setContent(content);
		vo.setUser_name(user_name);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String n_date = dateFormat.format(cal.getTime());
		vo.setN_date(n_date);
		vo.setFile(file);
		//------------------------------------------
		
		//--------- ����� ���� DAO ��� ��ü�� DB �� �Է�
		MemoDAO dao = new MemoDAO();
		// �ش� ���̺� insert
		dao.create(vo);
		//------------------------------------------

		doGet(request, response);
	}

}
