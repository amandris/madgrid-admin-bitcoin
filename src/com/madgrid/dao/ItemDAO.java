package com.madgrid.dao;import java.util.List;import org.apache.ojb.broker.PersistenceBroker;import org.apache.ojb.broker.PersistenceBrokerException;import org.apache.ojb.broker.PersistenceBrokerFactory;import org.apache.ojb.broker.query.Criteria;import org.apache.ojb.broker.query.Query;import org.apache.ojb.broker.query.QueryByCriteria;import com.madgrid.admin.util.Utils;import com.madgrid.model.Item;public class ItemDAO{				public void setItem( Item item)  throws Exception{		PersistenceBroker broker = null;			try{			broker = PersistenceBrokerFactory.defaultPersistenceBroker();			broker.beginTransaction();			broker.store( item);			broker.commitTransaction();		}catch (PersistenceBrokerException ex){			broker.abortTransaction();			throw ex;		}finally {			if (broker != null) {				broker.close();			}		}	}		public void deleteItem( Item item)  throws Exception{		PersistenceBroker broker = null;				try{			broker = PersistenceBrokerFactory.defaultPersistenceBroker();			broker.beginTransaction();			broker.delete( item);			broker.commitTransaction();		}catch ( PersistenceBrokerException ex){			broker.abortTransaction();						throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public List<Item> getItemListByCriteria(Criteria criteria, String order) throws Exception	{		PersistenceBroker broker = null;		try {						QueryByCriteria query = new QueryByCriteria( Item.class, criteria);						if( !Utils.nullOrBlank(order)){				query.addOrderBy( order, order.startsWith("-")?true:false);			}						broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return (List<Item>)broker.getCollectionByQuery( query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public int getItemCountByCriteria(Criteria criteria) throws Exception	{		PersistenceBroker broker = null;		try {			QueryByCriteria query = new QueryByCriteria( Item.class, criteria);						broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return broker.getCount( query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public Item getItemById( Integer id) throws Exception	{		Criteria 			criteria	= new Criteria();		PersistenceBroker 	broker 		= null;				if( id.intValue() == 0)			return null;				try {			criteria.addEqualTo		( "id", id);						Query query = new QueryByCriteria( Item.class, criteria);			broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return ( Item) broker.getObjectByQuery(query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public Item getItemByCriteria( Criteria criteria) throws Exception	{		PersistenceBroker 	broker 		= null;		try {			Query query = new QueryByCriteria( Item.class, criteria);						broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return ( Item)broker.getObjectByQuery( query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}	}