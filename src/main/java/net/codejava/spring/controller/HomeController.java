package net.codejava.spring.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.CRC32;

import javax.servlet.http.HttpServletRequest;

import net.codejava.spring.dao.UserDAO;
import net.codejava.spring.model.User;

import net.codejava.spring.model.ValidateUser;
import net.codejava.spring.model.phones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {


	String CURRENTSESSION = null;
	String error = "";

	@Autowired
	private UserDAO userDao;


	@RequestMapping("/")
	public ModelAndView handleRequest() throws Exception {
		//List<User> listUsers = userDao.list();
		ModelAndView model = new ModelAndView("UserList");
		//model.addObject("userList", listUsers);
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", new User());
		return model;
	}


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userDao.get(userId);
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		userDao.delete(userId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user) {
		userDao.saveOrUpdate(user);
		return new ModelAndView("redirect:/");
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm(Model model) {
		model.addAttribute("error", error);
		return new ModelAndView("LoginForm", "login", new ValidateUser());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String profile(@ModelAttribute("SpringWeb") ValidateUser validateUser, ModelMap model) {
		//model.addAttribute("username", validateUser.getUsername());
		String name = validateUser.getUsername();
		String password = validateUser.getPassword();


		User user = userDao.get(name);
		//User user = userDao.get(name, password);
		//model.addAttribute("usernameDB", user.getUsername());
		CURRENTSESSION = user.getUsername();


// nullpointer обработать + оптимизировать проверку внутри UserDAO
		if (user.getUsername().equals(validateUser.getUsername()) &&
				user.getPassword().equals(validateUser.getPassword())) {
			return "redirect:/" + user.getUsername();
		} else {
			error = "Ne verniy login/password";
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ModelAndView userprofile(@PathVariable String username) {
		User user = userDao.get(username);
		//List<phones> listPhones = userDao.list2();
		List<phones> listPhones = userDao.listbyid(user.getId());
		ModelAndView modelAndView = new ModelAndView("Profile");
		modelAndView.addObject("phone", new phones());
		modelAndView.addObject("phoneList", listPhones);
		modelAndView.addObject("user", username);


		//User user = userDao.get(username);
		//model.addAttribute(user);

		//model.addAttribute("phone", new phones());
		//List<phones> phonesList = phonesDao.list();
		//model.addAttribute(phonesList);
		return modelAndView;

	}

	@RequestMapping(value = "/savephone", method = RequestMethod.POST)
	public String savePhone(@ModelAttribute phones phone, Model model) {
		User user = userDao.get(CURRENTSESSION);
		Set<phones> phonesSet = new HashSet<phones>();
		phone.setUser(user);
		phonesSet.add(phone);
		user.setPhones(phonesSet);

		model.addAttribute("phone", phone);
		//userDao.saveOrUpdatephone(phone);
		userDao.saveOrUpdate(user);


		return "redirect:/" + CURRENTSESSION;
	}


	@RequestMapping(value = "/deletephone", method = RequestMethod.GET)
	public ModelAndView deletePhone(HttpServletRequest request) {
		int phoneId = Integer.parseInt(request.getParameter("id"));
		userDao.deletephone(phoneId);
		return new ModelAndView("redirect:/"+  CURRENTSESSION);
	}

	@RequestMapping(value = "/editphone", method = RequestMethod.GET)
	public ModelAndView editPhone(HttpServletRequest request) {
		int phoneId = Integer.parseInt(request.getParameter("id"));
		phones phone = userDao.getphone(phoneId);
		ModelAndView model = new ModelAndView("PhoneForm");
		model.addObject("phone", phone);
		return model;
	}

	@RequestMapping(value = "/savephone2", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute phones phone) {
		User user = userDao.get(CURRENTSESSION);
		userDao.savephone(phone.getId(), phone.getPhone(), phone.getUsername(), user.getId() );
		return new ModelAndView("redirect:/" + CURRENTSESSION);
	}
}

