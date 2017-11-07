package com.madgrid.admin.action;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.ItemForm;
import com.madgrid.admin.util.Utils;
import com.madgrid.dao.ItemDAO;
import com.madgrid.dao.ParameterDAO;
import com.madgrid.model.Item;
import com.madgrid.model.Parameter;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class ItemSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		ItemDAO itemDAO = new ItemDAO();
		ParameterDAO parameterDAO = new ParameterDAO();
		Item item = null;
		ItemForm itemForm = (ItemForm) form;
		ActionErrors errors	= new ActionErrors();
		
		String virtualPath = itemForm.getVirtualPath();
		
		if( !Utils.nullOrBlank(virtualPath)){
			if( !virtualPath.startsWith( "/item/") || virtualPath.equals( "/item/")){
				errors.add( "virtualPath",new ActionError( "errors.invalid"));
			} else{
				Criteria criteria = new Criteria();
				criteria.addEqualTo( "virtualPath", virtualPath);
				if( itemForm.getId() != 0){
					criteria.addNotEqualTo( "id", itemForm.getId());
				}
				Item itemToCheck = itemDAO.getItemByCriteria(criteria);
				if( itemToCheck != null){
					errors.add( "virtualPath",new ActionError( "errors.unique"));
				}
			}
		}
		
		if( !errors.isEmpty()){
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		
		Criteria criteria = new Criteria();
		criteria.addEqualTo( "name", "frontImgPath");
		Parameter parameter = parameterDAO.getParameterByCriteria(criteria);
		int lengthAction = request.getPathInfo().length();
		String path_back = request.getPathTranslated().substring(0, request.getPathTranslated().length()- lengthAction) + "/img/item/";
		String path_front  = null;
		
		if( parameter != null){
			path_front = parameter.getValue();
		}
		
		if( itemForm.getId() == 0){
			item = new Item();
			item.setId( null);
			item.setCreated( Utils.today());
			item.setModified(Utils.today());
			item.setHtmlDescription( itemForm.getHtmlDescription());
			item.setBitcoins( itemForm.getBitcoins());
			item.setName( itemForm.getName());
			item.setType( itemForm.getType());
			item.setCredits( itemForm.getCredits());
			item.setVirtualPath( itemForm.getVirtualPath());
		} else{
			item = itemDAO.getItemById( itemForm.getId());
			item.setHtmlDescription( itemForm.getHtmlDescription());
			item.setName( itemForm.getName());
			item.setBitcoins( itemForm.getBitcoins());
			item.setCreated( item.getCreated());
			item.setModified(Utils.today());
			item.setType( itemForm.getType());
			item.setCredits( itemForm.getCredits());
			item.setVirtualPath( itemForm.getVirtualPath());
		}
		
		itemDAO.setItem(item);
		
		if( itemForm.getPicture1Url() != null && !Utils.nullOrBlank( itemForm.getPicture1Url().getFileName())) {
			saveImage( itemForm.getPicture1Url().getFileData(), path_back + item.getId() + "_1.jpg", 198, 138);
			if( path_front != null){
				saveImage( itemForm.getPicture1Url().getFileData(), path_front + item.getId() + "_1.jpg", 198, 138);
			}
			item.setPicture1Url( item.getId() + "_1.jpg");
		}
		
		
		itemDAO.setItem(item);
		return mapping.findForward( "ok");
	}
	
	private void saveImage( byte[] data, String fileName, int width, int height) throws Exception
	{
		Image 			picture 		= null;
		BufferedImage 	finalPicture 	= null;
		Graphics2D 		graphics2d		= null;
		int 			imageWidth 		= 1;
		int				imageHeight 	= 1;
		
		picture = new ImageIcon( data).getImage();
		imageWidth 	= ( int) (picture.getWidth( null));
		imageHeight	= ( int) (picture.getHeight( null));
				
		float factor = (float)imageWidth / (float)width;
		height = new Float((float)imageHeight / factor).intValue();
		
		finalPicture = new BufferedImage( width, height, BufferedImage.SCALE_SMOOTH);
		
		graphics2d = finalPicture.createGraphics();
		
		graphics2d.drawImage( picture,0,0, width, height,  null);
		graphics2d.dispose();
		
		OutputStream output = null;

		output = new FileOutputStream( fileName);
		
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder( output);
		
		encoder.encode( finalPicture);
		
		output.close();
		
	}

}		
