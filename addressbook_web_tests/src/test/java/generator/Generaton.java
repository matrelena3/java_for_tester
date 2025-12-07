package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctoins;
import model.ClientData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generaton {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-c"})
    int count;

    public static void main(String[] args) throws IOException {
      var generator =  new Generaton();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
      generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return GenerateGroups();
        } else if ("clients".equals(type)) {
            return GenerateClients();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }


    private Object GenerateGroups() {
        return  generateData(() -> new GroupData()
                    .withName(CommonFunctoins.randomString(10))
                    .withFooter(CommonFunctoins.randomString(10))
                    .withHeader(CommonFunctoins.randomString(10)));
    }

    private Object GenerateClients() {
        return  generateData(() -> new ClientData()
                    .withName(CommonFunctoins.randomString(10), CommonFunctoins.randomString(10))
                    .withAddress(CommonFunctoins.randomString(10))
                    .withHome(CommonFunctoins.randomString(10))
                    .withEmail(CommonFunctoins.randomString(10))
                    .withPhoto(CommonFunctoins.randomFile("src/test/resources/images")));
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        var json = mapper.writeValueAsString(data);

        try (var writer = new FileWriter(output)) {
            writer.write(json);
        }
        } else if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } else if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        }else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }
}
