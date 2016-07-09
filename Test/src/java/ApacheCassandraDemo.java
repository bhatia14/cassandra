package java;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

public class ApacheCassandraDemo {

	private Cluster cluster;
	private Session session;
	
	public void connect(final String node){
		cluster = Cluster.builder().addContactPoint(node).build();
		session = cluster.connect();
		Metadata metadata = cluster.getMetadata();
		System.out.println("Connected to cluster"+ metadata.getClusterName());
		for(Host host : metadata.getAllHosts()){
			System.out.println(String.format("Data Center: "+ host.getDatacenter() 
									+ "Host: "+ host.getAddress()
									+ "Rack: "+ host.getRack()
									+ "Version: " + host.getCassandraVersion()));
		}
	}
	
	public void close(){
		cluster.close();
		session.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApacheCassandraDemo apacheCassandraDemo=new  ApacheCassandraDemo();
		apacheCassandraDemo.connect("127.0.0.1");
		apacheCassandraDemo.close();
	}

}
