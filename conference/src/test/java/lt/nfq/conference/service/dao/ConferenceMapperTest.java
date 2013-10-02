package lt.nfq.conference.service.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import lt.nfq.conference.domain.Conference;

import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConferenceMapperTest extends DaoTestBase {
	
	private ConferenceMapper mapper;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	@Before
	public void setup() {
		mapper = sqlSession.getMapper(ConferenceMapper.class);
	}
		
	@Test
	public void testGetConference() {
		Conference conference = mapper.getConference(1);
		assertEquals(Integer.valueOf(1), conference.getId());
		assertEquals("Conference B", conference.getName());
		assertEquals("2013-10-01 17:00:00", formatDate(conference.getStartDate()));
		assertEquals("2013-10-02 18:00:00", formatDate(conference.getEndDate()));
	}
	
	@Test
	public void testGetConferenceNonExisting() {
		Conference conference = mapper.getConference(10);
		assertNull(conference);
	}

	@Test
	public void testGetConferences() {
		List<Conference> result = mapper.getConferences();
		assertEquals(3, result.size());
		assertTrue(containsConference(result, "Conference A", "2013-09-30 17:00:00", "2013-09-30 18:00:00"));
		assertTrue(containsConference(result, "Conference B", "2013-10-01 17:00:00", "2013-10-02 18:00:00"));
		assertTrue(containsConference(result, "Conference C", "2013-10-20 00:00:00", "2013-10-30 00:00:00"));
	}

	@Test
	public void testGetConferencesByDates() {
		List<Conference> result = mapper.getConferencesByDates(parseDate("2013-10-01 00:00:00"), parseDate("2013-10-03 00:00:00"));
		assertEquals(1, result.size());
		assertTrue(containsConference(result, "Conference B", "2013-10-01 17:00:00", "2013-10-02 18:00:00"));
	}

	@Test
	public void testUpdateConference() {
		int id = 1;
		String name = "New Conference name";
		String fromDate = "2013-11-10 10:00:00";
		String endDate = "2013-11-11 12:00:00";

		Conference conference = new Conference();
		conference.setId(id);
		conference.setName(name);
		conference.setStartDate(parseDate(fromDate));
		conference.setEndDate(parseDate(endDate));
		assertEquals(1, mapper.updateConference(conference));
		
		Conference conferenceResult = mapper.getConference(id);
		assertEquals(Integer.valueOf(id), conferenceResult.getId());
		assertEquals(name, conferenceResult.getName());
		assertEquals(fromDate, formatDate(conferenceResult.getStartDate()));
		assertEquals(endDate, formatDate(conferenceResult.getEndDate()));
	}

	@Test
	public void testUpdateConferenceNonExisting() {
		Conference conference = new Conference();
		conference.setId(10);
		conference.setName("New Conference name");
		conference.setStartDate(parseDate("2013-11-10 10:00:00"));
		conference.setEndDate(parseDate("2013-11-11 12:00:00"));
		assertEquals(0, mapper.updateConference(conference));
	}

	@Test(expected = PersistenceException.class)
	public void testUpdateConferenceViolatingConstraints() {
		Conference conference = new Conference();
		conference.setId(1);
		conference.setName("New Conference name");
		conference.setStartDate(null);
		conference.setEndDate(null);
		mapper.updateConference(conference);
	}

	@Test
	public void testInsertConference() {
		String name = "New Conference name";
		String fromDate = "2013-11-10 10:00:00";
		String endDate = "2013-11-11 12:00:00";

		Conference conference = new Conference();
		conference.setName(name);
		conference.setStartDate(parseDate(fromDate));
		conference.setEndDate(parseDate(endDate));
		assertEquals(1, mapper.insertConference(conference));
		
		Assert.assertNotNull(conference.getId());
		
		Conference conferenceResult = mapper.getConference(conference.getId());
		assertEquals(conference.getId(), conferenceResult.getId());
		assertEquals(name, conferenceResult.getName());
		assertEquals(fromDate, formatDate(conferenceResult.getStartDate()));
		assertEquals(endDate, formatDate(conferenceResult.getEndDate()));
	}

	@Test(expected = PersistenceException.class)
	public void testInsertConferenceViolatingConstraints() {
		Conference conference = new Conference();
		conference.setName("New Conference name");
		conference.setStartDate(null);
		conference.setEndDate(null);
		mapper.insertConference(conference);
	}

	private boolean containsConference(Collection<Conference> conferences, String name, String startDate, String endDate) {
		for (Conference conference : conferences) {
			if (name.equals(conference.getName())
					&& startDate.equals(formatDate(conference.getStartDate()))
					&& startDate.equals(formatDate(conference.getStartDate()))) {
				return true;
			}
		}
		return false;
	}

	private String formatDate(Date date) {
		return dateFormat.format(date);
	}

	private Date parseDate(String date) {
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalud date format");
		}
	}

}
