package dns_resolver;

public class IPAddress {

	int network;
	int subnet;
	int subnet2;
	int host;

	public IPAddress(String ip) {
		String parts[] = ip.split("\\.");
		try {
			network = Integer.parseInt(parts[0]);
			subnet = Integer.parseInt(parts[1]);
			subnet2 = Integer.parseInt(parts[2]);
			host = Integer.parseInt(parts[3]);
		}
		catch (ArrayIndexOutOfBoundsException e)  {
			System.err.println("There was an error splitting '" + ip + "' to its components");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + host;
		result = prime * result + network;
		result = prime * result + subnet;
		result = prime * result + subnet2;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IPAddress other = (IPAddress) obj;
		if (host != other.host)
			return false;
		if (network != other.network)
			return false;
		if (subnet != other.subnet)
			return false;
		if (subnet2 != other.subnet2)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IPAddress [network=" + network + ", subnet=" + subnet + ", subnet2=" + subnet2 + ", host=" + host + "]";
	}



}
