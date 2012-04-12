/**
 * 
 */
package com.hendyirawan.storyexperiment.dozer;

import java.util.List;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hendyirawan.storyexperiment.dozer.Story;
import com.hendyirawan.storyexperiment.dozer.StoryDao;
import com.mongodb.MongoURI;

/**
 * @author atang, ceefour
 *
 */
@RunWith(Arquillian.class)
public class StoryDaoTest {
	//* kelas logger digunakan untuk membuat logger
	private transient Logger log = LoggerFactory.getLogger(StoryDaoTest.class);
	private StoryDao shopStoryDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		shopStoryDao = new StoryDao();
		shopStoryDao.setMongoUri(new MongoURI("mongodb://127.0.0.1:27017/storyexperiment"));
		shopStoryDao.setNamespace("person");
		shopStoryDao.init();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void listStoryShouldReturnNotEmpty() {
		List<Story> stories = shopStoryDao.findBySubject("hendy");
		log.info("Data story : {}", stories);
		Assert.assertNotNull("shopstory untuk hendy tidak boleh null", stories);
		Assert.assertTrue("ShopStory untuk hendy tidak boleh kosong", 
				!stories.isEmpty());
		Story story = stories.get(0);
		Assert.assertEquals("hendy", story.getSubject());
	}

}
