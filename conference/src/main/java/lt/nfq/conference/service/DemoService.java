package lt.nfq.conference.service;

import lt.nfq.conference.domain.Demo;
import lt.nfq.conference.service.dao.DemoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
	@Autowired 
	private DemoMapper demoMapper;
	
	public Demo getDemoData(int id) {
		return demoMapper.getDemoData(id);
	}
}
