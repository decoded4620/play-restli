package com.linkedin.playrestli;

import com.linkedin.restli.server.RestLiConfig;
import com.typesafe.config.Config;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class DefaultRestliConfigProvider implements RestliConfigProvider {
  private RestLiConfig restliConfig;

  @Inject
  public DefaultRestliConfigProvider(Config playConfig) {
    restliConfig = new RestLiConfig();
    // TODO: support more configs
    List<String> resourcePackages = playConfig.getStringList("restli.resourcePackages");
    restliConfig.addResourcePackageNames(resourcePackages);
  }

  @Override
  public RestLiConfig get() {
    return restliConfig;
  }
}
