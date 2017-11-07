package com.madgrid.dao;import java.util.List;import org.apache.ojb.broker.PersistenceBroker;import org.apache.ojb.broker.PersistenceBrokerException;import org.apache.ojb.broker.PersistenceBrokerFactory;import org.apache.ojb.broker.query.Criteria;import org.apache.ojb.broker.query.Query;import org.apache.ojb.broker.query.QueryByCriteria;import com.madgrid.admin.util.Utils;import com.madgrid.model.Box;public class BoxDAO{				public void setBox( Box box)  throws Exception{		PersistenceBroker broker = null;			try{			broker = PersistenceBrokerFactory.defaultPersistenceBroker();			broker.beginTransaction();			broker.store( box);			broker.commitTransaction();		}catch (PersistenceBrokerException ex){			broker.abortTransaction();			throw ex;		}finally {			if (broker != null) {				broker.close();			}		}	}		public void setBoxList( List<Box> boxList)  throws Exception{		PersistenceBroker broker = null;			try{			broker = PersistenceBrokerFactory.defaultPersistenceBroker();			broker.beginTransaction();			for( Box box:boxList){				broker.store( box);			}			broker.commitTransaction();		}catch (PersistenceBrokerException ex){			broker.abortTransaction();			throw ex;		}finally {			if (broker != null) {				broker.close();			}		}	}		public void deleteBox( Box box)  throws Exception{		PersistenceBroker broker = null;				try{			broker = PersistenceBrokerFactory.defaultPersistenceBroker();			broker.beginTransaction();			broker.delete( box);			broker.commitTransaction();		}catch ( PersistenceBrokerException ex){			broker.abortTransaction();						throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public List<Box> getBoxListByCriteria(Criteria criteria, String order) throws Exception	{		PersistenceBroker broker = null;		try {						QueryByCriteria query = new QueryByCriteria( Box.class, criteria);						if( !Utils.nullOrBlank(order)){				query.addOrderBy( order, order.startsWith("-")?true:false);			}									broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return (List<Box>)broker.getCollectionByQuery( query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public Box getBoxById( Integer id) throws Exception	{		Criteria 			criteria	= new Criteria();		PersistenceBroker 	broker 		= null;				if( id.intValue() == 0)			return null;				try {			criteria.addEqualTo		( "id", id);						Query query = new QueryByCriteria( Box.class, criteria);			broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return ( Box) broker.getObjectByQuery(query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public Box getBoxByCriteria( Criteria criteria) throws Exception	{		PersistenceBroker 	broker 		= null;		try {			Query query = new QueryByCriteria( Box.class, criteria);						broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return ( Box)broker.getObjectByQuery( query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}	}