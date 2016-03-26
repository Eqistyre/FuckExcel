package me.zacharyjia.fuckexcel;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index.do")
public class HelloController {

	@Autowired
	private Mongo mongo;

	@RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
	public String printWelcome(ModelMap model) {

		MongoOperations mongoOps = new MongoTemplate(mongo, "database");
		//mongoOps.insert(new Person("zachary", 22));
        Person p = mongoOps.findOne(new Query(Criteria.where("name").is("zachary")), Person.class);
		mongoOps.remove(new Query(Criteria.where("name").is("zachary")), Person.class);
		model.addAttribute("message","Hello Java");

		return "hello";
	}
}