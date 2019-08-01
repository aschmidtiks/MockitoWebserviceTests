package classes;

import java.util.ArrayList;
import java.util.List;

public class WebServiceTestClass {

    public int id = 1;

    public List<String> liste = new ArrayList<>();

    public int addOne() {
        id += 1;

        return id;
    }
}
