package io.fabric8.kubernetes.examples;

import io.fabric8.kubernetes.DefaultKubernetesClient;
import io.fabric8.kubernetes.KubernetesClient;
import io.fabric8.kubernetes.KubernetesClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetExamples {

  private static final Logger logger = LoggerFactory.getLogger(GetExamples.class);

  public static void main(String[] args) {
    KubernetesClient client = null;

    String master = "https://localhost:8443";
    if (args.length == 1) {
      master = args[0];
    }

    try {
      client = new DefaultKubernetesClient.Builder().configFromSysPropsOrEnvVars().masterUrl(master).build();

      System.out.println(
        client.namespaces().withName("fabric8").get()
      );

      System.out.println(
        client.pods().inNamespace("default").withName("rabbitmq-pod").get()
      );
    } catch (KubernetesClientException e) {
      logger.error(e.getMessage(), e);
    } finally {
      if (client != null) {
        client.close();
      }
    }
  }

}
