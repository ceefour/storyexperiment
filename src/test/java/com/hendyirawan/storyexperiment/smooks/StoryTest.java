/**
 * 
 */
package com.hendyirawan.storyexperiment.smooks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;

import org.bson.types.ObjectId;
import org.jboss.arquillian.junit.Arquillian;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.milyn.Smooks;
import org.milyn.payload.JavaSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.hendyirawan.storyexperiment.vo.Story;
import com.mongodb.BasicDBObject;

/**
 * Experiment with serializing {@link Story} from XML to VO and back.
 * @author ceefour
 */
@RunWith(Arquillian.class)
public class StoryTest {

	private transient Logger log = LoggerFactory.getLogger(StoryTest.class);
	private Story story;
	
	@Before public void setUp() {
		story = new Story();
		story.setId("9829148");
		story.setKind("PersonLikeArticle");
		story.setCreated(new DateTime());
	}
	
	@Test public void storyShouldConvertToXml() throws IOException, SAXException {
		Smooks smooks = new Smooks("com/hendyirawan/storyexperiment/smooks/storyexperiment.smooks.xml");
		JavaSource source = new JavaSource(story);
		log.info("Source: {}", source);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamResult streamResult = new StreamResult(outputStream);
//		SAXResult result = new SAXResult().;
		smooks.filterSource(source, streamResult);
		log.info("Result: {}", outputStream);
	}
	
	@Test public void storyBsonShouldConvertToXml() throws IOException, SAXException {
		Smooks smooks = new Smooks("com/hendyirawan/storyexperiment/smooks/storyexperiment.smooks.xml");
		BasicDBObject obj = new BasicDBObject();
		obj.put("_id", new ObjectId("4f86f21dc7e3f62b7b3a4a2f"));
		obj.put("kind", "PersonLikeArticle");
		JavaSource source = new JavaSource(obj);
		log.info("Source: {}", source);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamResult streamResult = new StreamResult(outputStream);
//		SAXResult result = new SAXResult().;
		smooks.filterSource(source, streamResult);
		log.info("Result: {}", outputStream);
	}
	
	@Test public void storyBsonShouldConvertToMapThenXml() throws IOException, SAXException {
		Smooks smooks = new Smooks("com/hendyirawan/storyexperiment/smooks/storyexperiment.smooks.xml");
		BasicDBObject obj = new BasicDBObject();
		obj.put("_id", new ObjectId("4f86f21dc7e3f62b7b3a4a2f"));
		obj.put("kind", "PersonLikeArticle");
		JavaSource source = new JavaSource(obj.toMap());
		log.info("Source: {}", source);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamResult streamResult = new StreamResult(outputStream);
//		SAXResult result = new SAXResult().;
		smooks.filterSource(source, streamResult);
		log.info("Result: {}", outputStream);
	}
	
	@Test public void storyBsonShouldConvertToString() throws IOException, SAXException {
		BasicDBObject obj = new BasicDBObject();
		obj.put("_id", new ObjectId("4f86f21dc7e3f62b7b3a4a2f"));
		obj.put("kind", "PersonLikeArticle");
		log.info("Obj: {}", obj);
	}
	
}
