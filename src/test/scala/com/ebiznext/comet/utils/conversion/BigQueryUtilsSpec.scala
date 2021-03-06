package com.ebiznext.comet.utils.conversion

import java.sql.{Date, Timestamp}

import com.ebiznext.comet.TestHelper
import com.ebiznext.comet.config.SparkEnv
import com.ebiznext.comet.utils.conversion.BigQueryUtils._
import com.ebiznext.comet.utils.conversion.syntax._
import org.apache.spark.sql.SparkSession
import com.google.cloud.bigquery.{Field, StandardSQLTypeName, Schema => BQSchema}

class BigQueryUtilsSpec extends TestHelper {
  new WithSettings() {
    val sparkEnv: SparkEnv = new SparkEnv("test")
    val session: SparkSession = sparkEnv.session
    import session.implicits._

    "Spark Types" should "be converted to corresponding BQ Types" in {
      val res: BQSchema = List(
        (
          1,
          true,
          2.5,
          "hello",
          'x'.asInstanceOf[Byte],
          new Date(System.currentTimeMillis()),
          new Timestamp(System.currentTimeMillis())
        )
      ).toDF().to[BQSchema]
      //Schema{fields=[Field{name=value, type=INTEGER, mode=NULLABLE, description=, policyTags=null}]}
      val fields =
        List(
          Field
            .newBuilder("_1", StandardSQLTypeName.INT64)
            .setDescription("")
            .setMode(Field.Mode.NULLABLE)
            .build(),
          Field
            .newBuilder("_2", StandardSQLTypeName.BOOL)
            .setDescription("")
            .setMode(Field.Mode.NULLABLE)
            .build(),
          Field
            .newBuilder("_3", StandardSQLTypeName.FLOAT64)
            .setDescription("")
            .setMode(Field.Mode.NULLABLE)
            .build(),
          Field
            .newBuilder("_4", StandardSQLTypeName.STRING)
            .setDescription("")
            .setMode(Field.Mode.NULLABLE)
            .build(),
          Field
            .newBuilder("_5", StandardSQLTypeName.INT64)
            .setDescription("")
            .setMode(Field.Mode.NULLABLE)
            .build(),
          Field
            .newBuilder("_6", StandardSQLTypeName.DATE)
            .setDescription("")
            .setMode(Field.Mode.NULLABLE)
            .build(),
          Field
            .newBuilder("_7", StandardSQLTypeName.TIMESTAMP)
            .setDescription("")
            .setMode(Field.Mode.NULLABLE)
            .build()
        )
      res.getFields should contain theSameElementsInOrderAs fields
    }
  }
}
