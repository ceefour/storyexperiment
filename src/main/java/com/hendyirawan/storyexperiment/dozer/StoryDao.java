/**
 * 
 */
package com.hendyirawan.storyexperiment.dozer;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoURI;

/**
 * Story DAO with mapped style.
 * @author atang, ceefour
 */
public class StoryDao {
	private transient Logger log = LoggerFactory.getLogger(StoryDao.class);
	private MongoURI mongoUri;
	private String namespace;
	private DBCollection coll;
	
	public void init(){
		try {
			log.info("Connecting to MongoDB database {}", mongoUri);
			DB db = mongoUri.connectDB();
			coll  = db.getCollection(getNamespace() + "Story");
		} catch (Exception e) {
			throw new RuntimeException("Cannot connect to mongo DB "+ mongoUri, e);
		} 
	}
	
	public MongoURI getMongoUri() {
		return mongoUri;
	}


	public void setMongoUri(MongoURI mongoUri) {
		this.mongoUri = mongoUri;
	}


	public List<Story> findBySubject(String subject) {
		DBCursor cursor = coll.find(new BasicDBObject("subject", subject));
		ArrayList<String> mappingFiles = new ArrayList<String>();
		mappingFiles.add("com/hendyirawan/storyexperiment/dozer/mapping.dozer.xml");
		final DozerBeanMapper mapper = new DozerBeanMapper(mappingFiles);
		Iterable<Story> storyIterable = Iterables.transform(cursor, new Function<DBObject, Story>() {
			@Override
			public Story apply(DBObject input) {
				String kind = (String) input.get("kind");
				try {
					Class<? extends Story> targetClass = (Class<? extends Story>) StoryDao.class.forName("com.hendyirawan.storyexperiment.dozer." + kind);
					Story story = mapper.map(input, targetClass);
					return story;
				} catch (ClassNotFoundException e) {
					log.error("Cannot create story with kind " + kind, e);
					return null;
				}
			}
		});
		ArrayList<Story> stories = Lists.newArrayList(storyIterable);
		log.debug("findByShopId {} return {} documents ", subject, stories.size());
		return stories;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}