package sample;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.servlet.http.HttpServletRequest;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @RestController
  public static class HelloController {
    @GetMapping("/")
    public String index(
            @ParameterObject Params params,
            HttpServletRequest request
    ) {
      return String.format("""
                      Hello, %s!
                      You sent the header: %s
                      Actual header value: %s
                      """,
              params.testparam,
              params.testheader,
              request.getHeader("testheader"));
    }
  }

  public static final class Params {
    @Parameter(
            in = ParameterIn.HEADER,
            name = "testheader",
            description = "a header",
            example = "header-value",
            required = true)
    private final String testheader;
    @Parameter(
            name = "testparam",
            description = "a parameter",
            example = "param-value",
            required = true)
    private final String testparam;

    public Params(
            String testheader,
            String testparam
    ) {
      this.testheader = testheader;
      this.testparam = testparam;
    }

    public String getTestheader() {
      return testheader;
    }

    public String getTestparam() {
      return testparam;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) return true;
      if (obj == null || obj.getClass() != this.getClass()) return false;
      var that = (Params) obj;
      return Objects.equals(this.testheader, that.testheader) &&
             Objects.equals(this.testparam, that.testparam);
    }

    @Override
    public int hashCode() {
      return Objects.hash(testheader, testparam);
    }

    @Override
    public String toString() {
      return "Params[" +
             "testheader=" + testheader + ", " +
             "testparam=" + testparam + ']';
    }


  }
}
