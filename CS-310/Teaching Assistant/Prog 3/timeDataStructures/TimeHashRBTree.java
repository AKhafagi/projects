package timeDataStructures;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import data_structures.Hash;
import data_structures.HashI;
import data_structures.LinkedList;
import data_structures.RedBlackTree;
import dns_resolver.IPAddress;
import dns_resolver.URL;

public class TimeHashRBTree {

	public static void main(String[] args) {
		LinkedList<IPAddress> toFind = new LinkedList<>();
		
		// we read the file once to get the list of addresses that we will find
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/data/top-250k.ip"));
			String line = null;
			while ((line=in.readLine()) != null) {
				String [] parts = line.split("\t");
				IPAddress ip = new IPAddress(parts[1]);
				toFind.add(ip);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// next, we load our hash and time it. We make the hash quite large to start with so we shouldn't have to resize it
		HashI<IPAddress, URL> hash = new Hash<>(16);
		long start = System.currentTimeMillis();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/data/top-250k.ip"));
			String line = null;
			while ((line=in.readLine()) != null) {
				String [] parts = line.split("\t");
				IPAddress ip = new IPAddress(parts[1]);
				URL u = new URL(parts[0]);
				hash.add(ip, u);
				toFind.add(ip);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long stop = System.currentTimeMillis();
		
		System.out.println("Loading the hash took " + (stop-start) + " millseconds");
		
		start = System.currentTimeMillis();
		for (IPAddress i : toFind)
			hash.getValue(i);
	
		stop = System.currentTimeMillis();
		
		System.out.println("Searching the hash took " + (stop-start) + " milliseconds");
		
		
		hash = null;
		
		RedBlackTree<IPAddress, URL> rbt = new RedBlackTree<>();
		
		
		// next, we load the Red Black Tree
		start = System.currentTimeMillis();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/data/top-250k.ip"));
			String line = null;
			while ((line=in.readLine()) != null) {
				String [] parts = line.split("\t");
				IPAddress ip = new IPAddress(parts[1]);
				URL u = new URL(parts[0]);
				rbt.add(ip, u);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		stop = System.currentTimeMillis();
		
		System.out.println("Loading the Red Black tree took " + (stop-start) + " millseconds");
		
		start = System.currentTimeMillis();
		for (IPAddress i : toFind)
			rbt.getValue(i);
	
		stop = System.currentTimeMillis();
		
		System.out.println("Searching the Red Black Tree took " + (stop-start) + " milliseconds");
		
		rbt = null;
		
		HashMap<IPAddress, URL> hm = new HashMap<>();
		
		// next, we load the Red Black Tree
		start = System.currentTimeMillis();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/data/top-250k.ip"));
			String line = null;
			while ((line=in.readLine()) != null) {
				String [] parts = line.split("\t");
				IPAddress ip = new IPAddress(parts[1]);
				URL u = new URL(parts[0]);
				hm.put(ip, u);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stop = System.currentTimeMillis();
		
		System.out.println("Loading the hash Map took " + (stop-start) + " millseconds");
		
		start = System.currentTimeMillis();
		for (IPAddress i : toFind)
			hm.get(i);
	
		stop = System.currentTimeMillis();
		
		System.out.println("Searching the HashMap took " + (stop-start) + " milliseconds");
		
		hm = null;

		
		TreeMap<IPAddress, URL> tm = new TreeMap<>();
		
		// next, we load the Red Black Tree
		start = System.currentTimeMillis();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/data/top-250k.ip"));
			String line = null;
			while ((line=in.readLine()) != null) {
				String [] parts = line.split("\t");
				IPAddress ip = new IPAddress(parts[1]);
				URL u = new URL(parts[0]);
				tm.put(ip, u);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stop = System.currentTimeMillis();
		
		System.out.println("Loading the Tree Map took " + (stop-start) + " millseconds");
		
		start = System.currentTimeMillis();
		for (IPAddress i : toFind)
			tm.get(i);
	
		stop = System.currentTimeMillis();
		
		System.out.println("Searching the Tree Map took " + (stop-start) + " milliseconds");
		

		
	}

}
