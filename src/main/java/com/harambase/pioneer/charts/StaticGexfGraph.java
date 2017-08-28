package com.harambase.pioneer.charts;

import com.harambase.common.constant.Type;
import com.harambase.pioneer.pojo.Advise;
import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.Transcript;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.List;


public class StaticGexfGraph {
	public static void graphGenerator(List<Person> personList,
									  List<Course> courseList,
									  List<Transcript> transcriptList,
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

		Attribute attType = attrList.createAttribute("type"  , AttributeType.INTEGER, "type");
		Attribute attid   = attrList.createAttribute("id"  , AttributeType.STRING, "id");
		Attribute attcrn   = attrList.createAttribute("crn"  , AttributeType.STRING, "crn");
		int index = 0;
		//设置person node
		for(Person p: personList){
			Node pNode = graph.createNode(p.getUserid());
			pNode.setLabel(p.getLastname()+p.getFirstname()).setSize(20);
			int type = 0;
			if(p.getType().equals(Type.STUDENT.getM())) {
				pNode.getAttributeValues()
						.addValue(attid, p.getUserid())
						.addValue(attType, String.valueOf(Type.STUDENT.getV()));
				type = Type.STUDENT.getV();
			}
			else if(p.getType().equals(Type.FACULTY.getM())) {
				pNode.getAttributeValues()
						.addValue(attid, p.getUserid())
						.addValue(attType, String.valueOf(Type.FACULTY.getV()));
				type = Type.FACULTY.getV();
			}
			else if(p.getType().equals(Type.ADMINISTRATOR.getM())) {
				pNode.getAttributeValues()
						.addValue(attid, p.getUserid())
						.addValue(attType, String.valueOf(Type.ADMINISTRATOR.getV()));
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
			cNode.setLabel(c.getName()).setSize(20);
			cNode.getAttributeValues().addValue(attcrn, c.getCrn()).addValue(attType, "0");
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
			if(type.equals(Type.STUDENT.getM())) {
				for (Transcript t : transcriptList) {
					if (t.getStudentid().equals(userid)) {
						Node cNode = graph.getNode(t.getCrn());
						pNode.connectTo(String.valueOf(index), cNode);
						index++;
					}
				}
			}else if(type.equals(Type.FACULTY.getM())) {
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
		//D:\Project\gitProjects
		//C:\Users\linsh\Documents\GitHub\Harambase_Project
		String path = StaticGexfGraph.class.getResource("").getPath();
		System.out.println(path);
		File f = new File(path + "../../../../../../../pioneer/static/data/static_graph_sample.gexf");
		Writer out;

		//File f = new File("D:\\Project\\gitProjects\\pioneer\\src\\main\\webapp\\static\\data\\static_graph_sample.gexf");
		try {
			if (!f.exists()) f.createNewFile();
			out =  new FileWriter(f, false);
			graphWriter.writeToStream(gexf, out, "UTF-8");
			System.out.println(f.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

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