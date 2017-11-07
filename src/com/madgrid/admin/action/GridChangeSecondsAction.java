package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.util.Utils;
import com.madgrid.dao.GridDAO;
import com.madgrid.model.Grid;

public class GridChangeSecondsAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		GridDAO gridDAO = new GridDAO();

		String secondsString = request.getParameter("seconds");
		String id = request.getParameter("id");
		Integer seconds = null;
		
		try{
			seconds = Integer.parseInt(secondsString);
		} catch (Exception e) {
			secondsString = null;
		}
		
		if( seconds != null && seconds >60){
			if( !Utils.nullOrBlank(id)){
				Grid grid = gridDAO.getGridById(Integer.parseInt(id));
				if( grid != null && !grid.getFinished() && grid.getOngoing() && !grid.getIsInPartialWin()){
					grid.setPartialWinSeconds(seconds);
					gridDAO.setGrid(grid);
					
				}
			}
		}
				
		
		
		return mapping.findForward( "ok");
	}


}		
