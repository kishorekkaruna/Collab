package com.niit.collaborationbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.dao.FriendDAO;
import com.niit.collaborationbackend.model.Friend;

@Repository("FriendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Friend> list() {
		List<Friend> friendList = sessionFactory.getCurrentSession().createQuery("from Friend").list();
		return friendList;
	}

	public List<Friend> list(int friendId) {

		return null;
	}

	public void save(Friend friend) {
		sessionFactory.getCurrentSession().save(friend);

	}

	public Friend getByFriendId(int friendId) {
		String oracle = "from Friend where friendId=" + "'" + friendId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(oracle);
		@SuppressWarnings("unchecked")
		List<Friend> friendlist = (List<Friend>) query.list();
		if (friendlist != null && !friendlist.isEmpty()) {
			return friendlist.get(0);
		}
		return null;
	}

	public void delete(int friendId) {
		Friend friendtoDelete = new Friend();
		friendtoDelete.setFriendId(friendId);
		sessionFactory.getCurrentSession().delete(friendtoDelete);
	}

	public void saveOrUpdate(Friend friend) {
		sessionFactory.getCurrentSession().saveOrUpdate(friend);

	}

	public List<Friend> listUser(int userId) {
		System.out.println("userid vvalue that logged in" +userId);
		String hql = "from Friend where userId =" + "'" + userId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) query.list();

		return listFriend;	
	}
	
	
	public List<Friend> getByFriendName(String name) {
		String hql = "from Friend where friendName =" + "'" + name + "' and status = " + "'P'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) query.list();
		return listFriend;
	}
	
	
	public List<Friend> getByFriendAccepted(String name){
		String hql = "from Friend where friendName =" + "'" + name + "' and status = " + "'A'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) query.list();
		return listFriend;
}

	public List<Friend> listUnion(int userId) {
//		String hql = "from Friend where friendName =" + "'" + friend + "' or userName = '" + friend +"'";
		String hql = "from Friend where userId =" + "'" + userId + "'";

		//String hql1 = "from Friend where friendName =" + "'" + friend + "'";
//		String hql = ("select * from user123 where user_name in (select user_name from user123 where user_name!=? minus (select userId from Friend where friendId=?"
//				+ "union select friendId from Friend where userId=?"
//				+ "))");
// 
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) query.list();
		return listFriend;
		
	}

}
