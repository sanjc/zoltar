/*-
 * -\-\-
 * apollo-service-example
 * --
 * Copyright (C) 2016 - 2018 Spotify AB
 * --
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * -/-/-
 */

package com.spotify.zoltar.examples.apollo;

import static org.hamcrest.core.Is.is;

import com.spotify.apollo.Response;
import com.spotify.apollo.request.RequestContexts;
import com.spotify.apollo.test.ServiceHelper;
import com.spotify.apollo.test.StubClient;
import com.spotify.metrics.core.MetricId;
import com.spotify.metrics.core.SemanticMetricRegistry;
import com.spotify.zoltar.IrisFeaturesSpec.Iris;
import com.spotify.zoltar.Predictor;
import com.spotify.zoltar.metrics.semantic.SemanticPredictorMetrics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletionStage;
import okio.ByteString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class IrisPredictionHandlerTest {

  @Rule
  public ServiceHelper serviceHelper = ServiceHelper.create(App::configure, "zoltar-example");

  @Test
  public void shouldPredict() throws Exception {
    final Response<ByteString> replyFuture = serviceHelper
        .request("GET", "/v1/predict/5.3-2.7-2.0-1.9")
        .toCompletableFuture()
        .get();

    final String reply = replyFuture.payload().get().utf8();

    Assert.assertThat(reply, is("Iris-versicolor"));
  }

}
