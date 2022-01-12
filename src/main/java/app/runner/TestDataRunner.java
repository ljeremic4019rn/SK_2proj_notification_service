package app.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {
        //Insert roles


    }
}
