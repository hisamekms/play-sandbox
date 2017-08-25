package controllers.admin;

import static org.assertj.core.api.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.route;
import static play.test.Helpers.start;
import static play.test.Helpers.stop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;

public class HomeControllerTest {

  Application application;

  @Before
  public void setUp() throws Exception {
    application = fakeApplication(inMemoryDatabase());
    start(application);
  }

  @After
  public void tearDown() throws Exception {
    stop(application);
  }

  @Test
  public void index() throws Exception {
    RequestBuilder request = fakeRequest().method("GET").uri("/");

    Result result = route(application, request);
    assertThat(contentAsString(result)).isEqualTo("admin");
  }

}