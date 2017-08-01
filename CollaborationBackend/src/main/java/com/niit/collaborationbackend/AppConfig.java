package com.niit.collaborationbackend;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaborationbackend.dao.BlogCommentDAO;
import com.niit.collaborationbackend.dao.BlogDAO;
import com.niit.collaborationbackend.dao.EventDAO;
import com.niit.collaborationbackend.dao.ForumCommentDAO;
import com.niit.collaborationbackend.dao.ForumDAO;
import com.niit.collaborationbackend.dao.FriendDAO;
import com.niit.collaborationbackend.dao.JobDAO;
import com.niit.collaborationbackend.dao.UserDAO;
import com.niit.collaborationbackend.daoimpl.BlogCommentDAOImpl;
import com.niit.collaborationbackend.daoimpl.BlogDAOImpl;
import com.niit.collaborationbackend.daoimpl.EventDAOImpl;
import com.niit.collaborationbackend.daoimpl.ForumCommentDAOImpl;
import com.niit.collaborationbackend.daoimpl.ForumDAOImpl;
import com.niit.collaborationbackend.daoimpl.FriendDAOImpl;
import com.niit.collaborationbackend.daoimpl.JobDAOImpl;
import com.niit.collaborationbackend.daoimpl.UserDAOImpl;
import com.niit.collaborationbackend.model.Blog;
import com.niit.collaborationbackend.model.BlogComment;
import com.niit.collaborationbackend.model.Chat;
import com.niit.collaborationbackend.model.Event;
import com.niit.collaborationbackend.model.Forum;
import com.niit.collaborationbackend.model.ForumComment;
import com.niit.collaborationbackend.model.Friend;
import com.niit.collaborationbackend.model.Job;
import com.niit.collaborationbackend.model.User;

@Configuration
@ComponentScan("com.niit.collaborationbackend")
@EnableTransactionManagement
public class AppConfig {

	@Autowired
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setUrl("jdbc:h2:tcp://localhost/~/Collaborationbackend");

		dataSource.setDriverClassName("org.h2.Driver");

		dataSource.setUsername("sa");
		dataSource.setPassword("");


		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());

		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(ForumComment.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		

		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired(required = true)
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {

		return new UserDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "blogDAO")
	public BlogDAO getBlogDAO(SessionFactory sessionFactory) {

		return new BlogDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "blogCommentDAO")
	public BlogCommentDAO getBlogCommentDAO(SessionFactory sessionFactory) {
		return new BlogCommentDAOImpl(sessionFactory);

	}
	
	@Autowired(required = true)
	@Bean(name = "forumDAO")
	public ForumDAO getForumDAO(SessionFactory sessionFactory) {
		return new ForumDAOImpl(sessionFactory);

	}
	
	@Autowired(required = true)
	@Bean(name = "forumCommentDAO")
	public ForumCommentDAO getForumCommentDAO(SessionFactory sessionFactory) {
		return new ForumCommentDAOImpl(sessionFactory);

	}
	
	@Autowired(required = true)
	@Bean(name = "jobDAO")
	public JobDAO getjobDAO(SessionFactory sessionFactory) {
		return new JobDAOImpl(sessionFactory);

	}
	
	@Autowired(required = true)
	@Bean(name = "friendDAO")
	public FriendDAO getfriendDAO(SessionFactory sessionFactory) {
		return new FriendDAOImpl(sessionFactory);

	}
	

	@Autowired(required = true)
	@Bean(name = "eventDAO")
	public EventDAO geteventDAO(SessionFactory sessionFactory) {
		return new EventDAOImpl(sessionFactory);

	}

}
