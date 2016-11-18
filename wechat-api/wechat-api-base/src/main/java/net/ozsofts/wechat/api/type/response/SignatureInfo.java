package net.ozsofts.wechat.api.type.response;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SignatureInfo implements Serializable {

    private String noncestr;
    private long   timestamp;
    private String url;
    private String signature;

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
