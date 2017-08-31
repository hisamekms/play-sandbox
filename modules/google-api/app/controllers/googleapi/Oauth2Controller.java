package controllers.googleapi;

import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

import google.api.GoogleOauth2Credential;
import play.mvc.Result;

public class Oauth2Controller {
  public Result callback() {
    // TODO
    return ok("callback");
  }

  public Result index() {
    String newAuthorizationUrl = new GoogleOauth2Credential().newAuthorizationUrl();
    return redirect(newAuthorizationUrl);
  }

}
