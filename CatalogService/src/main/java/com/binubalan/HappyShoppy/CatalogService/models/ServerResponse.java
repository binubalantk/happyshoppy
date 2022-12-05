package com.binubalan.HappyShoppy.CatalogService.models;

public class ServerResponse<Data> {
    public boolean status;
    public String message;
    public Data data;

    public ServerResponse() {
    }

    public ServerResponse(boolean status) {
        this.status = status;
    }

    public ServerResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ServerResponse(boolean status, Data data) {
        this.status = status;
        this.data = data;
    }

    public ServerResponse(boolean status, String message, Data data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
