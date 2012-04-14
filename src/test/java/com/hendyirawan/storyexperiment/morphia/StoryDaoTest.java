/**
 * 
 */
package com.hendyirawan.storyexperiment.morphia;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.jboss.arquillian.junit.Arquillian;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoURI;

/**
 * @author atang, ceefour
 *
 */
@RunWith(Arquillian.class)
public class StoryDaoTest {
	//* kelas logger digunakan untuk membuat logger
	private transient Logger log = LoggerFactory.getLogger(StoryDaoTest.class);
	private StoryDao storyDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		storyDao = new StoryDao();
		storyDao.setMongoUri(new MongoURI("mongodb://127.0.0.1:27017/storyexperiment_morphia"));
		storyDao.setNamespace("person");
		storyDao.init();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test public void createStories() {
		PersonLikeArticle story = new PersonLikeArticle("hendy", true,
				new SimpleRef("arum", "Person", "arum.puspita", "Arum Puspitasari", "arum_puspita"),
				new SimpleRef("air_sehat", "Article", "air-sehat", "Air Sehat", "air_sehat"));
		storyDao.insert(story);
	}

	@Test public void listStoryShouldReturnNotEmpty() {
		List<Story> stories = storyDao.findBySubject("hendy");
		log.info("Data story : {}", stories);
		Assert.assertNotNull("personStory untuk hendy tidak boleh null", stories);
		Assert.assertTrue("personStory untuk hendy tidak boleh kosong", 
				!stories.isEmpty());
		Story story = stories.get(0);
		Assert.assertEquals("hendy", story.getSubject());
	}

//	@Test
//	public void storyCanBeConvertedToDBObject() {
//		PersonLikeArticle story = new PersonLikeArticle();
//		story.setId("4f86f21dc7e3f62b7b3a4a2f");
//		story.setKind("PersonLikeArticle");
//		story.setLiked(new SimpleRef());
//		story.setLiker(new SimpleRef());
//		story.setCreated(new DateTime());
//
//		ArrayList<String> mappingFiles = new ArrayList<String>();
//		mappingFiles.add("com/hendyirawan/storyexperiment/dozer/storyexperiment.dozer.xml");
//		final DozerBeanMapper mapper = new DozerBeanMapper(mappingFiles);
//		
//		BasicDBObject obj = mapper.map(story, BasicDBObject.class);
//		log.info("DBObject: {}", obj);
//	}

}
