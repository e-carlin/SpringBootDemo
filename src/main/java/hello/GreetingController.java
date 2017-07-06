package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong(); //Longs are 64 bits. An atomic long guarantees
    // that all 64 bits are handled as a single unit. This is for concurrency so if a read and write happen
    // at the same time the reader doesn't get 32 "up to date" bits and 32 out of date bits

    @RequestMapping(value="/greeting", method= RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}