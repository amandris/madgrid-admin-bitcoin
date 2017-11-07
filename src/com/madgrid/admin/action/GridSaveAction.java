package com.madgrid.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.GridForm;
import com.madgrid.admin.util.Utils;
import com.madgrid.dao.BoxDAO;
import com.madgrid.dao.GridDAO;
import com.madgrid.dao.ItemDAO;
import com.madgrid.model.Box;
import com.madgrid.model.Grid;
import com.madgrid.model.Item;

public class GridSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		GridDAO gridDAO = new GridDAO();
		ItemDAO itemDAO = new ItemDAO();
		BoxDAO boxDAO = new BoxDAO();
		Grid grid = null;
		GridForm gridForm = (GridForm) form;
		ActionErrors errors	= new ActionErrors();
		Random generator = new Random( Utils.today().getTime());
		List<Box> boxList = new ArrayList<Box>(); 
		Date today = Utils.today();

		Date startDate = Utils.parseDateAndTime( gridForm.getStartDate());
		
		String virtualPath = gridForm.getVirtualPath();
		
		if( startDate == null){
			errors.add( "startDate",new ActionError( "errors.invalid"));
		}
		
		if( !Utils.nullOrBlank(virtualPath)){
			if( !virtualPath.startsWith( "/item/") || virtualPath.equals( "/item/")){
				errors.add( "virtualPath",new ActionError( "errors.invalid"));
			} else{
				Criteria criteria = new Criteria();
				criteria.addEqualTo( "virtualPath", virtualPath);
				Grid gridToCheck = gridDAO.getGridByCriteria(criteria);
				if( gridToCheck != null){
					errors.add( "virtualPath",new ActionError( "errors.unique"));
				}
			}
		}
		
		if( gridForm.getPartialWinSeconds() == null || gridForm.getPartialWinSeconds().intValue() <= 0){
			if( gridForm.getType() != Grid.GRID_TYPE_DOUBLE_OR_NOTHING && gridForm.getType() != Grid.GRID_TYPE_WINNER_IS_FIRST){
				errors.add( "partialWinSeconds",new ActionError( "errors.invalid"));
			}
		}
		
		if( gridForm.getBoxes()== null ||  gridForm.getBoxes().intValue() <= 2){
			if( gridForm.getType() != Grid.GRID_TYPE_DOUBLE_OR_NOTHING ){
				errors.add( "boxes",new ActionError( "errors.invalid"));
			}
		}
		
		if( gridForm.getBoxes()== null ||  gridForm.getBoxes().intValue() < 20){
			if( gridForm.getType() == Grid.GRID_TYPE_MULTIPRIZE ){
				errors.add( "boxes",new ActionError( "errors.invalid"));
			}
		}
		
		if( !errors.isEmpty()){
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		
		Item item = itemDAO.getItemById( Integer.parseInt(gridForm.getItemId()));

		double boxPrice = 1; 
				
		if( gridForm.getType() == Grid.GRID_TYPE_DOUBLE_OR_NOTHING ){
			if( item.getType() == Item.ITEM_TYPE_ONLY_BITCOINS){
				boxPrice = Math.round( (item.getBitcoins() * 1000) / 2);
			} else if( item.getType() == Item.ITEM_TYPE_ONLY_CREDITS){
				boxPrice = Math.round( item.getCredits() / 2);
			} else if( item.getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS){
				boxPrice = Math.round( ((item.getBitcoins() * 1000) + item.getCredits())  / 2);
			}
		} else{
			if( item.getType() == Item.ITEM_TYPE_ONLY_BITCOINS){
				boxPrice = Math.round( (item.getBitcoins() * 1000) / gridForm.getBoxes());
			} else if( item.getType() == Item.ITEM_TYPE_ONLY_CREDITS){
				boxPrice = Math.round( item.getCredits()  / gridForm.getBoxes());
			} else if( item.getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS){
				boxPrice = Math.round( ((item.getBitcoins() * 1000) + item.getCredits())  / gridForm.getBoxes());
			}
		}
		
		if( boxPrice < 1){ 
			 boxPrice = 1;
		}
		
		grid = new Grid();
		grid.setId( null);
		grid.setCreated( today);
		grid.setModified( today);
		grid.setBoughtBoxes(0);
		if( gridForm.getType().intValue() == Grid.GRID_TYPE_FIXED_PRICE){
			grid.setBoxPrice( gridForm.getBoxPrice());
		} else if( gridForm.getType().intValue() == Grid.GRID_TYPE_FREE){
			grid.setBoxPrice( 0d);
		} else{
			grid.setBoxPrice( boxPrice);
		}
		grid.setFinished(false);
		grid.setOngoing(false);
		
		grid.setMoneyWon(0d);
		
		if( gridForm.getType() == Grid.GRID_TYPE_DOUBLE_OR_NOTHING ){
			grid.setFreeBoxes(2);
			grid.setBoxes(2);
			grid.setWinPos(generator.nextInt(2));
		} else{
			grid.setFreeBoxes(gridForm.getBoxes());
			grid.setBoxes(gridForm.getBoxes());
			grid.setWinPos(generator.nextInt(gridForm.getBoxes()));
		}
		
		if( gridForm.getType() == Grid.GRID_TYPE_MULTIPRIZE){
			List<Integer> winPosValues = new ArrayList<Integer>();
			winPosValues.add(grid.getWinPos());

			int i = 0;
			while( i<9){
				int value = generator.nextInt(gridForm.getBoxes());
				while( winPosValues.contains( value)){
					value = generator.nextInt(gridForm.getBoxes());
				}
				
				winPosValues.add( value);
				i++;
			}
			
			i =0;
			for( int value:winPosValues){
				if( value != grid.getWinPos().intValue()){
					if( i == 0){
						grid.setMultiPrize1_1CreditPos(value);
					}
					if( i == 1){
						grid.setMultiPrize1_2CreditPos(value);
					}
					if( i == 2){
						grid.setMultiPrize1_3CreditPos(value);
					}
					if( i == 3){
						grid.setMultiPrize1_4CreditPos(value);
					}
					if( i == 4){
						grid.setMultiPrize1_5CreditPos(value);
					}
					if( i == 5){
						grid.setMultiPrize2_1CreditPos(value);
					}
					if( i == 6){
						grid.setMultiPrize2_2CreditPos(value);
					}
					if( i == 7){
						grid.setMultiPrize5CreditPos(value);
					}
					if( i == 8){
						grid.setMultiPrize10CreditPos(value);
					}
					
					i++;
				}
			}
			
			
			
			
		}
		
		grid.setItemId(Integer.parseInt(gridForm.getItemId()));
		grid.setItem(item);
		grid.setStartDate(startDate);
		grid.setFinishDate(null);
		grid.setType(gridForm.getType());
		if( gridForm.getType() == Grid.GRID_TYPE_DOUBLE_OR_NOTHING || gridForm.getType() == Grid.GRID_TYPE_WINNER_IS_FIRST){
			grid.setPartialWinSeconds(3600);
		} else{
			grid.setPartialWinSeconds(gridForm.getPartialWinSeconds());
		}
		grid.setIsInPartialWin( false);
		grid.setPartialWinPreviousUser(null);
		grid.setPartialWinStartTime( null);
		grid.setPartialWinUser(null);
		grid.setPartialWinUserId( null);
		grid.setVirtualPath( gridForm.getVirtualPath());
		
		
		//Generamos el texto y el hash para la comprobacion de ganador
		int randomValue = generator.nextInt(5);
		String winPosText = "";
		switch (randomValue){
			case 0: winPosText = "Box=" + grid.getWinPos() + " randomCode = " + generateCode(13);break;
			case 1: winPosText = "prize position = " + grid.getWinPos() + " ignoreThis = " + generateCode(8);break;
			case 2: winPosText = "PoSiTiOn : " + grid.getWinPos() + " CoDe :" + generateCode(10);break;
			case 3: winPosText = "Box With Prize = " + grid.getWinPos() + " Code=" + generateCode(14);break;
			case 4: winPosText = "THE PRIZE IS IN BOX " + grid.getWinPos() + ", RANDOM CODE " + generateCode(7);break;
		}
		
		grid.setWinPosText(winPosText);
		grid.setWinPosHash(Utils.digestMD5(winPosText));
		
		for (int i=0; i<grid.getBoxes();i++){
			Box box = new Box();
			box.setGrid(grid);
			box.setGridId(grid.getId());
			box.setPos( i);
			box.setType( Box.TYPE_FREE);
			box.setPrice( 0d);
			box.setUser( null);
			box.setUserId( null);
			
			boxList.add( box);
		}
		
		boxDAO.setBoxList( boxList);
		
		gridDAO.setGrid(grid);
		
		return mapping.findForward( "ok");
	}
	
	private String generateCode(int length) 
	{
		String 	chars 	= "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.-=?¿)(/&%$!<>*+[]{}:;,#@";
		String 	code	= "";
		
		
		while( code.length() < length){
	      code = code + chars.charAt( ( int) Math.round( Math.random() * ( chars.length() - 1)));
		}
		
		return code;
	}

}		
