package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
//import com.sun.org.apache.xpath.internal.operations.String;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter (names = "-c", description = "Group count")
    public int count;

    @Parameter (names = "-f", description = "target file")
    public String file; //jcommander не поддерживает напрямую работу с файлами

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main (String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
//        new JCommander(generator, args);
//        JCommander.newBuilder()
//                .addObject(generator)
//                .build()
//                .parse(args);
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(java.lang.String.valueOf(args));
        } catch (ParameterException ex) {
            jCommander.usage(); //выдаст список доступных опций
            return;
        }
        generator.run();
//        int count = Integer.parseInt(args[0]); //принимаем массив строк и преобразуем в число
////        File file = new File(args[1]);

//        List<GroupData> groups = generateGroups(count);
//        save(groups, file);

    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        if (format.equals("csv")) {
            saveAsCsv(groups, new File (String.valueOf(file)));
        } else if (format.equals("xml")){
            saveAsXml(groups, new File (java.lang.String.valueOf(file)));
        } else {
            System.out.println("unrecognised format" + format);
        }
//        save(groups, new File (file));
    }

    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        String xml = xstream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
//        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (GroupData group : groups)
            writer.write(java.lang.String.format("%s,%s,%s\n", group.getName(), group.getHeader(), group.getFooter()));
        writer.close();

    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++){
            groups.add(new GroupData().withName(java.lang.String.format("test %s", i))
                    .withHeader(String.format("header %s", i)).withFooter(java.lang.String.format("footer %s",i)));
        }
        return groups;
    }
}