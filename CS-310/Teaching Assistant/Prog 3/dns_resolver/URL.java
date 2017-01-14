package dns_resolver;

/**
 * A URL Object that just knows how to compare URLs
 * @author redwards
 *
 */
public class URL implements Comparable<URL> {
	String url;
	public URL (String url) {
		this.url = url;
	}
	
	public int compareTo(URL u) {
		return u.url.compareTo(url);
	}
	
	public boolean equals(URL u) {
		return u.url.equals(url);
	}
	
	public int hashCode() {
		return url.hashCode();
	}
	
	public String toString() {
		return url;
	}
	
	
}
