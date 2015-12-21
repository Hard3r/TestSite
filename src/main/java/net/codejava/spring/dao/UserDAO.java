package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.User;
import net.codejava.spring.model.phones;
import org.springframework.transaction.annotation.Transactional;

public interface UserDAO {
	public List<User> list();
	
	public User get(int id);

	@Transactional
	List<phones> list2();

	@Transactional
	List<phones> listbyid();

	@Transactional
	List<phones> listbyid(int id);

	public void saveOrUpdate(User user);

	public void saveOrUpdatephone(phones phone);

	public void delete(int id);

	@Transactional
	void deletephone(int id);

	@Transactional
	User get(String name, String password);

	@Transactional
	phones getphone(int id);

	@Transactional
	User get(String name);

	@Transactional
	void savephone(int id, String phone, String username, int userid);
}