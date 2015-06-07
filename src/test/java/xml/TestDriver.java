package xml;
//package my.xml;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//
//import com.thoughtworks.xstream.XStream;
//
//public class TestDriver {
//    public void output(int i, XStream xStream, Object obj) {
//        String xml = xStream.toXML(obj);
//        System.out.println(">>>第[ " + i + "]次输出\n");
//        System.out.println(xml + "\n");
//    }
//
//    @Test
//    public void test() throws Exception {
//        System.out.println("----------XStream学习:http://lavasoft.blog.51cto.com----------");
//        // 目标对象
//        Address address1 = new Address("郑州市经三路", "450001");
//        Address address2 = new Address("西安市雁塔路", "710002");
//        List<Address> addList = new ArrayList<Address>();
//        addList.add(address1);
//        addList.add(address2);
//        Profile profile = new Profile("软件工程师", "13512129933", "备注说明");
//        Person person = new Person("熔岩", "27", profile, addList);
//
//        // 转换装配
//        XStream xStream = new XStream();
//
//        output(0, xStream, person);
//        /************** 设置类别名 ****************/
//        xStream.alias("PERSON", Person.class);
//        xStream.alias("PROFILE", Profile.class);
//        xStream.alias("ADDRESS", Address.class);
//        output(1, xStream, person);
//
//        /************* 设置类成员的别名 ***************/
//        // 设置Person类的name成员别名Name
//        xStream.aliasField("Name", Person.class, "name");
//        /*
//         * [注意] 设置Person类的profile成员别名PROFILE,这个别名和Profile类的别名一致,
//         * 这样可以保持XStream对象可以从profile成员生成的xml片段直接转换为Profile成员,
//         * 如果成员profile的别名和Profile的别名不一致,则profile成员生成的xml片段不可
//         * 直接转换为Profile对象,需要重新创建XStream对象,这岂不给自己找麻烦?
//         */
//        xStream.aliasField("PROFILE", Person.class, "profile");
//        xStream.aliasField("ADDLIST", Person.class, "addlist");
//        xStream.aliasField("Add", Address.class, "add");
//        xStream.aliasField("Job", Profile.class, "job");
//        output(2, xStream, person);
//
//        /******* 设置类成员为xml一个元素上的属性 *******/
//        xStream.useAttributeFor(Address.class, "zipcode");
//        /************* 设置属性的别名 ***************/
//        xStream.aliasAttribute(Address.class, "zipcode", "Zipcode");
//        output(3, xStream, person);
//
//    }
//
//}
