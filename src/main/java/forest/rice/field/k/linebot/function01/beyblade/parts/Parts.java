package forest.rice.field.k.linebot.function01.beyblade.parts;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parts {
	String name;
	String type;
	String image;

	String attack;
	String defense;
	String stamina;
	String weight;
	String speed;
	String burst;

	@Override
	public String toString() {
		return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t", name, type, image, attack, defense, stamina,
				weight, speed, burst);
	}

	public static Map<String, Parts> createLayerMap(Document document) {
		Elements inner = document.getElementsByClass("inner");
		Element child = inner.get(0).child(3);
		Elements parts = child.getElementsByClass("js-matchHeigtht");

		Map<String, Parts> map = new HashMap<>();

		for (int i = 0; i < parts.size(); i++) {
			Element part = parts.get(i);
			Parts p = new Parts();

			p.name = part.getElementsByTag("h3").text();
			p.type = part.getElementsByTag("span").text();
			p.image = part.getElementsByTag("img").get(0).attr("src");

			p.attack = part.getElementsByTag("li").stream().filter(e -> e.hasClass("attack")).findFirst().get().text();
			p.defense = part.getElementsByTag("li").stream().filter(e -> e.hasClass("defense")).findFirst().get()
					.text();
			p.stamina = part.getElementsByTag("li").stream().filter(e -> e.hasClass("stamina")).findFirst().get()
					.text();
			p.weight = part.getElementsByTag("li").stream().filter(e -> e.hasClass("weight")).findFirst().get().text();
			p.speed = part.getElementsByTag("li").stream().filter(e -> e.hasClass("speed")).findFirst().get().text();
			p.burst = part.getElementsByTag("li").stream().filter(e -> e.hasClass("burst")).findFirst().get().text();

			map.put(p.name, p);
		}

		return map;
	}

	public static Map<String, Parts> createDiskMap(Document document) {
		Elements inner = document.getElementsByClass("inner");
		Element child = inner.get(0).child(5);
		Elements parts = child.getElementsByClass("js-matchHeigtht");

		Map<String, Parts> map = new HashMap<>();

		for (int i = 0; i < parts.size(); i++) {
			Element part = parts.get(i);
			Parts p = new Parts();

			p.name = part.getElementsByTag("h3").text();
			p.image = part.getElementsByTag("img").get(0).attr("src");

			p.attack = part.getElementsByTag("li").stream().filter(e -> e.hasClass("attack")).findFirst().get().text();
			p.defense = part.getElementsByTag("li").stream().filter(e -> e.hasClass("defense")).findFirst().get()
					.text();
			p.stamina = part.getElementsByTag("li").stream().filter(e -> e.hasClass("stamina")).findFirst().get()
					.text();
			p.weight = part.getElementsByTag("li").stream().filter(e -> e.hasClass("weight")).findFirst().get().text();
			p.speed = part.getElementsByTag("li").stream().filter(e -> e.hasClass("speed")).findFirst().get().text();
			p.burst = part.getElementsByTag("li").stream().filter(e -> e.hasClass("burst")).findFirst().get().text();

			map.put(p.name, p);
		}

		return map;
	}

	public static Map<String, Parts> createDriverMap(Document document) {
		Elements inner = document.getElementsByClass("inner");
		Element child = inner.get(0).child(7);
		Elements parts = child.getElementsByClass("js-matchHeigtht");

		Map<String, Parts> map = new HashMap<>();

		for (int i = 0; i < parts.size(); i++) {
			Element part = parts.get(i);
			Parts p = new Parts();

			p.name = part.getElementsByTag("h3").text();
			p.type = part.getElementsByTag("span").text();
			p.image = part.getElementsByTag("img").get(0).attr("src");

			p.attack = part.getElementsByTag("li").stream().filter(e -> e.hasClass("attack")).findFirst().get().text();
			p.defense = part.getElementsByTag("li").stream().filter(e -> e.hasClass("defense")).findFirst().get()
					.text();
			p.stamina = part.getElementsByTag("li").stream().filter(e -> e.hasClass("stamina")).findFirst().get()
					.text();
			p.weight = part.getElementsByTag("li").stream().filter(e -> e.hasClass("weight")).findFirst().get().text();
			p.speed = part.getElementsByTag("li").stream().filter(e -> e.hasClass("speed")).findFirst().get().text();
			p.burst = part.getElementsByTag("li").stream().filter(e -> e.hasClass("burst")).findFirst().get().text();

			map.put(p.name, p);
		}

		return map;
	}

}
