package sample;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @RestController
  public static class HelloController {
    @GetMapping("/")
    public String index(
            @ParameterObject Params params
    ) {
      return String.format("""
                      Hello, %s!
                      You sent the header: %s
                      """,
              params.testparam,
              params.testheader);
    }
  }

  public record Params(
          @Parameter(
                  in = ParameterIn.HEADER,
                  name = "testheader",
                  description = "a header",
                  example = "header-value",
                  required = true)
          @RequestHeader("testheader")
          String testheader,

          @Parameter(
                  name = "testparam",
                  description = "a parameter",
                  example = "param-value",
                  required = true)
          String testparam
  ) {
  }
}
