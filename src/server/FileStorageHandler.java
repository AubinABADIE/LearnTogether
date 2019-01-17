package server;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.services.drive.model.File;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class FileStorageHandler {
    private static final String APPLICATION_NAME = "Learn Together";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_METADATA_READONLY);

    private static final String CREDENTIALS_FILE_PATH = "/src/common/credentials.json";
    /**
     * Global instance of the DataStoreFactory. The best practice is to make it a single
     * globally shared instance across your application.
     */
    private FileDataStoreFactory dataStoreFactory;

    /**
     * Global instance of the HTTP transport.
     */
    private HttpTransport httpTransport;


    /**
     * Global Drive API client.
     */
    private Drive drive;

    /**
     * Authorizes the installed application to access user's protected data.
     */
    private Credential authorize() throws Exception {
        // load client secrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(FileStorageHandler.class.getResourceAsStream(CREDENTIALS_FILE_PATH)));
        if (clientSecrets.getDetails().getClientId().startsWith("Enter") || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
            System.out.println("Is this an error?");
            System.exit(1);
        }
        // set up authorization code flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets,
                Collections.singleton(DriveScopes.DRIVE_FILE)).setDataStoreFactory(dataStoreFactory)
                .build();
        // authorize
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public File insertFile(String name, String description, java.io.File file) {
        File body = new File();
        body.setMimeType("application/pdf");
        body.setDescription(description);
        FileContent mediaContent = new FileContent("application/pdf", file);
        try {
            return drive.files().create(body, mediaContent).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
            return null;
        }
    }

    public FileStorageHandler() throws Exception {
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        // authorization
        Credential credential = authorize();
        // set up the global Drive instance
        drive = new Drive.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
    }
}
