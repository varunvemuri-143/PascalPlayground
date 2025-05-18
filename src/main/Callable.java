package main;

import java.util.List;

public interface Callable {
    Object call(List<Object> args);
}
