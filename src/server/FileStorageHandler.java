package server;

import com.microsoft.azure.storage.blob.*;
import com.microsoft.rest.v2.http.HttpPipeline;
import io.reactivex.Flowable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.text.Normalizer;
import java.util.Locale;

/**
 * This class gets and retrieves files from an external service (Azure Cloud in this case).
 *
 * @author Yvan SANSON
 */
public class FileStorageHandler {
    private String accountName ="learntogetherfiles";
    private String accountKey="BI72gZagRw6dFddKVYKfb2raRNVWT3u20v2fss+0A/q6J0//cKhGnupI1pq2ycNVcYVa20Sa57XdsyprVTBagQ==";
    private URL u;
    private ServiceURL serviceURL;

    private SharedKeyCredentials sharedKeyCredentials;
    private HttpPipeline httpPipeline;
    private ContainerURL containerURL;

    /**
     * This constructor tries to connect to Azure Storage to store files.
     * @throws InvalidKeyException
     * @throws MalformedURLException
     */
    public FileStorageHandler() throws InvalidKeyException, MalformedURLException {
        sharedKeyCredentials = new SharedKeyCredentials(accountName, accountKey);
        httpPipeline = StorageURL.createPipeline(sharedKeyCredentials, new PipelineOptions());
        u = new URL(String.format(Locale.ROOT, "https://%s.blob.core.windows.net", accountName));
        serviceURL = new ServiceURL(u, httpPipeline);
        containerURL = serviceURL.createContainerURL("testcontainer");
    }

    /**
     * This method tries to store a file (known as a byte array) into the storage.
     * It creates a container per file, to avoid name conflicts.
     * @param fileName the name of the file to add to the storage.
     * @param file the actual file.
     */
    public void insertFile(String fileName, byte[] file){
        fileName = fileName.replace(' ', '-');
        fileName = Normalizer.normalize(fileName, Normalizer.Form.NFD);
        fileName = fileName.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        BlockBlobURL blobURL = containerURL.createBlockBlobURL(fileName);

        containerURL.create().flatMap(containerCreateResponse ->blobURL.upload(Flowable.just(ByteBuffer.wrap(file)), file.length)).blockingGet();
    }



}
