package org.acme.getting.started.config;

import com.couchbase.client.core.env.TimeoutConfig;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.codec.JacksonJsonSerializer;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.time.Duration;

public class CouchbaseClusterHelper {

    private static final Integer CONNECT_TIMEOUT_IN_MS = 20 * 1000;
    private static final Integer KV_TIMEOUT_IN_MS = 1000;
    private final CouchbaseConfiguration couchbaseConfiguration;
    private final Cluster cluster;

    public CouchbaseClusterHelper(CouchbaseConfiguration couchbaseConfig, ObjectMapper objectMapper) {
        this.couchbaseConfiguration = couchbaseConfig;

        final ClusterEnvironment couchbaseEnvironment = ClusterEnvironment.builder()
                .jsonSerializer(JacksonJsonSerializer.create(objectMapper))
                .timeoutConfig(TimeoutConfig.kvTimeout(Duration.ofMillis(KV_TIMEOUT_IN_MS))
                        .connectTimeout(Duration.ofMillis(CONNECT_TIMEOUT_IN_MS)))
                .build();

        cluster = Cluster.connect(couchbaseConfig.getUrls(),
                ClusterOptions.clusterOptions(couchbaseConfig.getUsername(), couchbaseConfig.getPassword())
                        .environment(couchbaseEnvironment));
    }

    private Collection openCollection(String bucketName) {
        return cluster.bucket(bucketName).defaultCollection();
    }


    @Singleton
    @Produces
    public Collection collection() {
        return openCollection(couchbaseConfiguration.getBucket());
    }


}
