package com.madgrid.admin.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.util.Utils;
import com.madgrid.dao.BoxDAO;
import com.madgrid.dao.GridDAO;
import com.madgrid.dao.GridHistoricDAO;
import com.madgrid.dao.PromotionDAO;
import com.madgrid.dao.UserDAO;
import com.madgrid.model.Box;
import com.madgrid.model.Grid;
import com.madgrid.model.GridHistoric;
import com.madgrid.model.Promotion;
import com.madgrid.model.User;




public class GridDeleteAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		GridDAO gridDAO = new GridDAO();
		BoxDAO boxDAO = new BoxDAO();
		GridHistoricDAO gridHistoricDAO = new GridHistoricDAO();

		String id=request.getParameter( "id");
		
		if( id != null){
			Grid grid = gridDAO.getGridById( Integer.parseInt( id));
			
			Criteria criteria = new Criteria();
			criteria.addEqualTo( "gridId", grid.getId());
			List<Box> boxList = boxDAO.getBoxListByCriteria(criteria, null);
			
			
			//Modificamos estadisticas de usuario
			Date today = Utils.today();
			UserDAO userDAO = new UserDAO();
			Map<Integer,int[]> userDataMap = new HashMap<Integer,int[]>();

		    for( Box box:boxList){
			   if( box.getUser() != null){
				   int[] userData = userDataMap.get( box.getUser().getId());
				   if( userData == null){
					   userData = new int[4];
					   userData[0] = 0;
					   userData[1] = 0;
					   userData[2] = 0;
					   userData[3] = 0;
					  
				   }
				   if( userData[0] == 0){
					   userData[0] =1;
				   }
				   if( userData[1]==0 && box.getType() == Box.TYPE_WIN){
					   userData[1] =1;
				   }
				   if( box.getType() == Box.TYPE_WIN || box.getType() == Box.TYPE_BOUGHT){
					   userData[2] = userData[2] + 1;
					   userData[3] = userData[3] + box.getPrice().intValue();
				   }
				   
				   userDataMap.put(box.getUser().getId(), userData);
			   }
		    }
		   
		    for( Integer userId:userDataMap.keySet()){
			   User user = userDAO.getUserById( userId);
			   
			   int[] userData = userDataMap.get( user.getId());
			   user.setStatisticsPlayedGames( user.getStatisticsPlayedGames() + userData[0]);
			   user.setStatisticsWonGames( user.getStatisticsWonGames() + userData[1]);
			   user.setStatisticsBoughtBoxes( user.getStatisticsBoughtBoxes() + userData[2]);
			   user.setStatisticsUsedCredits(user.getStatisticsUsedCredits() + userData[3]);
			   userDAO.setUser(user);
		    }
		   
		   //Fin Modificamos estadisticas de usuario

			
			
			for(Box box:boxList){
				boxDAO.deleteBox(box);
			}
			
			List<GridHistoric> gridHistoricList = gridHistoricDAO.getGridHistoricListByCriteria(criteria, null);
			for(GridHistoric gridHistoric:gridHistoricList){
				gridHistoricDAO.deleteGridHistoric(gridHistoric);
			}
			
			gridDAO.deleteGrid( grid);
		}
		
		return mapping.findForward( "ok");
	}

}		
