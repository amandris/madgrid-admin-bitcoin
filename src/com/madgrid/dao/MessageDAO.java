package com.madgrid.dao;import java.util.List;import java.util.StringTokenizer;import org.apache.ojb.broker.PersistenceBroker;import org.apache.ojb.broker.PersistenceBrokerException;import org.apache.ojb.broker.PersistenceBrokerFactory;import org.apache.ojb.broker.query.Criteria;import org.apache.ojb.broker.query.Query;import org.apache.ojb.broker.query.QueryByCriteria;import com.madgrid.admin.util.Utils;import com.madgrid.model.Message;public class MessageDAO{				public void setMessage( Message message)  throws Exception{		PersistenceBroker broker = null;			try{			broker = PersistenceBrokerFactory.defaultPersistenceBroker();			broker.beginTransaction();			broker.store( message);			broker.commitTransaction();		}catch (PersistenceBrokerException ex){			broker.abortTransaction();			throw ex;		}finally {			if (broker != null) {				broker.close();			}		}	}		public void deleteMessage( Message message)  throws Exception{		PersistenceBroker broker = null;				try{			broker = PersistenceBrokerFactory.defaultPersistenceBroker();			broker.beginTransaction();			broker.delete( message);			broker.commitTransaction();		}catch ( PersistenceBrokerException ex){			broker.abortTransaction();						throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public List<Message> getMessageListByCriteria(Criteria criteria, String order) throws Exception	{		PersistenceBroker broker = null;		try {						QueryByCriteria query = new QueryByCriteria( Message.class, criteria);						if( !Utils.nullOrBlank(order)){				query.addOrderBy( order, order.startsWith("-")?true:false);			}						broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return (List<Message>)broker.getCollectionByQuery( query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public int getMessageCountByCriteria(Criteria criteria) throws Exception	{		PersistenceBroker broker = null;		try {			QueryByCriteria query = new QueryByCriteria( Message.class, criteria);						broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return broker.getCount( query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public List<Message> getMessageListByCriteriaAndRange(Criteria criteria, String order, int index, int offset) throws Exception	{		PersistenceBroker broker = null;		try {						QueryByCriteria query = new QueryByCriteria( Message.class, criteria);			if( !Utils.nullOrBlank(order)){				StringTokenizer stringTokenizer = new StringTokenizer( order, ",");				while (stringTokenizer.hasMoreElements()){					String token = stringTokenizer.nextToken();					query.addOrderBy( token, token.startsWith("-")?true:false);				}			}						query.setStartAtIndex( index);			query.setEndAtIndex( index + offset);						broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return (List<Message>)broker.getCollectionByQuery( query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public Message getMessageById( Integer id) throws Exception	{		Criteria 			criteria	= new Criteria();		PersistenceBroker 	broker 		= null;				if( id.intValue() == 0)			return null;				try {			criteria.addEqualTo		( "id", id);						Query query = new QueryByCriteria( Message.class, criteria);			broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return ( Message) broker.getObjectByQuery(query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}		public Message getMessageByCriteria( Criteria criteria) throws Exception	{		PersistenceBroker 	broker 		= null;		try {			Query query = new QueryByCriteria( Message.class, criteria);						broker = PersistenceBrokerFactory.defaultPersistenceBroker();				return ( Message)broker.getObjectByQuery( query);		}catch ( PersistenceBrokerException ex) {			throw ex;		}finally {			if ( broker != null) {				broker.close();			}		}	}	}