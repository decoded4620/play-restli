package com.linkedin.restli.server.play;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.linkedin.parseq.Engine;
import com.linkedin.r2.filter.FilterChain;
import com.linkedin.r2.filter.transport.FilterChainDispatcher;
import com.linkedin.r2.transport.http.server.HttpDispatcher;
import com.linkedin.restli.server.DelegatingTransportDispatcher;
import com.linkedin.restli.server.RestLiConfig;
import com.linkedin.restli.server.RestLiServer;
import com.linkedin.restli.server.resources.ResourceFactory;


@Singleton
class HttpDispatcherProvider implements Provider<HttpDispatcher> {
  private HttpDispatcher httpDispatcher;

  @Inject
  HttpDispatcherProvider(
      RestLiConfig restLiConfig,
      ResourceFactory resourceFactory,
      Engine engine,
      FilterChain filterChain
  ) {
    RestLiServer restLiServer = new RestLiServer(restLiConfig, resourceFactory, engine);
    httpDispatcher = new HttpDispatcher(
        new FilterChainDispatcher(new DelegatingTransportDispatcher(restLiServer, restLiServer), filterChain));
  }

  @Override
  public HttpDispatcher get() {
    return httpDispatcher;
  }
}