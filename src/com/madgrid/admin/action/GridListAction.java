package com.madgrid.admin.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.GridDAO;
import com.madgrid.model.Grid;
import com.madgrid.model.Item;

public class GridListAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		GridDAO gridDAO = new GridDAO();
		
		List<Grid> gridList = gridDAO.getGridListByCriteria( new Criteria(), "startDate");
		
		Collections.sort( gridList, new Comparator<Grid>() {
	          public int compare( Grid g1, Grid g2) {
	        	  int value1 = 0;
	        	  int value2 = 0;
	        	  
	        	  if( g1.getItem().getType() == Item.ITEM_TYPE_ONLY_BITCOINS){
	        		  value1 = new Double(g1.getItem().getBitcoins()*1000d).intValue();
	        	  } else if( g1.getItem().getType() == Item.ITEM_TYPE_ONLY_CREDITS){
	        		  value1 = g1.getItem().getCredits();
	        	  } else if( g1.getItem().getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS){
	        		  value1 = g1.getItem().getCredits();
	        		  value1 = value1 + new Double(g1.getItem().getBitcoins()*1000d).intValue();
	        	  }
	        	  
	        	  if( g2.getItem().getType() == Item.ITEM_TYPE_ONLY_BITCOINS){
	        		  value2 = new Double(g2.getItem().getBitcoins()*1000d).intValue();
	        	  } else if( g2.getItem().getType() == Item.ITEM_TYPE_ONLY_CREDITS){
	        		  value2 = g2.getItem().getCredits();
	        	  } else if( g2.getItem().getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS){
	        		  value2 = g2.getItem().getCredits();
	        		  value2 = value2 + new Double(g2.getItem().getBitcoins()*1000d).intValue();
	        	  }
	        	  
	              if( value1>value2) return -1;
	              if(value1<value2) return 1;
	              return 0;
	          }
		});
		
		
		request.setAttribute( "gridList", gridList);
		
		return mapping.findForward( "ok");
	}

}		
