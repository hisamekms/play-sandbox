package google.api;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.DriveScopes;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GoogleOauth2Credential {

  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  private GoogleAuthorizationCodeFlow flow() {
    return new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT,
        JSON_FACTORY,
        clientSecrets(),
        Arrays.asList(DriveScopes.DRIVE_READONLY)
    )
        .setAccessType("offline")
        .build();
  }

  private GoogleClientSecrets clientSecrets() {
    try {
      return GoogleClientSecrets.load(JSON_FACTORY,
          new InputStreamReader(
              GoogleOauth2Credential.class.getResourceAsStream("/client_secret.json")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String newAuthorizationUrl() {
    return flow().newAuthorizationUrl().setRedirectUri("http://localhost:9000/oauth2/google/callback").build();
  }
}
