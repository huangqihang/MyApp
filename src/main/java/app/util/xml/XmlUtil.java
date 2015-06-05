package app.util.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.config.Application;
import app.model.Address;
import app.model.Person;

public class XmlUtil {
	
	private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);
	
	public static JAXBContext createJaxbContext(Class<?> toBeBound) throws JAXBException {
		return JAXBContext.newInstance(toBeBound);
	}
	
	/**
	 * @param object
	 * @return
	 */
	public static String toXML(Object object) {
		StringWriter sw = new StringWriter(1000);
		// 手动添加XML头部，解决standalone="yes"问题
		//sw.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(Application.NEW_LINE);
		try {
			JAXBContext context = createJaxbContext(object.getClass());
			Marshaller marshaller = createMarshaller(context);
			marshaller.marshal(object, sw);
			sw.flush();
		} catch (JAXBException e) {
			logger.error("", e);
		}
		
		return sw.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T objectFromXml(String xml, T rootClass) {
		
		try {
			JAXBContext context = createJaxbContext(rootClass.getClass());
			Unmarshaller unmarshaller = createUnmarshaller(context);
			return (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return null;
	}

	private static Marshaller createMarshaller(JAXBContext context)
			throws JAXBException, PropertyException {
		Marshaller marshaller = context.createMarshaller();
		// XML编码
		marshaller.setProperty(Marshaller.JAXB_ENCODING, Application.DEFAULT_ENCODING);
		// 格式化
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// 不输出XML头部，序列化后手动添加
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		return marshaller;
	}
	
	private static Unmarshaller createUnmarshaller(JAXBContext context)
			throws JAXBException {
		return context.createUnmarshaller();
	}
	
	public static void main(String[] args) {
		Person person = new Person(1, "张三", 20, new Date());
		person.addAddress(new Address("四川","洪雅","街道1","123456", "home"));
		person.addAddress(new Address("北京","朝阳","街道2","100100", "work"));
		
		String xml = XmlUtil.toXML(person);
		System.out.println(xml);
		
		Person p = XmlUtil.objectFromXml(xml, new Person());
		System.out.println(p.getAddress().size());
	}
	
}
