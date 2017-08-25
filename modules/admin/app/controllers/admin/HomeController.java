package controllers.admin;

import static play.mvc.Results.ok;

import play.mvc.Result;

public class HomeController {

  public Result index() {
    return ok("admin");
  }
}
