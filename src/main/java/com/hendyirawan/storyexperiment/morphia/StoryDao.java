/**
 * 
 */
package com.hendyirawan.storyexperiment.morphia;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.converters.SimpleValueConverter;
import com.google.code.morphia.converters.TypeConverter;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.google.code.morphia.mapping.MappedField;
import com.google.code.morphia.mapping.MappingException;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.hendyirawan.storyexperiment.vo.Story;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoURI;
import com.mongodb.WriteResult;

/**
 * Story DAO with mapped style.
 * @author atang, ceefour
 */
public class StoryDao {
	private transient Logger log = LoggerFactory.getLogger(StoryDao.class);
	private MongoURI mongoUri;
	private String namespace;
	private DBCollection coll;
	private Morphia morphia;
	private Datastore ds;

	public static class DateTimeConverter extends TypeConverter implements SimpleValueConverter {
		public DateTimeConverter() {
			super(DateTime.class);
		}
		
		@Override public Object decode(Class target, Object val, MappedField field)
				throws MappingException {
			if (val == null) return null;
			return new DateTime(val);
		}
		
		@Override
		public Object encode(Object value, MappedField optionalExtraInfo) {
			if (value == null) return null;
			return ((DateTime)value).toDate();
		}
	}
	
	public void init(){
		try {
			log.info("Connecting to MongoDB database {}", mongoUri);
			DB db = mongoUri.connectDB();
			coll  = db.getCollection(getNamespace() + "Story");
			MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
			morphia = new Morphia().mapPackage("com.hendyirawan.storyexperiment.morphia");
			morphia.getMapper().getConverters().addConverter(new DateTimeConverter());
			ds = morphia.createDatastore(db.getMongo(), db.getName());
			
			// Ensure indexes
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
	
	public ObjectId insert(Story story) {
		DBObject dbo = morphia.toDBObject(story);
		WriteResult result = coll.insert(dbo);
		ObjectId id = (ObjectId) dbo.get("_id");
		log.info("Inserted Story document {} result: {}", id, result);
		return id;
	}

	public List<Story> findBySubject(String subject) {
		DBCursor cursor = coll.find(new BasicDBObject("subject", subject));
		Iterable<Story> storyIterable = Iterables.transform(cursor, new Function<DBObject, Story>() {
			@Override
			public Story apply(DBObject input) {
				String kind = (String) input.get("kind");
				String className = "com.hendyirawan.storyexperiment.vo." + kind;
				log.debug("Morphing Story {} to {}", input.get("_id"), className);
				try {
					Class<? extends Story> targetClass = (Class<? extends Story>) StoryDao.class.forName(className);
					Story story = morphia.fromDBObject(targetClass, input);
					return story;
				} catch (ClassNotFoundException e) {
					log.error("Cannot create story with kind " + kind, e);
					return null;
				}
			}
		});
		ArrayList<Story> stories = Lists.newArrayList(storyIterable);
		log.debug("findBySubject {} returns {} documents ", subject, stories.size());
		return stories;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}