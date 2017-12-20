package com.harambase.support.charts;

import com.harambase.common.constant.Type;
import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.pojo.base.Advise;
import com.harambase.pioneer.pojo.base.Person;
import com.harambase.pioneer.pojo.base.TranscriptBase;
import it.uniroma1.dis.wsngroup.gexf4j.core.EdgeType;
import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf;
import it.uniroma1.dis.wsngroup.gexf4j.core.Graph;
import it.uniroma1.dis.wsngroup.gexf4j.core.Mode;
import it.uniroma1.dis.wsngroup.gexf4j.core.Node;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.Attribute;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeClass;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeList;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeType;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.GexfImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.StaxGraphWriter;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.data.AttributeListImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.viz.PositionImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.NodeShape;
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.Position;

import java.io.*;
import java.util.Calendar;
import java.util.List;


public class StaticGexfGraph {
	public static String graphGenerator(List<Person> personList,
									  List<Course> courseList,
									  List<TranscriptBase> transcriptList,
									  List<Advise> adviseList){
		Gexf gexf = new GexfImpl();
		Calendar date = Calendar.getInstance();

		gexf.getMetadata()
				.setLastModified(date.getTime())
				.setCreator("pioneer.org")
				.setDescription("A relation graph");
		gexf.setVisualization(true);

		Graph graph = gexf.getGraph();
		graph.setDefaultEdgeType(EdgeType.UNDIRECTED).setMode(Mode.STATIC);

		AttributeList attrList = new AttributeListImpl(AttributeClass.NODE);
		graph.getAttributeLists().add(attrList);

		Attribute attType  = attrList.createAttribute("type"  , AttributeType.INTEGER, "type");
		Attribute attValue = attrList.createAttribute("value" , AttributeType.STRING, "value");
		Attribute attID    = attrList.createAttribute("id"    , AttributeType.STRING, "id");
		Attribute attCRN   = attrList.createAttribute("crn"   , AttributeType.STRING, "crn");
		int index = 0;
		//设置person node
		for(Person p: personList){
			Node pNode = graph.createNode(p.getUserid());
			pNode.setLabel(p.getLastname()+p.getFirstname()).setSize(20);
			int type = 0;
			if(p.getType().contains("s")) {
				pNode.getAttributeValues()
						.addValue(attID, p.getUserid())
						.addValue(attType, String.valueOf(Type.STUDENT.getV()))
						.addValue(attValue, p.getInfo());
				type = Type.STUDENT.getV();
			}
			else if(p.getType().contains("f")) {
				pNode.getAttributeValues()
						.addValue(attID, p.getUserid())
						.addValue(attType, String.valueOf(Type.FACULTY.getV()))
						.addValue(attValue, p.getInfo());
				type = Type.FACULTY.getV();
			}
			else if(p.getType().contains("a")) {
				pNode.getAttributeValues()
						.addValue(attID, p.getUserid())
						.addValue(attType, String.valueOf(Type.ADMINISTRATOR.getV()))
						.addValue(attValue, p.getInfo());
				pNode.setSize(0);
				type = Type.ADMINISTRATOR.getV();
			}

			pNode.getShapeEntity().setNodeShape(NodeShape.DIAMOND);
			pNode.setPosition(generatePosition(type,index+1));
			index++;
		}
		//设置course Node
		for(Course c: courseList){
			Node cNode = graph.createNode(c.getCrn());
			int value = c.getCapa()-c.getRemain();
			cNode.setLabel(c.getName()).setSize(10 + value*5);
			cNode.getAttributeValues()
					.addValue(attCRN, c.getCrn())
					.addValue(attValue, String.valueOf(value))
					.addValue(attType, "0");
			
			cNode.getShapeEntity().setNodeShape(NodeShape.TRIANGLE);
			cNode.setPosition(generatePosition(0,index));
			index++;
		}

		index = 0;
		//设置COURSE_PERSON_connection
		for(Person p: personList) {
			String userid = p.getUserid();
			String type = p.getType();
			Node pNode = graph.getNode(userid);
			if(type.contains("s")) {
				for (TranscriptBase t : transcriptList) {
					if (t.getStudentid().equals(userid)) {
						Node cNode = graph.getNode(t.getCrn());
						pNode.connectTo(String.valueOf(index), cNode);
						index++;
					}
				}
			}else if(type.contains("f")) {
				for(Course c: courseList){
					if(c.getFacultyid().equals(userid)){
						Node cNode = graph.getNode(c.getCrn());
						pNode.connectTo(String.valueOf(index), cNode);
						index++;
					}
				}
			}
		}
		
		//设置FACULTY_STUDENT_CONNECTION
		for(Advise a: adviseList){
			Node sNode = graph.getNode(a.getStudentid());
			Node fNode = graph.getNode(a.getFacultyid());
			sNode.connectTo(String.valueOf(index), fNode);
			index++;
		}
		
		

		StaxGraphWriter graphWriter = new StaxGraphWriter();
		String path = StaticGexfGraph.class.getResource("").getPath();



		try {
			File f = new File(path + "/static_graph_sample.gexf");
			Writer out =  new FileWriter(f, false);

			graphWriter.writeToStream(gexf, out, "UTF-8");
//			System.out.println(f.getAbsolutePath());

			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = "";
			StringBuffer buffer = new StringBuffer();
			while((line=br.readLine())!=null)
				buffer.append(line);

			f.delete();
			return buffer.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	private static Position generatePosition(int type, int index){
		PositionImpl position = new PositionImpl();
		double x = 10*type + index;
		double y = 500 + index*2;

		position.setX(Float.parseFloat(String.valueOf(x)));
		position.setY(Float.parseFloat(String.valueOf(y)));

		return position;
	}
}
