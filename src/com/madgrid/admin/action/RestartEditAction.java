package com.madgrid.admin.action;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.util.Utils;
import com.madgrid.dao.ParameterDAO;
import com.madgrid.model.Parameter;

public class RestartEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		ParameterDAO parameterDAO = new ParameterDAO();
		GregorianCalendar scheduledTime = new GregorianCalendar();
		scheduledTime.setTime( Utils.today());
		
		scheduledTime.set( GregorianCalendar.MINUTE, ((scheduledTime.get( GregorianCalendar.MINUTE) / 10) * 10) + 10 );
		scheduledTime.set( GregorianCalendar.SECOND, 0);
		scheduledTime.set( GregorianCalendar.MILLISECOND, 0);
		
		Criteria criteria = new Criteria();
		criteria.addEqualTo("name", "serverRestart");
		
		Parameter parameter = parameterDAO.getParameterByCriteria( criteria);
		
		if( parameter != null && parameter.getValue().equalsIgnoreCase("true")){
			request.setAttribute( "isScheduled", true);
		} else{
			request.setAttribute( "isScheduled", false);
		}
		
		request.setAttribute( "scheduledTime", Utils.getTime( scheduledTime.getTime()));
		
		return mapping.findForward( "ok");
	}

}		
