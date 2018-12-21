package com.props.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Value("#{'${only.legacy.milestone.ids}'.split(',')}")
	private List<Integer> onlyLegacyIds;
	private final Set<Integer> onlyLegacyIdsSet = new HashSet<>();

	@Value("#{'${without.relation.milestone.ids}'.split(',')}")
	private List<Integer> withoutRelationIds;
	private final Set<Integer> withoutRelationIdsSet = new HashSet<>();

	// Make sets final explanation -->
	// https://stackoverflow.com/questions/23906808/should-i-mark-object-attributes-as-volatile-if-i-init-them-in-postconstruct-in
	@PostConstruct
	public void init() throws Exception {
		onlyLegacyIdsSet.addAll(onlyLegacyIds);
		withoutRelationIdsSet.addAll(withoutRelationIds);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		LOGGER.info("Only legacy IDS -> " + onlyLegacyIdsSet);
		LOGGER.info("Without relations IDS -> " + withoutRelationIdsSet);
	}
}

