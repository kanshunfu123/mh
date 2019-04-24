package com.xiaowei.mh.common.util;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/*******************************************************************
 * jumeiti-app-server - com.jumeiti.web.utils.https
 *
 * 类描述：  
 *
 *
 *     ___       ___       ___       ___       ___       ___
 *    /\  \     /\  \     /\__\     /\__\     /\  \     /\__\
 *    \:\  \   /::\  \   /:| _|_   |::L__L   _\:\  \   /:| _|_
 *    /::\__\ /::\:\__\ /::|/\__\ /::::\__\ /\/::\__\ /::|/\__\
 *   /:/\/__/ \/\::/  / \/|::/  / \;::;/__/ \::/\/__/ \/|::/  /
 *   \/__/      /:/  /    |:/  /   |::|__|   \:\__\     |:/  /
 *             \/__/     \/__/     \/__/     \/__/     \/__/
 *
 *
 *
 *                _ _             
 *               (_|_)            
 *  __      _____ _ _ _   _ _ __  
 *  \ \ /\ / / _ \ | | | | | '_ \ 
 *   \ V  V /  __/ | | |_| | | | |
 *    \_/\_/ \___|_| |\__,_|_| |_|
 *                _/ |            
 *               |__/   
 *
 *
 * @version ${VERSION}
 *
 *
 * Version    Date       ModifiedBy                 Content  
 * -------- ---------    ----------         ------------------------  
 * ${VERSION}      2018/7/1     weijun
 *
 *******************************************************************
 */
public class SSLClient extends DefaultHttpClient {
    public SSLClient() throws Exception {
        super();
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[] { tm }, null);
        SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 443, ssf));
    }
}

