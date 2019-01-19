package server;

import com.microsoft.azure.storage.blob.*;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.util.FlowableUtil;
import io.reactivex.Flowable;
import io.reactivex.Single;

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

    }

    /**
     * This method refactors the file name in order to store it in the storage system.
     * @param fileName the original file name.
     * @return the refactored file name, without spaces, accents and special characters.
     */
    private String refactorFileName(String fileName){
        fileName = fileName.replace(' ', '-');
        fileName = Normalizer.normalize(fileName, Normalizer.Form.NFD);
        return fileName.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    /**
     * This method refactors the container name in order to be accepted by the storage system.
     * @param containerName the original container name.
     * @return the refactored container name, without spaces, upper cases and special characters.
     */
    private String refactorContainerName(String containerName){
        containerName = containerName.substring(0, containerName.indexOf('.'));
        containerName = containerName.replaceAll("[^a-zA-Z0-9]", "");
        return containerName.toLowerCase();
    }

    /**
     * This method tries to store a file (known as a byte array) into the storage.
     * It creates a container per file, to avoid name conflicts.
     * @param fileName the name of the file to add to the storage.
     * @param file the actual file.
     */
    public void insertFile(String fileName, byte[] file){

        String name = refactorFileName(fileName);
        String containerName = refactorContainerName(fileName);
        containerURL = serviceURL.createContainerURL(containerName);
        BlockBlobURL blobURL = containerURL.createBlockBlobURL(name);
        containerURL.create().flatMap(containerCreateResponse ->blobURL.upload(Flowable.just(ByteBuffer.wrap(file)), file.length)).blockingGet();
    }

    /**
     * This method tries to retrieve a file stored in the storage service.
     * @param fileName the file name.
     * @return a byte array representing the file.
     */
    public byte[] downloadFile(String fileName){
        String name = refactorFileName(fileName);
        String containerName = refactorContainerName(fileName);
        containerURL = serviceURL.createContainerURL(containerName);
        BlockBlobURL blobURL = containerURL.createBlockBlobURL(name);
        DownloadResponse downloadResponse = containerURL.create().flatMap(containerCreateResponse -> blobURL.download()).blockingGet();
        return FlowableUtil.collectBytesInArray(downloadResponse.body(null)).blockingGet();
    }


}
