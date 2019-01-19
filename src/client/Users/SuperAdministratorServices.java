package client.Users;

import client.CoreClient;

import java.util.*;

/**
 * This class handles everything related to the  super admin services.
 */
public class SuperAdministratorServices extends AdministratorServices {

    private CoreClient client;

    public SuperAdministratorServices(CoreClient client) {
        this.client = client;
    }

}