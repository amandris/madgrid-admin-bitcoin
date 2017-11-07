package com.madgrid.admin.action;

import java.util.ArrayList;
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
import com.madgrid.dao.GridHistoricDAO;
import com.madgrid.dao.ItemDAO;
import com.madgrid.model.Grid;
import com.madgrid.model.GridHistoric;
import com.madgrid.model.Item;


public class GridEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		GridDAO gridDAO = new GridDAO();
		ItemDAO itemDAO = new ItemDAO();
		GridHistoricDAO gridHistoricDAO = new GridHistoricDAO();
		Grid grid = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			grid = gridDAO.getGridById( Integer.parseInt( id));
			request.setAttribute( "grid", grid);
		} 
		
		List<Item> itemList = itemDAO.getItemListByCriteria(new Criteria(), "name");
		
		
		Collections.sort( itemList, new Comparator<Item>() {
	          public int compare( Item i1, Item i2) {
	        	  int value1 = 0;
	        	  int value2 = 0;
	        	  int bitcoinValue1 = 0;
	        	  int bitcoinValue2 = 0;
	        	  
	        	  bitcoinValue1 = new Double(i1.getBitcoins()*1000d).intValue();
	        	  bitcoinValue2 = new Double(i2.getBitcoins()*1000d).intValue();
	        	  
	        	  if( i1.getType() == Item.ITEM_TYPE_ONLY_BITCOINS){
	        		  value1 = new Double(i1.getBitcoins()*1000d).intValue();
	        	  } else if( i1.getType() == Item.ITEM_TYPE_ONLY_CREDITS){
	        		  value1 = i1.getCredits();
	        	  } else if( i1.getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS){
	        		  value1 = i1.getCredits();
	        		  value1 = value1 + new Double(i1.getBitcoins()*1000d).intValue();
	        	  }
	        	  
	        	  if(i2.getType() == Item.ITEM_TYPE_ONLY_BITCOINS){
	        		  value2 = new Double(i2.getBitcoins()*1000d).intValue();
	        	  } else if( i2.getType() == Item.ITEM_TYPE_ONLY_CREDITS){
	        		  value2 = i2.getCredits();
	        	  } else if( i2.getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS){
	        		  value2 = i2.getCredits();
	        		  value2 = value2 + new Double(i2.getBitcoins()*1000d).intValue();
	        	  }
	        	  
	        	  if(  i1.getType() == Item.ITEM_TYPE_ONLY_BITCOINS &&  i2.getType() == Item.ITEM_TYPE_ONLY_CREDITS){
	        		  return -1;
	        	  }
	        	  if(  i1.getType() == Item.ITEM_TYPE_ONLY_BITCOINS &&  i2.getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS){
	        		  return -1;
	        	  }
	        	  
	        	  if(  i1.getType() == Item.ITEM_TYPE_ONLY_CREDITS &&  i2.getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS){
	        		  return -1;
	        	  }
	        	  if(  i1.getType() == Item.ITEM_TYPE_ONLY_CREDITS &&  i2.getType() == Item.ITEM_TYPE_ONLY_BITCOINS){
	        		  return 1;
	        	  }
	        	  
	        	  if(  i1.getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS &&  i2.getType() == Item.ITEM_TYPE_ONLY_BITCOINS){
	        		  return 1;
	        	  }
	        	  if(  i1.getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS &&  i2.getType() == Item.ITEM_TYPE_ONLY_CREDITS){
	        		  return 1;
	        	  }
	        	  
	        	  if( i1.getType() == Item.ITEM_TYPE_ONLY_BITCOINS &&  i2.getType() == Item.ITEM_TYPE_ONLY_BITCOINS){
	        		  if( value1>value2) return -1;
	        		  if(value1<value2) return 1;
	        	  }
	        	  
	        	  if( i1.getType() == Item.ITEM_TYPE_ONLY_CREDITS &&  i2.getType() == Item.ITEM_TYPE_ONLY_CREDITS){
	        		  if( value1>value2) return -1;
	        		  if(value1<value2) return 1;
	        	  }
	        	  
	        	  if( i1.getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS &&  i2.getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS){
	        		  if( i1.getName().equalsIgnoreCase("Treasure chest") && !i2.getName().equalsIgnoreCase("Treasure chest")){
	        			  return 1;
	        		  }
	        		  if( !i1.getName().equalsIgnoreCase("Treasure chest") && i2.getName().equalsIgnoreCase("Treasure chest")){
	        			  return -1;
	        		  }
	        		  
	        		  
	      			  if( bitcoinValue1>bitcoinValue2) return -1;
	        		  if( bitcoinValue1<bitcoinValue2) return 1;
	        		  if( bitcoinValue1 == bitcoinValue2){
	        			  if( value1>value2) return -1;
		        		  if(value1<value2) return 1;
	        		  }
	        		  
	        	  }
	              return 0;
	          }
		});
		
		request.setAttribute( "itemList", itemList);
		
		List<GridHistoric> gridHistoricList = new ArrayList<GridHistoric>();
		
		if( grid != null){
			Criteria criteria = new Criteria();
			criteria.addEqualTo( "gridId", grid.getId());
			gridHistoricList = gridHistoricDAO.getGridHistoricListByCriteria(criteria, "-created");
			
			Collections.sort( gridHistoricList, new Comparator<GridHistoric>() {
				public int compare( GridHistoric g1, GridHistoric g2) {
					return g1.getCreated().compareTo(g2.getCreated());
				}
			});
			
		}
		
		request.setAttribute( "gridHistoricList", gridHistoricList);
		
		return mapping.findForward( "ok");
	}

}		
