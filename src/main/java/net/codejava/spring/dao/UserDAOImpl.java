package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.User;

import net.codejava.spring.model.phones;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl() {
		
	}
	
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/* Лист юзеров */
	@Override
	@Transactional
	public List<User> list() {
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}

	/* Лист телефонов */
	@Override
	@Transactional
	public List<phones> list2() {
		@SuppressWarnings("unchecked")
		List<phones> listUser2 = (List<phones>) sessionFactory.getCurrentSession()
				.createCriteria(phones.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser2;
	}

	@Override
	public List<phones> listbyid() {
		return null;
	}

	/* Лист телефонов конкретного юзера */
	@Override
	@Transactional
	public List<phones> listbyid(int id){
		Query q = sessionFactory.getCurrentSession().
				createQuery("FROM phones where user_userid = :id").
				setParameter("id", id);

		@SuppressWarnings("unchecked")
		List<phones> phoneList = (List<phones>) q.list();

		if (phoneList != null && !phoneList.isEmpty()) {
			return phoneList;
		}

		return null;

	}

	@Override
	@Transactional
	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void saveOrUpdatephone(phones phone) {
		sessionFactory.getCurrentSession().saveOrUpdate(phone);
	}

	@Override
	@Transactional
	public void delete(int id) {
		User userToDelete = new User();
		userToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}


	@Override
	@Transactional
	public void deletephone(int id) {
		phones phoneToDelete = new phones();
		phoneToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(phoneToDelete);
	}




	@Override
	public User get(String name, String password) {
		return null;
	}

	@Override
	@Transactional
	public User get(int id) {
		String hql = "from User where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		
		return null;
	}

	@Override
	@Transactional
	public phones getphone(int id){

		Query q = sessionFactory.getCurrentSession().
				createQuery("FROM phones where phoneid = :id").
				setParameter("id", id);
		List<phones> phonelist = (List<phones>) q.list();

		return phonelist.get(0);


	}

	@Override
	@Transactional
	public User get(String name){

		//String hql = "from User where username" + "=" + name;
		//Query query = sessionFactory.getCurrentSession().createQuery(hql);
		//query.setString(1, name);


		Query q = sessionFactory.getCurrentSession().
				createQuery("FROM User where username = :name").
				setParameter("name", name);
		//q.setParameter(1-1, name);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) q.list();
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}

		return null;}

	@Override
	@Transactional
	public void savephone(int id, String phone, String username, int userid){

		Query q = sessionFactory.getCurrentSession().
				createQuery("UPDATE phones set phone = :phone, user_userid = :userid, username = :username WHERE phoneid = :phoneid");
				q.setParameter("phone", phone);
				q.setParameter("userid", userid);
				q.setParameter("username", username);
				q.setParameter("phoneid", id);

		q.executeUpdate();



	}

	public void save(phones phones)
	{
		sessionFactory.getCurrentSession().save(phones);

	}

}