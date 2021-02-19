/*
 *
 *  * Licensed to the Apache Software Foundation (ASF) under one or more
 *  * contributor license agreements.  See the NOTICE file distributed with
 *  * this work for additional information regarding copyright ownership.
 *  * The ASF licenses this file to You under the Apache License, Version 2.0
 *  * (the "License"); you may not use this file except in compliance with
 *  * the License.  You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 */

import sbt.{ExclusionRule, _}

object Dependencies {

  def scalaReflection(scalaVersion: String) =
    Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion
    )

  val jacksonExclusions = Vector[ExclusionRule](
    /* the purpose of these exclusions is to ensure we use the Jackson Core _provided-by_ Spark itself */
    ExclusionRule(organization = "com.fasterxml.jackson"),
    ExclusionRule(organization = "com.fasterxml.jackson.core"),
    ExclusionRule(organization = "com.fasterxml.jackson.databind"),
    ExclusionRule(organization = "com.fasterxml.jackson.jaxrs"),
    ExclusionRule(organization = "com.fasterxml.jackson.module"),
    ExclusionRule(organization = "com.fasterxml.jackson.jaxrs", name = "jackson-jaxrs-base"),
    ExclusionRule(organization = "com.fasterxml.jackson.jaxrs", name = "jackson-jaxrs-json-provider"),
    ExclusionRule(organization = "com.fasterxml.jackson.module", name = "jackson-module-jaxb-annotations"),
    ExclusionRule(organization = "com.fasterxml.jackson.core", name = "jackson-annotations"),
    ExclusionRule(organization = "com.fasterxml.jackson.core", name = "jackson-core"),
    ExclusionRule(organization = "com.fasterxml.jackson.core", name = "jackson-databind"),
  )

  val exclusionsForTestContainers = Vector[ExclusionRule](
    ExclusionRule(organization = "com.github.docker-java", name = "docker-java-api")
  ) ++ jacksonExclusions


  val scalaTest = Seq(
//    "org.scalatest" %% "scalatest" % Versions.scalatest,
    "org.scalatest" %% "scalatest" % Versions.scalatest % Test
  )

  val betterfiles = Seq("com.github.pathikrit" %% "better-files" % Versions.betterFiles)

  val logging = Seq(
    "com.typesafe" % "config" % Versions.typesafeConfig,
    "com.typesafe.scala-logging" %% "scala-logging" % Versions.scalaLogging
  )

  val typedConfigs = Seq("com.github.kxbmap" %% "configs" % Versions.configs)

  val jackson211 = Seq(
    "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % Versions.jackson211
  ).map(_.withExclusions(jacksonExclusions))

  val jackson212 = Seq(
    "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % Versions.jackson212
  ).map(_.withExclusions(jacksonExclusions))

  val jackson312 = Seq(
    "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % Versions.jackson312
  ).map(_.withExclusions(jacksonExclusions))

  val spark_2d4_forScala_2d11 = Seq(
    "org.apache.spark" %% "spark-core" % Versions.spark2d4 % "provided" exclude ("com.google.guava", "guava"),
    "org.apache.spark" %% "spark-sql" % Versions.spark2d4 % "provided" exclude ("com.google.guava", "guava"),
    "org.apache.spark" %% "spark-hive" % Versions.spark2d4 % "provided" exclude ("com.google.guava", "guava"),
    "org.apache.spark" %% "spark-mllib" % Versions.spark2d4 % "provided" exclude ("com.google.guava", "guava"),
    "com.databricks" %% "spark-xml" % Versions.sparXML211,
    "org.apache.spark" %% "spark-sql-kafka-0-10" % Versions.spark2d4
  )

  val spark_2d4_forScala_2d12 = Seq(
    "org.apache.spark" %% "spark-core" % Versions.spark2d4 % "provided" exclude ("com.google.guava", "guava") excludeAll (jacksonExclusions: _*),
    "org.apache.spark" %% "spark-sql" % Versions.spark2d4 % "provided" exclude ("com.google.guava", "guava") excludeAll (jacksonExclusions: _*),
    "org.apache.spark" %% "spark-hive" % Versions.spark2d4 % "provided" exclude ("com.google.guava", "guava") excludeAll (jacksonExclusions: _*),
    "org.apache.spark" %% "spark-mllib" % Versions.spark2d4 % "provided" exclude ("com.google.guava", "guava") excludeAll (jacksonExclusions: _*),
    "com.databricks" %% "spark-xml" % Versions.sparXML212,
    "org.apache.spark" %% "spark-sql-kafka-0-10" % Versions.spark2d4
  )

  val spark_3d0_forScala_2d12 = Seq(
    "org.apache.spark" %% "spark-core" % Versions.spark3d0 % "provided" exclude ("com.google.guava", "guava") excludeAll (jacksonExclusions: _*),
    "org.apache.spark" %% "spark-sql" % Versions.spark3d0 % "provided" exclude ("com.google.guava", "guava") excludeAll (jacksonExclusions: _*),
    "org.apache.spark" %% "spark-hive" % Versions.spark3d0 % "provided" exclude ("com.google.guava", "guava") excludeAll (jacksonExclusions: _*),
    "org.apache.spark" %% "spark-mllib" % Versions.spark3d0 % "provided" exclude ("com.google.guava", "guava") excludeAll (jacksonExclusions: _*),
    "com.databricks" %% "spark-xml" % Versions.sparXML212,
    "org.apache.spark" %% "spark-sql-kafka-0-10" % Versions.spark3d0
  )

  val hadoop_2d7_for_spark_2d4 = Seq(
    "org.apache.hadoop" % "hadoop-client" % Versions.hadoopForSpark2d4 % Test exclude ("com.google.guava", "guava") excludeAll (jacksonExclusions: _*),
  )
  val hadoop_3d2_for_spark_3d0 = Seq(
    "org.apache.hadoop" % "hadoop-client" % Versions.hadoopForSpark3d0 % Test exclude ("com.google.guava", "guava") excludeAll (jacksonExclusions: _*),
  )

  private def gcp(gcs: String, hadoopbq: String) = {
    {
      val gcsConnectorShadedJar =
        s"${Resolvers.googleCloudBigDataMavenRepo}/gcs-connector/${gcs}/gcs-connector-${gcs}-shaded.jar"

      val gcpBigQueryConnectorShadedJar =
        s"${Resolvers.googleCloudBigDataMavenRepo}/bigquery-connector/${hadoopbq}/bigquery-connector-${hadoopbq}-shaded.jar"

      Seq(
        /* NOTE: the "from url" syntax is ignored under sbt+coursier. So in effect, if the 'classifier "shaded"' clause
          fails to work, we're not using the shaded jars, which means we're exposing ourselves to the whole gcs
          dependency trees */

        "com.google.cloud.bigdataoss" % "gcs-connector" % gcs from gcsConnectorShadedJar exclude ("javax.jms", "jms") exclude ("com.sun.jdmk", "jmxtools") exclude ("com.sun.jmx", "jmxri") excludeAll (jacksonExclusions: _*) classifier "shaded",
        "com.google.cloud.bigdataoss" % "bigquery-connector" % hadoopbq from gcpBigQueryConnectorShadedJar exclude ("javax.jms", "jms") exclude ("com.sun.jdmk", "jmxtools") exclude ("com.sun.jmx", "jmxri") excludeAll (jacksonExclusions: _*) classifier "shaded",
        "com.google.cloud" % "google-cloud-bigquery" % Versions.bq exclude ("javax.jms", "jms") exclude ("com.sun.jdmk", "jmxtools") exclude ("com.sun.jmx", "jmxri") excludeAll (jacksonExclusions: _*),
        // see https://github.com/GoogleCloudDataproc/spark-bigquery-connector/issues/36
        // Add the jar file to spark dependencies
        "com.google.cloud.spark" %% "spark-bigquery-with-dependencies" % "0.18.1" % "provided" excludeAll (jacksonExclusions: _*)
      )
    }
  }

  val gcp_for_spark_2d4: Seq[ModuleID] = gcp(gcs = Versions.gcsForSpark2d4, hadoopbq = Versions.hadoopbqForSpark2d4)
  val gcp_for_spark_3d0: Seq[ModuleID] = gcp(gcs = Versions.gcsForSpark3d0, hadoopbq = Versions.hadoopbqForSpark3d0)

  val esHadoop = Seq(
    "org.elasticsearch" % "elasticsearch-hadoop" % Versions.esHadoop excludeAll(ExclusionRule("com.google.guava", "guava") +: jacksonExclusions: _*),
    "com.dimafeng" %% "testcontainers-scala-elasticsearch" % Versions.testContainers % Test excludeAll (exclusionsForTestContainers: _*)
  )

  val scopt = Seq(
    "com.github.scopt" %% "scopt" % Versions.scopt
  )

  val sttp = Seq(
    "com.softwaremill.sttp" %% "core" % Versions.sttp
  )

  // We need here to remove any reference to hadoop 3
  val atlas = Seq(
    //"org.apache.atlas" % "apache-atlas" % "2.0.0" pomOnly(),
    "org.apache.atlas" % "atlas-intg" % "2.0.0" excludeAll (jacksonExclusions: _*) exclude ("asm", "asm") exclude ("com.google.guava", "guava"),
    "org.apache.atlas" % "atlas-client-common" % "2.0.0" excludeAll (jacksonExclusions: _*) exclude ("asm", "asm") exclude ("com.google.guava", "guava"),
    //"org.apache.atlas" % "atlas-client" % "2.0.0" pomOnly(),
    "org.apache.atlas" % "atlas-common" % "2.0.0" excludeAll (jacksonExclusions: _*) exclude ("asm", "asm") exclude ("com.google.guava", "guava"),
    "org.apache.atlas" % "atlas-client-v2" % "2.0.0" excludeAll (jacksonExclusions: _*) exclude ("asm", "asm") exclude ("com.google.guava", "guava")
  )

  val azure = Seq(
    "org.apache.hadoop" % "hadoop-azure" % "3.3.0" % "provided" excludeAll (jacksonExclusions: _*) exclude ("com.google.guava", "guava"),
    "com.microsoft.azure" % "azure-storage" % "8.6.5" % "provided" excludeAll (jacksonExclusions: _*) exclude ("com.google.guava", "guava")
  )

  private def hadoop(hadoopVersion: String) = Seq(
    "org.apache.hadoop" % "hadoop-common" % hadoopVersion % "provided" excludeAll (jacksonExclusions: _*) exclude ("com.google.guava", "guava"),
    "org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion % "provided" excludeAll (jacksonExclusions: _*) exclude ("com.google.guava", "guava"),
    "org.apache.hadoop" % "hadoop-yarn-client" % hadoopVersion % "provided" excludeAll (jacksonExclusions: _*) exclude ("com.google.guava", "guava"),
    "org.apache.hadoop" % "hadoop-mapreduce-client-app" % hadoopVersion % "provided" excludeAll (jacksonExclusions: _*) exclude ("com.google.guava", "guava"),
    "org.apache.hadoop" % "hadoop-client" % hadoopVersion % "provided" excludeAll (jacksonExclusions: _*) exclude ("com.google.guava", "guava")
  )
  val hadoop_for_spark_2d4: Seq[ModuleID] = hadoop(Versions.hadoopForSpark2d4)
  val hadoop_for_spark_3d0: Seq[ModuleID] = hadoop(Versions.hadoopForSpark3d0)

  val excelClientApi = Seq(
    "org.apache.poi" % "poi-ooxml" % Versions.poi
      exclude("org.apache.xmlgraphics", "batik-all") // new dependency in POI 5.0.0, duplicates contents of inner Batik
  )

  val h2 = Seq(
    "com.h2database" % "h2" % Versions.h2 % Test
  )

  val scalate = Seq(
    "org.scalatra.scalate" %% "scalate-core" % Versions.scalate
  )

  val akkaHttp = Seq(
    "com.typesafe.akka" %% "akka-http" % Versions.akkaHttp
  )

  val akkaStream = Seq(
    "com.typesafe.akka" %% "akka-stream" % Versions.akkaStream
  )

  val kafkaClients = Seq(
    "org.apache.kafka" % "kafka-clients" % Versions.kafkaClients,
    "com.dimafeng" %% "testcontainers-scala-scalatest" % Versions.testContainers % Test excludeAll (exclusionsForTestContainers: _*) ,
    "com.dimafeng" %% "testcontainers-scala-kafka" % Versions.testContainers % Test excludeAll (exclusionsForTestContainers: _*)
  )

  val testContainersBase = Seq(
    "com.dimafeng" %% "testcontainers-scala-core" % Versions.testContainers % Test excludeAll (exclusionsForTestContainers: _*),
    "com.github.docker-java" % "docker-java-api" % Versions.dockerJavaApi % Test excludeAll (jacksonExclusions:_*)
  )

  val dependencies =
    scalate ++ logging ++ typedConfigs ++ betterfiles ++ scalaTest ++ scopt ++ esHadoop ++
    sttp ++ azure ++ h2 ++ excelClientApi ++ akkaHttp ++ akkaStream ++ kafkaClients ++ testContainersBase // ++ atlas
}
