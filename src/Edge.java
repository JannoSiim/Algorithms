import java.util.ArrayList;


public class Edge {
	
	private String tailNode;
	private String headNode;
	
	public Edge(String tailNode, String headNode) {
		this.tailNode = tailNode;
		this.headNode = headNode;
	}
	
	public String getTail() {
		return tailNode;
	}

	public String getHead() {
		return headNode;
	}
	
	public static int getDegree(ArrayList<Edge> edges, String node){
		int degree = 0;
		
		for(Edge e : edges){
			if(e.tailNode.equals(node)){
				degree++;
			}
			if(e.headNode.equals(node)){
				degree++;
			}
		}
		
		return degree;
	}
}
