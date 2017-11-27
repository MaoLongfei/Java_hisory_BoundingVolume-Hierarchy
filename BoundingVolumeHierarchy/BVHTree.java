package BVHTree;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

//public class BVHTree implements SpatialDataStructure {
public class BVHTree {
	
	List<BVHNode> recCenterList = new  ArrayList<BVHNode>();
	private BVHNode newNode;
//	private boolean areaFound = false;
	List<BVHNode> targetNodeList = new ArrayList<BVHNode>();;
	public class combinePair{
		int p1;
		int p2;
	}
	
	public static double nodeDistance(BVHNode p1, BVHNode p2) {  
        double dx = p1.recCenter.getX() - p2.recCenter.getX();  
        double dy = p1.recCenter.getY() - p2.recCenter.getY();  
        return Math.sqrt(dx * dx + dy * dy);  
    }  

	public BVHNode nodeCombine(BVHNode node1 , BVHNode node2){
		
		BVHNode rootNode = new BVHNode(node1 , node2);
		return rootNode;
		
	};
	
	
	public void oneStepProduce(){							
		int i = 0;
		int j = 0;
		combinePair pair = new combinePair();
		double tempDistance =Double.MAX_VALUE;
		for (i = 0; i < recCenterList.size() ; i++){
			for ( j = i + 1; j < recCenterList.size() ; j++ ){
				if (nodeDistance(recCenterList.get(i) , recCenterList.get(j)) < tempDistance){
					tempDistance = nodeDistance(recCenterList.get(i) , recCenterList.get(j));
					pair.p1 = i;
					pair.p2 = j;						
				}
			}
		}
		
		newNode = nodeCombine(recCenterList.get(pair.p1) , recCenterList.get(pair.p2)); 
		recCenterList.remove(pair.p2);
		recCenterList.remove(pair.p1);			//p2 > p1 ,remove pair.p2 first
		recCenterList.add(newNode);		
	}
	
	public void treeProduce(){
		
			do 
			oneStepProduce();
			while (recCenterList.size() > 1 );
	}
	
	
	public boolean areaSearchCondition(BVHNode areaNode, Point2D p1, Point2D p2){
		
		if( (p1.getX() <= areaNode.xMin || p2.getX() <= areaNode.xMin) && ( (p1.getX() >= areaNode.xMax || p2.getX() >= areaNode.xMax) )
			&& ((p1.getY() >= areaNode.yMin) && (p1.getY() <= areaNode.yMax ) || (p2.getY() >= areaNode.yMin) && (p2.getY() <= areaNode.yMax ))
				){
			return true;
		}
		if ((p1.getY() <= areaNode.yMin || p2.getY() <= areaNode.yMin) && ( (p1.getY() >= areaNode.yMax || p2.getY() >= areaNode.yMax) )
			&& ((p1.getX() >= areaNode.xMin) && (p1.getX() <= areaNode.xMax ) || (p2.getX() >= areaNode.xMin) && (p2.getX() <= areaNode.xMax ))
				){
			return true;
		}
		else return false;
	}
	
	public void areaSearch(BVHNode areaNode, Point2D p1, Point2D p2){
		

		if ( (areaNode.child1 != null) && (areaNode.child2 != null) ) {
			areaSearch(areaNode.child1 , p1 ,p2);
			areaSearch(areaNode.child2 , p1 ,p2);			
		}
		else {
			if (areaSearchCondition(areaNode , p1, p2)){
				targetNodeList.add(areaNode);
			}
		}

	}
	
}
