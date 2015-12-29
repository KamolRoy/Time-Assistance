package com.devcomol.ta.schedulejob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Component
public class RSSNewsFeed {

	private Deque<NewsModel> newsDB;

	RSSNewsFeed() {
		newsDB = new ConcurrentLinkedDeque<>();
		try {
			getRSSNews();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("Problem while retrieving RSS Feed");
			e.printStackTrace();
		}
		System.out.println(">>News DB created...");
	}

	public List<NewsModel> getNewsList() {
		List<NewsModel> newsListFull = new ArrayList<>();
		List<NewsModel> newsList = new ArrayList<>();
		
		
		for (NewsModel newsModel : newsDB) {
			newsListFull.add(newsModel);
		}
		
		for (int i = 0; i <=5; i++) {
			newsList.add(newsListFull.get(i));
		}
		
		return newsList;
	}
	
	public List<NewsModel> getNewsListFull() {
		List<NewsModel> newsListFull = new ArrayList<>();
		for (NewsModel newsModel : newsDB) {
			newsListFull.add(newsModel);
		}
		return newsListFull;
	}

	@Scheduled(cron = "0 0/20 * * * ?")
	public void getRSSNews() throws SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("http://www.abc.net.au/local/rss/adelaide/news.xml");

		NodeList Newsitems = doc.getElementsByTagName("item");

		for (int i = 1; i <= 10; i++) {
			Element NewsItem = (Element) Newsitems.item(i);

			Element title = (Element) NewsItem.getElementsByTagName("title").item(0);
			Element link = (Element) NewsItem.getElementsByTagName("link").item(0);
			Element description = (Element) NewsItem.getElementsByTagName("description").item(0);

			newsDB.addFirst(
					new NewsModel(title.getTextContent(), link.getTextContent(), description.getTextContent()));

			if (newsDB.size() > 10) {
				newsDB.removeLast();
			}
		}
		System.out.println(">>News information updated....");
	}
}
