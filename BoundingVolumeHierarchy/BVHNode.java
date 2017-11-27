package BVHTree;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

public class BVHNode {

	private Point2D pointA;
	private Point2D pointB;
	private Point2D pointC;
	
	double xMin;
	double xMax;
	double yMin;
	double yMax;
	Point2D recCenter;
	BVHNode child1;
	BVHNode child2;
	
	public BVHNode(Point2D a, Point2D b, Point2D c){

		child1 = null;
		child2 = null;
		
		double tempA, tempB, tempC,temp;

		tempA = a.getX();
		tempB = b.getX();
		tempC = c.getX();
		
		if ( tempA > tempB ){
			temp  = tempB;
			tempB = tempA;
			tempA = temp;
		}
		
		if ( tempB > tempC ){
			temp  = tempC;
			tempC = tempB;
			tempB = temp;
		}
		

		if ( tempA > tempB ){
			temp  = tempB;
			tempB = tempA;
			tempA = temp;
		}
		
		this.xMin = tempA;
		this.xMax = tempC;
		
		tempA = a.getY();
		tempB = b.getY();
		tempC = c.getY();
		
		if ( tempA > tempB ){
			temp  = tempB;
			tempB = tempA;
			tempA = temp;
		}
		
		if ( tempB > tempC ){
			temp  = tempC;
			tempC = tempB;
			tempB = temp;
		}
		

		if ( tempA > tempB ){
			temp  = tempB;
			tempB = tempA;
			tempA = temp;
		}
		
		this.yMin = tempA;
		this.yMax = tempC;
		
//		System.out.println(xMin);
//		System.out.println(xMax);
//		System.out.println(yMin);
//		System.out.println(yMax);
		
		recCenter = new Point2D ((xMin + xMax)/2 , (yMin + yMax) / 2);
//		System.out.println(recCenter.getX() + " , " + recCenter.getY());
		
	}
	public BVHNode( BVHNode node1, BVHNode node2){
		
		if (node1.xMin > node2.xMin){
			this.xMin = node2.xMin;
		}
		else{
			this.xMin = node1.xMin;
		}
		if(node1.yMin > node2.yMin){
			this.yMin = node2.yMin;
		}
		else{
			this.yMin = node1.yMin;
		}
		
		if (node1.xMax > node2.xMax){
			this.xMax = node1.xMax;
		}
		else{
			this.xMax = node2.xMax;
		}
		if(node1.yMax > node2.yMax){
			this.yMax = node1.yMax;
		}
		else{
			this.yMax = node2.yMax;
		}
		recCenter = new Point2D ((xMin + xMax)/2 , (yMin + yMax) / 2);
		
		this.child1 = node1;
		this.child2 = node2;
		
		
	}
	
	public void printBVHNode (){
		System.out.println(this.xMax);
		System.out.println(this.xMin);
		System.out.println(this.yMax);
		System.out.println(this.yMin);
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
	};
	
	public static void main(String args[]){
			
		
		Point2D a1, a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12, a13,a14,a15;
		a1 = new Point2D(1,1);
		a2 = new Point2D(2,3);
		a3 = new Point2D(3,1);
		a4 = new Point2D(4,1);
		a5 = new Point2D(4,4);
		a6 = new Point2D(5,1);
		a7 = new Point2D(9,1);
		a8 = new Point2D(9,4);
		a9 = new Point2D(10,2);
		a10 = new Point2D(11,3);
		a11 = new Point2D(12,4);
		a12 = new Point2D(13,3);
		a13 = new Point2D(14,1);
		a14 = new Point2D(14,2);
		a15 = new Point2D(15,1);

		BVHNode test1 = new BVHNode(a1, a2, a3);
		BVHNode test2 = new BVHNode(a4, a5, a6);
		BVHNode test3 = new BVHNode(a7, a8, a9);
		BVHNode test4 = new BVHNode(a10, a11, a12);
		BVHNode test5 = new BVHNode(a13, a14, a15);
		
		List<BVHNode> triangles= new ArrayList();
				
		triangles.add(test1);
		triangles.add(test2);
		triangles.add(test3);
		triangles.add(test4);
		triangles.add(test5);
		
		BVHTree testTree = new BVHTree();
		
		for(int i = 0 ; i < triangles.size(); i++ ){
			testTree.recCenterList.add(triangles.get(i));			
		}
		
		testTree.treeProduce();
		
//		testTree.recCenterList.get(0).printBVHNode();
		
		
		Point2D start = new Point2D( 3, 3 );
		Point2D end = new Point2D( 11, 2);
		
		testTree.areaSearch(testTree.recCenterList.get(0), start, end);
		
		for(int i = 0 ; i < testTree.targetNodeList.size() ; i++){
			testTree.targetNodeList.get(i).printBVHNode();
		}
	}
}
