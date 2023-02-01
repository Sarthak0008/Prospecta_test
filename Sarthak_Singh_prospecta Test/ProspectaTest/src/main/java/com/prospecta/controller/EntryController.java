package com.prospecta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prospecta.model.Entry;
import com.prospecta.model.EntryDto;
import com.prospecta.model.Entrystore;
import com.prospecta.repo.EntryRepository;

@RestController
public class EntryController {
	
	private RestTemplate restTemplate;

	@Autowired
	private EntryRepository entryRepo;
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<EntryDto>> getEntriesHandler(@PathVariable("Category") String category){
		
		Entrystore es=restTemplate.getForObject("https://api.publicapis.org/entries", Entrystore.class);
		
		
		List<Entry> entries=es.getEntries();
		List<EntryDto> list=entries.stream().filter(l->l.getCategory().equalsIgnoreCase(category)).map(m->new EntryDto(m.getApi(), m.getDesc())).toList();
		
		return new ResponseEntity<List<EntryDto>>(list,HttpStatus.OK);
	}
	
	@PostMapping("/entries")
	public ResponseEntity<Entry> saveEntriesHandler(@RequestBody EntryDto eDto){
		
		Entrystore es=restTemplate.getForObject("https://api.publicapis.org/entries", Entrystore.class);
		Entry e=es.getEntries().get(0);
		Entry newe=new Entry(eDto.getTitle(), eDto.getDescription(), e.getApi(), e.getAuth(), e.isHttps(), e.getCors(), e.getLink());
		
        Entry saved=entryRepo.save(newe);
		return new ResponseEntity<Entry>(saved,HttpStatus.OK);
	}
	
	
	
	
	
	
}
