package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import common.CommonFunctoins;
import model.GroupData;

import java.util.ArrayList;

public class Generaton {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-c"})
    int count;

    public static void main(String[] args) {
      var generator =  new Generaton();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
      generator.run();
    }

    private void run() {
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


    private Object GenerateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData().withName(CommonFunctoins.randomString(i * 10))
                    .withFooter(CommonFunctoins.randomString(i * 10))
                    .withHeader(CommonFunctoins.randomString(i * 10)));
        }
        return result;
    }

    private Object GenerateClients() {
        return null;
    }

    private void save(Object data) {

    }
}
